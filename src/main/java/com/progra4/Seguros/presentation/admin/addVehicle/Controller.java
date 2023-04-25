package com.progra4.Seguros.presentation.admin.addVehicle;

import com.progra4.Seguros.logic.Service;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.progra4.Seguros.logic.*;

@WebServlet(name = "addVehicleController", urlPatterns = {"/presentation/admin/addVehicle/show",
"/presentation/admin/addVehicle/add"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("model", new Model());
        
        String viewUrl="";
        switch (request.getServletPath()) {
            case "/presentation/admin/addVehicle/show":
                viewUrl = this.show(request);
                break;
            case "/presentation/admin/addVehicle/add":
                viewUrl = this.add(request);
                break;
        }          
        request.getRequestDispatcher(viewUrl).forward( request, response);
    }
    
    public String show(HttpServletRequest request) {
        return "/presentation/admin/addVehicle/View.jsp";
    }
    
    public String add(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        Service service = Service.instance();
        HttpSession session = request.getSession(true);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(request.getParameter("brand"));
        vehicle.setModel(request.getParameter("model"));
        vehicle.setYear(Integer.parseInt(request.getParameter("year")));
        
        
        try {
            model.setCurrent(vehicle);
            return "/presentation/admin/brand/show";
        } catch (Exception ex) {
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
