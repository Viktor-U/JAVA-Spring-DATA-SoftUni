package entities;

import jakarta.persistence.Entity;

@Entity(name = "bikes")
public class Bike extends Vehicle{
    private final static String BIKE_TYPE = "BIKE";
    public Bike(){
        super(BIKE_TYPE);
    }
}
