package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.ControleFinanceiroProjeto;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public class ControleFinanceiroProjetoDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
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
		List<EntidadeDominio> listaControle = new ArrayList<EntidadeDominio>();
		
		ControleFinanceiroProjeto ctrConsulta = (ControleFinanceiroProjeto)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(apontamento_horas.nr_horas * recursos_humanos.vl_hora) as vl_total,");
		sql.append(" CASE month(apontamento_horas.data) ");
		
		
		
		return null;
	}

}
