<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:template>
	<jsp:body>
    <form:form method="post" modelAttribute="addressList">
        <table>
        <tr>
            <th>No.</th>
            <th>Country</th>
            <th>Address</th>
            <th>PostCode</th>
        </tr>
        <c:forEach items="${addressList.addressEntityDTOs}" var="address" varStatus="status">
            <tr>
                <td align="center">${status.count}
                <input type="hidden" name="addressEntityDTOs[${status.index}].id" value="${address.id}"/>
                </td>
                <td><input name="addressEntityDTOs[${status.index}].country" value="${address.country}"/></td>
                <td><input name="addressEntityDTOs[${status.index}].detailAddress" value="${address.detailAddress}"/></td>
                <td><input name="addressEntityDTOs[${status.index}].postCode" value="${address.postCode}"/></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <input type="submit" name="action"  value="Save" />
    <input type="submit" name="action"  value="Add" />
    </form:form>
		</jsp:body>
</tags:template>