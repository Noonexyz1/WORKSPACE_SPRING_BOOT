package com.tutorial.feingclients;

import com.tutorial.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:8003/bike")
//@RequestMapping("/bike")
public interface BikeFeingClient {
    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
