<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page session="false" %>

<tags:template>
    <jsp:attribute name="breadcrumb">Home</jsp:attribute>
    <jsp:body>
        <h1>Welcome to movies library</h1>
        Manage your movies at an efficient way.
        <ul>
        <li>Easily find what movie to watch</li>
        <li>Download your movie details from online iMDB</li>
        <li>Export your libraries</li>
        <li>Manage your libraries with external disk</li>
        <li>Track where you have rented your libraries</li>
        </ul>
        <p><a href="movies/movie/new">Add a new <code>movie</code> Record</a></p>
    </jsp:body>
</tags:template>
