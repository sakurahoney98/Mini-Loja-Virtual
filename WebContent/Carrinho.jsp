<%@page import="bean.Cd"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>I Love Discos - Carrinho</title>
<link rel="stylesheet" type="text/css" href="estilo2.css" />
<link rel="shortcut icon" href="images/Imagem2.png" type="image/x-icon">
</head>
<body>
	<div class="header">
		<h1>Meu carrinho</h1>
		<a href="/Prova/Pagina_Inicial.html">Menu principal</a>
	</div>

	<c:if test="${!empty lista }">
		<form action="/Prova/adicionar" method="post">
			<c:set var="total" scope="session" value="0"></c:set>
			<c:forEach items="${lista }" var="objeto">

				<div class="select">
					<input type="checkbox" name="${objeto }">
				</div>

				<div class="image">
					<img alt="" src="images/${objeto.getTitulo() }.png">
				</div>
				<div class=conteudo">
					<div class="titulo">
						<c:out value="${objeto.getTitulo() }"></c:out>
					</div>

					Valor real:
					<c:out value="${objeto.getValor() }"></c:out>
					<div id="desconto">

						<fmt:parseNumber var="i" scope="session" type="number"
							value="${objeto.getValor()}" />
						<fmt:formatNumber var="precoF" type="number" pattern="###.##"
							value="${i - (i * 0.35) }"></fmt:formatNumber>

						Valor com desconto: R$
						<c:out value="${precoF}" />

					</div>

					<br>Quantidade:
					<c:out value="${objeto.getQtde() }"></c:out>


				</div>


				<br>
				<hr>
			</c:forEach>

			<p>
				Valor total:
				<c:out value="${valorT }"></c:out>
			</p>

			<div id="button">
				<button type="submit">Remover selecionados</button>
			</div>
	</c:if>
	<c:if test="${empty lista }">
		<div id="empty">
			<h1>Carrinho vazio!</h1>
		</div>

	</c:if>



	</form>

	<footer id="footer"> @copyright 2021 Sakura all rights
		reserved </footer>
</body>
</html>