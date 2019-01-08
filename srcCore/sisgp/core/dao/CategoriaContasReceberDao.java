package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public class CategoriaContasReceberDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		CategoriaContasReceber ccr = (CategoriaContasReceber) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into categoria_contas_receber(categoria,");
		sql.append("st_ativo) values (?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ccr.setStAtivo(true);
			
			pst.setString(1, ccr.getCategoria());
			pst.setBoolean(2, ccr.isStAtivo());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);				
			}
			ccr.setId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		CategoriaContasReceber ccr = (CategoriaContasReceber) entidade;
				
		StringBuilder sql = new StringBuilder();
		
		sql.append("update categoria_contas_receber set categoria = ?,");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ccr.setStAtivo(true);
			
			pst.setString(1, ccr.getCategoria());
			pst.setBoolean(2, ccr.isStAtivo());
			
			pst.setInt(3, ccr.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		CategoriaContasReceber ccr = (CategoriaContasReceber) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update categoria_contas_receber set ");
		sql.append("st_ativo = ? where id = ? and id != 1 ");
		
		abrirConexao();
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ccr.setStAtivo(false);
			
			pst.setBoolean(1, ccr.isStAtivo());			
			pst.setInt(2, ccr.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaCatContasReceber = new ArrayList<EntidadeDominio>();
		CategoriaContasReceber ccrConsulta = (CategoriaContasReceber) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from categoria_contas_receber where st_ativo = 1 ");
		
		if(ccrConsulta.getId() != null && ccrConsulta.getId() != 0) {
			sql.append(" and id = ");
			sql.append(ccrConsulta.getId());
		}
		
		if(ccrConsulta.getCategoria() != null && !ccrConsulta.getCategoria().trim().equals("")) {
			sql.append(" and categoria like '%");
			sql.append(ccrConsulta.getCategoria());
			sql.append("%'");
		}
		
		abrirConexao();
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				CategoriaContasReceber ccr = new CategoriaContasReceber();
				
				ccr.setId(rs.getInt("id"));
				ccr.setCategoria(rs.getString("categoria"));
				ccr.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaCatContasReceber.add(ccr);
			}
			
			return listaCatContasReceber;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
