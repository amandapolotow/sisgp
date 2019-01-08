package sisgp.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sisgp.core.aplicacao.Resultado;
import sisgp.core.util.Datas;
import sisgp.dominio.GraficoContasPagar;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.web.interfaces.InterfaceViewHelper;

public class GraficoContasPagarViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		GraficoContasPagar graf = null;
		
		//System.out.println("getEntidade: " + operacao);
		
		if(operacao != null && operacao.equals("consultar")) {
			//String ano = request.getParameter("ano");
			
			String dt_de = request.getParameter("dt_de");
			String dt_ate = request.getParameter("dt_ate");
			String id_projeto = request.getParameter("id_projeto");
			
			Date dt = null;
			graf = new GraficoContasPagar();
			
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				graf.setProjeto(new Projeto(id_projeto));
			}
			
			if(dt_de != null && !dt_de.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_de);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				graf.setDtDe(dt);
			}
			
			if(dt_ate != null && !dt_ate.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_ate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				graf.setDtAte(dt);
			}
			
		}
		
		return graf;
		
		//Projeto p = new Projeto();
		
		//return p;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		RequestDispatcher d = request.getRequestDispatcher("GraficoContasPagar.jsp");
		String operacao = request.getParameter("operacao");
		
		//System.out.println("setView: " + operacao);
		
		if(resultado != null && operacao.equals("consultar")){
			//System.out.println("If: resultado não-nulo");
			request.getSession().setAttribute("grafico_contas_pagar_resultado", resultado);
			d.forward(request, response);
		}
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
