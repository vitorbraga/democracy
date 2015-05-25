<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>

<h2>Dashboard</h2>
<marquee class="admin-marquee">Neste momento ${fn:length(questions)} perguntas ativas</marquee>

<div>
	<h3>Perguntas ativas</h3>
	<c:choose>
	    <c:when test="${empty questions}">
	        <span class="empty-result-msg">Não existem perguntas ativas.</span>
	    </c:when>
	    <c:otherwise>
			<table id="tourney-result-table" class="table table-condensed table-hover">
			
				<tr><th>Pergunta</th><th>Status</th><th>Data</th></tr>
				<c:forEach var="question" items="${questions}">
					<tr questionId="${question.id}">
						<td>${question.question}</td>
						<td>${question.status}</td>
						<td>${question.date}</td>
					</tr>
				</c:forEach>
				
			</table>
	    </c:otherwise>
	</c:choose>

</div>
    