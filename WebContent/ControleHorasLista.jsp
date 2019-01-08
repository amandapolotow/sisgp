<%@page import="sisgp.dominio.Atividade"%>
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
		
		
		Resultado resultProj = (Resultado) getServletContext().getAttribute("resultado_projeto");		
		List<EntidadeDominio> listaProj = resultProj.getListaEntidade();
		
		Resultado resultadoProjetos = (Resultado) session.getAttribute("controle_horas_resultado");
		
		String codigo = "";
		String nome = "";
		if(resultadoProjetos != null){
			
			List<EntidadeDominio> listaProjeto = resultadoProjetos.getListaEntidade();
			Projeto projeto = (Projeto)listaProjeto.get(0);
			codigo = projeto.getCodigo();
			nome = projeto.getNome();

		}
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>


 <br>

<br>
<h3>Consultar Projetos</h3>
	<br>
<div class="container">
  <form action="ControleHoras" method="post" >
      <div class="row">
      <div class="col-25">
        <label for="id_projeto">Projetos</label>
      </div>
      <div class="col-75">
        <select  id="id_projeto" name="id_projeto" required >
        	<option disabled selected >Selecione</option>
<%
	if(listaProj != null){
		for (EntidadeDominio proj : listaProj){
			out.print("<option value='");
			out.print(proj.getId());
			out.print("' >");
			out.print(((Projeto)proj).getCodigo() + " " + ((Projeto)proj).getNome());
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
	
	if(resultadoProjetos !=null && resultadoProjetos.getMensagem() != null){
		out.print("<p>" + resultadoProjetos.getMensagem() + "</p>");
	}
%>
<br>
<br>
	<h3>Controle Projeto <% out.print("- Codigo: " + codigo + " / Nome: " + nome); %> </h3>
	<br>
	<table>
		<tr>
			<!--<th width="10%">Selecionar</th>  -->
			<th width="20%">Tipo</th>
			<th width="10%">Id</th>
			<th width="10%">Código</th>
			<th width="20%">Nome</th>
			<th width="20%">Situação</th>
			<th width="20%">Status</th>
		</tr>
<%
StringBuilder sbRegistro = new StringBuilder();
String estilo = "";
if(resultadoProjetos != null){
	
	List<EntidadeDominio> listaProjeto = resultadoProjetos.getListaEntidade();
	Projeto projeto = (Projeto)listaProjeto.get(0);
	if(projeto != null){
		List<Atividade> listaAtividades = projeto.getListaAtividade();

	if(listaAtividades != null){
		for(int i = 0; i < listaAtividades.size(); i++){
			
			sbRegistro.setLength(0);
			
			Atividade atividade = listaAtividades.get(i);
			estilo = "";
			if(atividade.getStSistAtividade().getId() == 2){
				estilo = "style='background-color:#f9ea5e;color: white;'";
			}else if(atividade.getStSistAtividade().getId() == 3){
				estilo = "style='background-color:#ef2349;color: white;'";
			}
			
			sbRegistro.append("<tr style='background-color:#4CAF50;color: white;'>");				
			sbRegistro.append("<td>");
			sbRegistro.append("ATIVIDADE");
			sbRegistro.append("</td>");
			sbRegistro.append("<td>");
			sbRegistro.append(atividade.getId());
			sbRegistro.append("</td>");
			sbRegistro.append("<td>");
			sbRegistro.append(atividade.getCodigo());
			sbRegistro.append("</td>");
			sbRegistro.append("<td>");
			sbRegistro.append(atividade.getNome());
			sbRegistro.append("</td>");
			sbRegistro.append("<td>");
			sbRegistro.append(atividade.getStatusAtividade().getStatus());
			sbRegistro.append("</td>");
			sbRegistro.append("<td " + estilo + ">");
			sbRegistro.append(atividade.getStSistAtividade().getStatus());
			sbRegistro.append("</td>");
			sbRegistro.append("</tr>");

			
			
			List<SubAtividade>listaSA = atividade.getListaSubAtividade();
			
			out.print(sbRegistro.toString());
			
			
				if(listaSA != null){
					for(int j = 0; j < listaSA.size(); j++){
						
						sbRegistro.setLength(0);
						
						SubAtividade subAtividade = listaSA.get(j);
						estilo = "";
						if(subAtividade.getStSistAtividade().getId() == 2){
							estilo = "style='background-color:#f9ea5e;'";
						}else if(subAtividade.getStSistAtividade().getId() == 3){
							estilo = "style='background-color:#ef2349;'";
						}
						
						sbRegistro.append("<tr style='background-color:#99ef9c'>");				
						sbRegistro.append("<td>");
						sbRegistro.append("SUB ATIV.");
						sbRegistro.append("</td>");
						sbRegistro.append("<td>");
						sbRegistro.append(subAtividade.getId());
						sbRegistro.append("</td>");
						sbRegistro.append("<td>");
						sbRegistro.append(subAtividade.getCodigo());
						sbRegistro.append("</td>");
						sbRegistro.append("<td>");
						sbRegistro.append(subAtividade.getNome());
						sbRegistro.append("</td>");
						sbRegistro.append("<td>");
						sbRegistro.append(subAtividade.getStatusAtividade().getStatus());
						sbRegistro.append("</td>");
						sbRegistro.append("<td " + estilo + ">");
						sbRegistro.append(subAtividade.getStSistAtividade().getStatus());
						sbRegistro.append("</td>");
						sbRegistro.append("</tr>");
						
						//List<ApontamentoHoras> listaAh = subAtividade.getListaApontamentoHoras();
						
						out.print(sbRegistro.toString());
						
						/*if(listaAh != null){
							for(int k = 0; k < listaAh.size(); k++){
								
								sbRegistro.setLength(0);
								
								ApontamentoHoras ah = (ApontamentoHoras)listaAh.get(k);
								
								
								String data = Datas.dateToString(ah.getData());
								
								sbRegistro.append("<tr style='background-color:#ceedcf'>");				
								sbRegistro.append("<td>");
								sbRegistro.append("APONTAMENTO");
								sbRegistro.append("</td>");
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
								sbRegistro.append("&nbsp;&nbsp;&nbsp;");
								sbRegistro.append("</td>");
								sbRegistro.append("</tr>");

								out.print(sbRegistro.toString());
								
								
							}//for apontamentos
						}//if apontamentos*/
						
						
						
						
					}//for SubAtividades
				}//if lista de SubAtividades
			
			}//for Atividades
		}//if lista de Atividades
		
	
	}
}		
%>

	</table>
	<br>
		<br>
		<br>
		<br>
	</div>
</body>
</html>