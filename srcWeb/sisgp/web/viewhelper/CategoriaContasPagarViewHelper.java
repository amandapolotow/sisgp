package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class CategoriaContasPagarViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");
		CategoriaContasPagar ccp = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String categoria = request.getParameter("categoria");
			
			ccp = new CategoriaContasPagar();
			
			if(id != null && !id.trim().equals("")) {
				ccp.setId(Integer.parseInt(id));
				//System.out.println("Id: " + id);
			}
			
			if(categoria != null && !categoria.trim().equals("")) {
				ccp.setCategoria(categoria);
				//System.out.println("Nome: " + nome);
			}
		}else {
			//System.out.println("estou em visualizar!!");
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("categoria_contas_pagar_resultado");
			String id_cat_contas_pagar = request.getParameter("id");
			int id=0;
			
			if(id_cat_contas_pagar != null && !id_cat_contas_pagar.trim().equals("")){
				id = Integer.parseInt(id_cat_contas_pagar);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					ccp = (CategoriaContasPagar)e;
				}
			}
		}
		
		return ccp;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Categoria cadastrada com sucesso!");
			request.getSession().setAttribute("categoria_contas_pagar_resultado", resultado);
			d = request.getRequestDispatcher("CategoriaContasPagarLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("categoria_contas_pagar", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("CategoriaContasPagarCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("categoria_contas_pagar_resultado", resultado);
			d = request.getRequestDispatcher("CategoriaContasPagarLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("categoria_contas_pagar", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("CategoriaContasPagarLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("categoria_contas_pagar_resultado", null);
			d = request.getRequestDispatcher("CategoriaContasPagarLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("categoria_contas_pagar_resultado", resultado);
				d = request.getRequestDispatcher("CategoriaContasPagarLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
		
	}

}
