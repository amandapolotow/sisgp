<%@page import="sisgp.dominio.StatusProjeto"%>
<%@page import="sisgp.dominio.Cliente"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@page import="sisgp.core.util.Datas"%>
<%@page import="sisgp.dominio.Projeto"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css.css">
<title>SisGP</title>
</head>
<body class="parallax">
<%
		Projeto proj = (Projeto)request.getAttribute("projeto");

		Resultado resultGestor = (Resultado)getServletContext().getAttribute("resultado_gestor");
		Resultado resultCliente = (Resultado)getServletContext().getAttribute("resultado_cliente");
		Resultado resultStatus = (Resultado)getServletContext().getAttribute("resultado_status_projeto");
		
		List<EntidadeDominio> listaGestor = resultGestor.getListaEntidade();
		List<EntidadeDominio> listaCliente = resultCliente.getListaEntidade();
		List<EntidadeDominio> listaStatus = resultStatus.getListaEntidade();

%>
<%@ include file="menu.jsp" %>
<div class="content">
	<h3>Cadastro de Projeto</h3>
<br>
<div class="container">
<form  action="Projetos" method="post">
<input type="hidden" name="id" value="<%if(proj != null)out.print(proj.getId());%>" />
<h3>Dados do Projeto</h3>
<br>
 	<div class="row">
      <div class="col-25">
        <label for="id_rh_gestor">*Gestor</label>
      </div>
      <div class="col-75">
        <select  id="id_rh_gestor" name="id_rh_gestor" >
        <option disabled selected >Selecione</option>
<%		
	if(listaGestor != null){
		for (EntidadeDominio gestor : listaGestor){
			if(proj != null && proj.getRhGestor().getId() == gestor.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
			out.print(gestor.getId());
			out.print("' >");
			out.print(((RecursoHumano)gestor).getNome());
			out.print("</option>");
		}
	}
%>
</select>
      </div>
    </div>
	<div class="row">
      <div class="col-25">
        <label for="id_cliente">*Cliente</label>
      </div>
      <div class="col-75">
        <select  id="id_cliente" name="id_cliente" >
        <option disabled selected >Selecione</option>
<%
	if(listaCliente != null){
		for (EntidadeDominio cliente : listaCliente){
			if(proj != null && proj.getCliente().getId() == cliente.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
			out.print(cliente.getId());
			out.print("' >");
			out.print(((Cliente)cliente).getRazaoSocial());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
         <div class="row">
      <div class="col-25">
        <label for="codigo">*Código</label>
      </div>
      <div class="col-75">
        <input type="text"  id="codigo" name="codigo" value="<%if(proj != null)out.print(proj.getCodigo());%>" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nome">*Nome</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome" name="nome"  value="<%if(proj != null)out.print(proj.getNome());%>" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="descricao">*Descricão</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao"  value="<%if(proj != null)out.print(proj.getDescricao());%>" />
      </div>
    </div>
	<div class="row">
      <div class="col-25">
        <label for="id_status_projeto">*Status</label>
      </div>
      <div class="col-75">
        <select  id="id_status_projeto" name="id_status_projeto" >
<%
	if(listaStatus != null){
		for (EntidadeDominio status : listaStatus){
			if(proj != null && proj.getStatusProjeto().getId() == status.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
			out.print(status.getId());
			out.print("' >");
			out.print(((StatusProjeto)status).getStatusProjeto());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
<br>
<p>*Dados Obrigatórios</p>
<br>  
  <%if(proj != null){ %>
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