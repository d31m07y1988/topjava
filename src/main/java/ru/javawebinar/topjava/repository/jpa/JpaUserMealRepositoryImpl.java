package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
            if (userMeal.getUser()==null) {
            User ref = em.getReference(User.class, userId);
            userMeal.setUser(ref);}
            if (userMeal.isNew()) {
                em.persist(userMeal);
                return userMeal;
            } else {
                return userMeal.getUser().getId()==userId? em.merge(userMeal):null;
            }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query = em.createQuery("DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userid");
        return query.setParameter("id", id).setParameter("userid",userId).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal um = em.find(UserMeal.class, id);
        return um.getUser().getId()==userId?um:null;
        /*Query query = em.createQuery("SELECT um FROM UserMeal um WHERE um.id=:id AND um.user.id=:userid",UserMeal.class);
        return (UserMeal) query.setParameter("id", id).setParameter("userid", userId).getSingleResult();*/
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        Query query = em.createQuery("SELECT um FROM UserMeal um WHERE um.user.id=:userid ORDER BY um.dateTime desc",UserMeal.class);
        return query.setParameter("userid",userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createQuery("SELECT um FROM UserMeal um WHERE um.user.id=:userid AND um.dateTime BETWEEN :stDate AND :endDate ORDER BY um.dateTime desc",UserMeal.class);
        return query.setParameter("userid",userId).setParameter("stDate",startDate).setParameter("endDate",endDate).getResultList();
    }
}