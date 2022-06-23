package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.Repository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final Meal defaultMeal = new Meal(-1, null, null, 0);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    private static final Repository<Meal> repository = new MealRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if(servletPath.equals("/update")) {
            log.debug("update meal");
            int mealId = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("meal", repository.get(mealId));
            req.getRequestDispatcher("/addMeal.jsp").forward(req, resp);
        }
        else if(servletPath.equals("/create")) {
            log.debug("create meal");
            req.setAttribute("meal", defaultMeal);
            req.getRequestDispatcher("/addMeal.jsp").forward(req, resp);
        }
        else if(servletPath.equals("/delete")) {
            log.debug("delete meal");
            int mealId = Integer.parseInt(req.getParameter("id"));
            repository.delete(mealId);
            resp.sendRedirect("/topjava/meals");
        }
        else {
            log.debug("list meal");
            req.setAttribute("mealsTo", MealsUtil.filteredByStreams(
                    new ArrayList<>(repository.getAll()), LocalTime.MIN, LocalTime.MAX,
                    MealsUtil.CALORIES_PER_DAY));
            req.setAttribute("dateFormatter", DATE_TIME_FORMATTER);
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("update meal");
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("local_date_time"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        repository.add(new Meal(id, localDateTime, description, calories));
        resp.sendRedirect("/topjava/meals");
    }
}
