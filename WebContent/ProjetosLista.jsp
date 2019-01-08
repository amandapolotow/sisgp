<%@page import="sisgp.dominio.StatusProjeto"%>
<%@page import="sisgp.dominio.Cliente"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.dominio.Projeto"%>
<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css.css">
<title>SisGp</title>
</head>
<body class="parallax" >
	<%
		Resultado resultado = (Resultado) session.getAttribute("projeto_resultado");
	
	Resultado resultGestor = (Resultado)getServletContext().getAttribute("resultado_gestor");
	Resultado resultCliente = (Resultado)getServletContext().getAttribute("resultado_cliente");
	Resultado resultStatus = (Resultado)getServletContext().getAttribute("resultado_status_projeto");
	
	List<EntidadeDominio> listaGestor = resultGestor.getListaEntidade();
	List<EntidadeDominio> listaCliente = resultCliente.getListaEntidade();
	List<EntidadeDominio> listaStatus = resultStatus.getListaEntidade();
	%>
<%@ include file="menu.jsp" %>
<div class="content">
<br>
<a href="ProjetosCadastro.jsp" ><button class="adicionar" name="adicionar" id="adicionar" >Adicionar Projeto</button></a>
<br>
<br>
<h3>Consultar Projetos</h3>
	<br>
<div class="container">
  <form action="Projetos" method="post" >
  <div class="row">
      <div class="col-25">
        <label for="id_rh_gestor">Gestor</label>
      </div>
      <div class="col-75">
        <select  id="id_rh_gestor" name="id_rh_gestor" >
        <option disabled selected >Selecione</option>
<%
	if(listaGestor != null){
		for (EntidadeDominio gestor : listaGestor){
			out.print("<option value='");
			out.print(gestor.getId());
			out.print("' >");
			out.print(((RecursoHumano)gestor).getNome());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
	<div class="row">
      <div class="col-25">
        <label for="id_cliente">Cliente</label>
      </div>
      <div class="col-75">
        <select  id="id_cliente" name="id_cliente" >
        <option disabled selected >Selecione</option>
<%
	if(listaCliente != null){
		for (EntidadeDominio cliente : listaCliente){
			out.print("<option value='");
			out.print(cliente.getId());
			out.print("' >");
			out.print(((Cliente)cliente).getRazaoSocial());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="codigo">Código</label>
      </div>
      <div class="col-75">
        <input type="text"  id="codigo" name="codigo" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nome">Nome</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome" name="nome" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="descricao">Descricão</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao" />
      </div>
    </div>
	<div class="row">
      <div class="col-25">
        <label for="id_status_projeto">Status</label>
      </div>
      <div class="col-75">
        <select  id="id_status_projeto" name="id_status_projeto" >
        <option disabled selected >Selecione</option>
<%
	if(listaStatus != null){
		for (EntidadeDominio status : listaStatus){
			out.print("<option value='");
			out.print(status.getId());
			out.print("' >");
			out.print(((StatusProjeto)status).getStatusProjeto());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="consultar" name="operacao">
    </div>
  </form>
</div>
<br>
<%
	
	if(resultado !=null && resultado.getMensagem() != null){
		out.print("<p>" + resultado.getMensagem() + "</p>");
	}
%>
	<h3>Lista de Projetos</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="10%">Código</th>
			<th width="40%">Nome Projeto</th>
			<!-- <th width="10%">Atividades</th>
			<th width="10%">Recursos</th> -->
			<th width="10%">Editar</th>
		</tr>
		<%

	if(resultado != null){
		List<EntidadeDominio> listaEntidades = resultado.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		StringBuilder sbLink3 = new StringBuilder();
		
		if(listaEntidades != null){
			for(int i = 0; i < listaEntidades.size(); i++){
				Projeto proj = (Projeto)listaEntidades.get(i);
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				sbLink2.setLength(0);
				sbLink3.setLength(0);
				
				sbLink1.append("<a href=Projetos?id=");
				sbLink1.append(proj.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				/*sbLink2.append("<a href=ProjetosAtividades?id=");
				sbLink2.append(proj.getId());
				sbLink2.append("&operacao=consultar>&#9745;</a>");
				
				sbLink3.append("<a href=ProjetosRecursos?id=");
				sbLink3.append(proj.getId());
				sbLink3.append("&operacao=consultar>&#9745;</a>");*/
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(proj.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(proj.getCodigo());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(proj.getNome());
				sbRegistro.append("</td>");

				
							
				/*sbRegistro.append("<td>");
				sbRegistro.append(sbLink3.toString());				
				sbRegistro.append("</td>");
				
				sbRegistro.append("<td>");
				sbRegistro.append(sbLink2.toString());	
				sbRegistro.append("</td>");*/
				
				sbRegistro.append("<td>");
				sbRegistro.append(sbLink1.toString());	
				sbRegistro.append("</td>");
				
				sbRegistro.append("</tr>");
				
				out.print(sbRegistro.toString());
			}
		}
	}
		
%>
	</table>
	<br>
		<br>
		<br>
		<br>
	</div>
</body>
</html>