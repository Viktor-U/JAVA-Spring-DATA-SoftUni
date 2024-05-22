import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student newStudent = new Student();
        newStudent.setName("Moni");
        session.save(newStudent);
//        Student student = session.get(Student.class, 3L);

//        List<Student> studentList = session.createQuery("FROM Student ", Student.class).list();
//
//        for (Student student : studentList) {
//            System.out.println(student.getId() + " - " +student.getName());
//        }

        System.out.println(newStudent.getId() + " - " +newStudent.getName());

        session.getTransaction().commit();
        sessionFactory.close();


    }
}
