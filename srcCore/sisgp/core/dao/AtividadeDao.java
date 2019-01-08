package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.Atividade;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusAtividade;
import sisgp.dominio.StatusSistemaAtividade;
import sisgp.dominio.SubAtividade;

public class AtividadeDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Atividade at = (Atividade) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into atividades (id_projeto,codigo,nome,descricao,");
		sql.append("id_status_atividade,id_st_sist_atividade,");
		sql.append("st_ativo) values (?,?,?,?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			at.setStAtivo(true);
			at.setStSistAtividade(new StatusSistemaAtividade(1));//atividade não iniciada
			
			pst.setInt(1, at.getProjeto().getId());
			pst.setString(2, at.getCodigo());
			pst.setString(3, at.getNome());
			pst.setString(4, at.getDescricao());
			pst.setInt(5, at.getStatusAtividade().getId());
			pst.setInt(6, at.getStSistAtividade().getId());
			pst.setBoolean(7, at.isStAtivo());
			
			System.out.println(pst.toString());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				at.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Atividade at = (Atividade) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update atividades set id_projeto = ?,codigo = ?,nome = ?,descricao = ?,");
		sql.append("id_status_atividade = ?,id_st_sist_atividade = ?,");
		sql.append("st_ativo = ? where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			at.setStAtivo(true);
			
			pst.setInt(1, at.getProjeto().getId());
			pst.setString(2, at.getCodigo());
			pst.setString(3, at.getNome());
			pst.setString(4, at.getDescricao());
			pst.setInt(5, at.getStatusAtividade().getId());
			pst.setInt(6, at.getStSistAtividade().getId());
			pst.setBoolean(7, at.isStAtivo());
			
			pst.setInt(8, at.getId());
			
			pst.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Atividade at = (Atividade) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update atividades set st_ativo = ? ");
		sql.append("where id = ?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			at.setStAtivo(false);
			
			pst.setBoolean(1, at.isStAtivo());
			
			pst.setInt(2, at.getId());
			
			pst.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaAtividade = new ArrayList<EntidadeDominio>();
		
		Atividade atConsulta = (Atividade) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select atividades.*,status_atividade.status as st_at ,status_sistema_atividade.status as st_sis_at ");
		sql.append(" from atividades inner join status_atividade ");
		sql.append(" on atividades.id_status_atividade = status_atividade.id ");
		sql.append(" inner join status_sistema_atividade ");
		sql.append(" on atividades.id_st_sist_atividade = status_sistema_atividade.id ");
		sql.append(" where atividades.st_ativo = 1 ");

		if(atConsulta.getId() != null && atConsulta.getId() != 0) {
			sql.append(" and atividades.id = ");
			sql.append(atConsulta.getId());
		}
		
		if(atConsulta.getProjeto() != null) {
			sql.append(" and atividades.id_projeto = ");
			sql.append(atConsulta.getProjeto().getId());
		}
		
		if(atConsulta.getCodigo() != null && !atConsulta.getCodigo().trim().equals("")) {
			sql.append(" and atividades.codigo like '%");
			sql.append(atConsulta.getCodigo());
			sql.append("%'");
		}
		
		if(atConsulta.getNome() != null && !atConsulta.getNome().trim().equals("")) {
			sql.append(" and atividades.nome like '%");
			sql.append(atConsulta.getNome());
			sql.append("%'");
		}
		
		if(atConsulta.getDescricao() != null && !atConsulta.getDescricao().trim().equals("")) {
			sql.append(" and atividades.descricao like '%");
			sql.append(atConsulta.getDescricao());
			sql.append("%'");
		}
		
		if(atConsulta.getStatusAtividade() != null) {
			sql.append(" and atividades.id_status_atividade = ");
			sql.append(atConsulta.getStatusAtividade().getId());
		}
		
		if(atConsulta.getStSistAtividade() != null) {
			sql.append(" and atividades.id_st_sist_atividade = ");
			sql.append(atConsulta.getStSistAtividade().getId());
		}
		
		sql.append(" order by atividades.id ");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Atividade at = new Atividade();
				
				at.setId(rs.getInt("id"));
				
				Integer idProjeto = rs.getInt("id_projeto");
				Projeto pj = new Projeto(idProjeto);
				at.setProjeto(pj);
				
				at.setCodigo(rs.getString("codigo"));
				at.setNome(rs.getString("nome"));
				at.setDescricao(rs.getString("descricao"));
				
				Integer idStatus = rs.getInt("id_status_atividade");
				String stAt = rs.getString("st_at");
				StatusAtividade sta = new StatusAtividade(idStatus,stAt);
				at.setStatusAtividade(sta);
				
				Integer idStSist = rs.getInt("id_st_sist_atividade");
				String stSisAt = rs.getString("st_sis_at");
				StatusSistemaAtividade ssa = new StatusSistemaAtividade(idStSist,stSisAt);
				at.setStSistAtividade(ssa);
				
				at.setStAtivo(rs.getBoolean("st_ativo"));
				
				SubAtividade subA = new SubAtividade();
				SubAtividadeDao subAD = new SubAtividadeDao();
				
				subA.setAtividade(at);
				
				List<EntidadeDominio>listSA = subAD.consultar(subA);
				
				List<SubAtividade> listaSubAtividade = (List<SubAtividade>)(List<?>)listSA;
				at.setListaSubAtividade(listaSubAtividade);
						
				
				listaAtividade.add(at);
			}
			
			return listaAtividade;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
