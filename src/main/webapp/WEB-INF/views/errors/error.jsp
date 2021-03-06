<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tags:template>
    <jsp:body>
        <h1>Error</h1>
        An error occurred while processing request with reason:
        <br/>
        ${exception.specialMsg}
        <br />
        Please try again later and contact system administrator.
        <br />
    </jsp:body>
</tags:template>