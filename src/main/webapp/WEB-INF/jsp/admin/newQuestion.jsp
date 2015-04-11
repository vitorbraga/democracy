<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>

<div>
	<h2>Cadastrar Pergunta</h2>

	<form id="form-new-question" method="post" action="<c:url value="/question/new"/>">
		<div class="form-group">
			<label>Pergunta</label>
			<textarea rows="2" name="question.question" class="input_question form-control" placeholder="Nova pergunta..."></textarea>
   		</div>
   		<div class="form-group answer-box">
    		<label class="label_answer">Resposta 1</label>
			<input type="text" num="1" name="question.answers[0].answer" class="input_answer  form-control" placeholder="Resposta 1"/>
   		</div>
   		<div class="form-group answer-box">
    		<label class="label_answer">Resposta 2</label>
			<input type="text" num="2" name="question.answers[1].answer" class="input_answer  form-control" placeholder="Resposta 2"/>
   		</div>
   		<button type="submit" id="question-submit-but" class="btn btn-default">Enviar</button>
	</form>

	<br/><a href="javascript:void(0)" id="add-answer">+ Resposta</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="remove-answer">- Resposta</a>
</div>


