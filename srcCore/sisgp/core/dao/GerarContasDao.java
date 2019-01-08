package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GerarContas;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.SubAtividade;

public class GerarContasDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		
		GerarContas gc = (GerarContas)entidade;
		ContasPagar cp = gc.getContasPagar();
		ContasPagarDao cpd = new ContasPagarDao();
		
		cpd.adicionar(cp);//adiciona a conta gerada apos os calculos
		
		RecursoHumano rh = gc.getRecursoHumano();
		List<ApontamentoHoras> listaAh = rh.getListaApontamentoHoras();
		ApontamentoHorasDao ahd = new ApontamentoHorasDao();
		
		
		for(int i = 0; i < listaAh.size(); i++ ) {
			listaAh.get(i).setContasPagar(new ContasPagar(cp.getId()));
			ahd.editar(listaAh.get(i));//edita os apontamentos com o id da conta
		}
		
		
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		
		PreparedStatement pst = null;
		
		GerarContas gc = (GerarContas)entidade;
		//RecursoHumano rh = gc.getRecursoHumano();
		//Projeto proj = gc.getProjeto();
		
		List<EntidadeDominio> listaGc = new ArrayList<EntidadeDominio>();
		
		List<ApontamentoHoras> listaApontamentos = new ArrayList<ApontamentoHoras>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select apontamento_horas.* from apontamento_horas ");
		sql.append("inner join sub_atividades ");
		sql.append("on apontamento_horas.id_sub_atividade = sub_atividades.id ");
		sql.append("inner join atividades ");
		sql.append("on sub_atividades.id_atividade = atividades.id ");
		sql.append(" where apontamento_horas.st_ativo = 1 ");
		sql.append(" and apontamento_horas.id_conta_pagar is null ");
		
		if(gc.getRecursoHumano() != null) {
			sql.append(" and sub_atividades.id_recurso_humano =  ");			
			sql.append(gc.getRecursoHumano().getId());
		}
		
		if(gc.getProjeto() != null) {
			sql.append(" and atividades.id_projeto =  ");
			sql.append(gc.getProjeto().getId());
		}
		
		if(gc.getDtDe() != null && gc.getDtAte() != null) {
			sql.append(" and apontamento_horas.data between str_to_date('");
			sql.append(gc.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(gc.getDtAte());
			sql.append("','%Y-%m-%d')");
		}
		
		System.out.println("Query gerar contas: " + sql.toString());
		
		abrirConexao();
		try {		
			pst = conexao.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ApontamentoHoras ah = new ApontamentoHoras();
				
				ah.setId(rs.getInt("id"));
				
				Integer idSA = rs.getInt("id_sub_atividade");
				SubAtividade sa = new SubAtividade(idSA);
				ah.setSubAtividade(sa);
				
				Integer idCP = rs.getInt("id_conta_pagar");
				ContasPagar cp = new ContasPagar(idCP);
				ah.setContasPagar(cp);
				
				ah.setNrHoras(rs.getInt("nr_horas"));
				ah.setNrHorasExtras(rs.getInt("nr_horas_extras"));
				ah.setData(rs.getDate("data"));
				ah.setStAtivo(rs.getBoolean("st_pago"));
				ah.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaApontamentos.add(ah);
				
			}
			
			//rh.setListaApontamentoHoras(listaApontamentos);
			//gc.setRecursoHumano(rh);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		RecursoHumanoDao rhd = new RecursoHumanoDao();
		
		List<EntidadeDominio> listRh = rhd.consultar(gc.getRecursoHumano());
		
		gc.setRecursoHumano((RecursoHumano) listRh.get(0));
		
		gc.getRecursoHumano().setListaApontamentoHoras(listaApontamentos);
		
		
		
		listaGc.add(gc);
		
		return listaGc;
	}

}
