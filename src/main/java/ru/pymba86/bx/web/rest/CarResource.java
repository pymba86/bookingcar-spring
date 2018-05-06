package ru.pymba86.bx.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.pymba86.bx.domain.Car;
import ru.pymba86.bx.repository.CarRepository;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarResource {

     @Inject
    private CarRepository carRepository;

    /**
     * GET  /cars -> Получить все машины
     */
    @GetMapping("/cars")
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    /**
     * GET  /cars/:id -> Получить машину по id.
     */
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> get(@PathVariable Long id) {
        return carRepository.findOneById(id)
                .map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST  /cars -> Создать новую машину.
     */
    @PostMapping(value = "/cars")
    public ResponseEntity<Car> create(@RequestBody Car car) throws URISyntaxException {
        if (car.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new car cannot already have an ID").build();
        }
        carRepository.save(car);
        return ResponseEntity.created(new URI("/api/cars/" + car.getId())).body(car);
    }

    /**
     * PUT  /cars -> Обновить машину.
     */
    @PutMapping(value = "/cars/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Car car) throws URISyntaxException {
        Optional<Car> currentCar = carRepository.findOneById(id);
        if (currentCar.isPresent()) {
            currentCar.get().setName(car.getName());
            carRepository.save(currentCar.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().header("Failure", "A car is not updated").build();
    }

    /**
     * DELETE  /cars/:id -> Удалить машину по id
     */
    @DeleteMapping(value = "/cars/{id}")
    public void delete(@PathVariable Long id) {
         carRepository.findOneById(id).ifPresent(user -> {
            carRepository.delete(user);
        });
    }
}
