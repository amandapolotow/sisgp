<%@page import="sisgp.dominio.Cliente"%>
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
		Resultado resultadoCliente = (Resultado) session.getAttribute("cliente_resultado");

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="ClientesCadastro.jsp" ><button class="adicionar" >Adicionar Cliente</button></a>
<br>
<br>
<h3>Consultar Clientes</h3>
	<br>
<div class="container">
  <form action="Clientes" method="post" >
    <div class="row">
      <div class="col-25">
        <label for="razao_social">Razão Social</label>
      </div>
      <div class="col-75">
        <input type="text"  id="razao_social" name="razao_social" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nome_fantasia">Nome Fantasia</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome_fantasia" name="nome_fantasia" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="cnpj">CNPJ</label>
      </div>
      <div class="col-75">
        <input type="text"  id="cnpj" name="cnpj" OnKeyPress="return mascara(event, this, '##.###.###/####-##');" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="email">Email</label>
      </div>
      <div class="col-75">
        <input type="text"  id="email" name="email" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="telefone">Telefone</label>
      </div>
      <div class="col-75">
        <input type="text"  id="telefone" name="telefone" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="website">Website</label>
      </div>
      <div class="col-75">
        <input type="text"  id="website" name="website" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="responsavel">Responsável</label>
      </div>
      <div class="col-75">
        <input type="text"  id="responsavel" name="responsavel" />
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
	
	if(resultadoCliente !=null && resultadoCliente.getMensagem() != null){
		out.print("<p>" + resultadoCliente.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Categorias de Contas a Pagar</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="20%">Razão Social</th>
			<th width="20%">Email</th>
			<th width="20%">Telefone</th>
			<th width="20%">Responsável</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoCliente != null){
		List<EntidadeDominio> listaEntidadesCliente = resultadoCliente.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		
		if(listaEntidadesCliente != null){
			for(int i = 0; i < listaEntidadesCliente.size(); i++){
				Cliente cli = (Cliente)listaEntidadesCliente.get(i);
				
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=Clientes?id=");
				sbLink1.append(cli.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(cli.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(cli.getRazaoSocial());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(cli.getEmail());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(cli.getTelefone());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(cli.getResponsavel());
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