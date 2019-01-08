package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.StatusAtividade;

public class StatusAtividadeDao extends AbstractDao {

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
		List<EntidadeDominio> listaStAtividade = new ArrayList<EntidadeDominio>();
		
		String sql = "select * from status_atividade where st_ativo = 1";
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				StatusAtividade sa = new StatusAtividade();
				sa.setId(rs.getInt("id"));
				sa.setStatus(rs.getString("status"));
				listaStAtividade.add(sa);
			}
			
			return listaStAtividade;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
