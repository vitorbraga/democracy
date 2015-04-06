<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>

<style>
	#tourney-result-table {
		margin-top: 20px;
	}

	#tourney-result-table th {
		background-color: #096FAF;
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
	    <c:when test="${empty questions}">
	        <span class="empty-result-msg">Não foram encontrados resultados na busca.</span>
	    </c:when>
	    <c:otherwise>
			<table id="tourney-result-table" class="table table-condensed table-hover">
			
				<tr><th>Pergunta</th><th>Status</th><th>Data</th><th>Ação</th></tr>
				<c:forEach var="question" items="${questions}">
					<tr questionId="${question.id}">
						<td>${question.question}</td><td>${question.status}</td><td>${question.date}</td>
						<td>
							<c:choose>
							    <c:when test="${question.status == \"Ativo\"}">
							        <a href="#">Desativar</a>
							    </c:when>
							    <c:otherwise>
							    	<a href="#">Ativar</a>	
							    </c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				
			</table>
	    </c:otherwise>
	</c:choose>

</div>


<script>
	$('tr').on('click', function() {
		
		var questionId = $(this).attr('questionId');
		$.ajax({
			type : 'GET',
			url : basePath + 'question/questionDetails',
			data : {
				questionId : questionId
			},
			success : function(data) {
				var $modal = $(data);
			    $('body').append($modal);
			    $modal.filter('.modal').modal();
			    bindEditButton();
			}
		});
	});

	function bindEditButton() {
		$('#edit-question-button').on('click', function() {
			var edit = getSearchFilters();
			$.ajax({
				type : 'post',
				url : basePath + 'question/edit',
				data : edit,
				success : function(data) {
					alert('Editado com sucesso.');
				}
			});
		});
	}
</script>
