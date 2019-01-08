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
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusAtividade;
import sisgp.dominio.StatusSistemaAtividade;
import sisgp.dominio.SubAtividade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class SubAtividadeViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		SubAtividade sat = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			String nr_horas_previsto = request.getParameter("nr_horas_previsto");
			String id_recurso_humano = request.getParameter("id_recurso_humano");
			String id_atividade = request.getParameter("id_atividade");
			String id_status_atividade = request.getParameter("id_status_atividade");
			String id_st_sist_atividade = request.getParameter("id_st_sist_atividade");
			String dt_inicio = request.getParameter("dt_inicio");
			String dt_fim = request.getParameter("dt_fim");
			
			sat = new SubAtividade();
			Date dt = null;
			
			if(id != null && !id.trim().equals("")) {
				sat.setId(Integer.parseInt(id));
			}
			
			if(id_atividade != null && !id_atividade.trim().equals("")) {
				Integer idAtividade = Integer.parseInt(id_atividade);
				sat.setAtividade(new Atividade(idAtividade));
			}
			
			if(codigo != null && !codigo.trim().equals("")) {
				sat.setCodigo(codigo);
			}
			
			if(nome != null && !nome.trim().equals("")) {
				sat.setNome(nome);
			}
			
			if(descricao != null && !descricao.trim().equals("")) {
				sat.setDescricao(descricao);
			}
			
			if(nr_horas_previsto != null && !nr_horas_previsto.trim().equals("")) {
				sat.setNrHorasPrevisto(Integer.parseInt(nr_horas_previsto));
			}
			
			if(id_recurso_humano != null && !id_recurso_humano.trim().equals("")) {
				Integer idRH = Integer.parseInt(id_recurso_humano);
				sat.setRecursoHumano(new RecursoHumano(idRH));
			}
			
			if(id_status_atividade != null && !id_status_atividade.trim().equals("")) {
				Integer idStatus = Integer.parseInt(id_status_atividade);
				sat.setStatusAtividade(new StatusAtividade(idStatus));
			}
			
			if(id_st_sist_atividade != null && !id_st_sist_atividade.trim().equals("")) {
				Integer idStSist = Integer.parseInt(id_st_sist_atividade);
				sat.setStSistAtividade(new StatusSistemaAtividade(idStSist));
			}
			
			if(dt_inicio != null && !dt_inicio.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_inicio);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sat.setDtInicio(dt);
			}
			
			if(dt_fim != null && !dt_fim.trim().equals("")) {
				try {
					dt = Datas.stringToDate(dt_fim);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sat.setDtFim(dt);
			}
			
		}else {
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("sub_atividade_resultado");
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
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Subatividade cadastrada com sucesso!");
			request.getSession().setAttribute("sub_atividade_resultado", resultado);
			d = request.getRequestDispatcher("SubAtividadesLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("sub_atividade", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("SubAtividadesCadastro.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("sub_atividade_resultado", resultado);
			d = request.getRequestDispatcher("SubAtividadesLista.jsp");
		}	
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("sub_atividade", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("SubAtividadesLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("sub_atividade_resultado", null);
			d = request.getRequestDispatcher("SubAtividadesLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("sub_atividade_resultado", resultado);
				d = request.getRequestDispatcher("SubAtividadesLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
