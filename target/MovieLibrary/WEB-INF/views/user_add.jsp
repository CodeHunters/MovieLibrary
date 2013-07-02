<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / <a href="./new">MyEntity</a> / ${user.userName}</jsp:attribute>
	<jsp:body>
		<form:form commandName="user">
		<table>
			<tr>
				<td>User Name:</td><td><form:input size="40" path="userName"/></td>
			</tr>
			<tr>
				<td>First Name:</td><td><form:input size="40" path="first_name"/></td>
			</tr>
			<tr>
				<td>Family Name:</td><td><form:input size="40" path="last_name"/></td>
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
				<td>Password:</td><td><form:password path="password"/></td>
			</tr>
			<tr>
				<td>Active:</td><td><form:checkbox path="active" /></td>
			</tr>
			<tr>
				<td>Roles:</td>
				<td>
                    <form:select multiple="true" path="userSecurityRoleEntity">
                        <form:options items="${rolesOptionList}" itemValue="roleName" itemLabel="roleName"/>
                    </form:select>
				</td>
			</tr>
		</table>
		<p/>
		<input type="submit" value="Save"/>
		</form:form>
	</jsp:body>

</tags:template>