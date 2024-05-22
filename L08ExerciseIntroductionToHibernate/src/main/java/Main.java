import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;

public class Main {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final Comparator<Project> PROJECT_COMPARATOR = comparing(Project::getName);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String DEPARTMENT_MAX_SALARY_QUERY = """
                                                             SELECT department.name, MAX(salary)
                                                             FROM Employee
                                                             GROUP BY department.name
                                                             HAVING MAX(salary) NOT BETWEEN 30000 AND 70000""";

    public static void main(String[] args) throws IOException {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();



//2        changeCasing(entityManager);
//3        containsEmployee(entityManager, READER);
//4        selectEmployeeWithSalaryOver50000(entityManager);
//5        employeeFromDepartment(entityManager);
//6        addingNewAddressUpdatingEmployee(entityManager);
//7        addressesWithEmployeeCount(entityManager);
//8        getEmployeeWithProject(entityManager);
//9        findLatest10Projects(entityManager);
//10       increaseSalaries(entityManager);
//11       findEmployeesByFirstName(entityManager);
//12       employeesMaximumSalaries(entityManager);
//13       removeTowns(entityManager);


        entityManager.getTransaction().commit();

    }

    private static void removeTowns(EntityManager entityManager) throws IOException {
        String townName = READER.readLine();

        List<Address> resultList = entityManager.createQuery("FROM Address WHERE town.name = ?1", Address.class)
                .setParameter(1, townName)
                .getResultList();

        resultList.forEach(address -> {
            address.getEmployees().forEach(employee -> employee.setAddress(null));
            entityManager.remove(address);
        });

        Town townForDelete = entityManager.createQuery("FROM Town WHERE name = ?1", Town.class)
                .setParameter(1, townName)
                .getSingleResult();
        entityManager.remove(townForDelete);


        int deletedTowns = resultList.size();



        System.out.printf("%d addresses in %s deleted%n", deletedTowns, townName);



    }

    private static void employeesMaximumSalaries(EntityManager entityManager) {
        entityManager.createQuery(DEPARTMENT_MAX_SALARY_QUERY, Object[].class)
                .getResultList()
                .forEach(objects -> System.out.printf("%s %s%n", objects[0], objects[1]));

    }

    private static void findEmployeesByFirstName(EntityManager entityManager) throws IOException {
        entityManager.createQuery("FROM Employee WHERE firstName LIKE CONCAT(?1, '%')", Employee.class)
                .setParameter(1, READER.readLine())
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n"
                        , e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));


    }

    private static void increaseSalaries(EntityManager entityManager) {
        entityManager.createQuery("FROM Employee WHERE department.name " +
                                          "IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')"
                                        , Employee.class)
                .getResultStream()
                .forEach(e-> {
                    final BigDecimal bonusPercentToSalary = new BigDecimal("1.12");
                    e.setSalary(e.getSalary().multiply(bonusPercentToSalary));

                    System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
                });
    }

    private static void findLatest10Projects(EntityManager entityManager) {
        entityManager.createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted(PROJECT_COMPARATOR)
                .forEach(project -> {
                    String sb = "Project name: " + project.getName() + System.lineSeparator() +
                            "       Project Description: " + project.getDescription() + System.lineSeparator() +
                            "       Project Start Date: " + project.getStartDate().format(DATE_FORMAT) + System.lineSeparator() +
                            "       Project End Date: " + project.getEndDate()  + System.lineSeparator();

                    System.out.println(sb);
                });
    }

    private static void getEmployeeWithProject(EntityManager entityManager) throws IOException {
        Employee employee = entityManager.createQuery("FROM Employee WHERE id = ?1", Employee.class)
                .setParameter(1, Integer.parseInt(READER.readLine()))
                .getSingleResult();
        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        Set<Project> projectSet = employee.getProjects();
        projectSet.stream()
                .sorted(PROJECT_COMPARATOR)
                .forEach(p -> System.out.printf("      %s%n", p.getName()));
    }

    private static void addressesWithEmployeeCount(EntityManager entityManager) {
        entityManager.createQuery("FROM Address GROUP BY id ORDER BY employees.size desc ", Address.class)
                .getResultStream()
                .limit(10)
                .map(e->String.format("%s, %s - %d employee", e.getText(), e.getTown().getName(), e.getEmployees().size()))
                .forEach(System.out::println);
    }

    private static void addingNewAddressUpdatingEmployee(EntityManager entityManager) throws IOException {
        Employee employee = entityManager.createQuery("FROM Employee WHERE lastName = ?1", Employee.class)
                .setParameter(1, READER.readLine())
                .getSingleResult();
        if (employee != null){
            Address address = employee.getAddress();
            address.setText("Vitoshka 15");
            employee.setAddress(address);
        }

    }

    private static void employeeFromDepartment(EntityManager entityManager) {


        entityManager.createQuery("SELECT e FROM Employee e JOIN e.department d WHERE d.name = 'Research and Development'" +
                        "ORDER BY e.salary", Employee.class)
                .getResultStream()
                .map(e-> String.format("%s %s Research and Development - $%.2f", e.getFirstName(), e.getLastName(), e.getSalary()))
                .forEach(System.out::println);


    }

    private static void selectEmployeeWithSalaryOver50000(EntityManager entityManager) {
        entityManager.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);

    }

    private static void containsEmployee(EntityManager entityManager, BufferedReader reader) throws IOException {
        String fullName = reader.readLine();


        List<Employee> resultList = entityManager.createQuery("FROM Employee WHERE concat(firstName, ' ', lastName) = ?1", Employee.class)
                .setParameter(1, fullName)
                .getResultList();

        System.out.println(!resultList.isEmpty() ? "Yes" : "No");

    }

    private static void changeCasing(EntityManager entityManager) {
        entityManager.createQuery("FROM Town WHERE length(name) > 5 ", Town.class)
                    .getResultStream()
                    .forEach(t -> t.setName(t.getName().toUpperCase()));
    }
}
