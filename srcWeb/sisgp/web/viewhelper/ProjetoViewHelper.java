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
import sisgp.dominio.Cliente;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.Projeto;
import sisgp.dominio.RecursoHumano;
import sisgp.dominio.StatusProjeto;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ProjetoViewHelper implements InterfaceViewHelper {

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		
		Projeto proj = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String id_rh_gestor = request.getParameter("id_rh_gestor");
			String id_cliente = request.getParameter("id_cliente");
			String id_status_projeto = request.getParameter("id_status_projeto");
			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");

			proj = new Projeto();
			
			if(id != null && !id.trim().equals("")) {
				proj.setId(Integer.parseInt(id));
			}
			if(id_rh_gestor != null && !id_rh_gestor.trim().equals("")) {
				Integer idGestor = Integer.parseInt(id_rh_gestor);
				proj.setRhGestor(new RecursoHumano(idGestor));
			}
			if(id_cliente != null && !id_cliente.trim().equals("")) {
				Integer idCliente = Integer.parseInt(id_cliente);
				proj.setCliente(new Cliente(idCliente));
			}
			if(id_status_projeto != null && !id_status_projeto.trim().equals("")) {
				Integer idStatus = Integer.parseInt(id_status_projeto);
				proj.setStatusProjeto(new StatusProjeto(idStatus));
			}
			if(codigo != null && !codigo.trim().equals("")) {
				proj.setCodigo(codigo);
			}
			if(nome != null && !nome.trim().equals("")) {
				proj.setNome(nome);
			}
			if(descricao != null && !descricao.trim().equals("")) {
				proj.setDescricao(descricao);
			}


			
		}else {
			
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("projeto_resultado");
			String id_projeto = request.getParameter("id");
			int id=0;
			
			if(id_projeto != null && !id_projeto.trim().equals("")){
				id = Integer.parseInt(id_projeto);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					proj = (Projeto)e;
				}
			}
		}
		return proj;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		

		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Projeto cadastrado com sucesso!");
			request.getSession().setAttribute("projeto_resultado", resultado);
			d = request.getRequestDispatcher("ProjetosLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("projeto", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ProjetosCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("projeto_resultado", resultado);
			d = request.getRequestDispatcher("ProjetosLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("projeto", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ProjetosLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("projeto_resultado", null);
			d = request.getRequestDispatcher("ProjetosLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("projeto_resultado", resultado);
				d = request.getRequestDispatcher("ProjetosLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
