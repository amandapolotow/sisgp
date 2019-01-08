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
import sisgp.dominio.SubAtividade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ApontamentoHorasLista2ViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		
		SubAtividade sat = new SubAtividade();
		
		HttpSession session = request.getSession();
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		String id_sub_atividade = request.getParameter("id");
		int id=0;
		
		if(id_sub_atividade != null && !id_sub_atividade.trim().equals("")){
			id = Integer.parseInt(id_sub_atividade);
		}
		
		for(EntidadeDominio e: resultado.getListaEntidade()){
			if(e.getId() == id){
				sat = (SubAtividade)e;
			}
		}
		return sat;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		
		request.getSession().setAttribute("sub_atividade_apontamento", resultado.getListaEntidade().get(0));
		d = request.getRequestDispatcher("ApontamentoHorasLista2.jsp");
		d.forward(request, response);
	}
	
	

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
