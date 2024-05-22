import Entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

//        Student student = new Student("Teo");
//        em.persist(student);
        Student student = em.find(Student.class, 2L);
        em.remove(student);


//        Student o = em.find(Student.class, 1L);
//        em.remove(o);
        em.getTransaction().commit();

    }
}
