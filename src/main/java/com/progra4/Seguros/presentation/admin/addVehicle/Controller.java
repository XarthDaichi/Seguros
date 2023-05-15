package com.progra4.Seguros.presentation.admin.addVehicle;

import com.progra4.Seguros.logic.Service;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import com.progra4.Seguros.logic.*;
import java.io.*;

@WebServlet(name = "addVehicleController", urlPatterns = {"/presentation/admin/addVehicle/show",
"/presentation/admin/addVehicle/add"})
@MultipartConfig()
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
        
        try {
            
            vehicle.setBrand(request.getParameter("brand"));
            vehicle.setModel(request.getParameter("model"));
            vehicle.setYear(Integer.parseInt(request.getParameter("year")));
            model.setCurrent(vehicle);
            service.vehicleCreate(vehicle);
            this.postImage(request, vehicle);
            return "/presentation/admin/brand/show";
        } catch (Exception ex) {
            return "/presentation/Error.jsp";
        }
        
    }
    
    public void postImage(HttpServletRequest request, Vehicle vehicle) throws Exception {
        try {
            Service service = Service.instance();
            // Get the file part from the request
            Part filePart = request.getPart("img");
        
            // Get the filename from the file part
            int id = service.findVehicle(vehicle.getBrand(), vehicle.getModel(), vehicle.getYear()).getId();
        
            // Get the filename from the file part
            String fileName = String.format("%s.png", id);
        
            // Set the path to save the uploaded file
            String savePath = getServletContext().getRealPath("/") + "images/";
        
            // Create the save directory if it does not exist
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
        
            // Create a file object for the uploaded file
            File file = new File(savePath + fileName);
        
            // Copy the input stream to the output stream to save the file
            InputStream input = filePart.getInputStream();
            OutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.close();
            input.close();
        } catch (Exception ex) {
            throw new Exception("An error occurred", ex);
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
