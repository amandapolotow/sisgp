package sisgp.web.interfaces;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;

public interface InterfaceViewHelper {
	
	public InterfaceEntidade getEntidade(HttpServletRequest request);
	
	public void setView (Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException;
	
	public void setView(Resultado resultado, ServletConfig config);

}
