<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>
	<jsp:body>
		<form:form commandName="user">
		<form:input type="hidden" name="id" path="user_id" />
		<table>
			<tr>
				<td>User Name:</td><td><form:input size="40" path="userName"/></td>
				<td>
				   <form:errors path="userName" cssClass="error"/>
				</td>

			</tr>
			<tr>
				<td>First Name:</td><td><form:input size="40" path="first_name"/></td>
				<td><form:errors path="first_name" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Family Name:</td><td><form:input size="40" path="last_name"/></td>
				<td><form:errors path="last_name" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td>
                    <form:select path="gender">
                        <form:options items="${genderList}" />
                    </form:select>
				</td>
			</tr>
			<tr>
				<td>About You:</td><td><form:textarea path="aboutYou" rows="5" cols="30" /></td>
			</tr>
			<tr>
				<td>Active:</td><td><form:checkbox path="active" /></td>
				<td></td>
			</tr>
			<tr>
				<td>Alerts:</td><td><form:checkbox path="alertsEnabled" /></td>
				<td></td>
			</tr>
			<tr>
				<td>Roles:</td>
				<td>
                    <form:select multiple="true" path="userSecurityRoleEntity">
                        <c:forEach varStatus="loop" items="${rolesOptionList}" var="role">
							<c:choose>
								<c:when test="${role.second}">
										 <form:option selected="selected" value="${role.first}" label="${role.first}"></form:option>
									 </c:when>
									 <c:otherwise>
										  <form:option value="${role.first}" label="${role.first}"></form:option>
									 </c:otherwise>
							</c:choose>
					   </c:forEach>
                    </form:select>
				</td>
				<td><form:errors path="userSecurityRoleEntity" cssClass="error"/></td>
			</tr>
		</table>
		<p>
		<form:errors cssClass="error"/>
		</p>
		<input type="submit" value="Save"/>
		</form:form>
	</jsp:body>

</tags:template>