<%@page import="sisgp.dominio.StatusSistemaAtividade"%>
<%@page import="sisgp.dominio.StatusAtividade"%>
<%@page import="sisgp.dominio.Atividade"%>
<%@page import="sisgp.dominio.ContasReceber"%>
<%@page import="sisgp.core.util.Datas"%>
<%@page import="sisgp.dominio.Projeto"%>
<%@page import="sisgp.dominio.StatusContas"%>
<%@page import="sisgp.dominio.CategoriaContasPagar"%>
<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@page import="sisgp.dominio.ContasPagar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css.css">
<title>SisGp</title>
</head>
<body class="parallax">
<%
			Atividade at = (Atividade)request.getAttribute("atividade");
			
			Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
			Resultado resultStatusAtividade = (Resultado) getServletContext().getAttribute("resultado_status_atividade");
			Resultado resultStSistAtividade = (Resultado) getServletContext().getAttribute("resultado_st_sist_atividade");
			
			List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
			List<EntidadeDominio> listStatusAtividade = resultStatusAtividade.getListaEntidade();
			List<EntidadeDominio> listStSistAtividade = resultStSistAtividade.getListaEntidade();
			
			Integer idStSistAtividade = 0;
			String stSistAtividade = "";
			if(at != null){
				for(int i = 0; i<listStSistAtividade.size(); i++){
					if(listStSistAtividade.get(i).getId() == at.getStSistAtividade().getId()){
						idStSistAtividade = at.getStSistAtividade().getId();
						stSistAtividade = at.getStSistAtividade().getStatus();
					}
				}
			}
			
			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro Atividades</h3>
<br>
<div class="container">
<form action="Atividades" method="post">
<input type="hidden" id="id" name="id" value="<%if(at != null)out.print(at.getId());%>"  />
<input type="hidden" id="id_st_sist_atividade" name="id_st_sist_atividade" value="<%if(at != null)out.print(idStSistAtividade);%>"  />
<div class="row">
      <div class="col-25">
        <label for="codigo">*Código</label>
      </div>
      <div class="col-75">
        <input type="text"  id="codigo" name="codigo" value="<%if(at != null)out.print(at.getCodigo());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nome">*Nome</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome" name="nome" value="<%if(at != null)out.print(at.getNome());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="descricao">*Descrição</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao" value="<%if(at != null)out.print(at.getDescricao());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="id_projeto">*Projeto</label>
      </div>
      <div class="col-75">
        <select  id="id_projeto" name="id_projeto" >
        	<option disabled selected >Selecione</option>
<%
	if(listProjetos != null){
		for (EntidadeDominio projeto : listProjetos){
			if(at != null && at.getProjeto().getId() == projeto.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}	
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
        <label for="id_status_atividade">*Status Atividade</label>
      </div>
      <div class="col-75">
        <select  id="id_status_atividade" name="id_status_atividade" >
        	<option disabled selected >Selecione</option>
<%
	if(listStatusAtividade != null){
		for (EntidadeDominio status : listStatusAtividade){
			if(at != null && at.getStatusAtividade().getId() == status.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
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
<%
	if(at != null){
    
%>
    <div class="row">
      <div class="col-25">
        <label for="st_sist_atividade">Status Sistema Atividade</label>
      </div>
      <div class="col-75">
        <input type="text"  id="st_sist_atividade" name="st_sist_atividade" value="<% out.print(stSistAtividade);%>" readonly />
      </div>
    </div>
<%
	}
    
%>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(at != null){ %>
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