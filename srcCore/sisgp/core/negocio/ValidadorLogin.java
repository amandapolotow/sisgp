package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Login;

public class ValidadorLogin implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof Login) {
			Login login = (Login)entidade;
			
			if(login.getEmail() == null || 
				login.getSenha() == null) {
				return "Email e senha s�o de preenchimento obrigat�rio!";
			}
			if(login.getEmail().trim().equals("") || 
				login.getSenha().trim().equals("")) {
				return "Email e senha s�o de preenchimento obrigat�rio!";
			}
		}else {
			return "Deve ser informado um Login!";
		}
		return null;
	}

}
