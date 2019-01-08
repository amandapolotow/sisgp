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
<body class="parallax">
<%
			CategoriaContasReceber ccr = (CategoriaContasReceber)request.getAttribute("categoria_contas_receber");
			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Categoria de Contas a Receber</h3>
<br>
<div class="container">
<form action="CategoriaContasReceber" method="post">
<input type="hidden" id="id" name="id" value="<%if(ccr != null)out.print(ccr.getId());%>"  />
    <div class="row">
      <div class="col-25">
        <label for="categoria">Categoria</label>
      </div>
      <div class="col-75">
        <input type="text"  id="categoria" name="categoria" value="<%if(ccr != null)out.print(ccr.getCategoria());%>" required />
      </div>
    </div>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(ccr != null){ %>
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