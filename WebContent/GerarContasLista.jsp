<%@page import="sisgp.dominio.GerarContas"%>
<%@page import="sisgp.dominio.ApontamentoHoras"%>
<%@page import="sisgp.dominio.SubAtividade"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.core.util.Datas"%>
<%@page import="sisgp.dominio.ContasPagar"%>
<%@page import="sisgp.dominio.Projeto"%>
<%@page import="sisgp.dominio.StatusContas"%>
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
<body class="parallax" >
<%
		Resultado resultadoGerarContas = (Resultado) session.getAttribute("gerar_contas_resultado");
		
		Resultado resultRH = (Resultado) getServletContext().getAttribute("resultado_recurso_humano");
		Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
		
		List<EntidadeDominio> listaRH = resultRH.getListaEntidade();
		List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();

		

	%>
<%@ include file="menu.jsp" %>
<div class=content>


 <br>

<br>
<h3>Consultar Horas</h3>
	<br>
<div class="container">
  <form action="GerarContas" method="post" >
  <div class="row">
      <div class="col-25">
        <label for="dt_de">*Data de:</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_de" name="dt_de" OnKeyPress="return mascara(event, this, '##/##/####');"  />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_ate">*Data até:</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_ate" name="dt_ate" OnKeyPress="return mascara(event, this, '##/##/####');"  />
      </div>
    </div>
      <div class="row">
      <div class="col-25">
        <label for="id_recurso_humano">*Recursos Humanos</label>
      </div>
      <div class="col-75">
        <select  id="id_recurso_humano" name="id_recurso_humano"  >
        	<option disabled selected >Selecione</option>
<%
	if(listaRH != null){
		for (EntidadeDominio rh : listaRH){
			out.print("<option value='");
			out.print(rh.getId());
			out.print("' >");
			out.print(((RecursoHumano)rh).getNome() + " " + ((RecursoHumano)rh).getSobrenome());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
            <div class="row">
      <div class="col-25">
        <label for="id_projeto">*Projetos</label>
      </div>
      <div class="col-75">
        <select  id="id_projeto" name="id_projeto"  >
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
      </div>
    </div>
        <br>
<p>*Dados Obrigatórios</p>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="consultar" name="operacao">
    </div>
  </form>
</div>
<br>
<%
	
	if(resultadoGerarContas !=null && resultadoGerarContas.getMensagem() != null){
		out.print("<p>" + resultadoGerarContas.getMensagem() + "</p>");
	}
	if(resultadoGerarContas != null){
%>
<br>
<form method="post" action="GerarContas">
	<button type="submit" class="adicionar" name="operacao" value="adicionar" >Gerar Contas</button>
<br>
	<h3>Lista de Apontamentos em Aberto</h3>
	<br>
	<table>
		<tr>
			<!--<th width="10%">Selecionar</th>  -->
			<th width="10%">ID</th>
			<th width="30%">Nr. Horas</th>
			<th width="30%">Nr. Horas Extras</th>
			<th width="30%">Data</th>
		</tr>
<%

	
		
		List<EntidadeDominio> listaGerarContas = resultadoGerarContas.getListaEntidade();
		if(listaGerarContas != null){
			
			GerarContas gc = (GerarContas)listaGerarContas.get(0);
			RecursoHumano rh = gc.getRecursoHumano();
			Projeto proj = gc.getProjeto();
			
			List<ApontamentoHoras> listaAh = rh.getListaApontamentoHoras();
			
			StringBuilder sbRegistro = new StringBuilder();
	
			String data = "";
			
			sbRegistro.append("<input type='hidden' name='id_recurso_humano' value='");
			sbRegistro.append(rh.getId());
			sbRegistro.append("' >");
			
			sbRegistro.append("<input type='hidden' name='id_projeto' value='");
			sbRegistro.append(proj.getId());
			sbRegistro.append("' >");
			
			out.print(sbRegistro.toString());
			System.out.println(sbRegistro.toString());
	
			
			if(listaAh != null){
				for(int i = 0; i < listaAh.size(); i++){
					ApontamentoHoras ah = (ApontamentoHoras)listaAh.get(i);
					
					data = Datas.dateToString(ah.getData());
					
					sbRegistro.setLength(0);
					
					
					sbRegistro.append("<tr>");				
					/*sbRegistro.append("<td>");
					sbRegistro.append("<input type='checkbox' name='id_apontamento' value='");
					sbRegistro.append(ah.getId());
					sbRegistro.append("'>");
					sbRegistro.append("</td>");*/
					sbRegistro.append("<td>");
					sbRegistro.append(ah.getId());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(ah.getNrHoras());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(ah.getNrHorasExtras());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(data);
					sbRegistro.append("</td>");
	
					
					out.print(sbRegistro.toString());
				}
			}
		}
	
		
%>

	</table>
	</form>
<%
	}
%>
	<br>
		<br>
		<br>
		<br>
	</div>
</body>
</html>