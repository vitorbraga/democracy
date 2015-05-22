<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp"%>

<div>
	<h2>Buscar Pergunta</h2>
	<div class="form-group">
		<label>Status</label> 
		<select name="search.status" class="form-control search-field">
			<option value="0">Selecione uma op��o</option>
			<option value="1">Ativo</option>
			<option value="2">Inativo</option>
		</select>
	</div>
	<div class="form-group">
		<label>Tipo</label> 
		<select name="search.type" class="form-control search-field">
			<option value="0">Selecione uma op��o</option>
			<option value="1">M�ltipla escolha</option>
			<option value="2">Discursivas</option>
		</select>
	</div>
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