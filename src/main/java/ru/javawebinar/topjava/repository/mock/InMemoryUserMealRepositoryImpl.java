package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);

    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(um->save(1,um));
    }

    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        LOG.info("user: " + userId + " save " + userMeal);
        if (userMeal.getUserId().equals(userId)) {
            if (userMeal.isNew()) {
                userMeal.setId(counter.incrementAndGet());
            }
            repository.put(userMeal.getId(), userMeal);
        } else return null;
        return userMeal;
    }

    @Override
    public void delete(Integer userId, int id) {
        LOG.info("user: " + userId + " delete meal: " + id);
        if(repository.get(id).getUserId().equals(userId))
        repository.remove(id);
    }

    @Override
    public UserMeal get(Integer userId, int id) {
        LOG.info("user: " + userId + " get meal: " + id);
        return repository.get(id).getUserId().equals(userId)?repository.get(id):null;
    }

    @Override
    public Collection<UserMeal> getAll(Integer userId) {
        LOG.info("user: " + userId + " get all meals");
        List<UserMeal> list =  repository.entrySet().stream()
                .filter(um->um.getValue().getUserId().equals(userId))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        list.sort((um1,um2)->um2.getDateTime().compareTo(um1.getDateTime()));
        return list;
    }
}

