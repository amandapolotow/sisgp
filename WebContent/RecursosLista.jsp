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
		Resultado resultadoRecursos = (Resultado) session.getAttribute("recurso_resultado");

		System.out.println("teste1");
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="RecursosCadastro.jsp" ><button class="adicionar" >Adicionar Recurso</button></a>
<br>
<br>
<h3>Consultar Recursos</h3>
	<br>
<div class="container">
  <form action="Recursos" method="post" >
    <div class="row">
      <div class="col-25">
        <label for="descricao">Descrição</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="qtd_estoque">Quantidade em Estoque</label>
      </div>
      <div class="col-75">
        <input type="text"  id="qtd_estoque" name="qtd_estoque" OnKeyPress="return mascaraInt(event, this, '##########');" />
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
	
	if(resultadoRecursos !=null && resultadoRecursos.getMensagem() != null){
		out.print("<p>" + resultadoRecursos.getMensagem() + "</p>");
		System.out.println("teste2");
	}
	
	
%>
<br>
	<h3>Lista de Recursos</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="40%">Descrição</th>
			<th width="20%">Qtd. Estoque</th>
			<th width="20%">Qtd. Disponível</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoRecursos != null){
		List<EntidadeDominio> listaEntidadesRecurso = resultadoRecursos.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		
		System.out.println("teste3");
		if(listaEntidadesRecurso != null){
			for(int i = 0; i < listaEntidadesRecurso.size(); i++){
				Recurso rec = (Recurso)listaEntidadesRecurso.get(i);
				System.out.println("teste4");
				List<ControleEstoque> listaControle = rec.getControleEstoque();
				
				Integer qtdDisponivel = rec.getQtdEstoque();
				
				if(listaControle !=null){
					for(int j = 0; j<listaControle.size(); j++){
						qtdDisponivel = qtdDisponivel - listaControle.get(j).getQuantidade();
					}
				}
								
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=Recursos?id=");
				sbLink1.append(rec.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(rec.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(rec.getDescricao());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(rec.getQtdEstoque());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(qtdDisponivel);
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