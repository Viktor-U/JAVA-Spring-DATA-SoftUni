package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;

@Entity
public class Truck extends Vehicle{

    private static final String TRUCK_TYPE = "TRUCK";

    @Basic
    private Double loadCapacity;

    public Truck(Double loadCapacity) {
        super(TRUCK_TYPE);
        this.loadCapacity = loadCapacity;
    }

    private Truck() {}
}
