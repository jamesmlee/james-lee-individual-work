/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculatorjspservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
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
@WebServlet(name = "CalculatorServlet", urlPatterns = {"/CalculatorServlet"})
public class CalculatorServlet extends HttpServlet {

    Service service = new ServiceImpl();

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

        String annualRateIn = request.getParameter("annualRate");
        BigDecimal annualRate = new BigDecimal(annualRateIn);
        BigDecimal quarterlyRate = annualRate.divide(new BigDecimal("4"));

        String principalIn = request.getParameter("principal");
        BigDecimal principal = new BigDecimal(principalIn);

        String numYearsIn = request.getParameter("numYears");
        int numYears = Integer.parseInt(numYearsIn);

        BigDecimal begPrincipal = principal;
        for (int currentYear = 0; currentYear < numYears; currentYear++) {
            DTO currentDTO = new DTO();
            service.fillDTO(currentDTO, begPrincipal, quarterlyRate);
            service.addDTO(currentDTO);
            begPrincipal = currentDTO.getBeginningBalance();
        }

        List<DTO> dtoList = service.getAllDTO();
        request.setAttribute("dtoList", dtoList);
        request.setAttribute("annualRate", annualRate);
        request.setAttribute("principal", principal);
        request.setAttribute("numYears", numYears);

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
