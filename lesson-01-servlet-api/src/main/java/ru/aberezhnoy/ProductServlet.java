package ru.aberezhnoy;

import ru.aberezhnoy.persist.Product;
import ru.aberezhnoy.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {

    productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        if (req.getParameter("id") != null) {
            wr.println()
            //TODO
        } else if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            wr.println("<table>");

            wr.print("<tr>");
            wr.print("<th>Id</th>");
            wr.print("<th>Name</th>");
            wr.print("</tr>");

            for (Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td>" + product.getId() + "</td>");
                wr.println("<td>" + product.getName() + "</td>");
                //TODO <a href='product?id=12'></a>
                wr.println("</tr>");
            }

            wr.println("</table>");
        }
    }
}
