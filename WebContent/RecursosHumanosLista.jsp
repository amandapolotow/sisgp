<%@page import="sisgp.dominio.GrupoAcesso"%>
<%@page import="sisgp.dominio.Nivel"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="java.util.*"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css.css">
<title>Gestao de Projetos</title>
</head>
<body class="parallax">
	<%
		Resultado resultado = (Resultado) session.getAttribute("recurso_humano_resultado");
	
		Resultado resultadoNivel = (Resultado) getServletContext().getAttribute("resultado_nivel");
		Resultado resultadoGrupoAcesso = (Resultado) getServletContext().getAttribute("resultado_grupo_acesso");

		List<EntidadeDominio> listaNivel = resultadoNivel.getListaEntidade();
		List<EntidadeDominio> listaGrupoAcesso = resultadoGrupoAcesso.getListaEntidade();
		

		
	%>
<%@ include file="menu.jsp" %>
<div class=content>
<br>
<a href="RecursosHumanosCadastro.jsp" ><button class="adicionar" >Adicionar Recurso Humano</button></a>
<br>
<br>
<h3>Consultar Recursos Humanos</h3>
	<br>
<div class="container">
  <form action="RecursosHumanos" method="post" >
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
        <label for="sobrenome">Sobrenome</label>
      </div>
      <div class="col-75">
        <input type="text"  id="sobrenome" name="sobrenome" />
      </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="email">Email:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="email" name="email"  />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="telefone">Telefone:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="telefone" name="telefone" />
    </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="cargo">Cargo</label>
      </div>
      <div class="col-75">
        <input type="text" id="cargo" name="cargo" />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_nivel">Nivel</label>
      </div>
      <div class="col-75">
        <select  id="id_nivel" name="id_nivel" >
        	<option disabled selected >Selecione</option>
<%
	if(listaNivel != null){
		for (EntidadeDominio nivel : listaNivel){
			out.print("<option value='");
			out.print(nivel.getId());
			out.print("' >");
			out.print(((Nivel)nivel).getNivel());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
       <div class="row">
      <div class="col-25">
        <label for="id_grupo_acesso">Grupo Acesso</label>
      </div>
      <div class="col-75">
        <select  id="id_grupo_acesso" name="id_grupo_acesso" >
        	<option disabled selected >Selecione</option> 
<%
	if(listaGrupoAcesso != null){
		for (EntidadeDominio grupo : listaGrupoAcesso){
			out.print("<option value='");
			out.print(grupo.getId());
			out.print("' >");
			out.print(((GrupoAcesso)grupo).getGrupoAcesso());
			out.print("</option>");
		}
	}
%>
		</select>
      </div>
    </div>
        <div class="row">
<div class="col-25">
    <label for="valor_hora">Valor-Hora:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="valor_hora" name="valor_hora" OnKeyPress="return mascara(event, this, '####################');" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="valor_hora_extra">Valor-Hora-Extra:</label>
    </div>
    <div class="col-75"> 
      <input type="text" id="valor_hora_extra" name="valor_hora_extra" OnKeyPress="return mascara(event, this, '####################');" />
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
	
	if(resultado !=null && resultado.getMensagem() != null){
		out.print("<p>" + resultado.getMensagem() + "</p>");
	}
%>
<br>
	<h3>Lista de Recursos Humanos</h3>
	<br>
	<table>
		<tr>
			<th width="10%">ID</th>
			<th width="35%">Nome</th>
			<th width="35%">Cargo</th>
			<th width="10%">Editar</th>
		</tr>
<%

	if(resultado != null){
		List<EntidadeDominio> listaEntidades = resultado.getListaEntidade();
		StringBuilder sbRegistro = new StringBuilder();
		StringBuilder sbLink1 = new StringBuilder();
		StringBuilder sbLink2 = new StringBuilder();
		
		if(listaEntidades != null){
			for(int i = 0; i < listaEntidades.size(); i++){
				RecursoHumano rh = (RecursoHumano)listaEntidades.get(i);
				sbRegistro.setLength(0);
				sbLink1.setLength(0);
				
				sbLink1.append("<a href=RecursosHumanos?id=");
				sbLink1.append(rh.getId());
				sbLink1.append("&operacao=visualizar><span class='icone' id='icone'>&#10004;</span></a>");
				
				/*sbLink2.append("<a href=ControleHoras?id_recurso_humano=");
				sbLink2.append(rh.getId());
				sbLink2.append("&operacao=consultar><span class='icone' id='icone'>&#10004;</span></a>");*/
				
				sbRegistro.append("<tr>");
				sbRegistro.append("<td>");
				sbRegistro.append(rh.getId());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(rh.getNome() + " " + rh.getSobrenome());
				sbRegistro.append("</td>");
				sbRegistro.append("<td>");
				sbRegistro.append(rh.getCargo());
				sbRegistro.append("</td>");
				
				/*sbRegistro.append("<td>");
				sbRegistro.append(sbLink2.toString());				
				sbRegistro.append("</td>");*/
							
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