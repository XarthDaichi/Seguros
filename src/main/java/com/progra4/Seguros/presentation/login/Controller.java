/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.progra4.Seguros.presentation.login;

import com.progra4.Seguros.logic.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/show",
    "/presentation/login/register", "/presentation/login/login","/presentation/login/logout"})
public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/login/show":
                viewUrl=this.show(request);
                break;
            case "/presentation/login/register":
                viewUrl=this.register(request);
                break;
            case "/presentation/login/login":
                viewUrl=this.login(request);
                break;
            case "/presentation/login/logout":
                viewUrl=this.logout(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward( request, response);
    }
    
    public String show(HttpServletRequest request){
        return this.showAction(request);
    }
        
    public String showAction(HttpServletRequest request){
        Model model= (Model) request.getAttribute("model");
        model.reset();
        return "/presentation/login/View.jsp"; 
    }
    
    public String register(HttpServletRequest request){
        return "/presentation/login/Register.jsp";
    }
    
    void updateModel(HttpServletRequest request){
       Model model= (Model) request.getAttribute("model");
       
        model.getCurrent().setId(request.getParameter("id"));
        model.getCurrent().setPassword(request.getParameter("password"));
    }
    
    public String login(HttpServletRequest request){
        try{
            this.updateModel(request);          
            return this.loginAction(request);            
        }
        catch(Exception e){
            return "/presentation/Error.jsp";
        }
    }
    
    public String loginAction(HttpServletRequest request) {
        Model model= (Model) request.getAttribute("model");
        Service  service = Service.instance();
        HttpSession session = request.getSession(true);
        try {
            User real = service.userFind(model.getCurrent().getId(), model.getCurrent().getPassword());
            model.getCurrent().setAdministrator(real.getAdministrator());
            session.setAttribute("user", real);
            String viewUrl="";
            if (!real.getAdministrator()) viewUrl="/presentation/client/policies/show";
            else viewUrl="";
            return viewUrl;
        } catch (Exception ex) {
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            errores.put("cedulaFld","Usuario o clave incorrectos");
            errores.put("claveFld","Usuario o clave incorrectos");
            return "/presentation/login/View.jsp"; 
        }
    }
    
    public String logout(HttpServletRequest request){
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        session.invalidate();
        return "/presentation/login/show";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
