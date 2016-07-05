package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal m WHERE m.id=?1 AND m.user.id=?2")
    int delete(int id, int userId);

    @Override
    @Transactional
    UserMeal save(UserMeal meal);

    @Query("SELECT m FROM UserMeal m WHERE m.id=?1 AND m.user.id=?2")
    UserMeal findOne(Integer id, int userId);

    //UserMeal findOneByIdAndUserId(int id, int userId);

    List<UserMeal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime start, LocalDateTime end);

    List<UserMeal> findAllByUserIdOrderByDateTimeDesc(int userId);
}
