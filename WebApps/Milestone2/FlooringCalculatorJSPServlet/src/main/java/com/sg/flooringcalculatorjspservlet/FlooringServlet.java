/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringcalculatorjspservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author James
 */
@WebServlet(name = "FlooringServlet", urlPatterns = {"/FlooringServlet"})
public class FlooringServlet extends HttpServlet {

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
        BigDecimal costMaterials = new BigDecimal("0");
        BigDecimal costLabor = new BigDecimal("0");
        BigDecimal costTotal = new BigDecimal("0");
        BigDecimal quarterHourRate = new BigDecimal("21.50");
        double timeRequired = 0;
        
        String widthIn = request.getParameter("width");
        int width = Integer.parseInt(widthIn);
        String lengthIn = request.getParameter("length");
        int length = Integer.parseInt(lengthIn);
        String costPerSqFtIn = request.getParameter("costPerSqFt");
        BigDecimal costPerSqFt = new BigDecimal(costPerSqFtIn);
        
        int area = length * width;
// req1        
        costMaterials = costPerSqFt.multiply(new BigDecimal(area));
// 20ft/hr, so 5ft/15min
// req2
        costLabor = quarterHourRate.multiply(new BigDecimal(area/5));
// req3
        costTotal = costMaterials.add(costLabor);
// req4 ... should make it show up in 15 min intervals, not just int        
        timeRequired = area / 20;
        
        request.setAttribute("costMaterials", costMaterials);
        request.setAttribute("costLabor", costLabor);
        request.setAttribute("costTotal", costTotal);
        request.setAttribute("timeRequired", timeRequired);
        
//        request.setAttribute("width", width);
//        request.setAttribute("length", length);
//        request.setAttribute("costPerSqFt", costPerSqFt);
        
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
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
