<%@page import="sisgp.dominio.RecursoHumano"%>
<%@page import="sisgp.dominio.SubAtividade"%>
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
			SubAtividade sat = (SubAtividade)request.getAttribute("sub_atividade");
			
			Resultado resultAtividade = (Resultado) getServletContext().getAttribute("resultado_atividade");
			Resultado resultRecursoHumano = (Resultado) getServletContext().getAttribute("resultado_recurso_humano");
			Resultado resultStatusAtividade = (Resultado) getServletContext().getAttribute("resultado_status_atividade");
			Resultado resultStSistAtividade = (Resultado) getServletContext().getAttribute("resultado_st_sist_atividade");
			
			List<EntidadeDominio> listAtividades = resultAtividade.getListaEntidade();
			List<EntidadeDominio> listRecursosHumanos = resultRecursoHumano.getListaEntidade();
			List<EntidadeDominio> listStatusAtividade = resultStatusAtividade.getListaEntidade();
			List<EntidadeDominio> listStSistAtividade = resultStSistAtividade.getListaEntidade();
			
			Integer idStSistAtividade = 0;
			String stSistAtividade = "";
			
			if(sat != null){
				for(int i = 0; i<listStSistAtividade.size(); i++){
					if(listStSistAtividade.get(i).getId() == sat.getStSistAtividade().getId()){
						idStSistAtividade = sat.getStSistAtividade().getId();
						stSistAtividade = sat.getStSistAtividade().getStatus();
					}
				}
			}
			
			
			String dtInicio = "";
			String dtFim = "";
			
			if(sat != null){
				if(sat.getDtInicio() != null){
					dtInicio = Datas.dateToString(sat.getDtInicio());
				}
				if(sat.getDtFim() != null){
					dtFim = Datas.dateToString(sat.getDtFim());
				}
			}
			
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro Subatividades</h3>
<br>
<div class="container">
<form action="SubAtividades" method="post">
<input type="hidden" id="id" name="id" value="<%if(sat != null)out.print(sat.getId());%>"  />
<input type="hidden" id="id_st_sist_atividade" name="id_st_sist_atividade" value="<%if(sat != null)out.print(idStSistAtividade);%>"  />
<div class="row">
      <div class="col-25">
        <label for="codigo">*Código</label>
      </div>
      <div class="col-75">
        <input type="text"  id="codigo" name="codigo" value="<%if(sat != null)out.print(sat.getCodigo());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nome">*Nome</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nome" name="nome" value="<%if(sat != null)out.print(sat.getNome());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="descricao">*Descrição</label>
      </div>
      <div class="col-75">
        <input type="text"  id="descricao" name="descricao" value="<%if(sat != null)out.print(sat.getDescricao());%>" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="nr_horas_previsto">*Nr. Horas Previsto</label>
      </div>
      <div class="col-75">
        <input type="text"  id="nr_horas_previsto" name="nr_horas_previsto" value="<%if(sat != null)out.print(sat.getNrHorasPrevisto());%>" OnKeyPress="return mascaraInt(event, this, '##########');" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_inicio">*Data Início</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_inicio" name="dt_inicio" value="<%if(sat != null)out.print(dtInicio);%>" OnKeyPress="return mascara(event, this, '##/##/####');" required />
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="dt_fim">*Data Fim</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_fim" name="dt_fim" value="<%if(sat != null)out.print(dtFim);%>" OnKeyPress="return mascara(event, this, '##/##/####');" required />
      </div>
    </div>
 <div class="row">
      <div class="col-25">
        <label for="id_recurso_humano">*Recursos Humanos</label>
      </div>
      <div class="col-75">
        <select  id="id_recurso_humano" name="id_recurso_humano" required>
        	<option disabled selected >Selecione</option>
<%
	if(listRecursosHumanos != null){
		for (EntidadeDominio rh : listRecursosHumanos){
			if(sat != null && sat.getRecursoHumano().getId() == rh.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
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
    <div class="row">
      <div class="col-25">
        <label for="id_atividade">*Atividade</label>
      </div>
      <div class="col-75">
        <select  id="id_atividade" name="id_atividade" >
        	<option disabled selected >Selecione</option>
<%
	if(listAtividades != null){
		for (EntidadeDominio atividade : listAtividades){
			if(sat != null && sat.getAtividade().getId() == atividade.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
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
        <label for="id_status_atividade">*Status Atividade</label>
      </div>
      <div class="col-75">
        <select  id="id_status_atividade" name="id_status_atividade" required >
        	<option disabled selected >Selecione</option>
<%
	if(listStatusAtividade != null){
		for (EntidadeDominio status : listStatusAtividade){
			if(sat != null && sat.getStatusAtividade().getId() == status.getId()){
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
	if(sat != null){
    
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
  <%if(sat != null){ %>
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