<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Compare Property Files</title>
  <meta name="description" content="Compare the contents of two proeprty files">
  <meta name="author" content="Junho Park">
  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
  <link href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
</head>

<body>
  <div class="container">
    <h1>Compare Property Files</h1>

    <p class="lead">
      Copy &amp; paste the contents of your property files into the fields below and then click on the submit on the button to compare.
    </p>

    <c:if test="${isComparisonBeingSubmitted}">
      <c:if test="${propertiesAContainsDuplicateKeys || propertiesBContainsDuplicateKeys}">
        <h4 class="text-warning">
          WARNING:
          <c:choose>
            <c:when test="${propertiesAContainsDuplicateKeys && !propertiesBContainsDuplicateKeys}">
              It appears that Property File A may contain 1 or more duplicate keys
            </c:when>
            <c:when test="${!propertiesAContainsDuplicateKeys && propertiesBContainsDuplicateKeys}">
              It appears that Property File B may contain 1 or more duplicate keys
            </c:when>
            <c:when test="${propertiesAContainsDuplicateKeys && propertiesBContainsDuplicateKeys}">
              It appears that both Property File A &amp; Property File B may contain 1 or more duplicate keys
            </c:when>
          </c:choose>
        </h4>
      </c:if>

      <div class="row">
        <div class="col-md-12">
            <c:if test="${not empty inAButMissingInB}">
              <h4 class="text-danger">Found keys that are in Property File A but missing in Property Files B:</h4>
              <ul>
                <c:forEach items="${inAButMissingInB}" var="item">
                  <li>${item}</li>
                </c:forEach>
              </ul>
            </c:if>

            <c:if test="${not empty inBButMissingInA}">
              <h4 class="text-danger">Found keys that are in Property File B but missing in Property Files A:</h4>
              <ul>
                <c:forEach items="${inBButMissingInA}" var="item">
                  <li>${item}</li>
                </c:forEach>
              </ul>
            </c:if>

            <c:if test="${not empty differences}">
              <h4 class="text-danger">Found keys in both property files; however, values are different:</h4>
              <ul>
                <c:forEach items="${differences}" var="item">
                  <li>${item}</li>
                </c:forEach>
              </ul>
            </c:if>

            <c:if test="${areBothPropertiesEmpty}">
              <h4 class="text-danger">Both property files are empty!</h4>
            </c:if>

            <c:if test="${empty inAButMissingInB && empty inBButMissingInA && empty differences && not areBothPropertiesEmpty}">
              <h4 class="text-success">No differences were found!</h4>
            </c:if>
        </div>
      </div>

      <%-- Hack to get some vertical space between the rows--%>
      <div class="row">
        <div class="col-md-12">
          &nbsp;
        </div>
      </div>
    </c:if>

    <form action="/compare" method="POST">
      <div class="row">
        <div class="col-md-6">
          <label for="text-a">Property File A</label>
          <textarea id="text-a" class="form-control" rows="25" name="text-a">${propertiesAText}</textarea>
        </div>
        <div class="col-md-6">
          <label for="text-b">Property File B</label>
          <textarea id="text-b" class="form-control" rows="25" name="text-b">${propertiesBText}</textarea>
        </div>
      </div>

      <%-- Hack to get some vertical space between the rows--%>
      <div class="row">
        <div class="col-md-12">
          &nbsp;
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <button type="submit" class="btn btn-primary btn-lg">
            <c:choose>
              <c:when test="${isComparisonBeingSubmitted}">
                Compare again!
              </c:when>
              <c:otherwise>
                Compare!
              </c:otherwise>
            </c:choose>
          </button>
        </div>
      </div>
    </form>

    <%-- Hack to get some vertical space between the rows--%>
    <div class="row">
      <div class="col-md-12">
        &nbsp;
      </div>
    </div>

    <div class="row">
      <div class="col-md-12 text-center">
        <p>
          <em>
            DISCLAIMER: Data you are submitting won't be persisted anywhere.  Keep in mind, though, that the data you're submitting will be transmitted through various networks.
          </em>
        </p>

        <p>
          <strong>
            built by <a href="http://junhopark.com" target="_blank">Junho Park</a>
          </strong>
        </p>
      </div>
    </div>
  </div>

  <script src="lib/jquery-3.1.0.min.js" type="text/javascript"></script>
  <script src="lib/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>