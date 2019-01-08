<%@page import="sisgp.dominio.StatusProjeto"%>
<%@page import="sisgp.dominio.Cliente"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.dominio.Projeto"%>
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
	
	Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
	
	List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
	
	%>
<%@ include file="menu.jsp" %>
<div class="content">
<br>
<br>
<div class="container">
  <form action="GraficoContasReceber" method="post" >
  <h3>Gráfico de Receita</h3>
	<br>
    <div class="row">
      <div class="col-25">
        <label for="dt_de">Data De:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_de" name="dt_de" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_ate">Data Até:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_ate" name="dt_ate" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
            <div class="row">
      <div class="col-25">
        <label for="id_projeto">Projeto</label>
      </div>
      <div class="col-75">
        <select  id="id_projeto" name="id_projeto" >
        	<option disabled selected >Toda a Empresa</option>
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
      </div>
    </div>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="operacao" name="operacao">
    </div>
  </form>
<br>
<h3>Gráfico de Despesa</h3>
<br>
  <form action="GraficoContasPagar" method="post" >
    <div class="row">
      <div class="col-25">
        <label for="dt_de">Data De:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_de" name="dt_de" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_ate">Data Até:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_ate" name="dt_ate" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
            <div class="row">
      <div class="col-25">
        <label for="id_projeto">Projeto</label>
      </div>
      <div class="col-75">
        <select  id="id_projeto" name="id_projeto" >
        	<option disabled selected >Toda a Empresa</option>
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
      </div>
    </div>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="operacao" name="operacao">
    </div>
  </form>
<br>
<h3>Gráfico de Contas a Pagar por Categoria</h3>
	<br>
  <form action="GraficoCategoriaContasPagar" method="post" >
    <div class="row">
      <div class="col-25">
        <label for="dt_de">Data De:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_de" name="dt_de" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_ate">Data Até:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_ate" name="dt_ate" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="operacao" name="operacao">
    </div>
  </form>
  <br>
<h3>Gráfico de Contas a Receber por Categoria</h3>
	<br>
  <form action="GraficoCategoriaContasReceber" method="post" >
    <div class="row">
      <div class="col-25">
        <label for="dt_de">Data De:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_de" name="dt_de" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_ate">Data Até:</label>
      </div>
      <div class="col-75">
        <input type="text" id="dt_ate" name="dt_ate" OnKeyPress="return mascara(event, this, '##/##/####');" required >
      </div>
    </div>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="operacao" name="operacao">
    </div>
  </form>
</div>


	</div><!--div content-->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

</body>
</html>