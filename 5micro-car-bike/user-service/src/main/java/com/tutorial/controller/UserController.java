package com.tutorial.controller;

import com.tutorial.entity.Usuario;
import com.tutorial.model.Bike;
import com.tutorial.model.Car;
import com.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {
        Usuario user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping()              //para esto se usa el patron DTO
    public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
        Usuario userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    /*Esto es RestTemplate*/
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        Usuario user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        Usuario user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    /*Esto es OopenFeing*/
    @PostMapping("/savecar/{userId}")
    ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        //primero debe haber un usuario
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Car carNew = userService.saveCar(userId, car);
        //userService.saveCar(userId, car);
        //return ResponseEntity.ok(carNew);
        return ResponseEntity.ok(car);
    }
    @PostMapping("/savebike/{userId}")
    ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike){
        //primero debe haber un usuario
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Bike bikeNew = userService.saveBike(userId, bike);
        //userService.saveCar(userId, car);
        //return ResponseEntity.ok(carNew);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

}
