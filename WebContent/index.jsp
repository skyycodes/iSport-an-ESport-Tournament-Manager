<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="CSS/index.css" type="text/css">
	<meta charset="UTF-8">
	<title>"Negozio di modelle da torneo"</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	
	<br>
	<br>
	
	<div class="home">
	
		<div class="split robe">
			<h3>Robe sulle squadre</h3>
		</div>
	
		<div class="split altriTornei">
			<h3>Dai un occhiata a questi tornei</h3>
				<%@ include file="ListaTornei.jsp"%>
		</div>
	
		<div class="split news">
			<h2> NEWS</h2>

			<ul>
				<li>Adesso fino a 4 tecnici live</li>
				<li> Montefusco merda</li>
		
			</ul>
		</div>
	</div>
<%@ include file="Footer.jsp"%>
</body>
</html>