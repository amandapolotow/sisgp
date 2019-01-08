<%@page import="sisgp.core.util.Datas"%>
<%@page import="sisgp.dominio.CategoriaContasPagar"%>
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
		Resultado resultadoCatContasPagar = (Resultado) session.getAttribute("categoria_contas_pagar_resultado");

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="CategoriaContasPagarCadastro.jsp" ><button class="adicionar" >Adicionar Categoria</button></a>
<br>
<br>
<h3>Consultar Categoria Contas a Pagar</h3>
	<br>
<div class="container">
  <form action="CategoriaContasPagar" method="post" >
    <div class="row">
      <div class="col-25">
        <label for="categoria">Categoria</label>
      </div>
      <div class="col-75">
        <input type="text"  id="categoria" name="categoria" />
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
	
	if(resultadoCatContasPagar !=null && resultadoCatContasPagar.getMensagem() != null){
		out.print("<p>" + resultadoCatContasPagar.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Categorias de Contas a Pagar</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="50%">Categoria</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoCatContasPagar != null){
		List<EntidadeDominio> listaEntidadesCatContasPagar = resultadoCatContasPagar.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		
		if(listaEntidadesCatContasPagar != null){
			for(int i = 0; i < listaEntidadesCatContasPagar.size(); i++){
				CategoriaContasPagar ccp = (CategoriaContasPagar)listaEntidadesCatContasPagar.get(i);
				
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=CategoriaContasPagar?id=");
				sbLink1.append(ccp.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(ccp.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(ccp.getCategoria());
				sbRegistro.append("</td>");
				
							
				sbRegistro.append("<td>");
				sbRegistro.append(sbLink1.toString());				
				sbRegistro.append("</td>");
				
				
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