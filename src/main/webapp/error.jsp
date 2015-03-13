<%@ page pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" import="java.io.*" %>

<jsp:useBean id="date" class="java.util.Date" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Error</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>Error</h1>
        <p>Unfortunately an unexpected error has occurred. Below you can find the error details.</p>
        <h2>Details</h2>
        <ul>
        <%-- 
            <li><b>Status code:</b> <c:out value="${requestScope['javax.servlet.error.status_code']}" />
            <li><b>Timestamp:</b> <fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long" />
            <li><b>Message:</b> <c:out value="${requestScope['javax.servlet.error.message']}" />
            <li><b>Action:</b> <c:out value="${requestScope['javax.servlet.forward.request_uri']}" />
        --%>
        </ul>
        
        <h2>Stack Trace</h2>
        <div id="stacktrace" style="color: red;">
        <pre>
        <%
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			out.print(sw);
			sw.close();
			pw.close();
		%>
		</pre>
		</div>
        
    </body>
</html>