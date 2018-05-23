package ru.pymba86.bx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A CarActuator.
 */
@Entity
@Table(name = "car_actuator")
public class CarActuator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "actuator")
    @JsonIgnore
    private Set<Car> cars = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CarActuator name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public CarActuator cars(Set<Car> cars) {
        this.cars = cars;
        return this;
    }

    public CarActuator addCar(Car car) {
        this.cars.add(car);
        car.setActuator(this);
        return this;
    }

    public CarActuator removeCar(Car car) {
        this.cars.remove(car);
        car.setActuator(null);
        return this;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarActuator carActuator = (CarActuator) o;
        if (carActuator.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carActuator.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarActuator{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
