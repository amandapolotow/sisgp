<%@page import="sisgp.dominio.ApontamentoHoras"%>
<%@page import="sisgp.core.util.Datas"%>
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
<body class="parallax">
<%
		ApontamentoHoras ah = (ApontamentoHoras)request.getAttribute("apontamento_horas");
		

		if(ah != null){
			System.out.println("Apontamento: " + ah.getId());
		}
		
		String data = "";

		
		if(ah != null){
			if(ah.getData() != null){
				data = Datas.dateToString(ah.getData());
			}
		}
%>
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Cadastro de Horas</h3>
<br>
<div class="container">
<form action="ApontamentoHoras" method="post">

<input type="hidden" id="id" name="id" value="<%if(ah != null && ah.getId() != null)out.print(ah.getId());%>"  />
<input type="hidden" id="id_sub_atividade" name="id_sub_atividade" value="<%if(ah != null && ah.getSubAtividade().getId() != null)out.print(ah.getSubAtividade().getId());%>"  />

    <div class="row">
<div class="col-25">
    <label for="nr_horas">Número de Horas:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="nr_horas" name="nr_horas" value="<%if(ah != null && ah.getNrHoras() != null)out.print(ah.getNrHoras());%>" OnKeyPress="return mascaraInt(event, this, '##########');" />
    </div>
    </div>
        <div class="row">
<div class="col-25">
    <label for="nr_horas_extras">Número de Horas Extras:</label>
    </div>
    <div class="col-75"> 
      <input type="text"  id="nr_horas_extras" name="nr_horas_extras" value="<%if(ah != null && ah.getNrHorasExtras() != null)out.print(ah.getNrHorasExtras());%>" OnKeyPress="return mascaraInt(event, this, '##########');" />
    </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="data">*Data</label>
      </div>
      <div class="col-75">
        <input type="text"  id="data" name="data" value="<% out.print(data);%>" OnKeyPress="return mascara(event, this, '##/##/####');" required />
      </div>
    </div>
<%
	if(ah != null && ah.getContasPagar() != null){
%>
    <div class="row">
      <div class="col-25">
        <label for="id_conta_pagar">Id Contas a Pagar</label>
      </div>
      <div class="col-75">
        <input type="text" readonly  id="id_conta_pagar" name="id_conta_pagar" value="<%if(ah != null && ah.getContasPagar().getId() != null)out.print(ah.getContasPagar().getId());%>" />
      </div>
    </div>
<%
    }
%>
<%
	String pagamento = "";
	if(ah != null && ah.getStPago() != null){
		if(ah.getStPago() == true){
			pagamento = "Pagamento realizado";
		}else{
			pagamento = "Pagamento pendente";
		}
		
%>
    <div class="row">
      <div class="col-25">
        <label for="dt_vencimento">Status Pagamento</label>
      </div>
      <div class="col-75">
        <input type="text"  id="dt_vencimento" name="dt_vencimento" value="<% out.print(pagamento);%>" />
      </div>
    </div>
<%
    }
%>
    <br>
<p>*Dados Obrigatórios</p>
<br>
  <%if(ah != null && ah.getId() != null && ah.getId() != 0){ %>
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