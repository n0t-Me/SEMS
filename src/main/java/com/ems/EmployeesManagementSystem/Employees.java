package com.ems.EmployeesManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employees {
    public static ArrayList<Employee> getByParams(String fName, String lName, String gdr) {
        ArrayList<Employee> employeesArray = new ArrayList<>();
        try {
            SQLResult sqlResult = SQLHandler.executeSQL("SELECT employees.emp_no, employees.first_name, employees.last_name, employees.gender, employees.birth_date, employees.hire_date, salaries.salary, titles.title, departments.dept_name " +
                    "FROM employees INNER JOIN salaries ON employees.emp_no = salaries.emp_no " +
                    "INNER JOIN titles ON employees.emp_no = titles.emp_no " +
                    "INNER JOIN dept_emp ON dept_emp.emp_no = employees.emp_no " +
                    "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no " +
                    "WHERE employees.first_name LIKE '" + fName + "%' AND employees.last_name LIKE '" + lName + "%' " +
                    "AND employees.gender LIKE '" + gdr + "' " +
                    "AND salaries.to_date='9999-01-01' AND titles.to_date='9999-01-01' AND dept_emp.to_date='9999-01-01'", false);//SQL bad :(
            ResultSet resultSet = sqlResult.getResultSet();
            while (resultSet.next()) {
                int emp_no = resultSet.getInt("emp_no");
                int salary = resultSet.getInt("salary");
                String gender = resultSet.getString("gender");
                String birth_date = resultSet.getString("birth_date");
                String hire_date = resultSet.getString("hire_date");
                String department = resultSet.getString("dept_name");
                String title = resultSet.getString("title");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                Employee employee = new Employee(emp_no, first_name, last_name, gender, birth_date, hire_date, salary, title, department);
                employeesArray.add(employee);
            }
            return employeesArray;
        } catch (SQLException e) {
            e.printStackTrace();
            return employeesArray;
        }
    }
}
