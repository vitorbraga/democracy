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

.comment-box {
	width: 90%;
	border: 1px solid;
	border-radius: 5px;
	overflow: hidden;
	padding: 10px;
}

.comment-username {
	font-weight: bold;
}

.comment-date {
	color: #B9B8BA;
}


</style>

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-content">
		<div class="modal-body">
			<span class="info-header"> 
				Comentários
			</span>
			<c:choose>
				<c:when test="${empty comments}">
					<span class="empty-result-msg">Esta enquete ainda não possui comentários.</span>
				</c:when>
				<c:otherwise>
					<c:forEach var="comment" items="${comments}">
						<div class="comment-box">
							<span class="comment-username">${comment.userName}</span>
							<span class="comment-date">(${comment.date})</span><br/>
							<span class="comment-comment">${comment.comment}</span>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<div>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">Enviar</button>
					</span>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			<button type="button" class="btn btn-default" id="edit-question-button" data-dismiss="modal">Salvar</button>
		</div>
	</div>
</div>
