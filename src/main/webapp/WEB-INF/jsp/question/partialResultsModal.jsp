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
				Detalhes da pergunta
			</span>

			<div class="white-back">
				<table class="info-table">
					<tr>
						<td colspan=2>
							<span class="info-label">Pergunta</span>
							<input type="text" name="edit.question" class="form-control search-field" value="${question.question}" /> 
							<input type="hidden" name="edit.id" class="search-field" value="${question.id}" />
						</td>
					</tr>
					<c:forEach var="answer" items="${question.answers}" varStatus="myIndex">
						<tr>
							<td colspan=2>
								<span class="info-label">Resposta ${myIndex.index+1}</span> 
								<input type="text" name="edit.answers[${myIndex.index}].answer" class="form-control search-field" value="${answer.answer}" />
								<input type="hidden" name="edit.answers[${myIndex.index}].id" class="search-field" value="${answer.id}" />
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td><span class="info-label">Data criação</span> <span
							class="info-data">${question.date}</span></td>
						<td><span class="info-label">Status</span> 
							<select class="form-control search-field" name="edit.status">
								<c:forEach items="${question.allStatus}" var="sta">
									<option value="${sta.id}"
										${sta.desc == question.status ? 'selected' : ''}>${sta.desc}</option>
								</c:forEach>
							</select> 
							
					</tr>
				</table>
			</div>

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			<button type="button" class="btn btn-default" id="edit-question-button" data-dismiss="modal">Salvar</button>
		</div>
	</div>
</div>
