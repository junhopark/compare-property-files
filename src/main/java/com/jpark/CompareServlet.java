package com.jpark;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@WebServlet(urlPatterns = {"/compare/*"})
public class CompareServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("/");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    final String propertiesTextA = request.getParameter("text-a");
    final Properties propertiesA = new Properties();
    propertiesA.load(new StringReader(propertiesTextA));


    final String propertiesTextB = request.getParameter("text-b");
    final Properties propertiesB = new Properties();
    propertiesB.load(new StringReader(propertiesTextB));

    request.setAttribute("isComparisonBeingSubmitted", true);
    request.setAttribute("areBothPropertiesEmpty", ComparisonUtils.areBothPropertiesEmpty(propertiesA, propertiesB));
    request.setAttribute("differences", ComparisonUtils.getKeysWithDifferentValues(propertiesA, propertiesB));
    request.setAttribute("inAButMissingInB", ComparisonUtils.getKeysNotInSecondProperties(propertiesA, propertiesB));
    request.setAttribute("inBButMissingInA", ComparisonUtils.getKeysNotInSecondProperties(propertiesB, propertiesA));
    request.setAttribute("propertiesAContainsDuplicateKeys", ComparisonUtils.possiblyContainsDuplicateKeys(propertiesTextA));
    request.setAttribute("propertiesBContainsDuplicateKeys", ComparisonUtils.possiblyContainsDuplicateKeys(propertiesTextB));
    request.setAttribute("propertiesAText", propertiesTextA);
    request.setAttribute("propertiesBText", propertiesTextB);
    request.getRequestDispatcher("/").forward(request, response);
  }
}