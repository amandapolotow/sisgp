package sisgp.core.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.Atividade;
import sisgp.dominio.Cliente;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusProjeto;
import sisgp.dominio.SubAtividade;

public class ProjetoDao extends AbstractDao {

	@Override
	public void adicionar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		
		Projeto proj = (Projeto) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into projetos(id_rh_gestor,id_cliente,codigo,nome,descricao,");
		sql.append("id_status_projeto,st_ativo) values (?,?,?,?,?,?,?)");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			proj.setStAtivo(true);//marca como ativo no banco

			
			pst.setInt(1, proj.getRhGestor().getId());
			pst.setInt(2, proj.getCliente().getId());
			pst.setString(3, proj.getCodigo());
			pst.setString(4, proj.getNome());
			pst.setString(5, proj.getDescricao());
			pst.setInt(6, proj.getStatusProjeto().getId());			
			pst.setBoolean(7, proj.isStAtivo());
			
			
			//System.out.println("query:" + pst.toString());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				proj.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		
		Projeto proj = (Projeto) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update projetos set id_rh_gestor=?,id_cliente=?,codigo=?,nome=?,descricao=?,");
		sql.append("id_status_projeto=?,st_ativo=? where id=?");
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			proj.setStAtivo(true);//marca como ativo no banco
			
			pst.setInt(1, proj.getRhGestor().getId());
			pst.setInt(2, proj.getCliente().getId());
			pst.setString(3, proj.getCodigo());
			pst.setString(4, proj.getNome());
			pst.setString(5, proj.getDescricao());
			pst.setInt(6, proj.getStatusProjeto().getId());			
			pst.setBoolean(7, proj.isStAtivo());
			
			pst.setInt(8, proj.getId());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Projeto proj = (Projeto) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update projetos set ");
		sql.append("st_ativo=? where id=?");		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			proj.setStAtivo(false);//marca como inativo no banco
			
			pst.setBoolean(1, proj.isStAtivo());			
			pst.setInt(2, proj.getId());
			
			//System.out.println(pst.toString());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		
		PreparedStatement pst = null;
		List<EntidadeDominio> listaProj = new ArrayList<EntidadeDominio>();
		
		Projeto projConsulta = (Projeto) entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from projetos where st_ativo = 1 ");
		
		if(projConsulta.getRhGestor() != null) {
			sql.append(" and id_rh_gestor = ");
			sql.append(projConsulta.getRhGestor().getId());
		}
		
		if(projConsulta.getCliente() != null) {
			sql.append(" and id_cliente = ");
			sql.append(projConsulta.getCliente().getId());
		}
		
		if(projConsulta.getCodigo() != null && !projConsulta.getCodigo().trim().equals("")) {
			sql.append(" and codigo like '%");
			sql.append(projConsulta.getCodigo());
			sql.append("%' ");
		}
		if(projConsulta.getNome() != null && !projConsulta.getNome().trim().equals("")) {
			sql.append(" and nome like '%");
			sql.append(projConsulta.getNome());
			sql.append("%' ");
		}
		if(projConsulta.getDescricao() != null && !projConsulta.getDescricao().trim().equals("")) {
			sql.append(" and descricao like '%");
			sql.append(projConsulta.getDescricao());
			sql.append("%' ");
		}
		
		if(projConsulta.getStatusProjeto() != null) {
			sql.append(" and id_status_projeto = ");
			sql.append(projConsulta.getStatusProjeto().getId());
		}
		
		//System.out.println("Projetos: " + sql.toString());
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Projeto proj = new Projeto();
				
				proj.setId(rs.getInt("id"));
				
				Integer idGestor = rs.getInt("id_rh_gestor");
				RecursoHumano rh = new RecursoHumano(idGestor);
				proj.setRhGestor(rh);
				
				Integer idCliente = rs.getInt("id_cliente");
				Cliente cli = new Cliente(idCliente);
				proj.setCliente(cli);
				
				proj.setCodigo(rs.getString("codigo"));
				proj.setNome(rs.getString("nome"));
				proj.setDescricao(rs.getString("descricao"));
				
				Integer idStatus = rs.getInt("id_status_projeto");
				StatusProjeto stp = new StatusProjeto(idStatus);
				proj.setStatusProjeto(stp);
				
				Atividade at = new Atividade();
				AtividadeDao atD = new AtividadeDao();
				
				at.setProjeto(proj);
				
				List<EntidadeDominio>listAt = atD.consultar(at);
				
				List<Atividade> listaAtividade = (List<Atividade>)(List<?>)listAt;
				proj.setListaAtividade(listaAtividade);
				
				listaProj.add(proj);
				
			}
			
			return listaProj;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
