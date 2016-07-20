package com.jpark;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/current-date-time/*"})
public class CurrentDateTimeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy h:m:ss a");

    response.setContentType("text/plain");
    ServletOutputStream out = response.getOutputStream();

    out.write(dateFormat.format(new Date()).getBytes());
    out.flush();
    out.close();
  }
}