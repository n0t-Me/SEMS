package com.ems.EmployeesManagementSystem;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EmployeeManagerController {

    @FXML
    private TextField tfEmpFirstName;
    @FXML
    private TableView<Employee> empTable;
    @FXML
    private TableColumn<Employee, Integer> EmpNoCol;
    @FXML
    private TableColumn<Employee, String> FirstNameCol;
    @FXML
    private TableColumn<Employee, String> LastNameCol;
    @FXML
    private TableColumn<Employee, Integer> SalaryCol;
    @FXML
    private TableColumn<Employee, String> DepartmentCol;
    @FXML
    private Label empNo;
    @FXML
    private Label empFirstName;
    @FXML
    private Label empLastName;
    @FXML
    private Label empSalary;
    @FXML
    private Label empDept;
    @FXML
    private Label empGender;
    @FXML
    private Label empTitle;
    @FXML
    private Label empHireDate;
    @FXML
    private Label empBDay;
    @FXML
    private TextField tfEmpLastName;
    @FXML
    private ComboBox<String> cbGender;

    @FXML
    private void initialize() {
        EmpNoCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("emp_no"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));
        SalaryCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));
        DepartmentCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));
    }

    @FXML
    protected void onButtonClick() {
        String first_name = tfEmpFirstName.getText();
        String last_name = tfEmpLastName.getText();
        String gender = cbGender.getValue();
        if (gender == null) {
            gender = "%";
        } else if (gender.equals("Any")) {
            gender = "%";
        } else if (gender.equals("Male")) {
            gender = "M";
        } else {
            gender = "F";
        }
        ArrayList<Employee> employees = Employees.getByParams(first_name, last_name, gender);
        empTable.getItems().setAll(employees);
    }

    @FXML
    protected void onRowSelect() {
        Employee selectedEmployee = empTable.getSelectionModel().getSelectedItem();
        empNo.setText("Employee No.:  " + selectedEmployee.getEmp_no());
        empFirstName.setText("First name:  " + selectedEmployee.getFirst_name());
        empLastName.setText("Last name:  " + selectedEmployee.getLast_name());
        String employeeGender = selectedEmployee.getGender();
        if (employeeGender.equals("M")) {
            employeeGender = "Male";
        } else {
            employeeGender = "Female";
        }
        empGender.setText("Gender:  " + employeeGender);
        empDept.setText("Department:  " + selectedEmployee.getDepartment());
        empTitle.setText("Title:  " + selectedEmployee.getTitle());
        empHireDate.setText("Hire Date:  " + selectedEmployee.getHire_date());
        empSalary.setText("Salary:  " + selectedEmployee.getSalary());
        empBDay.setText("Birthday:  " + selectedEmployee.getBirth_date());
    }

}