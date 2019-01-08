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
import sisgp.dominio.ControleFinanceiro;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusContas;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ControleFinanceiroViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		ControleFinanceiro cf = null;
		
		if(operacao.equals("consultar")) {
			String id_projeto = request.getParameter("id_projeto");
			String id_status_conta = request.getParameter("id_status_conta");
			String dt_de = request.getParameter("dt_de");
			String dt_ate = request.getParameter("dt_ate");
			
			cf = new ControleFinanceiro();
			Date dt = null;
			
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				Integer idProj = Integer.parseInt(id_projeto);
				cf.setProjeto(new Projeto(idProj));
			}
			
			if(id_status_conta != null && !id_status_conta.trim().equals("")) {
				Integer idStatus = Integer.parseInt(id_status_conta);
				cf.setStatusContas(new StatusContas(idStatus));
			}
			
			if(dt_de != null && !dt_de.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_de);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cf.setDtDe(dt);
			}
			
			if(dt_ate != null && !dt_ate.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_ate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cf.setDtAte(dt);
			}
			
		}
		return cf;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("controle_financeiro_resultado", resultado);
			d = request.getRequestDispatcher("ControleFinanceiroLista.jsp");
		}
		if(resultado.getMensagem() != null && operacao.equals("consultar")) {
			request.getSession().setAttribute("controle_financeiro_resultado", resultado);
			d = request.getRequestDispatcher("ControleFinanceiroLista.jsp");
		}
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
