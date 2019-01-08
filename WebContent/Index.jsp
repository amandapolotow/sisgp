<%@page import="sisgp.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="sisgp.core.aplicacao.Resultado"%>
<%@page import="sisgp.dominio.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css.css">
<title>SisGP</title>
</head>
<body class="parallax">
<%
	Resultado resultadoLogin = (Resultado) session.getAttribute("login_resultado");
	Login login = (Login) session.getAttribute("login");
	if(login != null && login.getFlgNaoEncontrado() == true){
		request.getSession().invalidate();
	}
%>
<div class="container-login">
<h1>SisGP</h1>
	<h3>Login</h3>
	<br>
<form  action="Login" method="post">
 <div class="row"> 
	<div class="col-25">
    <label for="email">Email:</label>
    </div>
    <div class="col-75">
      <input type="text"  id="email" name="email" />
    </div>
</div>
 <div class="row">
<div class="col-25">
    <label  for="senha">Senha:</label>
    </div>
    <div class="col-75"> 
      <input type="password"  id="senha" name="senha" />
    </div>
</div>
<br>
  <div class="row"> 
      <input type="submit" class="submit-verde" value="consultar" id="operacao" name="operacao">
  </div>
  <%
	
	if(resultadoLogin !=null && resultadoLogin.getMensagem() != null){
		out.print(resultadoLogin.getMensagem());
	}
%>
</form>
</div>
</body>
</html>