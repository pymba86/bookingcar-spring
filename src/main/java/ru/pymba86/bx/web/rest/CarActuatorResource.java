package ru.pymba86.bx.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pymba86.bx.domain.CarActuator;
import ru.pymba86.bx.repository.CarActuatorRepository;
import ru.pymba86.bx.web.rest.errors.BadRequestAlertException;
import ru.pymba86.bx.web.rest.util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CarActuator.
 */
@RestController
@RequestMapping("/api")
public class CarActuatorResource {

    private final Logger log = LoggerFactory.getLogger(CarActuatorResource.class);

    private static final String ENTITY_NAME = "carActuator";

    private final CarActuatorRepository carActuatorRepository;

    public CarActuatorResource(CarActuatorRepository carActuatorRepository) {
        this.carActuatorRepository = carActuatorRepository;
    }

    /**
     * POST  /car-actuators : Create a new carActuator.
     *
     * @param carActuator the carActuator to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carActuator, or with status 400 (Bad Request) if the carActuator has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/car-actuators")
    public ResponseEntity<CarActuator> createCarActuator(@Valid @RequestBody CarActuator carActuator) throws URISyntaxException {
        log.debug("REST request to save CarActuator : {}", carActuator);
        if (carActuator.getId() != null) {
            throw new BadRequestAlertException("A new carActuator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarActuator result = carActuatorRepository.save(carActuator);
        return ResponseEntity.created(new URI("/api/car-actuators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /car-actuators : Updates an existing carActuator.
     *
     * @param carActuator the carActuator to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carActuator,
     * or with status 400 (Bad Request) if the carActuator is not valid,
     * or with status 500 (Internal Server Error) if the carActuator couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/car-actuators")
    public ResponseEntity<CarActuator> updateCarActuator(@Valid @RequestBody CarActuator carActuator) throws URISyntaxException {
        log.debug("REST request to update CarActuator : {}", carActuator);
        if (carActuator.getId() == null) {
            return createCarActuator(carActuator);
        }
        CarActuator result = carActuatorRepository.save(carActuator);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, carActuator.getId().toString()))
            .body(result);
    }

    /**
     * GET  /car-actuators : get all the carActuators.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of carActuators in body
     */
    @GetMapping("/car-actuators")
    public List<CarActuator> getAllCarActuators() {
        log.debug("REST request to get all CarActuators");
        return carActuatorRepository.findAll();
        }

    /**
     * GET  /car-actuators/:id : get the "id" carActuator.
     *
     * @param id the id of the carActuator to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carActuator, or with status 404 (Not Found)
     */
    @GetMapping("/car-actuators/{id}")
    public ResponseEntity<CarActuator> getCarActuator(@PathVariable Long id) {
        log.debug("REST request to get CarActuator : {}", id);
        return carActuatorRepository.findById(id)
                .map((carActuator) -> ResponseEntity.ok().body(carActuator))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /car-actuators/:id : delete the "id" carActuator.
     *
     * @param id the id of the carActuator to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/car-actuators/{id}")
    public ResponseEntity<Void> deleteCarActuator(@PathVariable Long id) {
        log.debug("REST request to delete CarActuator : {}", id);
        carActuatorRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
