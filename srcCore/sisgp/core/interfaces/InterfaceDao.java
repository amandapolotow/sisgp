package sisgp.core.interfaces;

import java.util.List;

import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public interface InterfaceDao {
	
	public void adicionar(EntidadeDominio entidade);
	public void editar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(InterfaceEntidade entidade);

}
