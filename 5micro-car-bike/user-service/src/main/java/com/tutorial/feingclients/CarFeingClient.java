package com.tutorial.feingclients;

import com.tutorial.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8002/car") //el ing, puso el contextpath directamente
/*si tu contexto es /car y tienes un método @GetMapping("/byuser/{userId}") en tu interfaz CarFeingClient, Spring lo
 mapeará como /car/byuser/{userId}*/
//@RequestMapping("/car")
public interface CarFeingClient {

    /*Me stoy trayendo los metodos de este controlador Car (bueno, los que necesite este microservicio)*/
    @PostMapping
    Car save(@RequestBody Car car);

    /*Bueno, en mi otro controler, mi metodo se llama: getByUserId(), pero solo necesito la firma*/
    @GetMapping("/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
