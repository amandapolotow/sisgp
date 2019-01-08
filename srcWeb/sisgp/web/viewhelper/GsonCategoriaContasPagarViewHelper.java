package sisgp.web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class GsonCategoriaContasPagarViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		resultado = (Resultado) session.getAttribute("grafico_categoria_contas_pagar_resultado");
		
		List<EntidadeDominio> listaGraf = resultado.getListaEntidade();
		
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter(); 
		out.println(gson.toJson(listaGraf));
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
