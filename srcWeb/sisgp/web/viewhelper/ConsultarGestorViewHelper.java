package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.GrupoAcesso;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.RecursoHumano;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ConsultarGestorViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RecursoHumano rh = new RecursoHumano();
		Integer idGrupo = 2;//grupo de acesso de gestores
		GrupoAcesso ga = new GrupoAcesso(idGrupo);
		rh.setGrupoAcesso(ga);
		
		return rh;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		config.getServletContext().setAttribute("resultado_gestor", resultado);
		
	}

}
