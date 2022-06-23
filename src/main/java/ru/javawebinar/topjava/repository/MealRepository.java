package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepository implements Repository<Meal> {
    private static final AtomicInteger mealCounter = new AtomicInteger(8);

    @Override
    public void add(Meal meal) {
        if(meal.getId() == -1)
            meal = new Meal(mealCounter.getAndIncrement(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        MealsUtil.meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        MealsUtil.meals.remove(id);
    }

    @Override
    public Meal get(int id) {
        return MealsUtil.meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MealsUtil.meals.values());
    }
}
