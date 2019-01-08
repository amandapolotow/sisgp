package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.Cliente;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorCliente implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof Cliente) {
			Cliente cli = (Cliente)entidade;
			
			if(cli.getNomeFantasia() == null ||
				cli.getRazaoSocial() == null ||
				cli.getCnpj() == null ||
				cli.getEmail() == null ||
				cli.getTelefone() == null ||
				cli.getResponsavel() == null) {
				return "Nome Fantasia, Raz�o Social, CNPJ, Email, Telefone e Respons�vel s�o de preenchimento obrigat�rio!";
			}
			
			if(cli.getNomeFantasia().trim().equals("") ||
					cli.getRazaoSocial().trim().equals("") ||
					cli.getCnpj().trim().equals("") ||
					cli.getEmail().trim().equals("") ||
					cli.getTelefone().trim().equals("") ||
					cli.getResponsavel().trim().equals("")) {
					return "Nome Fantasia, Raz�o Social, CNPJ, Email, Telefone e Respons�vel s�o de preenchimento obrigat�rio!";
				}
				
		}else {
			return "Deve ser cadastrado um Cliente!";
		}

		return null;
	}

}
