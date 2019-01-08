package sisgp.core.negocio;

import java.text.ParseException;

import sisgp.core.util.Datas;
import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.SubAtividade;

public class TesteStrategy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApontamentoHoras apont = new ApontamentoHoras();
		ValidadorApontamentoHoras vah = new ValidadorApontamentoHoras();
		
		try {
			apont.setData(Datas.stringToDate("03/11/2018"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Data" + apont.getData());
		
		apont.setNrHoras(3);
		apont.setSubAtividade(new SubAtividade(1));
		
		vah.processar(apont);

	}

}
