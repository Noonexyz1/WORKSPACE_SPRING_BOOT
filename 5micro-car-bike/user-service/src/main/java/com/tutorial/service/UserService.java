package com.tutorial.service;

import com.tutorial.entity.Usuario;
import com.tutorial.feingclients.BikeFeingClient;
import com.tutorial.feingclients.CarFeingClient;
import com.tutorial.model.Bike;
import com.tutorial.model.Car;
import com.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CarFeingClient carFeingClient;
    @Autowired
    BikeFeingClient bikeFeingClient;

    public List<Usuario> getAll() {
        return userRepository.findAll();
    }

    public Usuario getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario user) {
        Usuario userNew = userRepository.save(user);
        return userNew;
    }


    /*Esto es con RestTemplate*/
    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }
    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }


    /*Esto es con OpenFeing*/
    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeingClient.save(car);
        return carNew;
    }
    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeingClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();

        Usuario user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User", user);

        List<Car> cars = carFeingClient.getCars(userId);
        if (cars.isEmpty()){
            result.put("Cars", "Ese usuario no tiene coches");
        } else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeingClient.getBikes(userId);
        if (bikes.isEmpty()){
            result.put("Bikes", "Ese usuario no tiene motos");
        } else {
            result.put("Bikes", bikes);
        }

        return result;

    }


}
