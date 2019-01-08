package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ControleHorasViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		
		Projeto proj = null;
		
		if(operacao.equals("consultar")) {
			
			String id_projeto = request.getParameter("id_projeto");
			
			proj = new Projeto();
			
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				proj.setId(Integer.parseInt(id_projeto));
			}
			
		}
		return proj;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("controle_horas_resultado", resultado);
			d = request.getRequestDispatcher("ControleHorasLista.jsp");
		}
		d.forward(request, response);
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
