package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.ContasReceber;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Recurso;

public class ValidadorRecurso implements InterfaceStrategy {

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof Recurso) {
			Recurso rec = (Recurso)entidade;
			
			if(rec.getDescricao() == null || 
					rec.getQtdEstoque() == null) {
				return "Descri��o e Quantidade em Estoque s�o de preenchimento obrigat�rio!";
			}
			
			if(rec.getDescricao().trim().equals("") || 
					rec.getQtdEstoque() == 0) {
				return "Descri��o e Quantidade em Estoque s�o de preenchimento obrigat�rio!";
			}
			
			if(rec.getQtdEstoque() < 0) {
				return "N�o � possivel cadastrar um estoque negativo!";
			}
		}else {
			return "Deve ser cadastrado um Recurso!";
		}
		return null;
	}

}
