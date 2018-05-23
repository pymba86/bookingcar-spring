package ru.pymba86.bx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A CarFuel.
 */
@Entity
@Table(name = "car_fuel")
public class CarFuel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "fuel")
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

    public CarFuel name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public CarFuel cars(Set<Car> cars) {
        this.cars = cars;
        return this;
    }

    public CarFuel addCar(Car car) {
        this.cars.add(car);
        car.setFuel(this);
        return this;
    }

    public CarFuel removeCar(Car car) {
        this.cars.remove(car);
        car.setFuel(null);
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
        CarFuel carFuel = (CarFuel) o;
        if (carFuel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carFuel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarFuel{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
