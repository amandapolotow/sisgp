package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.ContasReceber;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorContasReceber implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof ContasReceber) {
			ContasReceber cr = (ContasReceber)entidade;
			
			if(cr.getStatusContas() == null || 
					cr.getValor() == null ||
					cr.getDtVencimento() == null ||
					cr.getNrParcelas() == null) {
				return "Status, Valor, Data de Vencimento e Número de Parcelas são de preenchimento obrigatório!";
			}
			
			if(cr.getStatusContas() == null || 
					cr.getStatusContas().getId() == 0 || 
					cr.getValor() == 0.0 ||
					cr.getNrParcelas() <= 0) {
				return "Status, Valor, Data de Vencimento e Número de Parcelas são de preenchimento obrigatório!";
			}
			
			if(cr.getNrParcelas() <= 0 || cr.getNrParcelas() > 20) {
				return "Número máximo de parcelas é 20!";
			}
		}else {
			return "Deve ser cadastrado uma Conta!";
		}
		return null;
	}

}
