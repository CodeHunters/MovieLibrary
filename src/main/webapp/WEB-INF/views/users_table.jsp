<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tags:template>
	<jsp:attribute name="breadcrumb"><a href="/home">Home</a> / <a href="./new">Users</a> /${user.userName}</jsp:attribute>
	<jsp:body>
        <center>
            <h1>Users</h1>
            <p>Here you can see the list of the Users, edit them, remove or update.</p>
            <table border="1px" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
            <th width="10%">Id</th>
            <th width="15%">User Name</th>
            <th width="10%">Family Name</th>
            <th width="10%">First Name</th>
            <th width="10%">Gender</th>
            <th width="10%">Active</th>
            <th width="10%">Alerts Enabled</th>
            </tr>
            </thead>
            <tbody>
             <c:forEach var="user" items="${users}" varStatus="status">

            <tr>
                <td>${user.user_id}</td>
                <td>${user.userName}</td>
                <td>${user.last_name}</td>
                <td>${user.first_name}</td>
                <td>${user.gender}</td>
                <td>${user.active}</td>
                <td>${user.alertsEnabled}</td>
                <td>
                <a href="${pageContext.request.contextPath}/users/user/edit?id=${user.user_id}">Edit</a><br>
                <a href="${pageContext.request.contextPath}/users/user/address/edit?id=${user.user_id}">Edit Address</a><br>
                <a href="${pageContext.request.contextPath}/users/user/role/edit?id=${user.user_id}">Edit Roles</a><br>
                <a href="${pageContext.request.contextPath}/users/user/details?id=${user.user_id}">View</a><br>
                <a href="${pageContext.request.contextPath}/users/user/delete?id=${user.user_id}">Delete</a><br>
                </td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
            <p><a href="${pageContext.request.contextPath}/users/user/commit">Add new User</a></p>
        </center>
	</jsp:body>
</tags:template>
