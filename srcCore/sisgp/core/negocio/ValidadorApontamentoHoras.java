package sisgp.core.negocio;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import sisgp.core.dao.ApontamentoHorasDao;
import sisgp.core.dao.AtividadeDao;
import sisgp.core.dao.SubAtividadeDao;
import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.core.util.Datas;
import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.Atividade;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.SubAtividade;

//referência: https://www.baeldung.com/java-date-difference

public class ValidadorApontamentoHoras implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof ApontamentoHoras) {
			
			//verificando dados obrigatórios
			ApontamentoHoras ah = (ApontamentoHoras)entidade;
			
			if(ah.getData() == null || ah.getSubAtividade().getId() == null) {
				return "Data e Id de SubAtividade são obrigatórios!";
			}
			
			if(ah.getSubAtividade().getId() == 0) {
				return "Data e Id de SubAtividade são obrigatórios!";
			}
			
			if(ah.getNrHoras() == null && ah.getNrHorasExtras() == null) {
				return "Número de Horas ou Número de Horas Extras deve ser preenchido!";
			}
			
			if(ah.getNrHoras() == 0 && ah.getNrHorasExtras() == 0) {
				return "Número de Horas ou Número de Horas Extras deve ser preenchido!";
			}
			
			//CALCULO DE STATUS DE ATIVIDADES SEGUNDO OS APONTAMENTOS
			
			//pegar dados da Sub atividade
			
			SubAtividadeDao sad = new SubAtividadeDao();
			SubAtividade saConsulta = new SubAtividade(ah.getSubAtividade().getId());
			
			List<EntidadeDominio> listaSubAtividade = sad.consultar(saConsulta);
			SubAtividade subAtividade = (SubAtividade)listaSubAtividade.get(0);
			
			
			
			/*
			 * Status das atividades (selecionados pelo gestor)
			 * 1 - Não iniciada
			 * 2 - Em andamento
			 * 3 - Finalizada
			 * 4 - Cancelada
			 * 
			 */
			
			//verificando se sub-atividade está em aberto (se não, não pode mais cadastrar apontamentos)
			
			if(subAtividade.getStatusAtividade().getId() == 3 || subAtividade.getStatusAtividade().getId() == 4) {
				return "Esta sub-atividade foi finalizada ou cancelada e não está mais aceitando apontamentos!";
			}else if(subAtividade.getStatusAtividade().getId() == 1) {
				return "Esta sub-atividade ainda não foi iniciada!";
			}
				
			//pegar dados de apontamentos
			
			ApontamentoHorasDao ahd = new ApontamentoHorasDao();
			ApontamentoHoras ahConsulta = new ApontamentoHoras();
			ahConsulta.setSubAtividade(new SubAtividade(ah.getSubAtividade().getId()));
			
			List<EntidadeDominio> listaApontamentos = ahd.consultar(ahConsulta);
									
			//Calculando as medias previstas e efetivas de horas daquela atividade
			
			//Dados necessarios
			Double totalHorasPrevisto = 0.0;
			Double totalHorasEfetivo = 0.0;
						
			Double totalDiasPrevisto = 0.0;
			Double totalDiasEfetivo = 0.0;
			
			Double mediaPrevistaDiaria = 0.0;
			Double mediaEfetivaDiaria = 0.0;
			
			Boolean prazoUltrapassado = false; //caso quaqlquer data de apontamento ultrapasse o prazo da atividade
			
			
			//Calculo Efetivo
			for(int i = 0; i < listaApontamentos.size(); i++ ) {
				totalHorasEfetivo += ((ApontamentoHoras)listaApontamentos.get(i)).getNrHoras();
				totalHorasEfetivo += ((ApontamentoHoras)listaApontamentos.get(i)).getNrHorasExtras();
				totalDiasEfetivo++;
				if(((ApontamentoHoras)listaApontamentos.get(i)).getData().after(subAtividade.getDtFim())){
					prazoUltrapassado = true;
					System.out.println("Ultrapassou 1!");
				}
			}
			if(ah.getData().after(subAtividade.getDtFim())){
				prazoUltrapassado = true;
				System.out.println("Ultrapassou 2!");
			}
			if(ah.getNrHoras() != null && ah.getNrHoras() != 0) {
				totalHorasEfetivo += ah.getNrHoras();
				totalDiasEfetivo++;				
			}
			if(ah.getNrHorasExtras() != null && ah.getNrHorasExtras() != 0) {
				totalHorasEfetivo += ah.getNrHorasExtras();
				totalDiasEfetivo++;
			}
			
						
			//Calculo previsto
			totalHorasPrevisto = (double) subAtividade.getNrHorasPrevisto();
			
			totalDiasPrevisto = Datas.getDifferenceInDays(subAtividade.getDtInicio(), subAtividade.getDtFim());
			
			
			//calculo das medias previsto e efetivo
			
			mediaPrevistaDiaria = (double) (totalHorasPrevisto / totalDiasPrevisto);
			mediaEfetivaDiaria = (double) (totalHorasEfetivo / totalDiasEfetivo);
			
			//mediaPrevistaDiaria = Math.ceil(mediaPrevistaDiaria);
			//mediaEfetivaDiaria = Math.ceil(mediaEfetivaDiaria);
			
			System.out.println("Media Prevista:" + mediaPrevistaDiaria); //alterar para double
			System.out.println("Media Efetiva:" + mediaEfetivaDiaria); //alterar para double
			
			
			/*
			 * Status do Sistema:
			 * 1 - Dentro do Previsto
			 * 2 - Em risco
			 * 3 - Em atraso
			 * 
			 */
			
			
			//Definindo o status da atividade segundo as medias
			
			if(prazoUltrapassado == true) {
				subAtividade.getStSistAtividade().setId(3);
			}else if(mediaPrevistaDiaria <= mediaEfetivaDiaria) {
				subAtividade.getStSistAtividade().setId(1);
			}else {
				subAtividade.getStSistAtividade().setId(2);
			}
			
			
			
			sad.editar(subAtividade);//atualiza o status da atividade
			
			
			//pegar dados da Atividade
			
			AtividadeDao ad = new AtividadeDao();
			Atividade aConsulta = new Atividade(subAtividade.getAtividade().getId());
			
			List<EntidadeDominio> listaAtividade = ad.consultar(aConsulta);
			Atividade atividade = (Atividade)listaAtividade.get(0);
			
			List<SubAtividade> listaSubAt = atividade.getListaSubAtividade();
			
			int contStatus2 = 0;
			int contStatus3 = 0;
			
			//verifica os status de todas as subatividades dessa atividade
			
			for(int i = 0; i < listaSubAt.size(); i++) {
				if(listaSubAt.get(i).getStSistAtividade().getId() == 2) {
					contStatus2++;
				}else if(listaSubAt.get(i).getStSistAtividade().getId() == 3) {
					contStatus3++;
				}
			}
			
			if(contStatus3 > 0 && contStatus2 == 0) {//encontrou uma sub-atividade status 3
				atividade.getStSistAtividade().setId(3);//atividade recebe status 3
			}else if(contStatus3 == 0 && contStatus2 > 0) {//encontrou uma sub-atividade status 2
				atividade.getStSistAtividade().setId(2);//atividade recebe status 2
			}else if(contStatus3 == 0 && contStatus2 == 0) {//encontrou uma sub-atividade status 1
				atividade.getStSistAtividade().setId(1);//atividade recebe status 1
			}
			ad.editar(atividade);//atualiza status da atividade
	
			
		}else {
			return "Deve ser cadastrado um Apontamento de Horas!";
		}
		return null;
	}

}
