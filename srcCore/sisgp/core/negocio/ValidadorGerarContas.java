package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.GerarContas;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorGerarContas implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof GerarContas) {
			GerarContas gc = (GerarContas)entidade;

			if(gc.getDtDe() == null || 
					gc.getDtAte() == null ||
					gc.getProjeto() == null ||
					gc.getRecursoHumano() == null) {
				return "Todos os campos s�o de preenchimento obrigat�rio!";
			}
			
			if(gc.getProjeto().getId() == 0 ||
					gc.getRecursoHumano().getId() == 0) {
				return "Todos os campos s�o de preenchimento obrigat�rio!";
			}

		}else {
			return "Deve ser informados dados v�lidos!";
		}
		return null;
	}

}
