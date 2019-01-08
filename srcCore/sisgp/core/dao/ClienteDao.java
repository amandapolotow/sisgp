package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.Cliente;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public class ClienteDao extends AbstractDao{

	@Override
	public void adicionar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Cliente cli = (Cliente)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into clientes(razao_social,nome_fantasia,");
		sql.append("cnpj,email,telefone,website,responsavel,observacao,");
		sql.append("st_ativo) values (?,?,?,?,?,?,?,?,?)");		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			cli.setStAtivo(true);//marca como ativo no banco
			
			pst.setString(1, cli.getRazaoSocial());
			pst.setString(2, cli.getNomeFantasia());
			pst.setString(3, cli.getCnpj());
			pst.setString(4, cli.getEmail());
			pst.setString(5, cli.getTelefone());
			pst.setString(6, cli.getWebsite());
			pst.setString(7, cli.getResponsavel());
			pst.setString(8, cli.getObservacao());

			pst.setBoolean(9, cli.isStAtivo());
			
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				cli.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Cliente cli = (Cliente)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update clientes set razao_social = ?,nome_fantasia = ?,");
		sql.append("cnpj = ?,email = ?,telefone = ?,website = ?,responsavel = ?,observacao = ?,");
		sql.append("st_ativo = ? where id = ?");		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			cli.setStAtivo(true);//marca como ativo no banco
			
			pst.setString(1, cli.getRazaoSocial());
			pst.setString(2, cli.getNomeFantasia());
			pst.setString(3, cli.getCnpj());
			pst.setString(4, cli.getEmail());
			pst.setString(5, cli.getTelefone());
			pst.setString(6, cli.getWebsite());
			pst.setString(7, cli.getResponsavel());
			pst.setString(8, cli.getObservacao());

			pst.setBoolean(9, cli.isStAtivo());
			
			pst.setInt(10, cli.getId());
			
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
PreparedStatement pst = null;
		
		Cliente cli = (Cliente)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update clientes set ");
		sql.append("st_ativo = ? where id = ?");		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			cli.setStAtivo(false);//marca como ativo no banco
			

			pst.setBoolean(1, cli.isStAtivo());
			
			pst.setInt(2, cli.getId());
			
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		PreparedStatement pst = null;
		List<EntidadeDominio> listaClientes = new ArrayList<EntidadeDominio>();
		
		Cliente ConsultaCli = (Cliente)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from clientes where st_ativo = 1 ");
		
		if(ConsultaCli.getId() != null && ConsultaCli.getId() != 0) {
			sql.append(" and id = ");
			sql.append(ConsultaCli.getId());
		}
		
		if(ConsultaCli.getRazaoSocial() != null && !ConsultaCli.getRazaoSocial().trim().equals("")) {
			sql.append(" and razao_social like '%");
			sql.append(ConsultaCli.getRazaoSocial());
			sql.append("%'");
		}
		
		if(ConsultaCli.getNomeFantasia() != null && !ConsultaCli.getNomeFantasia().trim().equals("")) {
			sql.append(" and nome_fatasia like '%");
			sql.append(ConsultaCli.getNomeFantasia());
			sql.append("%'");
		}
		
		if(ConsultaCli.getCnpj() != null && !ConsultaCli.getCnpj().trim().equals("")) {
			sql.append(" and cnpj like '%");
			sql.append(ConsultaCli.getCnpj());
			sql.append("%'");
		}
		
		if(ConsultaCli.getEmail() != null && !ConsultaCli.getEmail().trim().equals("")) {
			sql.append(" and email like '%");
			sql.append(ConsultaCli.getEmail());
			sql.append("%'");
		}
		
		if(ConsultaCli.getTelefone() != null && !ConsultaCli.getTelefone().trim().equals("")) {
			sql.append(" and telefone like '%");
			sql.append(ConsultaCli.getTelefone());
			sql.append("%'");
		}
		
		if(ConsultaCli.getWebsite() != null && !ConsultaCli.getWebsite().trim().equals("")) {
			sql.append(" and website like '%");
			sql.append(ConsultaCli.getWebsite());
			sql.append("%'");
		}
		
		if(ConsultaCli.getResponsavel() != null && !ConsultaCli.getResponsavel().trim().equals("")) {
			sql.append(" and responsavel like '%");
			sql.append(ConsultaCli.getResponsavel());
			sql.append("%'");
		}
		
		if(ConsultaCli.getObservacao() != null && !ConsultaCli.getObservacao().trim().equals("")) {
			sql.append(" and observacao like '%");
			sql.append(ConsultaCli.getObservacao());
			sql.append("%'");
		}
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Cliente cli = new Cliente();
				
				cli.setId(rs.getInt("id"));
				cli.setRazaoSocial(rs.getString("razao_social"));
				cli.setNomeFantasia(rs.getString("nome_fantasia"));
				cli.setCnpj(rs.getString("cnpj"));
				cli.setEmail(rs.getString("email"));
				cli.setTelefone(rs.getString("telefone"));
				cli.setWebsite(rs.getString("website"));
				cli.setResponsavel(rs.getString("responsavel"));
				cli.setObservacao(rs.getString("observacao"));			
				cli.setStAtivo(rs.getBoolean("st_ativo"));
				
				listaClientes.add(cli);
			}
			
			return listaClientes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
