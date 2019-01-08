package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Nivel;
import sisgp.web.interfaces.InterfaceViewHelper;

public class NivelViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {

		return new Nivel();
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {

		config.getServletContext().setAttribute("resultado_nivel", resultado);
		
	}

}
