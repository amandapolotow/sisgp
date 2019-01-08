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
<body class="parallax">
<%
			Recurso rec = (Recurso)request.getAttribute("recurso");

			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Recursos</h3>
<br>
<div class="container">
<form action="Recursos" method="post">
<input type="hidden" id="id" name="id" value="<%if(rec != null)out.print(rec.getId());%>"  />
    <div class="row">
      <div class="col-25">
        <label for="descricao">*Descrição</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao" value="<%if(rec != null)out.print(rec.getDescricao());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="qtd_estoque">*Quantidade em Estoque</label>
      </div>
      <div class="col-75">
        <input type="text"  id="qtd_estoque" name="qtd_estoque" value="<%if(rec != null)out.print(rec.getQtdEstoque());%>" OnKeyPress="return mascaraInt(event, this, '##########');" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="observacao">Observação</label>
      </div>
      <div class="col-75">
        <input type="text"  id="observacao" name="observacao" value="<%if(rec != null && rec.getObservacao() != null){
        																	out.print(rec.getObservacao());
        																}else{
        																	out.print("");
        																}%>" />
      </div>
    </div>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(rec != null){ %>
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