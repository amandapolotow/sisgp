package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GraficoContasReceber;
import sisgp.dominio.InterfaceEntidade;

public class GraficoContasReceberDao extends AbstractDao{

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
		List<EntidadeDominio> listaGrafico = new ArrayList<EntidadeDominio>();
		
		GraficoContasReceber grConsulta = (GraficoContasReceber)entidade;
		//Projeto proj = grConsulta.getProjeto();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(contas_receber.valor) as vl_total,");
		sql.append(" CASE month(contas_receber.dt_vencimento) ");
		sql.append(" when 1 then 'JAN' ");
		sql.append(" when 2 then 'FEV' ");
		sql.append(" when 3 then 'MAR' ");
		sql.append(" when 4 then 'ABR' ");
		sql.append(" when 5 then 'MAI' ");
		sql.append(" when 6 then 'JUN' ");
		sql.append(" when 7 then 'JUL' ");
		sql.append(" when 8 then 'AGO' ");
		sql.append(" when 9 then 'SET' ");
		sql.append(" when 10 then 'OUT' ");
		sql.append(" when 11 then 'NOV' ");
		sql.append(" when 12 then 'DEZ' ");
		sql.append(" END as mes ");
		sql.append(" from contas_receber ");
		sql.append(" where contas_receber.st_ativo = 1  ");
		
		if(grConsulta.getDtDe() != null && grConsulta.getDtAte() != null) {
			sql.append(" and contas_receber.dt_vencimento between str_to_date('");
			sql.append(grConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(grConsulta.getDtAte());
			sql.append("','%Y-%m-%d') ");
		}
		
		if(grConsulta.getProjeto() != null && grConsulta.getProjeto().getId() != null && grConsulta.getProjeto().getId() != 0) {
			sql.append(" and contas_receber.id_projeto = ");
			sql.append(grConsulta.getProjeto().getId());
		}
		
		
		//sql.append(" and year(apontamento_horas.data) = ? ");
		sql.append(" group by month(contas_receber.dt_vencimento) ");
		
		
		abrirConexao();
		
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			//pst.setString(1, grConsulta.getAno());
			
			//System.out.println("SQL Grafico: " + pst.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				GraficoContasReceber gcr = new GraficoContasReceber();
				gcr.setVlTotal(rs.getDouble("vl_total"));
				gcr.setMes(rs.getString("mes"));
				
				listaGrafico.add(gcr);
			}
			
			return listaGrafico;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
