package sisgp.web.command;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public class AdicionarCommand extends AbstractCommand{

	@Override
	public Resultado executar(InterfaceEntidade entidade) {
		return fachada.adicionar((EntidadeDominio)entidade);

	}

}
