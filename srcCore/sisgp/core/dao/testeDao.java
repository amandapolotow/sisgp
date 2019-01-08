package sisgp.core.dao;


import java.util.List;

import sisgp.dominio.ControleEstoque;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.Recurso;


public class testeDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Recurso rec = new Recurso();
		RecursoDao recDao = new RecursoDao();
		//List<EntidadeDominio> lista = null;
		
		//rec.setId(1);
		//rec.setDescricao("COMPUTADOR");
		//rec.setQtdEstoque(8);
		
		List<EntidadeDominio> lista = recDao.consultar(rec);
		
		
	for(int i = 0;i<lista.size();i++) {
		System.out.println("Contador1: " + i);
		Recurso recurso = (Recurso)lista.get(i);
		System.out.println("Recurso:" + recurso.getDescricao());
		List<ControleEstoque> listaCe = recurso.getControleEstoque();
		
		for(int j = 0; j<listaCe.size(); j++) {
			
			System.out.println("Contador2: " + j);
			System.out.println("Projeto: " + listaCe.get(j).getProjeto().getId());
			System.out.println("Recurso: " + listaCe.get(j).getRecurso().getId());
			
		}
	}

		
		

	}

}
