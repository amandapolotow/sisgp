<%@page import="sisgp.dominio.CategoriaContasReceber"%>
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
		Resultado resultadoCatContasReceber = (Resultado) session.getAttribute("categoria_contas_receber_resultado");
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="CategoriaContasReceberCadastro.jsp" ><button class="adicionar" >Adicionar Categoria</button></a>
<br>
<br>
<h3>Consultar Categoria de Contas a Receber</h3>
	<br>
<div class="container">
  <form action="CategoriaContasReceber" method="post" >
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
	
	if(resultadoCatContasReceber !=null && resultadoCatContasReceber.getMensagem() != null){
		out.print("<p>" + resultadoCatContasReceber.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Categorias de Contas a Receber</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="50%">Categoria</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoCatContasReceber != null){
		List<EntidadeDominio> listaEntidadesCatContasReceber = resultadoCatContasReceber.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		String dtVencimento = "";
		
		if(listaEntidadesCatContasReceber != null){
			for(int i = 0; i < listaEntidadesCatContasReceber.size(); i++){
				CategoriaContasReceber ccr = (CategoriaContasReceber)listaEntidadesCatContasReceber.get(i);
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=CategoriaContasReceber?id=");
				sbLink1.append(ccr.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(ccr.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(ccr.getCategoria());
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