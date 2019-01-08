package sisgp.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.core.util.Datas;
import sisgp.dominio.Atividade;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusAtividade;
import sisgp.dominio.StatusSistemaAtividade;
import sisgp.dominio.SubAtividade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ApontamentoHorasLista1ViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		SubAtividade sat = null;
		
		if(!operacao.equals("visualizar")) {

			String id_recurso_humano = request.getParameter("id_recurso_humano");

			
			sat = new SubAtividade();
			
			if(id_recurso_humano != null && !id_recurso_humano.trim().equals("")) {
				Integer idRH = Integer.parseInt(id_recurso_humano);
				sat.setRecursoHumano(new RecursoHumano(idRH));
			}
			
			sat.setStatusAtividade(new StatusAtividade(2));
			
			
		}else {
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado_rh_apontamento");
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
		}
		
		return sat;
	
	}
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.getSession().setAttribute("sub_atividade_apontamento", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ApontamentoHorasLista2.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("resultado_rh_apontamento", resultado);
			d = request.getRequestDispatcher("ApontamentoHorasLista1.jsp");
		}
		
		
		d.forward(request, response);
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
