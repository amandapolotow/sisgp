package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.StatusAtividade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class StatusAtividadeViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new StatusAtividade();
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		config.getServletContext().setAttribute("resultado_status_atividade", resultado);
	}

}
