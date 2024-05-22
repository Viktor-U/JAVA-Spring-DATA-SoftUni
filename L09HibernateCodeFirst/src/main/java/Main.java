import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle bike = new Bike();
        Vehicle car = new Car(4);
        Vehicle truck = new Truck(2000.00);
        Vehicle plane = new Plane(2);

        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(truck);
        entityManager.persist(plane);


        entityManager.getTransaction().commit();
    }
}
