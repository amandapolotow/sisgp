package sisgp.web.viewhelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sisgp.core.aplicacao.Resultado;
import sisgp.dominio.Cliente;
import sisgp.dominio.EntidadeDominio;
import sisgp.dominio.InterfaceEntidade;
import sisgp.dominio.RecursoHumano;
import sisgp.web.interfaces.InterfaceViewHelper;

public class ClienteViewHelper implements InterfaceViewHelper{

	@Override
	public InterfaceEntidade getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cliente cli = null;
		
		if(!operacao.equals("visualizar")) {
			String id = request.getParameter("id");
			String razao_social = request.getParameter("razao_social");
			String nome_fantasia = request.getParameter("nome_fantasia");
			String cnpj = request.getParameter("cnpj");
			String email = request.getParameter("email");
			String telefone = request.getParameter("telefone");
			String website = request.getParameter("website");
			String responsavel = request.getParameter("responsavel");
			String observacao = request.getParameter("observacao");
			
			cli = new Cliente();
			
			if(id != null && !id.trim().equals("")) {
				cli.setId(Integer.parseInt(id));
				System.out.println("Id: " + id);
			}
			
			if(razao_social != null && !razao_social.trim().equals("")) {
				cli.setRazaoSocial(razao_social);
			}
			
			if(nome_fantasia != null && !nome_fantasia.trim().equals("")) {
				cli.setNomeFantasia(nome_fantasia);
			}
			
			if(cnpj != null && !cnpj.trim().equals("")) {
				cli.setCnpj(cnpj);
			}
			
			if(email != null && !email.trim().equals("")) {
				cli.setEmail(email);
			}
			
			if(telefone != null && !telefone.trim().equals("")) {
				cli.setTelefone(telefone);
			}
			
			if(website != null && !website.trim().equals("")) {
				cli.setWebsite(website);
			}
			
			if(responsavel != null && !responsavel.trim().equals("")) {
				cli.setResponsavel(responsavel);
			}
			
			if(observacao != null && !observacao.trim().equals("")) {
				cli.setObservacao(observacao);
			}
			
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("cliente_resultado");
			String id_cliente = request.getParameter("id");
			int id=0;
			
			if(id_cliente != null && !id_cliente.trim().equals("")){
				id = Integer.parseInt(id_cliente);
			}
			
			for(EntidadeDominio e: resultado.getListaEntidade()){
				if(e.getId() == id){
					cli = (Cliente)e;
				}
			}
		}
		
		return cli;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMensagem() == null && operacao.equals("adicionar")) {
			resultado.setMensagem("Cliente cadastrado com sucesso!");
			request.getSession().setAttribute("cliente_resultado", resultado);
			d = request.getRequestDispatcher("ClientesLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("visualizar")) {
			request.setAttribute("cliente", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ClientesCadastro.jsp");
		}
		if(resultado.getMensagem() == null && operacao.equals("consultar")) {
			request.getSession().setAttribute("cliente_resultado", resultado);
			d = request.getRequestDispatcher("ClientesLista.jsp");
		}
		
		
		if(resultado.getMensagem() == null && operacao.equals("editar")) {
			request.setAttribute("cliente", resultado.getListaEntidade().get(0));
			d = request.getRequestDispatcher("ClientesLista.jsp");
		}
		
		if(resultado.getMensagem() == null && operacao.equals("excluir")) {
			request.getSession().setAttribute("cliente_resultado", null);
			d = request.getRequestDispatcher("ClientesLista.jsp");
		}
		
		if(resultado.getMensagem() != null) {
			if(operacao.equals("adicionar") || operacao.equals("editar")) {
				request.getSession().setAttribute("cliente_resultado", resultado);
				d = request.getRequestDispatcher("ClientesLista.jsp");
			}
		}
		
		d.forward(request, response);
		
	}

	@Override
	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
