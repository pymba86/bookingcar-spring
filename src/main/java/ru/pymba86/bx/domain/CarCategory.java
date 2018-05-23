package ru.pymba86.bx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A CarCategory.
 */
@Entity
@Table(name = "car_category")
public class CarCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "driver_age_min", nullable = false)
    private Integer driverAgeMin;

    @NotNull
    @Column(name = "driver_experience_min", nullable = false)
    private Integer driverExperienceMin;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Car> cars = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CarCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDriverAgeMin() {
        return driverAgeMin;
    }

    public CarCategory driverAgeMin(Integer driverAgeMin) {
        this.driverAgeMin = driverAgeMin;
        return this;
    }

    public void setDriverAgeMin(Integer driverAgeMin) {
        this.driverAgeMin = driverAgeMin;
    }

    public Integer getDriverExperienceMin() {
        return driverExperienceMin;
    }

    public CarCategory driverExperienceMin(Integer driverExperienceMin) {
        this.driverExperienceMin = driverExperienceMin;
        return this;
    }

    public void setDriverExperienceMin(Integer driverExperienceMin) {
        this.driverExperienceMin = driverExperienceMin;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public CarCategory cars(Set<Car> cars) {
        this.cars = cars;
        return this;
    }

    public CarCategory addCar(Car car) {
        this.cars.add(car);
        car.setCategory(this);
        return this;
    }

    public CarCategory removeCar(Car car) {
        this.cars.remove(car);
        car.setCategory(null);
        return this;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarCategory carCategory = (CarCategory) o;
        if (carCategory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carCategory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", driverAgeMin=" + getDriverAgeMin() +
            ", driverExperienceMin=" + getDriverExperienceMin() +
            "}";
    }
}
