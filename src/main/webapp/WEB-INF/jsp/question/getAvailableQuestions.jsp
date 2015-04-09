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
					<button type="button" class="btn btn-default comment-question">Comentários</button>
					<button type="button" class="btn btn-default partial-result">Resultado parcial</button>
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
			}
		}).done(function(data) {
			if(data.success == 'true') {
				alert('Pergunta ativada com sucesso.');
			} else {
				alert(data.message);
			}
		});
		
	});
	
	$('.comment-question').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var questionId = $(box).attr('questionId');
		
		$.ajax({
			type : 'GET',
			url : basePath + 'question/commentModal',
			data : {
				questionId : questionId
			},
			success : function(data) {
				//$('#loader-wrapper').fadeOut(150);
				var $modal = $(data);
			    $('body').append($modal);
			    $modal.filter('.modal').modal();
			    //bindEditButton();
			}
		});
		
	});
	
	$('.partial-result').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var questionId = $(box).attr('questionId');
		
		
		
	});


</script>