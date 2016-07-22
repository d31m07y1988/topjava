package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Ramil on 20.07.2016.
 */
public class UserMealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserMealRestController.REST_URL + '/';
    @Autowired
    protected UserMealService userMealService;

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(USER_MEALS)));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testCreateWithLocation() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), userMealService.getAll(100000));
    }

/*
    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = MEAL1;
        updated.setDescription("UpdatedName");
        updated.setCalories(1999);
        mockMvc.perform(put(REST_URL + MEAL1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, userMealService.get(MEAL1.getId(),1000000));
    }


    @Test
    public void testGetFiltered() throws Exception {
        mockMvc.perform(get(REST_URL + "filtered?startDate=2015-05-30T20:00:00&endDate=2015-05-31T20:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getFilteredWithExceeded(
                        Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1), LocalTime.parse("20:00:00"), LocalTime.parse("20:00:00"), AuthorizedUser.getCaloriesPerDay())));
    }
    */
}