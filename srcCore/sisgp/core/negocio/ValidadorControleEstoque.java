package sisgp.core.negocio;

import java.util.List;

import sisgp.core.dao.RecursoDao;
import sisgp.core.interfaces.InterfaceStrategy;
import sisgp.dominio.ControleEstoque;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Recurso;

public class ValidadorControleEstoque implements InterfaceStrategy {

	@Override
	public String processar(InterfaceEntidade entidade) {
		if(entidade instanceof ControleEstoque) {
			ControleEstoque ce = (ControleEstoque)entidade;
			//System.out.println("Estou no validador de estoque!");
			if(ce.getRecurso().getId() == null ||
				ce.getProjeto().getId() == null ||
				ce.getQuantidade() == null) {
				return "Recurso, Projeto e Quantidade são de preenchimento obrigatório!";
			}
			
			if(ce.getRecurso().getId() == 0 ||
				ce.getProjeto().getId() == 0 ||
				ce.getQuantidade() == 0) {
				return "Recurso, Projeto e Quantidade são de preenchimento obrigatório!";
			}
			
			//pegando dados do recurso a ser alocados e outras locações já feitas desse recurso
			RecursoDao recDao = new RecursoDao();
			Recurso recConsulta = new Recurso(ce.getRecurso().getId());
			List<EntidadeDominio> listaEntidade = recDao.consultar(recConsulta);
			Recurso rec = (Recurso)listaEntidade.get(0);
			List<ControleEstoque> listaControle = rec.getControleEstoque();
			
			//pegando a quantidade total do estoque
			Integer quantidadeEstoque = rec.getQtdEstoque();
			
			//calculando quantidade total disponível
			for(int i = 0; i < listaControle.size(); i++) {
				quantidadeEstoque = quantidadeEstoque - listaControle.get(i).getQuantidade();
			}
			
			//System.out.println("Quantidade Disponivel: " + quantidadeEstoque);
			//calculando quantidade disponível menos o que irá ser alocado agora
			quantidadeEstoque = quantidadeEstoque - ce.getQuantidade();
			
			if(quantidadeEstoque < 0) {
				return "Estoque insuficiente!";
			}
			
			
		}else {
			return "Deve ser cadastrado um Controle de Estoque!";
		}
		return null;
	}

}
