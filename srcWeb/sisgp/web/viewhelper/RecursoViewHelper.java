package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.Recurso;
import sisgp.dominio.RecursoHumano;
import sisgp.web.interfaces.InterfaceViewHelper;

public class RecursoViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		
		Recurso recurso = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String descricao = request.getParameter("descricao");
			String qtd_estoque = request.getParameter("qtd_estoque");
			String observacao = request.getParameter("observacao");
			
			recurso = new Recurso();
			
			if(id != null && !id.trim().equals("")) {
				recurso.setId(Integer.parseInt(id));
			}
			if(descricao != null && !descricao.trim().equals("")) {
				recurso.setDescricao(descricao);
			}
			if(qtd_estoque != null && !qtd_estoque.trim().equals("")) {
				recurso.setQtdEstoque(Integer.parseInt(qtd_estoque));
			}
			if(observacao != null && !observacao.trim().equals("")) {
				recurso.setObservacao(observacao);
			}
			
		}else {
			
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("recurso_resultado");
			String id_recurso = request.getParameter("id");
			int id=0;
			
			if(id_recurso != null && !id_recurso.trim().equals("")){
				id = Integer.parseInt(id_recurso);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					recurso = (Recurso)e;
				}
			}
		}
		return recurso;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		

		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Recurso cadastrado com sucesso!");
			request.getSession().setAttribute("recurso_resultado", resultado);
			d = request.getRequestDispatcher("RecursosLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("recurso", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("RecursosCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("recurso_resultado", resultado);
			d = request.getRequestDispatcher("RecursosLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("recurso", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("RecursosLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("recurso_resultado", null);
			d = request.getRequestDispatcher("RecursosLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("recurso_resultado", resultado);
				d = request.getRequestDispatcher("RecursosLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
