<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../include.jsp" %>

<style>

.modal {
	left: 50%;
	bottom: auto;
	right: auto;
	padding: 0;
	width: 500px;
	margin-left: -250px;
	background-color: #ffffff;
	border: none;
	-webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
	box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
	background-clip: padding-box;
}

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

.info-data {
	font-size: 11pt;
	letter-spacing: 0.5px;
}

.prize-box {
	margin-top: 10px;
	padding: 10px;
	width: 100%;
}

.white-back {
	background: rgba(255, 255, 255, 0.15);
	padding: 10px;
	margin-top: 20px;
}

.prize-title {
	font-size: 14pt;
	display: block;
}
</style>

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-content">
		<div class="modal-body">
			<span class="info-header"> 
				Resultados parciais
			</span>


		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			<button type="button" class="btn btn-default" id="edit-question-button" data-dismiss="modal">Salvar</button>
		</div>
	</div>
</div>
