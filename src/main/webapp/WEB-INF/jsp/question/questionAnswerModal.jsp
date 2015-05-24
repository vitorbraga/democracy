<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>

<style>

.info-header {
	font-size: 20pt;
	display: block;
}

.info-header img {
	display: inline-block;
}

.back-button {
	cursor: pointer;
	margin-right: 10px;
}

.info-table {
	width: 100%;
	border: none;
	table-layout: fixed;
}

.table-first-row {
	width: 150px;
}

.info-table td,.info-table th {
	padding: 4px;
}

.info-label {
	font-size: 12pt;
	display: block;
	font-weight: bold;
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

.user-discursive-answer {
	font-style: italic;
	display: block;
	padding: 5px 5px;
	color: black;
}
</style>

<div class="modal fade custom-modal" id="basicModal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog" style="margin:90px auto;">
		<div class="modal-content">
			<div class="modal-body">
				<span class="info-header"> 
					Detalhes da pergunta
				</span>
	
				<div class="question-box" questionId="${question.id}">
					<span class="question-question">${question.question}</span>
					<span class="question-date">(${question.dateActivated})</span><br/>
					<c:if test="${question.typeInt == 1}">
						<br/>
					   	<c:forEach var="answer" items="${question.answers}">
							<div class="form-group">
								<fieldset>
								  	<input name="question-${question.id}" ${question.userAnswer == answer.id ? 'checked' : ''} type="radio" id="${answer.id}" value="${answer.id}">
								  	<label for="${answer.id}">${answer.answer}</label>
								</fieldset>
							</div>
						</c:forEach>
						<button type="button" class="btn btn-default answer-question-multiple">Responder</button>
					</c:if>
	    			<c:if test="${question.typeInt == 2}">
	    				<c:choose>
	    					<c:when test="${not empty question.userDiscursiveAnswer}">
						        <span class="user-discursive-answer">${question.userDiscursiveAnswer}</span>
						    </c:when>
						    <c:otherwise>
			    				<div class="form-group">
							      	<label for="dicursive-answer">Resposta:</label>
							      	<textarea class="form-control" rows="5" id="dicursive-answer" name="question-${question.id}"></textarea>
							    </div>
							    <button type="button" class="btn btn-default answer-question-discursive">Responder</button>
						    </c:otherwise>
	    				</c:choose>
	    			</c:if>
		    			
					<button type="button" class="btn btn-default comment-question">Comentários (${question.numComments})</button>
					<button type="button" class="btn btn-default partial-result">Resultado parcial</button>
					<div class="question-comments" questionId="${question.id}">
						<img src="${pageContext.request.contextPath}/resources/images/loading-small.gif" />
					</div>
				</div>
	
			</div>
		</div>
	</div>
</div>

<script>

$('.answer-question-multiple').on('click', function() {
		
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

	$('.answer-question-discursive').on('click', function() {
		
		var box = $(this).parent('.question-box');
		
		var questionId = $(box).attr('questionId');
		
		var answer = $('textarea[name="question-'+ questionId +'"]').val();
		
		$.ajax({
			type : 'POST',
			url : basePath + 'question/answerDiscursiveQuestion',
			data : {
				questionId : questionId,
				answer : answer,
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