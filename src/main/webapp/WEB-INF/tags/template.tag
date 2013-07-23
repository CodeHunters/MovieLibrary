<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="utils" uri="/WEB-INF/tlds/utils.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ attribute name="breadcrumb" required="false" rtexprvalue="true"%>
<%@ attribute name="hidesearch" required="false" rtexprvalue="false"%>

<utils:default var="breadcrumb">
     <c:forEach items="${menuItems}" var="menuItem" varStatus="status">
       <a href="${pageContext.request.contextPath}/${menuItem.url}">${menuItem.name}</a>
	   <c:if test="${ ! status.last}" >/</c:if>
     </c:forEach>
</utils:default>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>MovieLibrary</title>
	<link href="${pageContext.request.contextPath}/resources/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        This session will be visible to an admin only.<br/>
        You are an Administrator.<br/>
    </sec:authorize>


	<div id="header"><div id="app_title">Movie Library</div></div>
	<div id="navigation">${breadcrumb}</div>
	<div id="userMenu">${userName}</div>
	<div id="content">
		<jsp:doBody/>
	</div>
	<div><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></div>
	<div id="footer">Code hunters 2013. All rights reserved</div>
</body>
</html>
