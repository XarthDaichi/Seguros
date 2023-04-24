/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.progra4.Seguros.presentation.client.register;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.progra4.Seguros.logic.*;

/**
 *
 * @author Dell
 */
@WebServlet(name = "registroController", urlPatterns = {"/presentation/client/register/show",
"/presentation/client/register/add"})
public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/client/register/show":
                viewUrl=this.show(request);
                break;       
            case "/presentation/client/register/add":
                viewUrl=this.add(request);
                break;   
        }
        request.getRequestDispatcher(viewUrl).forward( request, response);
        
    }
    
    public String show(HttpServletRequest request){
        return "/presentation/Register/View.jsp";
    }
    
    private String add(HttpServletRequest request) { 
        try{
            return this.addAction(request);      
        }
        catch(Exception e){
            return "/presentation/Error.jsp";             
        } 
    }
    
    public String addAction(HttpServletRequest request)throws Exception {
        Model model= (Model) request.getAttribute("model");
        Service  service = Service.instance();
        HttpSession session = request.getSession(true);
        
        model.getCurrent().setId(request.getParameter("id"));
        model.getCurrent().setName(request.getParameter("name"));
        model.getCurrent().setPassword(request.getParameter("pass"));
        model.getCurrent().setEmail(request.getParameter("mail"));
        model.getCurrent().setCellphone(request.getParameter("phone"));
        model.getCurrent().setCardNumber(request.getParameter("card"));
        model.getCurrent().setAdministrator(false);
        
        try{
            service.userRegister(model.getCurrent());
            session.setAttribute("user", model.getCurrent());
            return "/presentation/client/policies/show";
        }catch(Exception e){
            return "/presentation/Error.jsp";             
        }
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
