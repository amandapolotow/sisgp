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
import sisgp.dominio.CategoriaContasReceber;
import sisgp.dominio.ContasPagar;
import sisgp.dominio.ContasReceber;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.StatusContas;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ContasReceberViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		ContasReceber cr = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String id_cat_contas_receber = request.getParameter("id_cat_contas_receber");
			String id_status_contas = request.getParameter("id_status_contas");
			String dt_lancamento = request.getParameter("dt_lancamento");
			String dt_vencimento = request.getParameter("dt_vencimento");
			String valor = request.getParameter("valor");
			String nr_parcelas = request.getParameter("nr_parcelas");
			String contrato = request.getParameter("contrato");
			String observacao = request.getParameter("observacao");
			String id_projeto = request.getParameter("id_projeto");
			
			cr = new ContasReceber();
			Date dt = null;
			
			if(id != null && !id.trim().equals("")) {
				cr.setId(Integer.parseInt(id));
			}
			
			if(id_cat_contas_receber != null && !id_cat_contas_receber.trim().equals("")) {
				Integer idCategoria = Integer.parseInt(id_cat_contas_receber);
				cr.setCategoriaContasReceber(new CategoriaContasReceber(idCategoria));
			}
			
			if(id_status_contas != null && !id_status_contas.trim().equals("")) {
				Integer idStatus = Integer.parseInt(id_status_contas);
				cr.setStatusContas(new StatusContas(idStatus));
			}
			
			if(dt_lancamento != null && !dt_lancamento.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_lancamento);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cr.setDtLancamento(dt);
			}
			
			if(dt_vencimento != null && !dt_vencimento.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_vencimento);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cr.setDtVencimento(dt);
			}
			if(valor != null && !valor.trim().equals("")) {
				cr.setValor(Double.parseDouble(valor));
			}
			if(nr_parcelas != null && !nr_parcelas.trim().equals("")) {
				cr.setNrParcelas(Integer.parseInt(nr_parcelas));
			}
			if(contrato != null && !contrato.trim().equals("")) {
				cr.setContrato(contrato);
			}
			if(observacao != null && !observacao.trim().equals("")) {
				cr.setObservacao(observacao);
			}
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				Integer idProjeto = Integer.parseInt(id_projeto);
				cr.setProjeto(new Projeto(idProjeto));
			}

		}else {
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("contas_receber_resultado");
			String id_contas_receber = request.getParameter("id");
			int id=0;
			
			if(id_contas_receber != null && !id_contas_receber.trim().equals("")){
				id = Integer.parseInt(id_contas_receber);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					cr = (ContasReceber)e;
				}
			}
		}
		return cr;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Conta cadastrada com sucesso!");
			request.getSession().setAttribute("contas_receber_resultado", resultado);
			d = request.getRequestDispatcher("ContasReceberLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("contas_receber", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ContasReceberCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("contas_receber_resultado", resultado);
			d = request.getRequestDispatcher("ContasReceberLista.jsp");
		}	
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("contas_receber", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ContasReceberLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("contas_receber_resultado", null);
			d = request.getRequestDispatcher("ContasReceberLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("contas_receber_resultado", resultado);
				d = request.getRequestDispatcher("ContasReceberLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
