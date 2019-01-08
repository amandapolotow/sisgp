package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.ControleEstoque;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Recurso;

public class RecursoDao extends AbstractDao {

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Recurso recurso = (Recurso) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into recursos (descricao,qtd_estoque,");
		sql.append("observacao,st_ativo) values (?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			recurso.setStAtivo(true);
			
			pst.setString(1, recurso.getDescricao());
			pst.setInt(2, recurso.getQtdEstoque());
			pst.setString(3, recurso.getObservacao());
			pst.setBoolean(4, recurso.isStAtivo());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);				
			}
			recurso.setId(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Recurso recurso = (Recurso) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update recursos set descricao=?,qtd_estoque=?,");
		sql.append("observacao=?,st_ativo=? where id=?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			recurso.setStAtivo(true);
			
			pst.setString(1, recurso.getDescricao());
			pst.setInt(2, recurso.getQtdEstoque());
			pst.setString(3, recurso.getObservacao());
			pst.setBoolean(4, recurso.isStAtivo());
			
			pst.setInt(5, recurso.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Recurso recurso = (Recurso) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update recursos set ");
		sql.append("st_ativo=? where id=?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			recurso.setStAtivo(false);
			
			pst.setBoolean(1, recurso.isStAtivo());
			
			pst.setInt(2, recurso.getId());
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaRecursos = new ArrayList<EntidadeDominio>();
		Recurso rConsulta = (Recurso) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from recursos where st_ativo = 1 ");
		
		if(rConsulta.getId() != null && rConsulta.getId() != 0) {
			sql.append(" and id = ");
			sql.append(rConsulta.getId());
		}
		
		if(rConsulta.getDescricao() != null && !rConsulta.getDescricao().trim().equals("")) {
			sql.append(" and descricao like '%");
			sql.append(rConsulta.getDescricao());
			sql.append("%'");
		}
		
		if(rConsulta.getQtdEstoque() != null) {
			sql.append(" and qtd_estoque = ");
			sql.append(rConsulta.getQtdEstoque());
		}
		
		if(rConsulta.getObservacao() != null && !rConsulta.getObservacao().trim().equals("")) {
			sql.append(" and observacao like '%");
			sql.append(rConsulta.getObservacao());
			sql.append("%'");
		}
		System.out.println("SQL:" + sql.toString());
		
		abrirConexao();
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Recurso recurso = new Recurso();
				
				recurso.setId(rs.getInt("id"));
				recurso.setDescricao(rs.getString("descricao"));
				recurso.setQtdEstoque(rs.getInt("qtd_estoque"));
				recurso.setObservacao(rs.getString("observacao"));
				recurso.setStAtivo(rs.getBoolean("st_ativo"));
				
				ControleEstoque ce = new ControleEstoque();
				ControleEstoqueDao ceDao = new ControleEstoqueDao();
				
				ce.setRecurso(recurso);
				
				List<EntidadeDominio> listaCe = ceDao.consultar(ce);
				
				List<ControleEstoque> listaControleEstoque = (List<ControleEstoque>)(List<?>)listaCe;
				recurso.setControleEstoque(listaControleEstoque);
				
				listaRecursos.add(recurso);
			}
			
			return listaRecursos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
