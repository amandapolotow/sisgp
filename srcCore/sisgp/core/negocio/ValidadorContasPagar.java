package sisgp.core.negocio;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.InterfaceEntidade;

public class ValidadorContasPagar implements InterfaceStrategy{

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof ContasPagar) {
			ContasPagar cp = (ContasPagar)entidade;
			
			if(cp.getStatusContas() == null || 
				cp.getValor() == null ||
				cp.getDtVencimento() == null ||
				cp.getNrParcelas() == null) {
				return "Status, Valor, Data de Vencimento e N�mero de Parcelas s�o de preenchimento obrigat�rio!";
			}
			
			if(cp.getStatusContas() == null || cp.getStatusContas().getId() == 0 || 
				cp.getValor() == 0.0 ||
				cp.getNrParcelas() <= 0) {
				return "Status, Valor, Data de Vencimento e N�mero de Parcelas s�o de preenchimento obrigat�rio!";
			}
			
			if(cp.getNrParcelas() <= 0 || cp.getNrParcelas() > 20) {
				return "N�mero m�ximo de parcelas � 20!";
			}
		}else {
			return "Deve ser cadastrado uma Conta!";
		}
		return null;
	}

}
