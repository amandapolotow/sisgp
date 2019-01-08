package sisgp.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.StatusContas;

public class StatusContasDao extends AbstractDao{

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
		List<EntidadeDominio> listaStatusContas = new ArrayList<EntidadeDominio>();
		StatusContas st = (StatusContas)entidade;
		String sql = "select * from status_contas where st_ativo = 1";
		
		abrirConexao();
		
		try {
			pst = conexao.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				StatusContas sc = new StatusContas();
				sc.setId(rs.getInt("id"));
				sc.setStatus(rs.getString("status"));
				listaStatusContas.add(sc);
			}
			
			return listaStatusContas;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
