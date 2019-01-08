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
import sisgp.dominio.CategoriaContasPagar;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusContas;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ContasPagarViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");
		ContasPagar cp = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String id_cat_contas_pagar = request.getParameter("id_cat_contas_pagar");
			String id_status_contas = request.getParameter("id_status_contas");
			String dt_lancamento = request.getParameter("dt_lancamento");
			String dt_vencimento = request.getParameter("dt_vencimento");
			String valor = request.getParameter("valor");
			String nr_parcelas = request.getParameter("nr_parcelas");
			String contrato = request.getParameter("contrato");
			String observacao = request.getParameter("observacao");
			String id_projeto = request.getParameter("id_projeto");
			
			cp = new ContasPagar();
			Date dt = null;
			
			if(id != null && !id.trim().equals("")) {
				cp.setId(Integer.parseInt(id));
			}
			
			if(id_cat_contas_pagar != null && !id_cat_contas_pagar.trim().equals("")) {
				Integer idCategoria = Integer.parseInt(id_cat_contas_pagar);
				cp.setCategoriaContasPagar(new CategoriaContasPagar(idCategoria));
			}
			
			if(id_status_contas != null && !id_status_contas.trim().equals("")) {
				Integer idStatus = Integer.parseInt(id_status_contas);
				cp.setStatusContas(new StatusContas(idStatus));
			}
			
			if(dt_lancamento != null && !dt_lancamento.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_lancamento);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cp.setDtLancamento(dt);
			}
			
			if(dt_vencimento != null && !dt_vencimento.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_vencimento);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cp.setDtVencimento(dt);
			}
			if(valor != null && !valor.trim().equals("")) {
				cp.setValor(Double.parseDouble(valor));
			}
			if(nr_parcelas != null && !nr_parcelas.trim().equals("")) {
				cp.setNrParcelas(Integer.parseInt(nr_parcelas));
			}
			if(contrato != null && !contrato.trim().equals("")) {
				cp.setContrato(contrato);
			}
			if(observacao != null && !observacao.trim().equals("")) {
				cp.setObservacao(observacao);
			}
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				Integer idProjeto = Integer.parseInt(id_projeto);
				cp.setProjeto(new Projeto(idProjeto));
			}

		}else {
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("contas_pagar_resultado");
			String id_contas_pagar = request.getParameter("id");
			int id=0;
			
			if(id_contas_pagar != null && !id_contas_pagar.trim().equals("")){
				id = Integer.parseInt(id_contas_pagar);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					cp = (ContasPagar)e;
				}
			}
		}
		return cp;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Conta cadastrada com sucesso!");
			request.getSession().setAttribute("contas_pagar_resultado", resultado);
			d = request.getRequestDispatcher("ContasPagarLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("contas_pagar", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ContasPagarCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("contas_pagar_resultado", resultado);
			d = request.getRequestDispatcher("ContasPagarLista.jsp");
		}	
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("contas_pagar", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ContasPagarLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("contas_pagar_resultado", null);
			d = request.getRequestDispatcher("ContasPagarLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("contas_pagar_resultado", resultado);
				d = request.getRequestDispatcher("ContasPagarLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
