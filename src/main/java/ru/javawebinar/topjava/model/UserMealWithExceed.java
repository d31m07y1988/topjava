package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed extends BaseEntity {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final Integer userId;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, Integer userId, boolean exceed) {
        this(null, dateTime, description, calories, userId, exceed);
    }

    public UserMealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, Integer userId, boolean exceed) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
        this.exceed = exceed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", userId=" + userId +
                ", exceed=" + exceed +
                '}';
    }
}
