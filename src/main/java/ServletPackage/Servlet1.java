/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
public class Servlet1 extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Servlet1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "}\n" +
            "</style>");
            
                try {   // Trouver la valeur du paramètre HTTP customerID
                String val = request.getParameter("state");
                
                DAledO dao2 = new DAledO(DataSourceFactory.getDataSource());
                List<String> etats = dao2.getStates();
                out.println("<form action=\"/AppWeb/Servlet1\" id=\"Sform\">");
                out.println("<input type=\"submit\">");
                out.println("</form>");

                out.println("<select name=\"state\"form=\"Sform\">");

                for (int i = 0; i<etats.size(); i++) {
                    if (val.equals(etats.get(i))){
                        out.printf("<option value =%s selected>%s</option>",etats.get(i),etats.get(i)); 
                    }
                    else{
                        out.printf("<option value =%s>%s</option>",etats.get(i),etats.get(i));
                    }
                       
                }
                out.println("</select>");
                
                if (val != null) {
                    //throw new Exception("La paramètre state n'a pas été transmis");
                
                
 
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                
                List<CustomerEntity> aled = dao.customersInState(val);

                
                out.println("<table>");
                out.println("<tr>");
                out.println("<th> Id </th>");
                out.println("<th> Name </th>");
                out.println("<th> Address </th>");
                out.println("</tr>");
                    for (int i = 0; i < aled.size(); i++) {
                        out.println("<tr>");
                        
                        out.println("<th>");
                        out.printf("%d", aled.get(i).getCustomerId());
                        out.println("</th>");
                        
                        out.println("<th>");
                        out.printf("%s", aled.get(i).getName());
                        out.println("</th>");
                        
                        out.println("<th>");
                        out.printf("%s", aled.get(i).getAddressLine1());
                        out.println("</th>");
                        
                        out.println("</tr>");
                        
                        
                        
                        
                    }
                out.println("</table>");
                }
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            out.printf("<hr><a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
            out.println("</html>");
        
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
