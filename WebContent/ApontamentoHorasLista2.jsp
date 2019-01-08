<%@page import="java.util.Date"%>
<%@page import="sisgp.dominio.SubAtividade"%>
<%@page import="sisgp.dominio.ApontamentoHoras"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.core.util.Datas"%>
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
		
		SubAtividade subAtividade = (SubAtividade)session.getAttribute("sub_atividade_apontamento");

		List<ApontamentoHoras> listaApontamentos = null;

		Resultado resultRH = (Resultado) getServletContext().getAttribute("resultado_recurso_humano");
		
		List<EntidadeDominio> listaRH = resultRH.getListaEntidade();
		
		Double totalHorasPrevisto = 0.0;
		/*Date dataI = null;
		Date dataF = null;
		LocalDate dataInicio = null;
		LocalDate dataFim = null;
		Period periodo = null;*/
		Double totalDiasPrevisto = 0.0;
		
		//media horas atividade
		if(subAtividade != null){
			if(subAtividade.getNrHorasPrevisto() != null && subAtividade.getNrHorasPrevisto() != 0){
				totalHorasPrevisto = (double) subAtividade.getNrHorasPrevisto();
			}
			if(subAtividade.getDtInicio() != null && subAtividade.getDtFim() != null){
				
				totalDiasPrevisto = Datas.getDifferenceInDays(subAtividade.getDtInicio(), subAtividade.getDtFim());
				
				//dataI = ((Timestamp) subAtividade.getDtInicio());
				//dataF = ((Timestamp)subAtividade.getDtFim());
			}
			
			/*dataInicio = dataI.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			dataFim = dataF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			periodo = Period.between(dataInicio, dataFim);
			totalDiasPrevisto = (double) periodo.getDays();*/
		}
		
		//media horas atividade
		
		
	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<form action="ApontamentoHoras" method="post">
	<input type="hidden" name="id_sub_atividade" value="<% if(subAtividade != null && subAtividade.getId() != null)out.print(subAtividade.getId()); %>" >
<button type="submit" class="adicionar" name="operacao" value="visualizar" >Adicionar Apontamento</button></a>
</form>
<br>
<br>
	<h3>Lista de Apontamentos <% if(subAtividade != null)out.print(" - " + subAtividade.getCodigo() + " - " + subAtividade.getNome()); %></h3>
	<h3>Média de Horas/Dia: <% if(subAtividade != null)out.print((totalHorasPrevisto/totalDiasPrevisto)); %></h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="40%">Nr. Horas</th>
			<th width="40%">Nr. Horas Extras</th>
			<th width="40%">Data</th>
			<th width="10%">Editar</th>
		</tr>
<%
	if(subAtividade != null){
		listaApontamentos = subAtividade.getListaApontamentoHoras();

		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		String data = "";
		
		if(listaApontamentos != null){
			for(int i = 0; i < listaApontamentos.size(); i++){
				ApontamentoHoras ah = (ApontamentoHoras)listaApontamentos.get(i);
				
				data = Datas.dateToString(ah.getData());
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=ApontamentoHoras?id=");
				sbLink1.append(ah.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
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