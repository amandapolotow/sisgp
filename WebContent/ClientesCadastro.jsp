<%@page import="sisgp.dominio.Cliente"%>
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
<body class="parallax">
<%
			Cliente cli = (Cliente)request.getAttribute("cliente");

			
			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Clientes</h3>
<br>
<div class="container">
<form action="Clientes" method="post">
<input type="hidden" id="id" name="id" value="<%if(cli != null)out.print(cli.getId());%>"  />
    <div class="row">
      <div class="col-25">
        <label for="razao_social">*Razão Social</label>
      </div>
      <div class="col-75">
        <input type="text"  id="razao_social" name="razao_social" value="<%if(cli != null)out.print(cli.getRazaoSocial());%>" required />
      </div>
    </div>
        <div class="row">
      <div class="col-25">
        <label for="nome_fantasia">*Nome Fantasia</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome_fantasia" name="nome_fantasia" value="<%if(cli != null)out.print(cli.getNomeFantasia());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="cnpj">*CNPJ</label>
      </div>
      <div class="col-75">
        <input type="text"  id="cnpj" name="cnpj" value="<%if(cli != null)out.print(cli.getCnpj());%>" OnKeyPress="return mascara(event, this, '##.###.###/####-##');" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="email">*Email</label>
      </div>
      <div class="col-75">
        <input type="text"  id="email" name="email" value="<%if(cli != null)out.print(cli.getEmail());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="telefone">*Telefone</label>
      </div>
      <div class="col-75">
        <input type="text"  id="telefone" name="telefone" value="<%if(cli != null)out.print(cli.getTelefone());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="responsavel">*Responsável</label>
      </div>
      <div class="col-75">
        <input type="text"  id="responsavel" name="responsavel" value="<%if(cli != null)out.print(cli.getResponsavel());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="website">Website</label>
      </div>
      <div class="col-75">
        <input type="text"  id="website" name="website" value="<%if(cli != null && cli.getWebsite() != null){
																	out.print(cli.getWebsite());
																} else {
																	out.print("");
																}%>" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="observacao">Observação</label>
      </div>
      <div class="col-75">
        <input type="text"  id="observacao" name="observacao" value="<%if(cli != null && cli.getObservacao() != null){
        																	out.print(cli.getObservacao());
        																} else {
        																	out.print("");
        																}%>" />
      </div>
    </div>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(cli != null){ %>
  <div class="row"> 
      <input type="submit" class="submit-verde" name="operacao" id="editar" value="editar" >
  </div>
  <br>
  <div class="row"> 
      <input type="submit" class="submit-vermelho" name="operacao" id="excluir" value="excluir" >
  </div>
  <% } else { %>
  <div class="row"> 
      <input type="submit" class="submit-verde" name="operacao" id="adicionar" value="adicionar" >
  </div>
  <% } %>
</form>
</div> 

</div>

</body>
</html>