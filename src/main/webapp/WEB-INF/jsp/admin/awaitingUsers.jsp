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
		vertical-align: middle;
	}
	
	.table-hover>tbody>tr:hover>td {
		background-color: #ECECED !important;
	}

</style>
<div>
	<h2>Usuários pendentes</h2>
	<div>
		<c:choose>
		    <c:when test="${empty users}">
		        <span class="empty-result-msg">Não existem usuários pendentes de aprovação.</span>
		    </c:when>
		    <c:otherwise>
				<table id="tourney-result-table" class="table table-condensed table-striped table-hover">
				
					<tr><th>Email</th><th>Nome</th><th>Data de cadastro</th><th>Ação</th></tr>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.email}</td>
							<td>${user.name}</td>
							<td>${user.dateRegistered}</td>
							<td>
							    <a href="javascript:void(0)" class="deactivate-link" userId="${user.id}">Desativar</a><br/>
							    <a href="javascript:void(0)" class="activate-link" userId="${user.id}">Ativar</a>	
							</td>
						</tr>
					</c:forEach>
					
				</table>
		    </c:otherwise>
		</c:choose>
	</div>
</div>


<script>

	$('.activate-link').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		var userId = $(this).attr('userId');
		$.ajax({
			type : 'post',
			url : basePath + 'user/activateUser',
			data : {
				userId : userId
			}
		}).done(function(data) {
			$('#loader-wrapper').fadeOut(150);
			if(data.success == 'true') {
				alert('Usuário ativado com sucesso.');
			} else {
				alert(data.message);
			}
		});
	});
	
	$('.deactivate-link').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		var userId = $(this).attr('userId');
		$.ajax({
			type : 'post',
			url : basePath + 'user/deactivateUser',
			data : {
				userId : userId
			}
		}).done(function(data) {
			$('#loader-wrapper').fadeOut(150);
			if(data.success == 'true') {
				alert('Usuário desativado com sucesso.');
			} else {
				alert(data.message);
			}
		});
	});

	
</script>
