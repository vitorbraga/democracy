<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>

<div class="container 75%">
	<h2>Buscar Pergunta</h2>
	<div class="form-group">
		<label class="label_answer">Per�odo</label> 
		<select name="search.date" class="form-control search-field">
			<option value="0">Selecione uma op��o</option>
			<option value="1">�ltima semana</option>
			<option value="2">�ltimo m�s</option>
		</select>
	</div>
	<button type="button" id="question-search-but" class="btn btn-default">Buscar</button>
	<div id="questions-result"></div>
</div>

<script>
	$('#question-search-but').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		doSearchQuestion();
	});
</script>