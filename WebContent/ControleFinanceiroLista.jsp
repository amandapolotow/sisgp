<%@page import="sisgp.dominio.CategoriaContasReceber"%>
<%@page import="sisgp.dominio.ControleFinanceiro"%>
<%@page import="sisgp.dominio.ContasReceber"%>
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
/*
	#ef2349 = vermelho
	#f9ea5e = amarelo
	#4CAF50 = verde escuro
	#99ef9c = verde claro
*/
		
		Resultado resultadoControle = (Resultado) session.getAttribute("controle_financeiro_resultado");

		Resultado resultStatusContas = (Resultado) getServletContext().getAttribute("resultado_status_contas");
		Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
		
		List<EntidadeDominio> listStContas = resultStatusContas.getListaEntidade();
		List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();

		List<CategoriaContasPagar> listaCatContasPagar = null;
		List<CategoriaContasReceber> listaCatContasReceber = null;
		ControleFinanceiro controle = null;
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>


 <br>

<br>
<h3>Consultar Controle Financeiro</h3>
	<br>
<div class="container">
  <form action="ControleFinanceiro" method="post" >
   <div class="row">
      <div class="col-25">
        <label for="dt_de">*Data de:</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_de" name="dt_de" OnKeyPress="return mascara(event, this, '##/##/####');" />
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
        <label for="id_status_conta">Status de Contas</label>
      </div>
      <div class="col-75">
        <select  id="id_status_conta" name="id_status_conta" >
        	<option disabled selected >Todas</option>
<%
	if(listStContas != null){
		for (EntidadeDominio statusContas : listStContas){
			out.print("<option value='");
			out.print(statusContas.getId());
			out.print("' >");
			out.print(((StatusContas)statusContas).getStatus());
			out.print("</option>");
		}
	}
%>
		</select>
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
<p>*Dados Obrigatórios</p>
    <br>
    <div class="row">
      <input type="submit" class="submit-verde" value="consultar" id="consultar" name="operacao">
    </div>
  </form>
</div>
<br>
<%
	
	if(resultadoControle !=null && resultadoControle.getMensagem() != null){
		out.print("<p>" + resultadoControle.getMensagem() + "</p>");
	}

	StringBuilder sbRegistro = new StringBuilder();
	String estilo = "";
	String dtDe = "";
	String dtAte = "";
	
	if(resultadoControle != null){
		List<EntidadeDominio> listaControle = resultadoControle.getListaEntidade();
		
		if(listaControle != null){
			controle = (ControleFinanceiro)listaControle.get(0);
			
			if(controle != null){
	
	
	%>
	<br>
		<h3>Filtros</h3>
		<br>
		<table>
		<tr>
			<th width="20%">Data de</th>
			<th width="20%">Data até</th>
			<th width="20%">Status Contas</th>
			<th width="20%">Projeto</th>
		</tr>
	<%
		
				
				listaCatContasPagar = controle.getListaCategoriaContasPagar();
				listaCatContasReceber = controle.getListaCategoriaContasReceber();
	
				if(controle.getDtDe() != null && controle.getDtAte() != null){
					dtDe = Datas.dateToString(controle.getDtDe());
					dtAte = Datas.dateToString(controle.getDtAte());
				}
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(dtDe);
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(dtAte);
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append((controle.getStatusContas() != null && controle.getStatusContas().getId() != null) ? controle.getStatusContas().getId() : " - ");
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append((controle.getProjeto() != null && controle.getProjeto().getId() != null) ? controle.getProjeto().getId() : " - ");
				sbRegistro.append("</td>");
				sbRegistro.append("<tr>");
				
				out.print(sbRegistro.toString());
		
	%>	
		</table>
		<br>
		<h3>Valores</h3>
		<br>
		<table>
		<tr>
			<th width="20%">Receita</th>
			<th width="20%">Despesas</th>
			<th width="20%">Diferença</th>
		</tr>
		<%
		
			Double diferenca = controle.getReceita() - controle.getDespesa();
		
			if(diferenca < 0){
				estilo = "style='background-color:#ef2349;color: black;'";
			}else{
				estilo = "style='background-color:#99ef9c;color: black;'";
			}
			sbRegistro.setLength(0);
		
			sbRegistro.append("<tr>");
			sbRegistro.append("<td>");
			sbRegistro.append(controle.getReceita());
			sbRegistro.append("</td>");
			sbRegistro.append("<td>");
			sbRegistro.append(controle.getDespesa());
			sbRegistro.append("</td>");
			sbRegistro.append("<td " + estilo + ">");
			sbRegistro.append(diferenca);
			sbRegistro.append("</td>");
			sbRegistro.append("<tr>");
			
			out.print(sbRegistro.toString());
	
	%>
	</table>
	<br>
		<br>
		<h3>Receita</h3>
		<br>
		<table>
		<tr>
			<th width="10%">Id</th>
			<th width="50%">Categoria</th>
			<th width="30%">Valor</th>
		</tr>
	<%
	
			if(listaCatContasReceber != null){
				for(int i = 0; i < listaCatContasReceber.size(); i++){
					CategoriaContasReceber ccr = listaCatContasReceber.get(i);
					sbRegistro.setLength(0);
					
					sbRegistro.append("<tr>");
					sbRegistro.append("<td>");
					sbRegistro.append(ccr.getId());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(ccr.getCategoria());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(ccr.getValor());
					sbRegistro.append("</td>");
					sbRegistro.append("<tr>");
					
					out.print(sbRegistro.toString());
				}
			}
	
	
	%>
	</table>
	<br>
		<br>
		<h3>Despesas</h3>
		<br>
		<table>
		<tr>
			<th width="10%">Id</th>
			<th width="50%">Categoria</th>
			<th width="30%">Valor</th>
		</tr>
	<%
			
			if(listaCatContasPagar != null){
				for(int i = 0; i < listaCatContasPagar.size(); i++){
					CategoriaContasPagar ccr = listaCatContasPagar.get(i);
					sbRegistro.setLength(0);
					
					sbRegistro.append("<tr>");
					sbRegistro.append("<td>");
					sbRegistro.append(ccr.getId());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(ccr.getCategoria());
					sbRegistro.append("</td>");
					sbRegistro.append("<td>");
					sbRegistro.append(ccr.getValor());
					sbRegistro.append("</td>");
					sbRegistro.append("<tr>");
					
					out.print(sbRegistro.toString());
				}
			}
	%>
	</table>
	<br>
	<%
			} // if controle 
		} // if listaControle
	}	//if resultadoControle	
%>

	<br>
		<br>
		<br>
		<br>
	</div>
</body>
</html>