<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>

<style>
	#tourney-result-table {
		margin-top: 20px;
	}

	#tourney-result-table th {
		background-color: #096FAF;
		color: white;
	}

	#tourney-result-table tr td {
		cursor: pointer;
	}

	#tourney-result-table td {
		background-color: white;
		color: #555;
	}
	
	.table-hover>tbody>tr:hover>td {
		background-color: #ECECED !important;
	}

</style>
<div>

	<c:choose>
	    <c:when test="${empty users}">
	        <span class="empty-result-msg">Não foram encontrados resultados na busca.</span>
	    </c:when>
	    <c:otherwise>
			<table id="tourney-result-table" class="table table-condensed table-hover">
			
				<tr><th>Email</th><th>Nome</th><th>Data de cadastro</th></tr>
				<c:forEach var="user" items="${users}">
					<tr userId="${user.id}">
						<td>${user.email}</td>
						<td>${user.name}</td>
						<td>${user.dateRegistered}</td>
					</tr>
				</c:forEach>
				
			</table>
	    </c:otherwise>
	</c:choose>

</div>
