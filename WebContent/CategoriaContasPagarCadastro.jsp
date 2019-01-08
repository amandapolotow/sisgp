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
			CategoriaContasPagar ccp = (CategoriaContasPagar)request.getAttribute("categoria_contas_pagar");

			
			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Categoria de Contas a Pagar</h3>
<br>
<div class="container">
<form action="CategoriaContasPagar" method="post">
<input type="hidden" id="id" name="id" value="<%if(ccp != null)out.print(ccp.getId());%>"  />
    <div class="row">
      <div class="col-25">
        <label for="categoria">Categoria</label>
      </div>
      <div class="col-75">
        <input type="text"  id="categoria" name="categoria" value="<%if(ccp != null)out.print(ccp.getCategoria());%>" required />
      </div>
    </div>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(ccp != null){ %>
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