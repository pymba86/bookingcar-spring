package ru.pymba86.bx.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pymba86.bx.domain.CarFuel;
import ru.pymba86.bx.repository.CarFuelRepository;
import ru.pymba86.bx.web.rest.errors.BadRequestAlertException;
import ru.pymba86.bx.web.rest.util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CarFuel.
 */
@RestController
@RequestMapping("/api")
public class CarFuelResource {

    private final Logger log = LoggerFactory.getLogger(CarFuelResource.class);

    private static final String ENTITY_NAME = "carFuel";

    private final CarFuelRepository carFuelRepository;

    public CarFuelResource(CarFuelRepository carFuelRepository) {
        this.carFuelRepository = carFuelRepository;
    }

    /**
     * POST  /car-fuels : Create a new carFuel.
     *
     * @param carFuel the carFuel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carFuel, or with status 400 (Bad Request) if the carFuel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/car-fuels")
    public ResponseEntity<CarFuel> createCarFuel(@Valid @RequestBody CarFuel carFuel) throws URISyntaxException {
        log.debug("REST request to save CarFuel : {}", carFuel);
        if (carFuel.getId() != null) {
            throw new BadRequestAlertException("A new carFuel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarFuel result = carFuelRepository.save(carFuel);
        return ResponseEntity.created(new URI("/api/car-fuels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /car-fuels : Updates an existing carFuel.
     *
     * @param carFuel the carFuel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carFuel,
     * or with status 400 (Bad Request) if the carFuel is not valid,
     * or with status 500 (Internal Server Error) if the carFuel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/car-fuels")
    public ResponseEntity<CarFuel> updateCarFuel(@Valid @RequestBody CarFuel carFuel) throws URISyntaxException {
        log.debug("REST request to update CarFuel : {}", carFuel);
        if (carFuel.getId() == null) {
            return createCarFuel(carFuel);
        }
        CarFuel result = carFuelRepository.save(carFuel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, carFuel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /car-fuels : get all the carFuels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of carFuels in body
     */
    @GetMapping("/car-fuels")
    public List<CarFuel> getAllCarFuels() {
        log.debug("REST request to get all CarFuels");
        return carFuelRepository.findAll();
        }

    /**
     * GET  /car-fuels/:id : get the "id" carFuel.
     *
     * @param id the id of the carFuel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carFuel, or with status 404 (Not Found)
     */
    @GetMapping("/car-fuels/{id}")
    public ResponseEntity<CarFuel> getCarFuel(@PathVariable Long id) {
        log.debug("REST request to get CarFuel : {}", id);
        return carFuelRepository.findById(id)
                .map(carFuel -> ResponseEntity.ok().body(carFuel))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /car-fuels/:id : delete the "id" carFuel.
     *
     * @param id the id of the carFuel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/car-fuels/{id}")
    public ResponseEntity<Void> deleteCarFuel(@PathVariable Long id) {
        log.debug("REST request to delete CarFuel : {}", id);
        carFuelRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
