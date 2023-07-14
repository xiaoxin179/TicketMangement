package controller;

import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/login")
public class LoginController extends HttpServlet {
    TicketService ticketservice = new TicketService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("userName");
        String s = "";
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        System.out.println(name);
        System.out.println(password);
        System.out.println(phone);
        try {
            s = ticketservice.user_login(name, phone, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        // 设置跨域请求响应头
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        out.write(s);
        out.close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
