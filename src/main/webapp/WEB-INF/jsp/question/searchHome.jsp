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
	    <c:when test="${empty questions}">
	        <span class="empty-result-msg">N�o foram encontrados resultados na busca.</span>
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


<script>

	var lastSearch = {};

	$('.activate-link').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		var questionId = $(this).attr('questionId');
		$.ajax({
			type : 'GET',
			url : basePath + 'question/activateQuestion',
			data : {
				questionId : questionId
			}, 
			error : function(data) {
				$('#loader-wrapper').fadeOut(150);
				var error = jQuery.parseJSON(data.responseText);
				sweetAlert("Oops...", error.message, "error");
			}
		}).done(function(data) {
			$('#loader-wrapper').fadeOut(150);
			swal("Sucesso!", 'Pergunta ativada com sucesso.', "success");
			doSearchQuestion();
		});
	});
	
	$('.deactivate-link').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		
		var questionId = $(this).attr('questionId');
		$.ajax({
			type : 'GET',
			url : basePath + 'question/deactivateQuestion',
			data : {
				questionId : questionId
			},
			error : function(data) {
				$('#loader-wrapper').fadeOut(150);
				var error = jQuery.parseJSON(data.responseText);
				sweetAlert("Oops...", error.message, "error");
			},
		}).done(function(data) {
			$('#loader-wrapper').fadeOut(150);
			swal("Sucesso!", 'Pergunta desativada com sucesso.', "success");
			doSearchQuestion();
		});
	});

	$('table tr td:not(:last-child)').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		var questionId = $(this).parent().attr('questionId');
		$.ajax({
			type : 'GET',
			url : basePath + 'question/questionDetails',
			data : {
				questionId : questionId
			},
			success : function(data) {
				
				$('#loader-wrapper').fadeOut(150);
				
				var $modal = $(data);
			    $('body').append($modal);
			    $modal.filter('.modal').modal();
				
			    $('.modal').on('hidden.bs.modal', function (e) {
			        $(this).remove();
			    });

			    bindEditButton();
			}
		});
	});

	function bindEditButton() {
		$('#edit-question-button').on('click', function() {
			$('#loader-wrapper').fadeIn(200);
			var edit = getSearchFilters();
			$.ajax({
				type : 'post',
				url : basePath + 'question/edit',
				data : edit,
				success : function(data) {
					$('#loader-wrapper').fadeOut(150);
					alert('Editado com sucesso.');
				}
			});
		});
	}
	
	
</script>
