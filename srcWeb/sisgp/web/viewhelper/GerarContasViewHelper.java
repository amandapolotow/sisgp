package sisgp.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.core.util.Datas;
import sisgp.dominio.ApontamentoHoras;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GerarContas;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.web.interfaces.InterfaceViewHelper;

public class GerarContasViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");		
		GerarContas gc = null;
		
		if(operacao.equals("consultar")) {
			
			
			String id_projeto = request.getParameter("id_projeto");
			String id_recurso_humano = request.getParameter("id_recurso_humano");
			String dt_de = request.getParameter("dt_de");
			String dt_ate = request.getParameter("dt_ate");
			
			gc = new GerarContas();

			Date dt = null;
			
			if(id_recurso_humano != null && !id_recurso_humano.trim().equals("")) {
				Integer idRH = Integer.parseInt(id_recurso_humano);
				gc.setRecursoHumano(new RecursoHumano(idRH));
			}
			
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				Integer idProj = Integer.parseInt(id_projeto);
				gc.setProjeto(new Projeto(idProj));
			}
			
			if(dt_de != null && !dt_de.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_de);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				gc.setDtDe(dt);
			}
			
			if(dt_ate != null && !dt_ate.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_ate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				gc.setDtAte(dt);
			}
			
			
		}else if(operacao.equals("adicionar")){
			
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("gerar_contas_resultado");
						
			gc = new GerarContas();
			
			gc = (GerarContas)resultado.getListaEntidade().get(0);
			
		}
		
		
		
		return gc;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Conta cadastrada com sucesso!");
			request.getSession().setAttribute("gerar_contas_resultado", resultado);
			d = request.getRequestDispatcher("GerarContasLista.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("gerar_contas_resultado", resultado);
			d = request.getRequestDispatcher("GerarContasLista.jsp");
		}
		if(resultado.getMensagem() != null && operacao.equals("consultar")) {
			request.getSession().setAttribute("gerar_contas_resultado", resultado);
			d = request.getRequestDispatcher("GerarContasLista.jsp");
		}
		d.forward(request, response);
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
