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
		border: 1px dotted;
		padding: 10px;
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
					<p>${question.question}</p>
					<c:forEach var="answer" items="${question.answers}">
						<div class="form-group">
							<div class="radio">
							  	<label><input name="question-${question.id}" type="radio" value="${answer.id}">${answer.answer}</label>
							</div>
						</div>
					</c:forEach>
					<button type="button" class="btn btn-default answer-question">Responder</button>
				</div>
			</c:forEach>
	    </c:otherwise>
	</c:choose>
	
</div>

<script>

	$('.answer-question').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var questionId = $(box).attr('questionId');
		
		var answerId = $('input[name="question-"'+ questionId +'"]:checked').val();
		$.ajax({
			type : 'GET',
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


</script>