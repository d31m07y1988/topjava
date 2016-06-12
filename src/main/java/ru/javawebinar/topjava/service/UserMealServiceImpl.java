package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        return ExceptionUtil.checkNotFound(repository.save(userId, userMeal), "meal: " + userMeal.getId()+" for user:" + userId);
    }

    @Override
    public void delete(Integer userId, int id) {
        repository.delete(userId, id);
    }

    @Override
    public UserMeal get(Integer userId, int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public Collection<UserMeal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Integer userId, UserMeal userMeal) {
        repository.save(userId, userMeal);
    }
}
