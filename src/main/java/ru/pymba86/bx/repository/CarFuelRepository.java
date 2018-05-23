package ru.pymba86.bx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pymba86.bx.domain.CarFuel;


/**
 * Spring Data JPA repository for the CarFuel entity.
 */
@Repository
public interface CarFuelRepository extends JpaRepository<CarFuel, Long> {

}
