package sisgp.core.controle;

import java.util.ArrayList;
import java.util.List;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusContas;

public class testeFachada {

	public static void main(String[] args) {
		
		StatusContas sc = new StatusContas();
		ContasPagar cp = new ContasPagar();
		CategoriaContasPagar ccp = new CategoriaContasPagar();
		Projeto proj = new Projeto();
		Fachada fachada = new Fachada();
		List<EntidadeDominio> entidade = new ArrayList<EntidadeDominio>();
		Resultado rs = new Resultado();

		rs = fachada.consultar(cp);
		
		entidade = rs.getListaEntidade();
		for(int i = 0;i<entidade.size();i++) {
			System.out.println("Teste");
			System.out.println(((ContasPagar)entidade.get(i)).getId());
			System.out.println(((ContasPagar)entidade.get(i)).getValor());
			System.out.println(((ContasPagar)entidade.get(i)).getNrParcelas());

		}


	}

}
