package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;

@Entity(name = "cars")
public class Car extends Vehicle{
    private static final String CAR_TYPE = "CAR";
    @Basic
    private Integer seats;

    public Car(Integer seats) {
        super(CAR_TYPE);
        this.seats = seats;
    }

    private Car() {}
}
