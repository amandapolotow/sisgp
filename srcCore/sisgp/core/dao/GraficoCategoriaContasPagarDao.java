package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GraficoCategoriaContasPagar;
import sisgp.dominio.InterfaceEntidade;

public class GraficoCategoriaContasPagarDao extends AbstractDao{

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
		
		GraficoCategoriaContasPagar grConsulta = (GraficoCategoriaContasPagar)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(contas_pagar.valor) as vl_total,");
		sql.append(" categoria_contas_pagar.categoria ");
		sql.append(" from contas_pagar ");
		sql.append(" inner join categoria_contas_pagar ");
		sql.append(" on contas_pagar.id_cat_contas_pagar = categoria_contas_pagar.id ");
		sql.append(" where contas_pagar.st_ativo = 1 and categoria_contas_pagar.id != 1 ");		
		if(grConsulta.getDtDe() != null && grConsulta.getDtAte() != null) {
			sql.append(" and contas_pagar.dt_vencimento between str_to_date('");
			sql.append(grConsulta.getDtDe());
			sql.append("','%Y-%m-%d') and str_to_date('");
			sql.append(grConsulta.getDtAte());
			sql.append("','%Y-%m-%d')");
		}				
		sql.append(" group by categoria_contas_pagar.categoria ");
		
		
		abrirConexao();
		
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				GraficoCategoriaContasPagar g = new GraficoCategoriaContasPagar();
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
