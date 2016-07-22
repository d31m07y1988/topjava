package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(UserMealRestController.REST_URL)
public class UserMealRestController extends AbstractUserMealController {

    static final String REST_URL = "/rest/meals";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMeal> createWithLocation(@RequestBody UserMeal userMeal) {
        UserMeal created = super.create(userMeal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal userMeal, @PathVariable("id") int id) {
        super.update(userMeal, id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getFiltered(@RequestParam("startDateTime") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
                                                @RequestParam("endDateTime") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        return super.getBetween(startDateTime.toLocalDate(),startDateTime.toLocalTime(),endDateTime.toLocalDate(),endDateTime.toLocalTime());
    }

/*    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getFiltered(@RequestParam("startDate") String startDate,
                                                @RequestParam("startTime") String startTime,
                                                @RequestParam("endDate") String endDate,
                                                @RequestParam("endTime") String endTime) {
        LocalDate stD = TimeUtil.parseLocalDate(startDate);
        LocalDate endD = TimeUtil.parseLocalDate(endDate);
        LocalTime stT = TimeUtil.parseLocalTime(startTime);
        LocalTime endT = TimeUtil.parseLocalTime(endTime);
        return super.getBetween(stD,stT,endD,endT);
    }*/
}