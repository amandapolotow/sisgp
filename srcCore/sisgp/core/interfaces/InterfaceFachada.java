package sisgp.core.interfaces;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public interface InterfaceFachada {
	
	public Resultado adicionar(EntidadeDominio entidade);
	public Resultado editar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(InterfaceEntidade entidade);
	public Resultado visualizar(EntidadeDominio entidade);

}
