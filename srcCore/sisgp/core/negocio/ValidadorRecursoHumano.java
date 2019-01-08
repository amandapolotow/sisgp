package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.RecursoHumano;

public class ValidadorRecursoHumano implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		
		if(entidade instanceof RecursoHumano) {
			RecursoHumano rh = (RecursoHumano)entidade;
			
			if(rh.getNome() == null || 
				rh.getSobrenome() == null ||
				rh.getEmail() == null ||
				rh.getTelefone() == null ||
				rh.getCargo() == null ||
				rh.getNivel().getId() == null ||
				rh.getGrupoAcesso().getId() == null ||
				rh.getValorHora() == null ||
				rh.getValorHoraExtra() == null) {
				return "Nome, Sobrenome, Email, Telefone, Cargo, Nível, Grupo, Valor Hora e Valor Hora Extra são de preenchimento obrigatório!";
			}
			
			if(rh.getNome().trim().equals("") || 
					rh.getSobrenome().trim().equals("") ||
					rh.getEmail().trim().equals("") ||
					rh.getTelefone().trim().equals("") ||
					rh.getCargo().trim().equals("") ||
					rh.getNivel().getId() == 0 ||
					rh.getGrupoAcesso().getId() == 0 ||
					rh.getValorHora() == 0.0 ||
					rh.getValorHoraExtra() == 0.0) {
					return "Nome, Sobrenome, Email, Telefone, Cargo, Nível, Grupo, Valor Hora e Valor Hora Extra são de preenchimento obrigatório!";
				}
		}else {
			return "Deve ser cadastrado um Recurso Humano!";
		}
		return null;
	}
	

}
