package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public synchronized Meal save(int userId, Meal meal) {
        logger.info("save {} (userId: {})", meal, userId);
        return service.save(userId, meal);
    }

    public synchronized boolean delete(int userId, int id) {
        logger.info("delete {} (userId: {})", id, userId);
        return service.delete(userId, id);
    }

    public Meal get(int userId, int id) {
        logger.info("get {} (userId: {})", id, userId);
        return service.get(userId, id);
    }

    public Collection<Meal> getAll(int userId) {
        logger.info("getAll (userId: {})", userId);
        return service.getAll(userId);
    }
}