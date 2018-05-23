package ru.pymba86.bx.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "places")
    private Integer places;

    @Column(name = "motor_power")
    private Integer motorPower;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "gearbox")
    private CarGearbox gearbox;

    @ManyToOne
    @JoinColumn(name = "fuel")
    private CarFuel fuel;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "actuator")
    private CarActuator actuator;

    @ManyToOne
    @JoinColumn(name = "category")
    private CarCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Car name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public Car productionYear(Integer productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getDoors() {
        return doors;
    }

    public Car doors(Integer doors) {
        this.doors = doors;
        return this;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getPlaces() {
        return places;
    }

    public Car places(Integer places) {
        this.places = places;
        return this;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Integer getMotorPower() {
        return motorPower;
    }

    public Car motorPower(Integer motorPower) {
        this.motorPower = motorPower;
        return this;
    }

    public void setMotorPower(Integer motorPower) {
        this.motorPower = motorPower;
    }

    public Integer getPrice() {
        return price;
    }

    public Car price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CarGearbox getGearbox() {
        return gearbox;
    }

    public Car gearbox(CarGearbox carGearbox) {
        this.gearbox = carGearbox;
        return this;
    }

    public void setGearbox(CarGearbox carGearbox) {
        this.gearbox = carGearbox;
    }

    public CarFuel getFuel() {
        return fuel;
    }

    public Car fuel(CarFuel carFuel) {
        this.fuel = carFuel;
        return this;
    }

    public void setFuel(CarFuel carFuel) {
        this.fuel = carFuel;
    }

    public Location getLocation() {
        return location;
    }

    public Car location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CarActuator getActuator() {
        return actuator;
    }

    public Car actuator(CarActuator carActuator) {
        this.actuator = carActuator;
        return this;
    }

    public void setActuator(CarActuator carActuator) {
        this.actuator = carActuator;
    }

    public CarCategory getCategory() {
        return category;
    }

    public Car category(CarCategory carCategory) {
        this.category = carCategory;
        return this;
    }

    public void setCategory(CarCategory carCategory) {
        this.category = carCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        if (car.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", productionYear=" + getProductionYear() +
                ", doors=" + getDoors() +
                ", places=" + getPlaces() +
                ", motorPower=" + getMotorPower() +
                ", price=" + getPrice() +
                "}";
    }
}
