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
<body class="parallax">
<%
		ControleEstoque ce = (ControleEstoque)request.getAttribute("controle_estoque");
		Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
		Resultado resultRecurso = (Resultado) getServletContext().getAttribute("resultado_recurso");
		
		List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
		List<EntidadeDominio> listRecursos = resultRecurso.getListaEntidade();
			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Controle de Estoque</h3>
<br>
<div class="container">
<form action="ControleEstoque" method="post">
<input type="hidden" id="id" name="id" value="<%if(ce != null)out.print(ce.getId());%>"  />
    <div class="row">
      <div class="col-25">
        <label for="id_projeto">Projetos</label>
      </div>
      <div class="col-75">
<%
		if(ce == null){  
%>
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
<%
	}else{
%>
<input type="text" id="id_projeto" name="id_projeto" value="<%if(ce != null)out.print(ce.getProjeto().getId());%>" readonly />
<%
	}
%>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_recurso">Recursos</label>
      </div>
      <div class="col-75">
<%
		if(ce == null){  
%>
        <select  id="id_recurso" name="id_recurso" >
        	<option disabled selected >Selecione</option>
<%
	if(listRecursos != null){
		for (EntidadeDominio recurso : listRecursos){
			if(ce != null && ce.getRecurso().getId() == recurso.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
			out.print(recurso.getId());
			out.print("' >");
			out.print(((Recurso)recurso).getDescricao());
			out.print("</option>");
		}
	}
%>
		</select>
<%
	}else{
%>
<input type="text" id="id_recurso" name="id_recurso" value="<%if(ce != null)out.print(ce.getRecurso().getId());%>" readonly />
<%
	}
%>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="quantidade">Quantidade</label>
      </div>
      <div class="col-75">
        <input type="text"  id="quantidade" name="quantidade" value="<%if(ce != null)out.print(ce.getQuantidade());%>" OnKeyPress="return mascaraInt(event, this, '##########');" />
      </div>
    </div>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(ce != null){ %>
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