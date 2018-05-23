package ru.pymba86.bx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pymba86.bx.domain.CarGearbox;


/**
 * Spring Data JPA repository for the CarGearbox entity.
 */
@Repository
public interface CarGearboxRepository extends JpaRepository<CarGearbox, Long> {

}
