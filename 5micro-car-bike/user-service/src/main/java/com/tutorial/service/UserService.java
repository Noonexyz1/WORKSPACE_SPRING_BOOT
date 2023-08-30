package com.tutorial.service;

import com.tutorial.entity.Usuario;
import com.tutorial.model.Bike;
import com.tutorial.model.Car;
import com.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

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


    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
        return cars;
    }
    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
        return bikes;
    }
}
