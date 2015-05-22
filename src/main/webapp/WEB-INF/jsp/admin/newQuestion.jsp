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
   		<div class="form-group">
			<label>Tipo</label> 
			<select name="question.type" class="form-control question-type">
				<option>Selecione uma opção</option>
				<option value="1">Múltipla escolha</option>
				<option value="2">Discursivas</option>
			</select>
		</div>
		<div id="multiple-choice-wrapper" style="display:none;">
	   		<div class="form-group answer-box">
	    		<label class="label_answer">Resposta 1</label>
				<input type="text" num="1" name="question.answers[0].answer" class="input_answer  form-control" placeholder="Resposta 1"/>
	   		</div>
	   		<div class="form-group answer-box">
	    		<label class="label_answer">Resposta 2</label>
				<input type="text" num="2" name="question.answers[1].answer" class="input_answer  form-control" placeholder="Resposta 2"/>
	   		</div>
   		</div>
   		<button type="submit" id="question-submit-but" class="btn btn-default">Enviar</button>
	</form>

	<br/>
	<div id="add-remove-answer-wrapper">
		<a href="javascript:void(0)" id="add-answer">+ Resposta</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="remove-answer">- Resposta</a>
	</div>
</div>

<script>
	
	$('#add-answer').on('click', function() {
		var answers = $('.answer-box');
		var num = answers.length;
		num++;
		var newAnswer = '<div class="form-group answer-box"><label class="label_answer">Resposta '+num+'</label>' +
			'<input type="text" num="'+ num +'" name="question.answers['+(num-1)+'].answer" class="input_answer  form-control" '+
			'placeholder="Resposta '+num+'"/></div>';
		$(newAnswer).insertBefore('#question-submit-but');
	});
	
	$('#remove-answer').on('click', function() {
		var answers = $('.answer-box');
		var length = answers.length;
		if(length > 2) {
			$(answers[length-1]).remove();
		}
	});

	$('.question-type').on('change', function() {
		var value = $(this).val();
		if(value == '1') {
			$('#multiple-choice-wrapper').fadeIn(200);
			$('#add-remove-answer-wrapper').fadeIn(200);
		} else {
			$('#multiple-choice-wrapper').fadeOut(200);
			$('#add-remove-answer-wrapper').fadeOut(200);
		}
	});

</script>


