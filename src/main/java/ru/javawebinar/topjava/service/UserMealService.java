package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(Integer userId, UserMeal userMeal) throws NotFoundException;

    void delete(Integer userId, int id);

    UserMeal get(Integer userId, int id) throws NotFoundException;

    Collection<UserMeal> getAll(Integer userId);

    void update(Integer userId, UserMeal userMeal);
}
