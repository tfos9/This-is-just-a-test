/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 690106
 */
@WebServlet(name = "InventoryServlet", urlPatterns = {"/InventoryServlet"})
public class InventoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher("additem.jsp");
        
         String path = getServletContext().getRealPath("/WEB-INF/HomeItem.txt");
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        
       
        
         
        
        //try (PrintWriter out = response.getWriter()) {
          String category = request.getParameter("category");
          String itemName = request.getParameter("name");
          double itemPrice = Double.parseDouble(request.getParameter("price"));
          String message = null;
          
          if(itemPrice < 0 || category.equals("default"))
          {
           message = "Invalid. Please re-enter.";
           request.setAttribute("message", message);
                    rd = request.getRequestDispatcher("additem.jsp");
                    rd.include(request, response);
          }
          else 
          {
          message = "Item was successfully added to your inventory.";
          String addedItem = category + "," + itemName + "," + itemPrice  + "\n";
            fw.write(addedItem);
            fw.close();
          request.setAttribute("message", message);
                    rd = request.getRequestDispatcher("inventory.jsp");
                    rd.include(request, response);
            
          }
      //  }
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
