package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Login;
import sisgp.web.interfaces.InterfaceViewHelper;

public class LoginViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Login login = null;
		
		if(operacao.equals("consultar")) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			login = new Login();
			
			if(email != null && !email.trim().equals("")) {
				login.setEmail(email);
			}
			
			if(senha != null && !senha.trim().equals("")) {
				login.setSenha(senha);
			}
		}
		return login;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		Login login = null;
		
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			HttpSession session = request.getSession();
			
			login = (Login)resultado.getListaEntidade().get(0);
			
			if(login.getFlgNaoEncontrado()) {
				resultado.setMensagem("Cadastro não encontrado!");
				session.setAttribute("login_resultado", resultado);
				d = request.getRequestDispatcher("Index.jsp");
			}else {
				session.setAttribute("login", resultado.getListaEntidade().get(0));
				d = request.getRequestDispatcher("Home.jsp");
			}
			
		}
		
		d.forward(request, response);
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
