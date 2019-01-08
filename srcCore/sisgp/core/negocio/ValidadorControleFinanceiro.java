package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.ControleFinanceiro;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorControleFinanceiro implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof ControleFinanceiro) {
			ControleFinanceiro cf = (ControleFinanceiro)entidade;

			if(cf.getDtDe() == null || 
					cf.getDtAte() == null) {
				return "Os campos de 'Data De' e 'Data At�' s�o de preenchimento obrigat�rio!";
			}

		}else {
			return "Deve ser informados dados v�lidos!";
		}
		return null;
	}

}
