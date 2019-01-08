<!--menus-->
<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@page import="sisgp.dominio.Login"%>
<div class="header">
  <a href="Home.jsp" class="logo">SisGP</a>
  <div class="header-right">
    <!-- <a href="#">Trocar Senha</a> -->
    <a href="Logout.jsp">Sair</a>
  </div>
  <%
	//checar sessao
	Login login = (Login) session.getAttribute("login");
	
  if(login != null && login.getFlgNaoEncontrado() == true){
		//request.getSession().invalidate();
		response.sendRedirect("Index.jsp");
	}
	
%>
</div>
	 <div class="sidebar">	
	 <a href="ApontamentoHorasLista1.jsp">Apontamento de Horas</a><!-- OK -->
	  <a href="ControleHorasLista.jsp">Controle de Horas</a>
<%
	if(login != null && login.getGrupoAcesso() !=null){
		if(login.getGrupoAcesso().getGrupoAcesso().trim().equals("GESTOR") || login.getGrupoAcesso().getGrupoAcesso().trim().equals("GESTOR_GERAL")){
%>
		<a href="ProjetosLista.jsp">Projetos</a><!-- OK -->
	  <a href="AtividadesLista.jsp">Atividades</a>
	  <a href="SubAtividadesLista.jsp">Subatividades</a>
	  <a href="ClientesLista.jsp">Clientes</a>
	  <a href="RecursosHumanosLista.jsp">Recursos Humanos</a><!-- OK -->
	  <a href="RecursosLista.jsp">Recursos</a>
	  <a href="ControleEstoqueLista.jsp">Controle Estoque</a>
	  <a href="GerarContasLista.jsp">Gerar Contas</a><!-- OK -->
	  <a href="ContasPagarLista.jsp">Contas Pagar</a><!-- OK -->
	  <a href="ContasReceberLista.jsp">Contas Receber</a>	  
	  <a href="CategoriaContasPagarLista.jsp">Categoria Contas Pagar</a>
	  <a href="CategoriaContasReceberLista.jsp">Categoria Contas Receber</a>


<%			
		}
	}

	if(login != null && login.getGrupoAcesso() !=null){
		if(login.getGrupoAcesso().getGrupoAcesso().trim().equals("GESTOR_GERAL")){
%>	  

	  <a href="ControleFinanceiroLista.jsp">Controle Financeiro</a>
	  <a href="GraficoLista.jsp">Análise</a>
<%			
		}
	}
%>	
	</div>
	<script>
	function mascara(evt, campo, padrao) {  
        //testa a tecla pressionada pelo usuario  
        var charCode = (evt.which) ? evt.which : evt.keyCode;  
        if (charCode == 8) return true; //tecla backspace permitida  
        if (charCode != 46 && (charCode < 48 || charCode > 57)) return false; //apenas numeros            
        campo.maxLength = padrao.length; //Define dinamicamente o tamanho maximo do campo de acordo com o padrao fornecido  
        //aplica a mascara de acordo com o padrao fornecido  
        entrada = campo.value;  
        if (padrao.length > entrada.length && padrao.charAt(entrada.length) != '#') {  
             campo.value = entrada + padrao.charAt(entrada.length);                 
        }  
        return true;  
   } 
	
	function mascaraInt(evt, campo, padrao) {  
        //testa a tecla pressionada pelo usuario  
        var charCode = (evt.which) ? evt.which : evt.keyCode;  
        if (charCode == 8) return true; //tecla backspace permitida  
        if (charCode < 48 || charCode > 57) return false; //apenas numeros            
        campo.maxLength = padrao.length; //Define dinamicamente o tamanho maximo do campo de acordo com o padrao fornecido  
        //aplica a mascara de acordo com o padrao fornecido  
        entrada = campo.value;  
        if (padrao.length > entrada.length && padrao.charAt(entrada.length) != '#') {  
             campo.value = entrada + padrao.charAt(entrada.length);                 
        }  
        return true;  
   }
	

	</script>
<!--menus-->