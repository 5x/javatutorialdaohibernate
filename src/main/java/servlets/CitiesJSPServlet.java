package servlets;

import brewery.persistence.DAOFactory;
import brewery.persistence.dao.ICity;
import brewery.persistence.entities.City;
import utils.HttpMethods;
import utils.RequestHelpers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CitiesJSPServlet", urlPatterns = {"/cities/"})
public class CitiesJSPServlet extends HttpServlet {
    private static final String VISIT_COUNT_SESSION_KEY = "VISIT_COUNT_SESSION_KEY";
    private static final String COOKIE_SELECTED_CITY_ITEMS_KEY = "CITY_ITEMS";

    private DAOFactory daoFactory;
    private ICity cityDAO;

    @Override
    public void init() throws ServletException {
        super.init();

        this.daoFactory = DAOFactory.getDAOFactory(DAOFactory.AvailableImplementations.HIBERNATE);
        this.cityDAO = this.daoFactory.getCityDAO();

        // Create some sample data.
        cityDAO.create(new City("Paris"));
        cityDAO.create(new City("London"));
        cityDAO.create(new City("Kiev"));
        cityDAO.create(new City("Odessa"));
        cityDAO.create(new City("Lviv"));
        cityDAO.create(new City("Krakiv"));
    }

    @Override
    public void destroy() {
        daoFactory.closeSession();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = RequestHelpers.getVirtualMethod(request);
        String forwardedURI = request.getRequestURI();

        if (method.equals(HttpMethods.POST.name())) {
            try {
                this.createCity(request);
            } catch (IllegalArgumentException e) {
                forwardedURI = request.getRequestURI() + "?error=bad_input";
            }
        }

        if (method.equals(HttpMethods.PUT.name())) {
            try {
                this.updateCity(request);
            } catch (IllegalArgumentException e) {
                forwardedURI = request.getRequestURI() + "?error=bad_input";
            }
        }

        if (method.equals(HttpMethods.DELETE.name())) {
            this.deleteCity(request);
        }

        String autoRedirect = request.getParameter("redirect");

        // Redirect by default to the page, after actions success completed.
        if (!method.equals(HttpMethods.GET.name()) && autoRedirect != null) {
            response.sendRedirect(forwardedURI);
        }

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

        String idRepr;
        if (cityIdValue == null) {
            idRepr = "";
        } else {
            idRepr = cityIdValue.toString();
        }

        String error = request.getParameter("error");
        String errorCodeValue = Objects.requireNonNullElse(error, "");


        String selectedCitiesString = "";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_SELECTED_CITY_ITEMS_KEY)) {
                    selectedCitiesString = cookie.getValue();
                }
            }
        }

        String[] selectedCitiesIds = selectedCitiesString.split("\\+");

        request.setAttribute("errorCodeValue", errorCodeValue);
        request.setAttribute("selectedCitiesIds", selectedCitiesIds);
        request.setAttribute("selectedCitiesString", selectedCitiesString);
        request.setAttribute("visitCount", visitCount);
        request.setAttribute("cities", cities);
        request.setAttribute("idRepr", idRepr);
        request.setAttribute("nameValue", nameValue);
        request.setAttribute("nameValue", nameValue);
        request.setAttribute("sortValue", sortValue);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cities.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.updateCity(request);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.deleteCity(request);
    }

    private void createCity(HttpServletRequest request) {
        String cityName = request.getParameter("city-name");

        if (cityName != null) {
            City city = new City(cityName);
            cityDAO.create(city);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void updateCity(HttpServletRequest request) {
        String cityName = request.getParameter("city-name");
        String cityIdValueStr = request.getParameter("city_id");
        Integer cityIdValue;

        try {
            cityIdValue = Integer.parseInt(cityIdValueStr);
            City city = cityDAO.read(cityIdValue);
            city.setName(cityName);
            cityDAO.update(city);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void deleteCity(HttpServletRequest request) {
        String cityIdValueStr = request.getParameter("city_id");
        Integer cityIdValue;

        try {
            cityIdValue = Integer.parseInt(cityIdValueStr);
            cityDAO.delete(cityIdValue);
        } catch (NumberFormatException e) {
            // Do nothing when entry don`t exist.
        }
    }

}
