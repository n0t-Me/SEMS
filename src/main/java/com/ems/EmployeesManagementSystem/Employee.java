package com.ems.EmployeesManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
    private int emp_no;
    private int salary;
    private String first_name;
    private String last_name;
    private String gender;
    private String birth_date;
    private String hire_date;
    private String title;
    private String department;

    public Employee(int emp_no, String first_name, String last_name, String gender, String birth_date, String hire_date, int salary, String title, String department) {
        this.emp_no = emp_no;
        this.salary = salary;
        this.first_name = first_name;
        this.last_name = last_name;
        this.title = title;
        this.department = department;
        this.gender = gender;
        this.hire_date = hire_date;
        this.birth_date = birth_date;
    }

    public Employee() {
        this.emp_no = 0;
        this.salary = 0;
        this.first_name = "";
        this.last_name = "";
        this.title = "";
        this.department = "";
        this.birth_date = "";
        this.hire_date = "";
        this.gender = "";
    }

    public String toString() {
        return String.format("Employee[\n\temp_no = %d,\n\tfirst_name = %s,\n\tlast_name = %s,\n\tgender = %s,\n\tbirth_date = %s,\n\thire_date = %s,\n\tdepartment = %s,\n\ttitle = %s,\n\tsalary = %d\n]", emp_no, first_name, last_name, gender, birth_date, hire_date, department, title, salary);
    }

    public void getByEmp_no(int emp_no) throws SQLException {
        SQLResult sqlResult = SQLHandler.executeSQL("SELECT employees.first_name, employees.last_name, employees.gender, employees.birth_date, employees.hire_date, salaries.salary, titles.title, departments.dept_name " +
                "FROM employees INNER JOIN salaries ON employees.emp_no = salaries.emp_no " +
                "INNER JOIN titles ON employees.emp_no = titles.emp_no " +
                "INNER JOIN dept_emp ON dept_emp.emp_no = employees.emp_no " +
                "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no " +
                "WHERE employees.emp_no=" + emp_no + " AND salaries.to_date='9999-01-01' AND titles.to_date='9999-01-01' AND dept_emp.to_date='9999-01-01'", false);//SQL :(
        ResultSet resultSet = sqlResult.getResultSet();
        try {
            resultSet.next();
            this.emp_no = emp_no;
            this.salary = resultSet.getInt("salary");
            this.gender = resultSet.getString("gender");
            this.birth_date = resultSet.getString("birth_date");
            this.hire_date = resultSet.getString("hire_date");
            this.department = resultSet.getString("dept_name");
            this.title = resultSet.getString("title");
            this.first_name = resultSet.getString("first_name");
            this.last_name = resultSet.getString("last_name");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateSalary(int newSalary) throws SQLException {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SQLResult updateResult = SQLHandler.executeSQL("UPDATE salaries SET to_date='" + currentDate + "' WHERE to_date='9999-01-01' AND emp_no=" + emp_no, true);
        int updateCount = updateResult.getCount();
        System.out.println("[+] Updated: " + updateCount);
        if (updateCount == 0) {
            throw new RuntimeException("[!] No such employee is found OR Error in salaries db");
        } else if (updateCount == 1) {
            SQLHandler.executeSQL(String.format("INSERT INTO salaries VALUES(%d,%d,'%s','9999-01-01')", emp_no, salary, currentDate), true);// INSERT new salary
            System.out.println("Updated salary");
            salary = newSalary;
        } else {
            salary = newSalary;
            throw new RuntimeException("[!] Got more than one entry. Aborting");
        }
    }

    public int getEmp_no() {
        return emp_no;
    }

    public int getSalary() {
        return salary;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getHire_date() {
        return hire_date;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }
}
