package sisgp.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
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
import sisgp.dominio.ContasPagar;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.SubAtividade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ApontamentoHorasViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		ApontamentoHoras ah = null;
		//System.out.println("Operacao: " + operacao);
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String nr_horas = request.getParameter("nr_horas");
			String nr_horas_extras = request.getParameter("nr_horas_extras");
			String data = request.getParameter("data");
			String st_pago = request.getParameter("st_pago");
			String id_sub_atividade = request.getParameter("id_sub_atividade");
			String id_contas_pagar = request.getParameter("id_contas_pagar");
			
			ah = new ApontamentoHoras();
			Date dt = null;
			
			if(id != null && !id.trim().equals("")) {
				ah.setId(Integer.parseInt(id));
			}
			
			if(nr_horas != null && !nr_horas.trim().equals("")) {
				ah.setNrHoras(Integer.parseInt(nr_horas));
			}
			
			if(nr_horas_extras != null && !nr_horas_extras.trim().equals("")) {
				ah.setNrHorasExtras(Integer.parseInt(nr_horas_extras));
			}
			
			if(data != null && !data.trim().equals("")) {
				try {
					dt = Datas.stringToDate(data);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ah.setData(dt);
			}
			
			if(st_pago != null && !st_pago.trim().equals("")) {
				ah.setStPago(Boolean.parseBoolean(st_pago));
			}
			
			if(id_sub_atividade != null && !id_sub_atividade.trim().equals("")) {
				Integer idSubAtividade = Integer.parseInt(id_sub_atividade);
				ah.setSubAtividade(new SubAtividade(idSubAtividade));
			}
			
			if(id_contas_pagar != null && !id_contas_pagar.trim().equals("")) {
				Integer idContasPagar = Integer.parseInt(id_contas_pagar);
				ah.setContasPagar(new ContasPagar(idContasPagar));
			}
		} else {
			HttpSession session = request.getSession();
			SubAtividade subAtividade = (SubAtividade) session.getAttribute("sub_atividade_apontamento");
			String id_apontamento = request.getParameter("id");
			String id_sub_atividade = request.getParameter("id_sub_atividade");
			
			Integer idApontamento=null;
			Integer idSubAtividade=null;
			
			//Quando é enviado um id de apontamento, é um editar
			if(id_apontamento != null && !id_apontamento.trim().equals("")){
				idApontamento = Integer.parseInt(id_apontamento);
				System.out.println("Primeiro if");
				for(ApontamentoHoras e: subAtividade.getListaApontamentoHoras()){
					if(e.getId() == idApontamento){
						ah = (ApontamentoHoras)e;
					}
				}

			}
			
			//Quando é enviado um id de sub atividade, é um adicionar
			if(id_sub_atividade != null && !id_sub_atividade.trim().equals("")){
				idSubAtividade = Integer.parseInt(id_sub_atividade);
				System.out.println("Segundo if");
				ah = new ApontamentoHoras();
				ah.setSubAtividade(new SubAtividade(idSubAtividade));
				
			}
			
			
		}
		return ah;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Apontamento cadastrado com sucesso!");
			request.getSession().setAttribute("apontamento_horas_resultado", resultado);
			d = request.getRequestDispatcher("ApontamentoHorasLista1.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("apontamento_horas", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ApontamentoHorasCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("apontamento_horas_resultado", resultado);
			d = request.getRequestDispatcher("ApontamentoHorasLista1.jsp");
		}	
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("apontamento_horas", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ApontamentoHorasLista1.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("apontamento_horas", null);
			d = request.getRequestDispatcher("ApontamentoHorasLista1.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("apontamento_horas_resultado", resultado);
				d = request.getRequestDispatcher("ApontamentoHorasLista1.jsp");
			}
		}
		
		d.forward(request, response);
		
	}
		

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
