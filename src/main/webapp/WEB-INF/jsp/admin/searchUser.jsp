<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div>
	<h2>Buscar Usuário</h2>
	<div class="form-group">
		<label>Status</label> 
		<select name="search.status" class="form-control search-field">
			<option value="0">Selecione uma opção</option>
			<option value="1">Ativo</option>
			<option value="2">Inativo</option>
		</select>
	</div>
	<div class="form-group">
	  	<label>Email:</label>
	  	<input name="search.email" placeholder="Email" class="form-control search-field">
	</div>
	<div class="form-group">
	  	<label>Name:</label>
	  	<input name="search.name" placeholder="Nome" class="form-control search-field">
	</div>
	
	<button type="button" id="user-search-but" class="btn btn-default">Buscar</button>
	
	<div id="users-result"></div>
</div>