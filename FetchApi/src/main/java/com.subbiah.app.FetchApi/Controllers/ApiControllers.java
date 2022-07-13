package com.subbiah.app.FetchApi.Controllers;

import com.subbiah.app.FetchApi.Models.PointsEntity;
import com.subbiah.app.FetchApi.Repo.PointsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
public class ApiControllers {

    @Autowired
    private PointsRepo pointsRepo;

    @GetMapping(value = "/")
    public String getPage()
    {
        return "Welcome";
    }

    @GetMapping(value="/points")
    public List<PointsEntity> getUsers() {
        return pointsRepo.findAll();
    }

    @PostMapping(value="/save")
    public String saveUser(@RequestBody PointsEntity pointsEntity) {

        //I coded a random date for testing purposes, to test that my date-sort-method works, obviously I would use Date.Now() in production
        Random rnd = new Random();
        Date randomDate = new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()));
        LocalDateTime rndDateTime = randomDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        pointsEntity.setTransactionDate(rndDateTime);

        //uncomment this line if you want the transactionDate to be the date the record was entered
        pointsEntity.setTransactionDate(LocalDateTime.now());
        pointsRepo.save(pointsEntity);

        return "Saved!";
    }

    class SortByDate implements Comparator<PointsEntity> {
        // Used for sorting in ascending order of ID
        public int compare(PointsEntity a, PointsEntity b)
        {
            return a.getTransactionDate().compareTo(b.getTransactionDate());
        }
    }

    @PutMapping(value="/spend")
    //I am still working on this Put method. It is not working yet.
    public String spend(@RequestBody int transactionPoints) {
        PointsEntity updatedPointsEntity = pointsRepo.findById((long)1).get();

        List<PointsEntity> pointsEntities = pointsRepo.findAll();
        Collections.sort(pointsEntities, new SortByDate());
        updatedPointsEntity.setTransactionPoints(transactionPoints);
        pointsRepo.save(updatedPointsEntity);
        return "Updated!";
    }
}
