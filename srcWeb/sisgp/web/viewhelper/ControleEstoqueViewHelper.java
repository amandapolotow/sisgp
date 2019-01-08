package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.ControleEstoque;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.Recurso;
import sisgp.dominio.RecursoHumano;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ControleEstoqueViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		
		ControleEstoque ce = null;
		
		if(!operacao.equals("visualizar")) {
			String id_projeto = request.getParameter("id_projeto");
			String id_recurso = request.getParameter("id_recurso");
			String quantidade = request.getParameter("quantidade");
			
			ce = new ControleEstoque();
			
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				Integer idProjeto = Integer.parseInt(id_projeto);
				ce.setProjeto(new Projeto(idProjeto));
			}
			if(id_recurso != null && !id_recurso.trim().equals("")) {
				Integer idRecurso = Integer.parseInt(id_recurso);
				ce.setRecurso(new Recurso(idRecurso));
			}
			if(quantidade != null && !quantidade.trim().equals("")) {
				ce.setQuantidade(Integer.parseInt(quantidade));
			}
			
			//System.out.println("Controle: " + ce.getProjeto().getId());
			
		}else {
			
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("controle_estoque_resultado");
			String id_recurso = request.getParameter("id_recurso");
			String id_projeto = request.getParameter("id_projeto");
			int idRecurso=0;
			int idProjeto=0;
			
			if(id_recurso != null && !id_recurso.trim().equals("")){
				idRecurso = Integer.parseInt(id_recurso);
			}
			
			if(id_projeto != null && !id_projeto.trim().equals("")){
				idProjeto = Integer.parseInt(id_projeto);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(((ControleEstoque)e).getProjeto().getId() == idProjeto && ((ControleEstoque)e).getRecurso().getId() == idRecurso){
					ce = (ControleEstoque)e;
				}
			}
		}
		return ce;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Recurso alocado com sucesso!");
			request.getSession().setAttribute("controle_estoque_resultado", resultado);
			d = request.getRequestDispatcher("ControleEstoqueLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("controle_estoque", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ControleEstoqueCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("controle_estoque_resultado", resultado);
			d = request.getRequestDispatcher("ControleEstoqueLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("controle_estoque", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ControleEstoqueLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("controle_estoque_resultado", null);
			d = request.getRequestDispatcher("ControleEstoqueLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("controle_estoque_resultado", resultado);
				d = request.getRequestDispatcher("ControleEstoqueLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
