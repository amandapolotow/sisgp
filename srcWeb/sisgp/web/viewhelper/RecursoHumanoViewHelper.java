package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.GrupoAcesso;
import sisgp.dominio.Nivel;
import sisgp.dominio.RecursoHumano;
import sisgp.web.interfaces.InterfaceViewHelper;

public class RecursoHumanoViewHelper implements InterfaceViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		RecursoHumano rh = null;
		
		
		if(!operacao.equals("visualizar")) {
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String telefone = request.getParameter("telefone");
			String cargo = request.getParameter("cargo");
			String id_nivel = request.getParameter("id_nivel");
			String id_grupo_acesso = request.getParameter("id_grupo_acesso");
			String valor_hora = request.getParameter("valor_hora");
			String valor_hora_extra = request.getParameter("valor_hora_extra");
			String observacao = request.getParameter("observacao");
			
			rh = new RecursoHumano();
			
			if(id != null && !id.trim().equals("")) {
				rh.setId(Integer.parseInt(id));
				System.out.println("Id: " + id);
			}
			
			if(nome != null && !nome.trim().equals("")) {
				rh.setNome(nome);
				System.out.println("Nome: " + nome);
			}
			if(sobrenome != null && !sobrenome.trim().equals("")) {
				rh.setSobrenome(sobrenome);
			}
			if(email != null && !email.trim().equals("")) {
				rh.setEmail(email);
			}
			if(senha != null && !senha.trim().equals("")) {
				rh.setSenha(senha);
			}
			if(telefone != null && !telefone.trim().equals("")) {
				rh.setTelefone(telefone);
			}
			if(cargo != null && !cargo.trim().equals("")) {
				rh.setCargo(cargo);
			}
			if(id_nivel != null && !id_nivel.trim().equals("")) {
				Integer idNivel = Integer.parseInt(id_nivel);
				rh.setNivel(new Nivel(idNivel));
			}
			if(id_grupo_acesso != null && !id_grupo_acesso.trim().equals("")) {
				Integer idGrupoAcesso = Integer.parseInt(id_grupo_acesso);
				rh.setGrupoAcesso(new GrupoAcesso(idGrupoAcesso));
			}
			if(valor_hora != null && !valor_hora.trim().equals("")) {
				rh.setValorHora(Double.parseDouble(valor_hora));
			}
			if(valor_hora_extra != null && !valor_hora_extra.trim().equals("")) {
				rh.setValorHoraExtra(Double.parseDouble(valor_hora_extra));
			}
			if(observacao != null && !observacao.trim().equals("")) {
				rh.setObservacao(observacao);
			}
			

		}else{
			System.out.println("estou em visualizar!!");
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("recurso_humano_resultado");
			String id_recurso_humano = request.getParameter("id");
			int id=0;
			
			if(id_recurso_humano != null && !id_recurso_humano.trim().equals("")){
				id = Integer.parseInt(id_recurso_humano);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					rh = (RecursoHumano)e;
				}
			}
		}
		return rh;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Recurso Humano cadastrado com sucesso!");
			request.getSession().setAttribute("recurso_humano_resultado", resultado);
			d = request.getRequestDispatcher("RecursosHumanosLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("recurso_humano", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("RecursosHumanosCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("recurso_humano_resultado", resultado);
			d = request.getRequestDispatcher("RecursosHumanosLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("recurso_humano", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("RecursosHumanosLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("recurso_humano_resultado", null);
			d = request.getRequestDispatcher("RecursosHumanosLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("recurso_humano_resultado", resultado);
				d = request.getRequestDispatcher("RecursosHumanosLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
