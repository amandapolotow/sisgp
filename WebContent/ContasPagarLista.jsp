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
		Resultado resultadoContasPagar = (Resultado) session.getAttribute("contas_pagar_resultado");
		Resultado resultCatContasPagar = (Resultado) getServletContext().getAttribute("resultado_categoria_contas_pagar");
		Resultado resultStatusContas = (Resultado) getServletContext().getAttribute("resultado_status_contas");
		Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
		
		List<EntidadeDominio> listCatContasPagar = resultCatContasPagar.getListaEntidade();
		List<EntidadeDominio> listStContas = resultStatusContas.getListaEntidade();
		List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="ContasPagarCadastro.jsp" ><button class="adicionar" >Adicionar Conta</button></a>
<br>
<br>
<h3>Consultar Contas a Pagar</h3>
	<br>
<div class="container">
  <form action="ContasPagar" method="post" >
      <div class="row">
      <div class="col-25">
        <label for="id_cat_contas_pagar">Catagoria de Contas a Pagar</label>
      </div>
      <div class="col-75">
        <select  id="id_cat_contas_pagar" name="id_cat_contas_pagar" >
        	<option disabled selected >Selecione</option>
<%
	if(listCatContasPagar != null){
		for (EntidadeDominio catContasPagar : listCatContasPagar){
			out.print("<option value='");
			out.print(catContasPagar.getId());
			out.print("' >");
			out.print(((CategoriaContasPagar)catContasPagar).getCategoria());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
        <div class="row">
      <div class="col-25">
        <label for="id_st_contas_pagar">Status de Contas a Pagar</label>
      </div>
      <div class="col-75">
        <select  id="id_st_contas_pagar" name="id_st_contas_pagar" >
        	<option disabled selected >Selecione</option>
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
        <label for="dt_lancamento">Data de Lançamento</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_lancamento" name="dt_lancamento" OnKeyPress="return mascara(event, this, '##/##/####');" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_vencimento">Data de Vencimento</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_vencimento" name="dt_vencimento" OnKeyPress="return mascara(event, this, '##/##/####');" />
      </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="valor">Valor do Título:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="valor" name="valor"  />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="nr_parcelas">Número de Parcelas:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="nr_parcelas" name="nr_parcelas" />
    </div>
    </div>
        <div class="row">
<div class="col-25">
    <label for="contrato">Contrato:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="contrato" name="contrato" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="observacao">Observação:</label>
    </div>
    <div class="col-75"> 
      <input type="text" id="observacao" name="observacao" />
    </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_projeto">Projeto</label>
      </div>
      <div class="col-75">
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
	
	if(resultadoContasPagar !=null && resultadoContasPagar.getMensagem() != null){
		out.print("<p>" + resultadoContasPagar.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Contas a Pagar</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="40%">Valor</th>
			<th width="40%">Dt. Vencimento</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoContasPagar != null){
		List<EntidadeDominio> listaEntidadesContasPagar = resultadoContasPagar.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		String dtVencimento = "";
		
		if(listaEntidadesContasPagar != null){
			for(int i = 0; i < listaEntidadesContasPagar.size(); i++){
				ContasPagar cp = (ContasPagar)listaEntidadesContasPagar.get(i);
				
				dtVencimento = Datas.dateToString(cp.getDtVencimento());
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=ContasPagar?id=");
				sbLink1.append(cp.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(cp.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(cp.getValor());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(dtVencimento);
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