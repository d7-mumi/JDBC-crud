package main;

import model.entity.Employee;
import repository.Repository;
import repository.impl.EmployeeRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome");
        System.out.println("------------------------FIND ALL-------------------------------");
        Repository<Employee, Integer> employeeRepository = new EmployeeRepository();

        //gets all employees from db
        List<Employee> allEmployees = employeeRepository.findAll();

        for(Employee e : allEmployees){
            System.out.println(
                    e.getEmail() + " " +
                            e.getExtension() + " " +
                            e.getFirstName() + " " +
                            e.getLastName()
            );
        }
        System.out.println("------------------------FIND BY ID-------------------------------");

        Employee emp = new Employee();
        emp.setId(1000);
        employeeRepository.findById(emp.getId());
        System.out.println(emp.toString());


        System.out.println("--------------------------EXISTS-----------------------------");

        ///check if employee with id ? exists

        boolean existsEmp = employeeRepository.exists(emp.getId());
        System.out.println(existsEmp);


        System.out.println("--------------------------SAVE-----------------------------");
        Employee empSave = new Employee();
        empSave.setId(3);
        empSave.setJobTitle("1000");
        empSave.setEmail("1000");
        empSave.setFirstName("1000");
        empSave.setLastName("1000");
        empSave.setExtension("1000");
        System.out.println(employeeRepository.save(empSave));
        //e lexon employee
        //System.out.println("EmpSave object: "+ empSave.toString());


//        System.out.println("--------------------------UPDATE-----------------------------");
//        Employee updateEmp = new Employee();
//        updateEmp.setId(1000);
//        updateEmp.setJobTitle("dance");
//        updateEmp.setEmail("etest@gmail.com");
//        updateEmp.setFirstName("test");
//        updateEmp.setLastName("last test");
//        updateEmp.setExtension("x315");
//        employeeRepository.update(updateEmp);

//        System.out.println("--------------------------FIND BY ID-----------------------------");
//        employeeRepository.findById(updateEmp.getId());
//        System.out.println(emp.toString());
    }

}
