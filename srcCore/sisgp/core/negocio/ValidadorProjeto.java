package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;

public class ValidadorProjeto implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof Projeto) {
			Projeto proj = (Projeto)entidade;
			
			if(proj.getRhGestor() == null || 
				proj.getCliente() == null ||
				proj.getCodigo() == null ||
				proj.getNome() == null ||
				proj.getDescricao() == null ||
				proj.getStatusProjeto() == null) {
				return "Gestor, Cliente, Código, Nome, Descrição e Status são de preenchimento obrigatório!";
			}
			
				if(proj.getRhGestor().getId() == 0 || 
				proj.getCliente().getId() == 0 ||
				proj.getCodigo().trim().equals("") ||
				proj.getNome().trim().equals("") ||
				proj.getDescricao().trim().equals("") ||
				proj.getStatusProjeto().getId() == 0) {
					return "Gestor, Cliente, Código, Nome, Descrição e Status são de preenchimento obrigatório!";
				}
		}else {
			return "Deve ser cadastrado um Projeto!";
		}
		return null;
	}

}
