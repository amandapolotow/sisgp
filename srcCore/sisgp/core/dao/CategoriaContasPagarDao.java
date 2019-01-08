package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public class CategoriaContasPagarDao extends AbstractDao {

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		CategoriaContasPagar ccp = (CategoriaContasPagar) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into categoria_contas_pagar(categoria,");
		sql.append("st_ativo) values (?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ccp.setStAtivo(true);
			
			pst.setString(1, ccp.getCategoria());
			pst.setBoolean(2, ccp.isStAtivo());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);				
			}
			ccp.setId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editar(EntidadeDominio entidade) {
PreparedStatement pst = null;
		
CategoriaContasPagar ccp = (CategoriaContasPagar) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update categoria_contas_pagar set categoria = ?,");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ccp.setStAtivo(true);
			
			pst.setString(1, ccp.getCategoria());
			pst.setBoolean(2, ccp.isStAtivo());
			
			pst.setInt(3, ccp.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		CategoriaContasPagar ccp = (CategoriaContasPagar) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update categoria_contas_pagar set ");
		sql.append("st_ativo = ? where id = ?  and id != 1 ");
		
		abrirConexao();
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ccp.setStAtivo(false);
			
			pst.setBoolean(1, ccp.isStAtivo());			
			pst.setInt(2, ccp.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaCatContasPagar = new ArrayList<EntidadeDominio>();
		CategoriaContasPagar ccpConsulta = (CategoriaContasPagar) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from categoria_contas_pagar where st_ativo = 1 ");
		
		if(ccpConsulta.getId() != null && ccpConsulta.getId() != 0) {
			sql.append(" and id = ");
			sql.append(ccpConsulta.getId());
		}
		
		if(ccpConsulta.getCategoria() != null && !ccpConsulta.getCategoria().trim().equals("")) {
			sql.append(" and categoria like '%");
			sql.append(ccpConsulta.getCategoria());
			sql.append("%'");
		}
		
		abrirConexao();
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				CategoriaContasPagar ccp = new CategoriaContasPagar();
				
				ccp.setId(rs.getInt("id"));
				ccp.setCategoria(rs.getString("categoria"));
				ccp.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaCatContasPagar.add(ccp);
			}
			
			return listaCatContasPagar;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
