package ru.pymba86.bx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pymba86.bx.domain.CarCategory;


/**
 * Spring Data JPA repository for the CarCategory entity.
 */
@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {

}
