package servlets;

import brewery.persistence.DAOFactory;
import brewery.persistence.dao.ICity;
import brewery.persistence.entities.City;
import uglySht.UglySht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;


@WebServlet(name = "CitiesServlet", urlPatterns = {"/lab2/servlets/cities/"})
public class CitiesServlet extends HttpServlet {
    private static final String VISIT_COUNT_SESSION_KEY = "VISIT_COUNT_SESSION_KEY";
    private DAOFactory daoFactory;

    @Override
    public void init() throws ServletException {
        super.init();

        this.daoFactory = DAOFactory.getDAOFactory(DAOFactory.AvailableImplementations.HIBERNATE);

        // Create some sample data.
        ICity cityDAO = this.daoFactory.getCityDAO();
        cityDAO.create(new City("Paris"));
        cityDAO.create(new City("London"));
        cityDAO.create(new City("Kiev"));
        cityDAO.create(new City("Odessa"));
        cityDAO.create(new City("Lviv"));
        cityDAO.create(new City("Krakiv"));
    }

    @Override
    public void destroy() {
        super.destroy();
        this.daoFactory.closeSession();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final boolean openNewSessionIfNotExist = true;
        HttpSession session = request.getSession(openNewSessionIfNotExist);

        Integer visitCount = (Integer) session.getAttribute(VISIT_COUNT_SESSION_KEY);
        if (visitCount == null) {
            visitCount = 0;
        }

        visitCount++;
        session.setAttribute(VISIT_COUNT_SESSION_KEY, visitCount);


        PrintWriter out = new PrintWriter(response.getWriter());
        response.setContentType("text/html");

        String pageTitle = "Cities";
        out.print(UglySht.getHeadTemplatePart(pageTitle));

        out.println(String.format("<p>Number of page view: %s</p>", visitCount));


        ICity cityDAO = this.daoFactory.getCityDAO();

        String nameValue = request.getParameter("name");
        nameValue = Objects.requireNonNullElse(nameValue, "");
        String sortValue = request.getParameter("sort");
        sortValue = Objects.requireNonNullElse(sortValue, "asc");

        String cityIdValueStr = request.getParameter("city_id");
        Integer cityIdValue;
        try {
            cityIdValue = Integer.parseInt(cityIdValueStr);
        } catch (NumberFormatException e) {
            cityIdValue = null;
        }

        if (sortValue == null || !sortValue.toLowerCase().equals("desc")) {
            sortValue = "asc";
        } else {
            sortValue = "desc";
        }

        List<City> cities = cityDAO.filteredSearch(cityIdValue, nameValue, sortValue);

        out.print("<div class=\"table-responsive\"><table class=\"table\"><thead>" +
                "<tr><th scope=\"col\">Id</th><th scope=\"col\">Name</th></tr></thead><tbody>\n");

        String row;
        for (City city : cities) {
            row = String.format("<tr><th>%s</th><td>%s</td></tr>", city.getId(), city.getName());
            out.print(row);
        }

        out.print("</tbody></table></div>");


        String idRepr;
        if (cityIdValue == null) {
            idRepr = "";
        } else {
            idRepr = cityIdValue.toString();
        }

        out.print(String.format("<form class=\"form-inline\" method=\"GET\">\n" +
                "  <label class=\"sr-only\" for=\"city_id\">City id</label>\n" +
                "  <input type=\"text\" value=\"%s\" name=\"city_id\" class=\"form-control mb-2 mr-sm-2\" id=\"city_id\" placeholder=\"City id\">\n" +
                "  <label class=\"sr-only\" for=\"name\">Name</label>\n" +
                "  <input type=\"text\" value=\"%s\" name=\"name\" class=\"form-control mb-2 mr-sm-2\" id=\"name\" placeholder=\"City name\">\n" +
                "\n" +
                "  <label class=\"my-1 mr-2\" for=\"order-by\">Order by</label>\n" +
                "  <select class=\"custom-select my-1 mr-sm-2\" name=\"sort\" id=\"order-by\">\n" +
                "    <option value=\"%2$s\" selected disabled>%3$s</option>\n" +
                "    <option value=\"asc\">ascending</option>\n" +
                "    <option value=\"desc\">descending</option>\n" +
                "  </select>\n" +
                "\n" +
                "  <button type=\"submit\" class=\"btn btn-primary mb-2\">Filter</button>\n" +
                "</form>\n", idRepr, nameValue, sortValue));

        out.print(UglySht.getFooterTemplatePart());
    }
}
