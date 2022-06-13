package util;

public final class Queries {

    private Queries() {}

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    public static final String UPDATE_EMPLOYEES = "UPDATE employees \n" +
            "SET lastName = ?, firstName = ?, extension = ?, email = ?, jobTitle = ? \n" +
            "WHERE employeeNumber = ?";
    /////               1              2              3          4             5                        6

    public static final String INSERT_INTO_EMPLOYEES = "INSERT INTO employees(employeeNumber, " +
            "lastName, firstName, extension, email, officeCode, jobTitle) VALUES (?, ?, ?, ?, ?, ?, 1, ?)";

}
