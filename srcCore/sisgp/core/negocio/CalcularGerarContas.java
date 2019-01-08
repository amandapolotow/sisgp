package sisgp.core.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.core.util.Datas;
import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.GerarContas;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusContas;

public class CalcularGerarContas implements InterfaceStrategy {

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof GerarContas) {
			GerarContas gc = (GerarContas)entidade;
			
			System.out.println("Estou no Validador");
			
			RecursoHumano rh = gc.getRecursoHumano();
			List<ApontamentoHoras> listaAh = rh.getListaApontamentoHoras();
			
			ContasPagar cp = new ContasPagar();
			Double valorHoraTotal = 0.0;
			Double valorHoraExtraTotal = 0.0;
			
			for(int i = 0; i < listaAh.size(); i++ ) {
				valorHoraTotal += (rh.getValorHora() * listaAh.get(i).getNrHoras());
				valorHoraExtraTotal += (rh.getValorHoraExtra() * listaAh.get(i).getNrHorasExtras());
			}
			
			//Calendar c = Calendar.getInstance();
			//c.add(Calendar.DATE, +3);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String date = sdf.format(new Date()); 
			System.out.println(date);
			
			
			
			cp.setValor(valorHoraTotal + valorHoraExtraTotal);
			//cp.setDtVencimento(c.getTime());
			try {
				cp.setDtVencimento(Datas.stringToDate(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cp.setNrParcelas(1);
			cp.setStatusContas(new StatusContas(2));// Status 2 = em aberto
			cp.setCategoriaContasPagar(new CategoriaContasPagar(1));//Categoria 1 = PROJETO (categoria padrão no sistema)
			if(gc.getProjeto() != null) {
				cp.setProjeto(gc.getProjeto());
			}			
			cp.setObservacao("Pagamento gerado pelo sistema. Id " + rh.getId() + "/Nome: " + rh.getNome() + " " + rh.getSobrenome());
			
			gc.setContasPagar(cp);
		}
		return null;
	}

}
