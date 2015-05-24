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

.info-data {
	font-size: 11pt;
	letter-spacing: 0.5px;
}

.comment-box {
	width: 90%;
	border-bottom: 1px solid;
	overflow: hidden;
	padding: 10px;
}

.comment-username {
	font-weight: bold;
}

.comment-date {
	color: #B9B8BA;
}

.make-comment-ct {
	margin-top: 10px;
}

</style>

<div>
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
	<div class="make-comment-ct">
		<div class="input-group">
			<input type="text" questionId="${questionId}" class="form-control input-comment" placeholder="">
			<span class="input-group-btn">
				<button class="btn btn-default send-comment-button" questionId="${questionId}" type="button">Enviar</button>
			</span>
		</div>
	</div>
</div>

<script>

	$('.send-comment-button').on('click', function() {
		
		var questionId = $(this).attr('questionId');
		
		var comment = $('.input-comment[questionId="'+ questionId +'"]').val();
		
		$.ajax({
			type : 'POST',
			url : basePath + 'question/makeComment',
			data : {
				questionId : questionId,
				comment : comment,
			}
		}).done(function(data) {
			$('.question-comments[questionId="'+ questionId +'"]').html(data);
		});
	});
	
</script>
		
