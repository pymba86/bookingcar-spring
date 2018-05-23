package ru.pymba86.bx.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pymba86.bx.domain.CarGearbox;
import ru.pymba86.bx.repository.CarGearboxRepository;
import ru.pymba86.bx.web.rest.errors.BadRequestAlertException;
import ru.pymba86.bx.web.rest.util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CarGearbox.
 */
@RestController
@RequestMapping("/api")
public class CarGearboxResource {

    private final Logger log = LoggerFactory.getLogger(CarGearboxResource.class);


    private final CarGearboxRepository carGearboxRepository;

    public CarGearboxResource(CarGearboxRepository carGearboxRepository) {
        this.carGearboxRepository = carGearboxRepository;
    }

    /**
     * POST  /car-gearboxes : Create a new carGearbox.
     *
     * @param carGearbox the carGearbox to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carGearbox, or with status 400 (Bad Request) if the carGearbox has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/car-gearboxes")
    public ResponseEntity<CarGearbox> createCarGearbox(@Valid @RequestBody CarGearbox carGearbox) throws URISyntaxException {
        log.debug("REST request to save CarGearbox : {}", carGearbox);
        if (carGearbox.getId() != null) {
            throw new BadRequestAlertException("A new carGearbox cannot already have an ID", "idexists");
        }
        CarGearbox result = carGearboxRepository.save(carGearbox);
        return ResponseEntity.created(new URI("/api/car-gearboxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /car-gearboxes : Updates an existing carGearbox.
     *
     * @param carGearbox the carGearbox to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carGearbox,
     * or with status 400 (Bad Request) if the carGearbox is not valid,
     * or with status 500 (Internal Server Error) if the carGearbox couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/car-gearboxes")
    public ResponseEntity<CarGearbox> updateCarGearbox(@Valid @RequestBody CarGearbox carGearbox) throws URISyntaxException {
        log.debug("REST request to update CarGearbox : {}", carGearbox);
        if (carGearbox.getId() == null) {
            return createCarGearbox(carGearbox);
        }
        CarGearbox result = carGearboxRepository.save(carGearbox);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert( carGearbox.getId().toString()))
            .body(result);
    }

    /**
     * GET  /car-gearboxes : get all the carGearboxes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of carGearboxes in body
     */
    @GetMapping("/car-gearboxes")
    public List<CarGearbox> getAllCarGearboxes() {
        log.debug("REST request to get all CarGearboxes");
        return carGearboxRepository.findAll();
        }

    /**
     * GET  /car-gearboxes/:id : get the "id" carGearbox.
     *
     * @param id the id of the carGearbox to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carGearbox, or with status 404 (Not Found)
     */
    @GetMapping("/car-gearboxes/{id}")
    public ResponseEntity<CarGearbox> getCarGearbox(@PathVariable Long id) {
        log.debug("REST request to get CarGearbox : {}", id);
        return carGearboxRepository.findById(id)
                .map(carGearbox -> ResponseEntity.ok().body(carGearbox))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /car-gearboxes/:id : delete the "id" carGearbox.
     *
     * @param id the id of the carGearbox to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/car-gearboxes/{id}")
    public ResponseEntity<Void> deleteCarGearbox(@PathVariable Long id) {
        log.debug("REST request to delete CarGearbox : {}", id);
        carGearboxRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(id.toString())).build();
    }
}
