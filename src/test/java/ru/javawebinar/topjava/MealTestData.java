package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final UserMeal meal1 = new UserMeal(100002, LocalDateTime.of(2016, Month.MAY, 11, 10, 00), "breakfast", 500);
    public static final UserMeal meal2 = new UserMeal(100003, LocalDateTime.of(2016, Month.MAY, 12, 10, 00), "lunch", 800);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
