<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html lang="en">

<%
  pageContext.setAttribute("status", response.getStatus());
%>

<head>
  <jsp:include page="include/head.jsp">
    <jsp:param name="pageName" value="${status}"/>
  </jsp:include>
</head>

<body>

<div class="container">
  <h1>Compare Property Files</h1>

  <h2 class="text-info">${status} ERROR</h2>

  <div class="row">
    <div class="col-md-12">
      <h3 class="text-info">
        <c:choose>
          <c:when test="${status eq '404'}">
            Can't find the page you're looking for.
          </c:when>
          <c:when test="${status eq '500'}">
            Uh oh - You encountered an internal server error.
          </c:when>
          <c:otherwise>
            Sorry, you encountered an unknown error.
          </c:otherwise>
        </c:choose>
        </h3>

        <h4>
          <a href="/" class="btn btn-info">Take me back home</a>
        </h4>
    </div>
  </div>
</div>

</body>
</html>