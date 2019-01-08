package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.Atividade;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusAtividade;
import sisgp.dominio.StatusSistemaAtividade;
import sisgp.dominio.SubAtividade;
import sisgp.web.interfaces.InterfaceViewHelper;

public class AtividadeViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Atividade at = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			String id_projeto = request.getParameter("id_projeto");
			String id_status_atividade = request.getParameter("id_status_atividade");
			String id_st_sist_atividade = request.getParameter("id_st_sist_atividade");
			
			at = new Atividade();
			
			if(id != null && !id.trim().equals("")) {
				at.setId(Integer.parseInt(id));
			}
			
			if(codigo != null && !codigo.trim().equals("")) {
				at.setCodigo(codigo);
			}
			
			if(nome != null && !nome.trim().equals("")) {
				at.setNome(nome);
			}
			
			if(id_projeto != null && !id_projeto.trim().equals("")) {
				Integer idProj = Integer.parseInt(id_projeto);
				at.setProjeto(new Projeto(idProj));
			}
			
			if(descricao != null && !descricao.trim().equals("")) {
				at.setDescricao(descricao);
			}
			
			if(id_status_atividade != null && !id_status_atividade.trim().equals("")) {
				Integer idStatus = Integer.parseInt(id_status_atividade);
				at.setStatusAtividade(new StatusAtividade(idStatus));
			}
			
			if(id_st_sist_atividade != null && !id_st_sist_atividade.trim().equals("")) {
				Integer idStSist = Integer.parseInt(id_st_sist_atividade);
				at.setStSistAtividade(new StatusSistemaAtividade(idStSist));
			}
		}else {
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("atividade_resultado");
			String id_atividade = request.getParameter("id");
			int id=0;
			
			if(id_atividade != null && !id_atividade.trim().equals("")){
				id = Integer.parseInt(id_atividade);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					at = (Atividade)e;
				}
			}
		}
		return at;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Atividade cadastrada com sucesso!");
			request.getSession().setAttribute("atividade_resultado", resultado);
			d = request.getRequestDispatcher("AtividadesLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("atividade", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("AtividadesCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("atividade_resultado", resultado);
			d = request.getRequestDispatcher("AtividadesLista.jsp");
		}	
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("atividade", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("AtividadesLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("atividade_resultado", null);
			d = request.getRequestDispatcher("AtividadesLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("atividade_resultado", resultado);
				d = request.getRequestDispatcher("AtividadesLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
