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
			ContasPagar cp = (ContasPagar)request.getAttribute("contas_pagar");
			
			Resultado resultCatContasPagar = (Resultado) getServletContext().getAttribute("resultado_categoria_contas_pagar");
			Resultado resultStatusContas = (Resultado) getServletContext().getAttribute("resultado_status_contas");
			Resultado resultProjeto = (Resultado) getServletContext().getAttribute("resultado_projeto");
			
			List<EntidadeDominio> listCatContasPagar = resultCatContasPagar.getListaEntidade();
			List<EntidadeDominio> listStContas = resultStatusContas.getListaEntidade();
			List<EntidadeDominio> listProjetos = resultProjeto.getListaEntidade();
			
			String dtLancamento = "";
			String dtVencimento = "";
			
			if(cp != null){
				if(cp.getDtLancamento() != null){
					dtLancamento = Datas.dateToString(cp.getDtLancamento());
				}
				if(cp.getDtVencimento() != null){
					dtVencimento = Datas.dateToString(cp.getDtVencimento());
				}
			}
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Contas a Pagar</h3>
<br>
<div class="container">
<form action="ContasPagar" method="post">
<input type="hidden" id="id" name="id" value="<%if(cp != null)out.print(cp.getId());%>"  />
<div class="row">
      <div class="col-25">
        <label for="id_cat_contas_pagar">Categoria de Contas a Pagar</label>
      </div>
      <div class="col-75">
        <select  id="id_cat_contas_pagar" name="id_cat_contas_pagar" >
        	<option disabled selected >Selecione</option>
<%
	if(listCatContasPagar != null){
		for (EntidadeDominio catContasPagar : listCatContasPagar){
			if(cp != null && cp.getCategoriaContasPagar().getId() == catContasPagar.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}			
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
        <label for="id_status_contas">*Status de Contas a Pagar</label>
      </div>
      <div class="col-75">
        <select  id="id_status_contas" name="id_status_contas" >
        	<option disabled selected >Selecione</option>
<%
	if(listStContas != null){
		for (EntidadeDominio statusContas : listStContas){
			if(cp != null && cp.getStatusContas().getId() == statusContas.getId()){
				out.print("<option selected value='");
			}else{
				out.print("<option value='");
			}
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
    <%
    if(cp != null){
    %>
    <div class="row">
      <div class="col-25">
        <label for="dt_lancamento">Data de Lançamento</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_lancamento" name="dt_lancamento" value="<% out.print(dtLancamento);%>" readonly />
      </div>
    </div>
    <%
    }
    %>
    <div class="row">
      <div class="col-25">
        <label for="dt_vencimento">*Data de Vencimento</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_vencimento" name="dt_vencimento" value="<%if(cp != null)out.print(dtVencimento);%>" OnKeyPress="return mascara(event, this, '##/##/####');" />
      </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="valor">*Valor do Título:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="valor" name="valor" value="<%if(cp != null)out.print(cp.getValor());%>" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="nr_parcelas">*Número de Parcelas:</label>
    </div>
    <div class="col-75"> 
<% if(cp != null){ %>
      <input type="text"  id="nr_parcelas" name="nr_parcelas" value="<%if(cp != null)out.print(cp.getNrParcelas());%>" readonly />
<% }else{ %>
      <input type="text"  id="nr_parcelas" name="nr_parcelas" value="1" />
<% }  %>
    </div>
    </div>
        <div class="row">
<div class="col-25">
    <label for="contrato">Contrato:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="contrato" name="contrato" value="<%if(cp != null && cp.getContrato() != null){
    	  															out.print(cp.getContrato());
    	  														} else {
    	  															out.print("");
    	  														}%>" />
    </div>
    </div>
    <div class="row">
<div class="col-25">
    <label for="observacao">Observação:</label>
    </div>
    <div class="col-75"> 
      <input type="text" id="observacao" name="observacao" value="<%if(cp != null && cp.getObservacao() != null){
    	  															out.print(cp.getObservacao());
    	  														} else {
    	  															out.print("");
    	  														}%>" />
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
			if(cp != null && cp.getProjeto().getId() == projeto.getId()){
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
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(cp != null){ %>
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