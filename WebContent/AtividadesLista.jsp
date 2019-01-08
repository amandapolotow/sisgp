<%@page import="sisgp.dominio.Atividade"%>
<%@page import="sisgp.dominio.StatusSistemaAtividade"%>
<%@page import="sisgp.dominio.StatusAtividade"%>
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
		Resultado resultadoAtividade = (Resultado) session.getAttribute("atividade_resultado");
		Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
		Resultado resultStatusAtividade = (Resultado) getServletContext().getAttribute("resultado_status_atividade");
		Resultado resultStSistAtividade = (Resultado) getServletContext().getAttribute("resultado_st_sist_atividade");
		
		List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
		List<EntidadeDominio> listStatusAtividade = resultStatusAtividade.getListaEntidade();
		List<EntidadeDominio> listStSistAtividade = resultStSistAtividade.getListaEntidade();
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="AtividadesCadastro.jsp" ><button class="adicionar" >Adicionar Atividade</button></a>
<br>
<br>
<h3>Consultar Atividades</h3>
	<br>
<div class="container">
  <form action="Atividades" method="post" >
  <div class="row">
      <div class="col-25">
        <label for="codigo">Código</label>
      </div>
      <div class="col-75">
        <input type="text"  id="codigo" name="codigo" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nome">Nome</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome" name="nome" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="descricao">Descrição</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao" />
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
        <div class="row">
      <div class="col-25">
        <label for="id_status_atividade">Status Atividade</label>
      </div>
      <div class="col-75">
        <select  id="id_status_atividade" name="id_status_atividade" >
        	<option disabled selected >Selecione</option>
<%
	if(listStatusAtividade != null){
		for (EntidadeDominio status : listStatusAtividade){
			out.print("<option value='");
			out.print(status.getId());
			out.print("' >");
			out.print(((StatusAtividade)status).getStatus());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
            <div class="row">
      <div class="col-25">
        <label for="id_st_sist_atividade">Status Sistema Atividade</label>
      </div>
      <div class="col-75">
        <select  id="id_st_sist_atividade" name="id_st_sist_atividade" >
        	<option disabled selected >Selecione</option>
<%
	if(listStSistAtividade != null){
		for (EntidadeDominio stSist : listStSistAtividade){
			out.print("<option value='");
			out.print(stSist.getId());
			out.print("' >");
			out.print(((StatusSistemaAtividade)stSist).getStatus());
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
	
	if(resultadoAtividade !=null && resultadoAtividade.getMensagem() != null){
		out.print("<p>" + resultadoAtividade.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Atividades</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="20%">Código</th>
			<th width="30%">Nome</th>
			<th width="30%">Descrição</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultadoAtividade != null){
		List<EntidadeDominio> listaEntidadesAtividade = resultadoAtividade.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		String dtVencimento = "";
		
		if(listaEntidadesAtividade != null){
			for(int i = 0; i < listaEntidadesAtividade.size(); i++){
				Atividade at = (Atividade)listaEntidadesAtividade.get(i);
				
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=Atividades?id=");
				sbLink1.append(at.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(at.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(at.getCodigo());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(at.getNome());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(at.getDescricao());
				sbRegistro.append("</td>");
				
							
				sbRegistro.append("<td>");
				sbRegistro.append(sbLink1.toString());				
				sbRegistro.append("</td>");
				sbRegistro.append("</tr>");
				
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