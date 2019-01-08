

package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GrupoAcesso;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Nivel;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.SubAtividade;

public class RecursoHumanoDao extends AbstractDao{
	
	public void adicionar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		
		RecursoHumano rh = (RecursoHumano)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into recursos_humanos(nome,sobrenome,");
		sql.append("email,senha,telefone,cargo,id_nivel,id_grupo_acesso,");
		sql.append("vl_hora,vl_hora_extra,observacao,");
		sql.append("st_ativo) values (?,?,?,?,?,?,?,?,?,?,?,?)");		
		
		abrirConexao();

		try {
			pst = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			rh.setStAtivo(true);//marca como ativo no banco
			
			pst.setString(1, rh.getNome());
			pst.setString(2, rh.getSobrenome());
			pst.setString(3, rh.getEmail());
			pst.setString(4, rh.getSenha());
			pst.setString(5, rh.getTelefone());
			pst.setString(6, rh.getCargo());
			pst.setInt(7, rh.getNivel().getId());
			pst.setInt(8, rh.getGrupoAcesso().getId());
			pst.setDouble(9, rh.getValorHora());
			pst.setDouble(10, rh.getValorHoraExtra());
			pst.setString(11, rh.getObservacao());
			pst.setBoolean(12, rh.isStAtivo());
			
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
				rh.setId(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		PreparedStatement pst = null;
		
		RecursoHumano rh = (RecursoHumano)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update recursos_humanos set nome = ?,sobrenome = ?,");
		sql.append("email = ?,senha = ?,telefone = ?,cargo = ?,id_nivel = ?,id_grupo_acesso = ?,");
		sql.append("vl_hora = ?,vl_hora_extra = ?,observacao = ?,");
		sql.append("st_ativo = ? where id = ?");		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			rh.setStAtivo(true);//marca como ativo no banco
			
			pst.setString(1, rh.getNome());
			pst.setString(2, rh.getSobrenome());
			pst.setString(3, rh.getEmail());
			pst.setString(4, rh.getSenha());
			pst.setString(5, rh.getTelefone());
			pst.setString(6, rh.getCargo());
			pst.setInt(7, rh.getNivel().getId());
			pst.setInt(8, rh.getGrupoAcesso().getId());
			pst.setDouble(9, rh.getValorHora());
			pst.setDouble(10, rh.getValorHoraExtra());
			pst.setString(11, rh.getObservacao());
			pst.setBoolean(12, rh.isStAtivo());
			
			pst.setInt(13, rh.getId());
			
			//System.out.println(pst.toString());
			
			pst.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		
		RecursoHumano rh = (RecursoHumano)entidade;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update recursos_humanos set ");
		sql.append("st_ativo=? where id=?");		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			rh.setStAtivo(false);//marca como ativo no banco
			
			pst.setBoolean(1, rh.isStAtivo());			
			pst.setInt(2, rh.getId());
			
			System.out.println(pst.toString());
			
			pst.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade) {
		
		PreparedStatement pst = null;
		ApontamentoHoras ahConsulta = null;
		List<EntidadeDominio> listaRh = new ArrayList<EntidadeDominio>();
		
		RecursoHumano rhConsulta = (RecursoHumano)entidade;
		if(rhConsulta.getListaApontamentoHoras() != null) {
			ahConsulta = rhConsulta.getListaApontamentoHoras().get(0);
		}
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from recursos_humanos where st_ativo = 1 ");
		   
		if(rhConsulta.getId() != 0) {
			sql.append(" and id = ");
			sql.append(rhConsulta.getId());
		}
		
		if(rhConsulta.getNome() != null && !rhConsulta.getNome().trim().equals("")) {
			sql.append(" and nome like '%");
			sql.append(rhConsulta.getNome());
			sql.append("%'");
		}

		if(rhConsulta.getSobrenome() != null && !rhConsulta.getSobrenome().trim().equals("")) {
			sql.append(" and sobrenome like '%");
			sql.append(rhConsulta.getSobrenome());
			sql.append("%'");
		}
		
		if(rhConsulta.getEmail() != null && !rhConsulta.getEmail().trim().equals("")) {
			sql.append(" and email like '%");
			sql.append(rhConsulta.getEmail());
			sql.append("%'");
		}
		
		if(rhConsulta.getTelefone() != null && !rhConsulta.getTelefone().trim().equals("")) {
			sql.append(" and telefone like '%");
			sql.append(rhConsulta.getTelefone());
			sql.append("%'");
		}
		
		if(rhConsulta.getCargo() != null && !rhConsulta.getCargo().trim().equals("")) {
			sql.append(" and cargo like '%");
			sql.append(rhConsulta.getCargo());
			sql.append("%'");
		}

		if(rhConsulta.getNivel() != null) {
			if(rhConsulta.getNivel().getId() != null && rhConsulta.getNivel().getId()!= 0) {
				sql.append(" and id_nivel = ");
				sql.append(rhConsulta.getNivel().getId());
			}
		}
		
		if(rhConsulta.getGrupoAcesso() != null) {
			if(rhConsulta.getGrupoAcesso().getId() != null && rhConsulta.getGrupoAcesso().getId()!= 0) {
				sql.append(" and id_grupo_acesso = ");
				sql.append(rhConsulta.getGrupoAcesso().getId());
			}
		}	
		
		if(rhConsulta.getValorHora() != null && rhConsulta.getValorHora() != 0) {
			sql.append(" and vl_hora = ");
			sql.append(rhConsulta.getValorHora());
		}
		
		if(rhConsulta.getValorHoraExtra() != null && rhConsulta.getValorHoraExtra() != 0) {
			sql.append(" and vl_hora_extra = ");
			sql.append(rhConsulta.getValorHoraExtra());
		}
		
		//System.out.println("Query recursos_humanos: " + sql.toString());
	
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());

			ResultSet rs = pst.executeQuery();
			//int cont = 0;
			while (rs.next()) {
			//	cont++;
				RecursoHumano rh = new RecursoHumano();
				
				rh.setId(rs.getInt("id"));
				rh.setNome(rs.getString("nome"));
				rh.setSobrenome(rs.getString("sobrenome"));
				rh.setEmail(rs.getString("email"));
				rh.setSenha(rs.getString("senha"));
				rh.setTelefone(rs.getString("telefone"));
				rh.setCargo(rs.getString("cargo"));
				
				Integer idNivel = rs.getInt("id_nivel");
				Nivel n = new Nivel(idNivel);
				rh.setNivel(n);
				
				Integer idGrupo = rs.getInt("id_grupo_acesso");
				GrupoAcesso ga = new GrupoAcesso(idGrupo);
				rh.setGrupoAcesso(ga);

				rh.setValorHora(rs.getDouble("vl_hora"));
				rh.setValorHoraExtra(rs.getDouble("vl_hora_extra"));
				rh.setObservacao(rs.getString("observacao"));
				rh.setStAtivo(rs.getBoolean("st_ativo"));
				
				
				
				if(ahConsulta != null) {
					ApontamentoHoras ah = new ApontamentoHoras();//para consulta de apontamentos
					ApontamentoHorasDao ahd = new ApontamentoHorasDao();
					
					//dados de RH para consulta
					SubAtividade sa = new SubAtividade();
					sa.setRecursoHumano(rh);
					ah.setSubAtividade(sa);
					
					//System.out.println("ID RH em RHDAO: " + ah.getSubAtividade().getRecursoHumano().getId());
					
					//Datas e outros dados para consulta
					if(ahConsulta != null && ahConsulta.getDtDe() != null) {
						ah.setDtDe(ahConsulta.getDtDe());
					}
					if(ahConsulta != null && ahConsulta.getDtAte() != null) {
						ah.setDtAte(ahConsulta.getDtAte());
					}
					if(ahConsulta != null && ahConsulta.getStPago() != null) {
						ah.setStPago(ahConsulta.getStPago());
					}
					if(ahConsulta != null && ahConsulta.getContasPagar() != null) {
						ah.setContasPagar(ahConsulta.getContasPagar());
					}
					
					
					//Consulta de Apontamentos
					List<EntidadeDominio> listAh = ahd.consultar(ah);									
					List<ApontamentoHoras> listaApontamentoHoras = (List<ApontamentoHoras>)(List<?>)listAh;
					
					//'setando' lista de apontamentos em rh
					rh.setListaApontamentoHoras(listaApontamentoHoras);
				}
				
				
				listaRh.add(rh);
			}
			//System.out.println("Contador RH: " + cont);
			return listaRh;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
