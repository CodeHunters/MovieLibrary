<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>

	<jsp:attribute name="breadcrumb"><a href="..">Home</a> / <a href="./new">MyEntity</a> / ${passwordEntity.name}</jsp:attribute>
	<jsp:body>
		<form:form commandName="passwordEntity">
		<form:input type="hidden" name="name" path="name" />
		<form:input type="hidden" name="id" path="id" />
		<table>
			<tr>
				<td>Old Password:</td><td><form:password path="oldPassword"/></td>
			</tr>
			<tr>
				<td>New Password:</td><td><form:password path="password"/></td>
			</tr>
			<tr>
				<td>Confirm Password:</td><td><form:password path="confirmPassword"/></td>
			</tr>
		</table>
        <p>
		<form:errors path="*" cssClass="error"/>

        </p>
		<input type="submit" value="Save"/>
		</form:form>
	</jsp:body>

</tags:template>