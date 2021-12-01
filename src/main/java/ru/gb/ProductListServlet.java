package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductListServlet", urlPatterns = "/list")
public class ProductListServlet extends HttpServlet {
    List<Product> productList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(ProductListServlet.class);

    // GET http://localhost:8080/jee/list
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        if (productList.isEmpty()) {
            for (int i = 1; i < 11; i++) {
                productList.add(new Product(i, "product" + i, 10.5f + i));
            }
        }
        out.println("<html><body><h5>");
        for (Product product : productList) {
            out.println("<p>" + String.format("%d %s %.2f", product.getId(), product.getTitle(), product.getCost()) + "</p>");
        }
        out.println("</h5></body></html>");
        out.close();
    }

}
