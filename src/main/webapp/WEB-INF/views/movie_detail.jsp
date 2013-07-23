<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>

	<jsp:body>
		<c:if test="${not empty message}">
		<div class="message green">${message}</div>
		</c:if>
		<form:form commandName="movie">
		<table>
			<tr>
				<td>Name:</td><td><form:input size="40" path="name"/></td>
				<td><form:errors path="name" cssClass="error"/></td>
			</tr>
		</table>
		<input type="submit" value="Save"/>
		</form:form>
	</jsp:body>
	
</tags:template>
