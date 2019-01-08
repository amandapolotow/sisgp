package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorCategoriaContasReceber implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof CategoriaContasReceber) {
			
			CategoriaContasReceber ccr = (CategoriaContasReceber)entidade;
			
			if(ccr.getCategoria() == null) {
				return "Categoria � de preenchimento obrigat�rio!";
			}
			
			if(ccr.getCategoria().trim().equals("")) {
				return "Categoria � de preenchimento obrigat�rio!";
			}
		}else {
			return "Deve ser cadastrada uma Categoria de Contas a Receber!";
		}
		return null;
	}

}
