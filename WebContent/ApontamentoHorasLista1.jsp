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
		Resultado resultadoSubAtividade = (Resultado) session.getAttribute("resultado_rh_apontamento");//
		
		Resultado resultRH = (Resultado) getServletContext().getAttribute("resultado_recurso_humano");
		
		List<EntidadeDominio> listaRH = resultRH.getListaEntidade();

		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<!-- <br>
<a href="SubAtividadesCadastro.jsp" ><button class="adicionar" >Adicionar Sub Atividade</button></a> -->
<br>
<br>
<h3>Consultar Horas</h3>
	<br>
<div class="container">
  <form action="ApontamentoHorasLista1" method="post" >
      <div class="row">
      <div class="col-25">
        <label for="id_recurso_humano">Recursos Humanos</label>
      </div>
      <div class="col-75">
        <select  id="id_recurso_humano" name="id_recurso_humano" >
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
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="consultar" name="operacao">
    </div>
  </form>
</div>
<br>
<%
	
	if(resultadoSubAtividade !=null && resultadoSubAtividade.getMensagem() != null){
		out.print("<p>" + resultadoSubAtividade.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Sub Atividades</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="20%">Codigo</th>
			<th width="20%">Nome</th>
			<th width="20%">Inicio</th>
			<th width="20%">Fim</th>
			<th width="20%">Apontamentos</th>
		</tr>
<%

	if(resultadoSubAtividade != null){
		List<EntidadeDominio> listaSubAtividades = resultadoSubAtividade.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		String dtInicio = "";
		String dtFim= "";
		
		if(listaSubAtividades != null){
			for(int i = 0; i < listaSubAtividades.size(); i++){
				SubAtividade sa = (SubAtividade)listaSubAtividades.get(i);
				
				dtInicio = Datas.dateToString(sa.getDtInicio());
				dtFim = Datas.dateToString(sa.getDtFim());
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				sbLink2.setLength(0);
				
				/*sbLink1.append("<a href=SubAtividades?id=");
				sbLink1.append(sa.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");*/
				
				sbLink2.append("<a href=ApontamentoHorasLista1?id=");
				sbLink2.append(sa.getId());
				sbLink2.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(sa.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(sa.getCodigo());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(sa.getNome());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(dtInicio);
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(dtFim);
				sbRegistro.append("</td>");
				
							
				/*sbRegistro.append("<td>");
				sbRegistro.append(sbLink1.toString());				
				sbRegistro.append("</td>");*/
				
				sbRegistro.append("<td>");
				sbRegistro.append(sbLink2.toString());				
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