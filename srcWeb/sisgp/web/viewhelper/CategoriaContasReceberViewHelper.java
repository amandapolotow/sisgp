package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class CategoriaContasReceberViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		CategoriaContasReceber ccr = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String categoria = request.getParameter("categoria");
			
			ccr = new CategoriaContasReceber();
			
			if(id != null && !id.trim().equals("")) {
				ccr.setId(Integer.parseInt(id));
				//System.out.println("Id: " + id);
			}
			
			if(categoria != null && !categoria.trim().equals("")) {
				ccr.setCategoria(categoria);
				//System.out.println("Nome: " + nome);
			}
		}else {
			//System.out.println("estou em visualizar!!");
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("categoria_contas_receber_resultado");
			String id_cat_contas_receber = request.getParameter("id");
			int id=0;
			
			if(id_cat_contas_receber != null && !id_cat_contas_receber.trim().equals("")){
				id = Integer.parseInt(id_cat_contas_receber);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					ccr = (CategoriaContasReceber)e;
				}
			}
		}
		
		return ccr;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Categoria cadastrada com sucesso!");
			request.getSession().setAttribute("categoria_contas_receber_resultado", resultado);
			d = request.getRequestDispatcher("CategoriaContasReceberLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("categoria_contas_receber", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("CategoriaContasReceberCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("categoria_contas_receber_resultado", resultado);
			d = request.getRequestDispatcher("CategoriaContasReceberLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("categoria_contas_receber", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("CategoriaContasReceberLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("categoria_contas_receber_resultado", null);
			d = request.getRequestDispatcher("CategoriaContasReceberLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("categoria_contas_receber_resultado", resultado);
				d = request.getRequestDispatcher("CategoriaContasReceberLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
