package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.ControleEstoque;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.Recurso;

public class ControleEstoqueDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ControleEstoque ce = (ControleEstoque) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into recursos_projetos (id_recurso,id_projeto,");
		sql.append("quantidade,st_ativo) values (?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			ce.setStAtivo(true);
			
			pst.setInt(1, ce.getProjeto().getId());
			pst.setInt(2, ce.getRecurso().getId());
			pst.setInt(3, ce.getQuantidade());
			pst.setBoolean(4, ce.isStAtivo());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);				
			}
			ce.setId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ControleEstoque ce = (ControleEstoque) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update recursos_projetos set id_recurso=?,id_projeto=?,");
		sql.append("quantidade=?,st_ativo=? where id_projeto=? and id_recurso=?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ce.setStAtivo(true);
			
			pst.setInt(1, ce.getProjeto().getId());
			pst.setInt(2, ce.getRecurso().getId());
			pst.setInt(3, ce.getQuantidade());
			pst.setBoolean(4, ce.isStAtivo());
			pst.setInt(5, ce.getProjeto().getId());
			pst.setInt(6, ce.getRecurso().getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		ControleEstoque ce = (ControleEstoque) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update recursos_projetos set ");
		sql.append("st_ativo=? where id_projeto=? and id_recurso=?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ce.setStAtivo(false);
			
			pst.setBoolean(1, ce.isStAtivo());
			pst.setInt(2, ce.getProjeto().getId());
			pst.setInt(3, ce.getRecurso().getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaControleEstoque = new ArrayList<EntidadeDominio>();
		ControleEstoque ceConsulta = (ControleEstoque) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from recursos_projetos where st_ativo = 1 ");
		
		if(ceConsulta.getProjeto() != null) {
			if(ceConsulta.getProjeto().getId() != null && ceConsulta.getProjeto().getId() != 0) {
				sql.append(" and id_projeto = ");
				sql.append(ceConsulta.getProjeto().getId());
			}
		}
		
		if(ceConsulta.getRecurso() != null) {
			if(ceConsulta.getRecurso().getId() != null && ceConsulta.getRecurso().getId() != 0) {
				sql.append(" and id_recurso = ");
				sql.append(ceConsulta.getRecurso().getId());
			}
		}		
		
		if(ceConsulta.getQuantidade() != null && ceConsulta.getQuantidade() != 0) {
			sql.append(" and quantidade = ");
			sql.append(ceConsulta.getQuantidade());
		}
		

		
		abrirConexao();
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				ControleEstoque ce = new ControleEstoque();
				
				ce.setQuantidade(rs.getInt("quantidade"));
				
				Integer idProjeto = rs.getInt("id_projeto");
				Projeto projeto = new Projeto(idProjeto);
				ce.setProjeto(projeto);
				
				Integer idRecurso = rs.getInt("id_recurso");
				Recurso recurso = new Recurso(idRecurso);
				ce.setRecurso(recurso);

				
				listaControleEstoque.add(ce);
			}
			
			return listaControleEstoque;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaControleEstoque;
	}

}
