package sisgp.web.interfaces;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.InterfaceEntidade;

public interface InterfaceCommand {
	
	public Resultado executar(InterfaceEntidade entidade);

}
