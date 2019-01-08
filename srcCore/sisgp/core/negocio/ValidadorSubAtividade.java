package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.Atividade;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.SubAtividade;

public class ValidadorSubAtividade implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof SubAtividade) {
			SubAtividade sat = (SubAtividade)entidade;
			
			if(sat.getCodigo() == null ||
				sat.getDescricao() == null ||
				sat.getNome() == null ||
				sat.getDtInicio() == null ||
				sat.getDtFim() == null ||
				sat.getNrHorasPrevisto() == null ||
				sat.getAtividade() == null ||
				sat.getAtividade().getId() == null ||
				sat.getRecursoHumano() == null ||
				sat.getRecursoHumano().getId() == null ||
				sat.getStatusAtividade() == null ||
				sat.getStatusAtividade().getId() == null) {
				return "C�digo, Nome, Descri��o, Data In�cio, Data Fim, Atividade, Recurso Humano e Status s�o de preenchimento obrigat�rio!";
			}
			
			if(sat.getCodigo().trim().equals("") ||
				sat.getDescricao().trim().equals("") ||
				sat.getNome().trim().equals("") ||
				sat.getNrHorasPrevisto() == 0 ||
				sat.getAtividade() == null ||
				sat.getAtividade().getId() == 0 ||
				sat.getRecursoHumano() == null ||
				sat.getRecursoHumano().getId() == 0 ||
				sat.getStatusAtividade() == null ||
				sat.getStatusAtividade().getId() == 0) {
				return "C�digo, Nome, Descri��o, Data In�cio, Data Fim, Atividade, Recurso Humano e Status s�o de preenchimento obrigat�rio!";
			}
		}else {
			return "Deve ser cadastrado um Subatividade!";
		}
		return null;
	}

}
