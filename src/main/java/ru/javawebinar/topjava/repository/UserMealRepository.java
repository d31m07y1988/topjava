package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    //null if someone else's meal
    UserMeal save(Integer userId, UserMeal userMeal);

    void delete(Integer userId, int id);

    // null if not found
    UserMeal get(Integer userId, int id);

    Collection<UserMeal> getAll(Integer userId);
}
