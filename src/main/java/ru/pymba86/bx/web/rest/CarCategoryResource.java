package ru.pymba86.bx.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pymba86.bx.domain.CarCategory;
import ru.pymba86.bx.repository.CarCategoryRepository;
import ru.pymba86.bx.web.rest.errors.BadRequestAlertException;
import ru.pymba86.bx.web.rest.util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CarCategory.
 */
@RestController
@RequestMapping("/api")
public class CarCategoryResource {

    private final Logger log = LoggerFactory.getLogger(CarCategoryResource.class);

    private static final String ENTITY_NAME = "carCategory";

    private final CarCategoryRepository carCategoryRepository;

    public CarCategoryResource(CarCategoryRepository carCategoryRepository) {
        this.carCategoryRepository = carCategoryRepository;
    }

    /**
     * POST  /car-categories : Create a new carCategory.
     *
     * @param carCategory the carCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carCategory, or with status 400 (Bad Request) if the carCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/car-categories")
    public ResponseEntity<CarCategory> createCarCategory(@Valid @RequestBody CarCategory carCategory) throws URISyntaxException {
        log.debug("REST request to save CarCategory : {}", carCategory);
        if (carCategory.getId() != null) {
            throw new BadRequestAlertException("A new carCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarCategory result = carCategoryRepository.save(carCategory);
        return ResponseEntity.created(new URI("/api/car-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /car-categories : Updates an existing carCategory.
     *
     * @param carCategory the carCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carCategory,
     * or with status 400 (Bad Request) if the carCategory is not valid,
     * or with status 500 (Internal Server Error) if the carCategory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/car-categories")
    public ResponseEntity<CarCategory> updateCarCategory(@Valid @RequestBody CarCategory carCategory) throws URISyntaxException {
        log.debug("REST request to update CarCategory : {}", carCategory);
        if (carCategory.getId() == null) {
            return createCarCategory(carCategory);
        }
        CarCategory result = carCategoryRepository.save(carCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, carCategory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /car-categories : get all the carCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of carCategories in body
     */
    @GetMapping("/car-categories")
    public List<CarCategory> getAllCarCategories() {
        log.debug("REST request to get all CarCategories");
        return carCategoryRepository.findAll();
        }

    /**
     * GET  /car-categories/:id : get the "id" carCategory.
     *
     * @param id the id of the carCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carCategory, or with status 404 (Not Found)
     */
    @GetMapping("/car-categories/{id}")
    public ResponseEntity<CarCategory> getCarCategory(@PathVariable Long id) {
        log.debug("REST request to get CarCategory : {}", id);
        return carCategoryRepository.findById(id)
                .map((carCategory -> ResponseEntity.ok().body(carCategory)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /car-categories/:id : delete the "id" carCategory.
     *
     * @param id the id of the carCategory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/car-categories/{id}")
    public ResponseEntity<Void> deleteCarCategory(@PathVariable Long id) {
        log.debug("REST request to delete CarCategory : {}", id);
        carCategoryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
