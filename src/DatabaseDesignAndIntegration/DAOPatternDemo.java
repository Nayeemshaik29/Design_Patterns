package DatabaseDesignAndIntegration;

class Employee {

    int id;
    String name;

    Employee(int id, String name) {

        this.id = id;
        this.name = name;
    }
}

interface EmployeeDAO {

    void save(Employee employee);

    Employee findById(int id);
}

class EmployeeDAOImpl implements EmployeeDAO {

    public void save(Employee employee) {

        System.out.println("Employee Saved : " + employee.name);

    }

    public Employee findById(int id) {

        return new Employee(id, "Nayeem");
    }
}

public class DAOPatternDemo {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAOImpl();

        dao.save(new Employee(1, "Nayeem"));

        Employee employee = dao.findById(1);

        System.out.println(employee.name);

    }
}