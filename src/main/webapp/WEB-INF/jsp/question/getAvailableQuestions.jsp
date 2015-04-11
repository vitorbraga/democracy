<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>

<style>

	.question-box {
		position: relative;
		width: 95%;
		height: auto;
		margin-top: 10px;
		overflow: hidden;
		border: 1px solid;
		border-radius: 5px;
		padding: 10px;
	}

	.question-question {
		font-size: 14pt;
	}
	
	.question-date {
		font-size: 12pt;
		color: #B9B8BA;
	}
	
	.question-comments {
		margin-top: 10px;
		padding: 10px;
		width: 95%;
		padding: 5px;
		display: none;
	}
</style>

<div>
	<h2>Perguntas ativas</h2>

	<c:choose>
	    <c:when test="${empty questions}">
	        <span class="empty-result-msg">Não existem perguntas ativas.</span>
	    </c:when>
	    <c:otherwise>
			<c:forEach var="question" items="${questions}">
				<div class="question-box" questionId="${question.id}">
					<span class="question-question">${question.question}</span>
					<span class="question-date">(${question.dateActivated})</span>
					<c:forEach var="answer" items="${question.answers}">
						<div class="form-group">
							<div class="radio">
							  	<label><input name="question-${question.id}" ${question.userAnswer == answer.id ? 'checked' : ''} type="radio" value="${answer.id}">
							  		${answer.answer}
							  	</label>
							</div>
						</div>
					</c:forEach>
					<button type="button" class="btn btn-default answer-question">Responder</button>
					<button type="button" class="btn btn-default comment-question">Comentários (${question.numComments})</button>
					<button type="button" class="btn btn-default partial-result">Resultado parcial</button>
					<div class="question-comments" questionId="${question.id}">
						<img src="${pageContext.request.contextPath}/resources/images/loading-small.gif" />
					</div>
				</div>
			</c:forEach>
	    </c:otherwise>
	</c:choose>
	
</div>

<script>

	$('.answer-question').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var questionId = $(box).attr('questionId');
		
		var answerId = $('input[name="question-'+ questionId +'"]:checked').val();
		$.ajax({
			type : 'POST',
			url : basePath + 'question/answerQuestion',
			data : {
				questionId : questionId,
				answerId : answerId,
			}, 
			error : function(data) {
				$('#loader-wrapper').fadeOut(150);
				var error = jQuery.parseJSON(data.responseText);
				sweetAlert("Oops...", error.message, "error");
			}
		}).done(function(data) {
			$('#loader-wrapper').fadeOut(150);
			swal("Sucesso!", 'Sua resposta foi salva!', "success");
		});
		
	});
	
	$('.comment-question').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var commentsDiv = $(this).siblings('.question-comments');
		
		$(commentsDiv).fadeIn(150); 
		var questionId = $(box).attr('questionId');
		
		$.ajax({
			type : 'GET',
			url : basePath + 'question/commentBox',
			data : {
				questionId : questionId
			},
			success : function(data) {
				$(commentsDiv).html(data);
			}
		});
		
	});
	
	$('.partial-result').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var questionId = $(box).attr('questionId');
		
		$.ajax({
			type : 'GET',
			url : basePath + 'question/partialResultsModal',
			data : {
				questionId : questionId
			},
			success : function(data) {
				var $modal = $(data);
			    $('body').append($modal);
			    $modal.filter('.modal').modal();
			}
		});
		
	});


</script>