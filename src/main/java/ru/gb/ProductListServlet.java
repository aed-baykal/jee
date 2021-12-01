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

    // GET http://localhost:8080/jee/list?id1=1&title1=product1&cost1=10.5&id2=2&title2=product2&cost2=10.5&id3=3&title3=product3&cost3=10.5&id4=4&title4=product4&cost4=10.5&id5=5&title5=product5&cost5=10.5&id6=6&title6=product6&cost6=10.5&id7=7&title7=product7&cost7=10.5&id8=8&title8=product8&cost8=10.5&id9=9&title9=product9&cost9=10.5&id10=10&title10=product10&cost10=10.5
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        for (int i = 1; i < 11; i++) {
            Product product = new Product();
            product.setId(Integer.parseInt(req.getParameter("id" + i)));
            product.setTitle(req.getParameter("title" + i));
            product.setCost(Double.parseDouble(req.getParameter("cost" + i)));
            productList.add(product);
        }
        for (Product product : productList) {
            out.println("<html><body><h1>" + String.format("%d %s %s", product.getId(), product.getTitle(), product.getCost()) + "</h1></body></html>");
        }
        out.close();
    }
}
