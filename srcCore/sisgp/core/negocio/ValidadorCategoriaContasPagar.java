package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorCategoriaContasPagar implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof CategoriaContasPagar) {
			
			CategoriaContasPagar ccp = (CategoriaContasPagar)entidade;
			
			if(ccp.getCategoria() == null) {
				return "Categoria � de preenchimento obrigat�rio!";
			}
			
			if(ccp.getCategoria().trim().equals("")) {
				return "Categoria � de preenchimento obrigat�rio!";
			}
		}else {
			return "Deve ser cadastrada uma Categoria de Contas a Pagar!";
		}
		return null;
	}

}
