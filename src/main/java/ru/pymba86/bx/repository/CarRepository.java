package ru.pymba86.bx.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.pymba86.bx.domain.Car;

/**
 * Spring Data JPA repository for the CarGearbox entity.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
