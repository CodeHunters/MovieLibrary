<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="false" %>
<tags:template>
	<jsp:attribute name="breadcrumb"><a href="/home">Home</a> / <a href="./new">User</a> /${user.userName}</jsp:attribute>
	<jsp:body>
        <center>
            <h3>User Details.</h3><br>
            <table>
                <tr>
                <td colspan="2" align="center"><font size="5">User Information</font></td>
                </tr>
                <tr>
                <td>User First Name:</td>
                <td><core:out value="${user.first_name}"/></td>
                </tr>
                <tr>
                <td>User Family Name:</td>
                <td><core:out value="${user.last_name}"/></td>
                </tr>
                <tr>
                <td>Username:</td>
                <td><core:out value="${user.userName}"/></td>
                </tr>
                <tr>
                <td>Gender:</td>
                <td><core:out value="${user.gender}"/></td>
                </tr>
                <tr>
                <td>About:</td>
                <td><core:out value="${user.aboutYou}"/></td>
                </tr>
            </table>
        </center>
	</jsp:body>
</tags:template>
