package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GrupoAcesso;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Login;

public class LoginDao extends AbstractDao{

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
		List<EntidadeDominio> listaLogin = new ArrayList<EntidadeDominio>();
		
		Login loginConsulta = (Login)entidade;
		
		StringBuilder sql = new StringBuilder();		
		sql.append("select recursos_humanos.id as id_recurso_humano, concat(recursos_humanos.nome, ' ', recursos_humanos.sobrenome) as nome, ");
		sql.append("recursos_humanos.senha, recursos_humanos.email, ");
		sql.append("grupos_acesso.id as id_grupo_acesso, grupos_acesso.grupo_acesso ");
		sql.append("from recursos_humanos inner join grupos_acesso ");
		sql.append("on recursos_humanos.id_grupo_acesso = grupos_acesso.id ");
		sql.append("where recursos_humanos.st_ativo = 1 and recursos_humanos.email = ? ");
		sql.append("and recursos_humanos.senha = ?");
		
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql.toString());
			
			pst.setString(1,loginConsulta.getEmail());
			pst.setString(2, loginConsulta.getSenha());
			
			//System.out.println("Login: " + pst.toString());
			
			ResultSet rs = pst.executeQuery();
			Login login = null;
			
			if (rs.next()) {
				login = new Login();
				login.setId(rs.getInt("id_recurso_humano"));
				login.setNome(rs.getString("nome"));
				login.setEmail(rs.getString("email"));
				login.setSenha(rs.getString("senha"));
				
				Integer idGrupo = rs.getInt("id_grupo_acesso");
				String grupo = rs.getString("grupo_acesso");
				GrupoAcesso ga = new GrupoAcesso(idGrupo,grupo);
				login.setGrupoAcesso(ga);
				
				listaLogin.add(login);
			}else {
				login = new Login();
				login.setFlgNaoEncontrado(true);
				listaLogin.add(login);
			}
			
			return listaLogin;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaLogin;
	}

}
