package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GraficoCategoriaContasReceber;
import sisgp.dominio.InterfaceEntidade;

public class GraficoCategoriaContasReceberDao extends AbstractDao{

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
		
		GraficoCategoriaContasReceber grConsulta = (GraficoCategoriaContasReceber)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(contas_receber.valor) as vl_total,");
		sql.append(" categoria_contas_receber.categoria ");
		sql.append(" from contas_receber ");
		sql.append(" inner join categoria_contas_receber ");
		sql.append(" on contas_receber.id_cat_contas_receber = categoria_contas_receber.id ");
		sql.append(" where contas_receber.st_ativo = 1 and categoria_contas_receber.id ");		
		if(grConsulta.getDtDe() != null && grConsulta.getDtAte() != null) {
			sql.append(" and contas_receber.dt_vencimento between str_to_date('");
			sql.append(grConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(grConsulta.getDtAte());
			sql.append("','%Y-%m-%d')");
		}				
		sql.append(" group by categoria_contas_receber.categoria ");
		
		System.out.println(sql.toString());
		
		abrirConexao();
		
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				GraficoCategoriaContasReceber g = new GraficoCategoriaContasReceber();
				g.setVlTotal(rs.getDouble("vl_total"));
				g.setCategoria(rs.getString("categoria"));
				
				listaGrafico.add(g);
			}
			
			return listaGrafico;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
