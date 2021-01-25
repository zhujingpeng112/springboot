package com.jump.zhu.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * today
 * @params No such property: code for class: Script1
 * @return
 * @author jump.zhu
 * @date   2021/1/23 10:25
*/
@WebServlet(name="FirstServlet",urlPatterns = "/first")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("First running...");
        PrintWriter out = resp.getWriter();
        out.write("first FirstServlet");
        out.flush();
        out.close();
    }
}
