package ru.pymba86.bx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pymba86.bx.domain.CarActuator;


/**
 * Spring Data JPA repository for the CarActuator entity.
 */
@Repository
public interface CarActuatorRepository extends JpaRepository<CarActuator, Long> {

}
