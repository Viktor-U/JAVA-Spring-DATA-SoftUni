package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Plane extends Vehicle{


    private static final String PLANE_TYPE = "PLANE";
    @Column(name = "passenger_capacity")
    @Basic
    private int passengerCapacity;

    public Plane(int passengerCapacity) {
        super(PLANE_TYPE);
        this.passengerCapacity = passengerCapacity;
    }

    private Plane() {}
}
