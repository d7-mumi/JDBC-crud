package repository.impl;

import mapper.EmployeeMapper;
import model.entity.Employee;
import util.JdbcConnection;
import util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee, Integer> {

    public EmployeeRepository() {
        super(new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_EMPLOYEES)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Employee employee = getMapper().map(result);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return employees;
    }

    public Employee findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        // TODO: Implement a method which checks if an employee with the given id exists in the employees table
        if(findById(id) != null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean save(Employee employee) {
        /*
         * TODO: Implement a method which adds an employee to the employees table
         *  If the employee exists then the method should instead update the employee
         *
         */
        if (exists(employee.getId())) {
            ///update query
            if(update(employee) != null){
                return true;
            } else {
                return false;
            }

        } else {
            ///insert query
            try (Connection connection = JdbcConnection.connect();
                 PreparedStatement statement = connection.prepareStatement(Queries.INSERT_INTO_EMPLOYEES)){
                System.out.println("testing here");
                statement.setInt(1, employee.getId());
                statement.setString(2, employee.getLastName()+"");
                statement.setString(3, employee.getFirstName()+"");
                statement.setString(4, employee.getExtension()+"");
                statement.setString(5, employee.getEmail()+"");
                statement.setString(6, employee.getJobTitle()+"");
                System.out.println("testing here");
                ResultSet result = statement.executeQuery();
                System.out.println("testing here above if");
                if (result.next()) {
                    return true;
                }
            } catch (SQLException err) {
                System.err.println("Error in insert statement");
            }
        }
        return false;
    }



    @Override
    public Integer update(Employee employee) {
        /*
          * TODO: Implement a method which updates an employee with the given Employee instance
          *  The method should then return the number of updated records
         */
        System.out.println("does it get here" + employee.toString());
        try (Connection connection = JdbcConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_EMPLOYEES)) {
            statement.setString(1, employee.getLastName()+"");
            statement.setString(2, employee.getFirstName()+"");
            statement.setString(3, employee.getExtension()+"");
            statement.setString(4, employee.getEmail()+"");
            statement.setString(5, employee.getJobTitle()+"");
            statement.setInt(6, employee.getId());

            //statement.execute();
            int rows = statement.executeUpdate();
            if(rows < 0){
                System.out.println("DB not updated");
            } else {
                System.out.println("DB updated");
            }
            return rows;

        } catch (SQLException err) {
            System.err.println("Error in update statement");
            System.out.println(Arrays.toString(err.getStackTrace()));
        }
        return null;
    }

}
