<%@page import="sisgp.dominio.SubAtividade"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.dominio.Atividade"%>
<%@page import="sisgp.dominio.StatusSistemaAtividade"%>
<%@page import="sisgp.dominio.StatusAtividade"%>
<%@page import="sisgp.dominio.StatusContas"%>
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
		Resultado resultadoSubAtividade = (Resultado) session.getAttribute("sub_atividade_resultado");
		Resultado resultAtividade = (Resultado) getServletContext().getAttribute("resultado_atividade");
		Resultado resultRecursoHumano = (Resultado) getServletContext().getAttribute("resultado_recurso_humano");
		Resultado resultStatusAtividade = (Resultado) getServletContext().getAttribute("resultado_status_atividade");
		Resultado resultStSistAtividade = (Resultado) getServletContext().getAttribute("resultado_st_sist_atividade");
		
		List<EntidadeDominio> listAtividades = resultAtividade.getListaEntidade();
		List<EntidadeDominio> listRecursosHumanos = resultRecursoHumano.getListaEntidade();
		List<EntidadeDominio> listStatusAtividade = resultStatusAtividade.getListaEntidade();
		List<EntidadeDominio> listStSistAtividade = resultStSistAtividade.getListaEntidade();
		

	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="SubAtividadesCadastro.jsp" ><button class="adicionar" >Adicionar Subatividade</button></a>
<br>
<br>
<h3>Consultar Subatividades</h3>
	<br>
<div class="container">
  <form action="SubAtividades" method="post" >
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
        <label for="nr_horas_previsto">Nr. Horas Previsto</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nr_horas_previsto" name="nr_horas_previsto" OnKeyPress="return mascaraInt(event, this, '##########');" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_inicio">Data Início</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_inicio" name="dt_inicio" OnKeyPress="return mascara(event, this, '##/##/####');" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_fim">Data Fim</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_fim" name="dt_fim" OnKeyPress="return mascara(event, this, '##/##/####');" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_recurso_humano">Recursos Humanos</label>
      </div>
      <div class="col-75">
        <select  id="id_recurso_humano" name="id_recurso_humano" >
        	<option disabled selected >Selecione</option>
<%
	if(listRecursosHumanos != null){
		for (EntidadeDominio rh : listRecursosHumanos){
			out.print("<option value='");
			out.print(rh.getId());
			out.print("' >");
			out.print(((RecursoHumano)rh).getNome());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_atividade">Atividades</label>
      </div>
      <div class="col-75">
        <select  id="id_atividade" name="id_atividade" >
        	<option disabled selected >Selecione</option>
<%
	if(listAtividades != null){
		for (EntidadeDominio atividade : listAtividades){
			out.print("<option value='");
			out.print(atividade.getId());
			out.print("' >");
			out.print(((Atividade)atividade).getCodigo() + " - " + ((Atividade)atividade).getNome());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
        <div class="row">
      <div class="col-25">
        <label for="id_status_atividade">Status Subatividade</label>
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
        <label for="id_st_sist_atividade">Status Sistema Subatividade</label>
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
	
	if(resultadoSubAtividade !=null && resultadoSubAtividade.getMensagem() != null){
		out.print("<p>" + resultadoSubAtividade.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Subatividades</h3>
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

	if(resultadoSubAtividade != null){
		List<EntidadeDominio> listaEntidadesSubAtividade = resultadoSubAtividade.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		String dtVencimento = "";
		
		if(listaEntidadesSubAtividade != null){
			for(int i = 0; i < listaEntidadesSubAtividade.size(); i++){
				SubAtividade sat = (SubAtividade)listaEntidadesSubAtividade.get(i);
				
				
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=SubAtividades?id=");
				sbLink1.append(sat.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(sat.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(sat.getCodigo());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(sat.getNome());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(sat.getDescricao());
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