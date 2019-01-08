package sisgp.web.command;

import sisgp.core.controle.Fachada;
import sisgp.core.interfaces.InterfaceFachada;
import sisgp.web.interfaces.InterfaceCommand;

public abstract class AbstractCommand implements InterfaceCommand {
	
	protected InterfaceFachada fachada = new Fachada();

}
