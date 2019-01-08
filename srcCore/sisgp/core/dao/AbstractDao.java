package sisgp.core.dao;

import java.sql.Connection;
import java.sql.SQLException;

import sisgp.core.interfaces.InterfaceDao;
import sisgp.core.util.Conexao;

public abstract class AbstractDao implements InterfaceDao{
	
	protected Connection conexao;


	protected void abrirConexao() {
		
		try {
			if(conexao == null || conexao.isClosed()) {
				conexao = Conexao.getConnection();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
