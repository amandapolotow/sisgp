package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.Atividade;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorAtividade implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof Atividade) {
			Atividade at = (Atividade)entidade;
			
			if(at.getCodigo() == null ||
				at.getDescricao() == null ||
				at.getNome() == null ||
				at.getProjeto() == null ||
				at.getProjeto().getId() == null ||
				at.getStatusAtividade() == null ||
				at.getStatusAtividade().getId() == null) {
				return "C�digo, Nome, Descri��o, Projeto e Status s�o de preenchimento obrigat�rio!1";
			}
			
			if(at.getCodigo().trim().equals("") ||
				at.getDescricao().trim().equals("") ||
				at.getNome().trim().equals("") ||
				at.getProjeto().getId() == 0 ||
				at.getStatusAtividade().getId() == 0) {
				return "C�digo, Nome, Descri��o, Projeto e Status s�o de preenchimento obrigat�rio!2";
			}
		}else {
			return "Deve ser cadastrado um Atividade!";
		}
		return null;
	}

}
