<%@page import="sisgp.dominio.GrupoAcesso"%>
<%@page import="sisgp.dominio.Nivel"%>
<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		RecursoHumano rh = (RecursoHumano)request.getAttribute("recurso_humano");

		Resultado resultadoNivel = (Resultado) getServletContext().getAttribute("resultado_nivel");
		Resultado resultadoGrupoAcesso = (Resultado) getServletContext().getAttribute("resultado_grupo_acesso");
		
		List<EntidadeDominio> listaNivel = resultadoNivel.getListaEntidade();
		List<EntidadeDominio> listaGrupoAcesso = resultadoGrupoAcesso.getListaEntidade();

%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Editar Recurso Humano</h3>
<br>
<div class="container">
<form action="RecursosHumanos" method="post">
<input type="hidden" id="id" name="id" value="<%if(rh != null)out.print(rh.getId());%>"  />
<div class="row">
<div class="col-25">
    <label for="nome">*Nome:</label>
    </div>
    <div class="col-75">
      <input type="text"  id="nome" name="nome" value="<%if(rh != null)out.print(rh.getNome());%>" />
    </div>
</div>
<div class="row">
<div class="col-25">
    <label for="sobrenome">*Sobrenome:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="sobrenome" name="sobrenome" value="<%if(rh != null)out.print(rh.getSobrenome());%>" />
    </div>
</div>
<div class="row">
<div class="col-25">
    <label for="email">*Email:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="email" name="email" value="<%if(rh != null)out.print(rh.getEmail());%>" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="senha">Senha:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="senha" name="senha" value="<%if(rh != null && rh.getSenha() != null){
    	  														out.print(rh.getSenha());
    	  													} else {
    	  														out.print("");
    	  													}%>" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="telefone">*Telefone:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="telefone" name="telefone" value="<%if(rh != null)out.print(rh.getTelefone());%>" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="cargo">*Cargo:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="cargo" name="cargo" value="<%if(rh != null)out.print(rh.getCargo());%>" />
    </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_nivel">*Nivel</label>
      </div>
      <div class="col-75">
        <select  id="id_nivel" name="id_nivel" >
        	<!-- <option disabled selected >Selecione</option> -->
<%
	if(listaNivel != null){
		for (EntidadeDominio nivel : listaNivel){
			if(rh != null && rh.getNivel().getId() == nivel.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
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
        <label for="id_grupo_acesso">*Grupo Acesso</label>
      </div>
      <div class="col-75">
        <select  id="id_grupo_acesso" name="id_grupo_acesso" >
        	<!-- <option disabled selected >Selecione</option> -->
<%
	if(listaGrupoAcesso != null){
		for (EntidadeDominio grupo : listaGrupoAcesso){
			if(rh != null && rh.getGrupoAcesso().getId() == grupo.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
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
    <label for="valor_hora">*Valor-Hora:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="valor_hora" name="valor_hora" value="<%if(rh != null)out.print(rh.getValorHora());%>" OnKeyPress="return mascara(event, this, '####################');" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="valor_hora_extra">*Valor-Hora-Extra:</label>
    </div>
    <div class="col-75"> 
      <input type="text" id="valor_hora_extra" name="valor_hora_extra" value="<%if(rh != null)out.print(rh.getValorHoraExtra());%>" OnKeyPress="return mascara(event, this, '####################');" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="observacao">Observação:</label>
    </div>
    <div class="col-75"> 
      <input type="text" id="observacao" name="observacao" value="<%if(rh != null && rh.getObservacao() != null){
																		out.print(rh.getObservacao());
																	} else {
																		out.print("");
																	}%>" />
    </div>
</div>
 <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(rh != null){ %>
  <div class="row"> 
      <input type="submit" class="submit-verde" name="operacao" id="editar" value="editar" >
  </div>
  <br>
  <div class="row"> 
      <input type="submit" class="submit-vermelho" name="operacao" id="excluir" value="excluir" >
  </div>
  <% } else { %>
  <div class="row"> 
      <input type="submit" class="submit-verde" name="operacao" id="adicionar" value="adicionar" >
  </div>
  <% } %>
</form>
</div> 

</div>

</body>
</html>