<%@page import="sisgp.dominio.Projeto"%>
<%@page import="sisgp.dominio.ControleEstoque"%>
<%@page import="sisgp.dominio.Recurso"%>
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
		Resultado resultadoControleEstoque = (Resultado) session.getAttribute("controle_estoque_resultado");
		Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
		Resultado resultRecurso = (Resultado) getServletContext().getAttribute("resultado_recurso");
		
		List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
		List<EntidadeDominio> listRecursos = resultRecurso.getListaEntidade();
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="ControleEstoqueCadastro.jsp" ><button class="adicionar" >Nova Locação</button></a>
<br>
<br>
<h3>Consultar Estoque</h3>
	<br>
<div class="container">
  <form action="ControleEstoque" method="post" >
        <div class="row">
      <div class="col-25">
        <label for="id_projeto">Projetos</label>
      </div>
      <div class="col-75">
        <select  id="id_projeto" name="id_projeto" >
        	<option disabled selected >Selecione</option>
<%
	if(listProjetos != null){
		for (EntidadeDominio projeto : listProjetos){
			out.print("<option value='");
			out.print(projeto.getId());
			out.print("' >");
			out.print(((Projeto)projeto).getNome());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
        <div class="row">
      <div class="col-25">
        <label for="id_recurso">Recursos</label>
      </div>
      <div class="col-75">
        <select  id="id_recurso" name="id_recurso" >
        	<option disabled selected >Selecione</option>
<%
	if(listRecursos != null){
		for (EntidadeDominio recurso : listRecursos){
			out.print("<option value='");
			out.print(recurso.getId());
			out.print("' >");
			out.print(((Recurso)recurso).getDescricao());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="quantidade">Quantidade</label>
      </div>
      <div class="col-75">
        <input type="text"  id="quantidade" name="quantidade" OnKeyPress="return mascaraInt(event, this, '##########');" />
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
	
	if(resultadoControleEstoque !=null && resultadoControleEstoque.getMensagem() != null){
		out.print("<p>" + resultadoControleEstoque.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Recursos Alocados</h3>
	<br>
	<table>
		<tr>
			<th width="30%">Projeto</th>
			<th width="30%">Recurso</th>
			<th width="20%">Quantidade</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoControleEstoque != null){
		List<EntidadeDominio> listaEntidadesControleEstoque = resultadoControleEstoque.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		//StringBuilder sbLink2 = new StringBuilder();
		
		if(listaEntidadesControleEstoque != null){
			for(int i = 0; i < listaEntidadesControleEstoque.size(); i++){
				ControleEstoque ce = (ControleEstoque)listaEntidadesControleEstoque.get(i);
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=ControleEstoque?id_recurso=");
				sbLink1.append(ce.getRecurso().getId());
				sbLink1.append("&id_projeto=");
				sbLink1.append(ce.getProjeto().getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(ce.getProjeto().getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(ce.getRecurso().getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(ce.getQuantidade());
				sbRegistro.append("</td>");
				
							
				sbRegistro.append("<td>");
				sbRegistro.append(sbLink1.toString());				
				sbRegistro.append("</td>");
				sbRegistro.append("</tr>");
				
				out.print(sbRegistro.toString());
			}
		}
	}
		
%>
		<!-- <tr>
			<td>teste</td>
			<td>teste</td>
			<td>teste</td>
			<td>&#9745;</td>
			<td>&#9746;</td>
		</tr> -->
	</table>
	<br>
		<br>
		<br>
		<br>
	</div>
</body>
</html>