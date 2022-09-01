module com.ems.employeemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens com.ems.EmployeesManagementSystem to javafx.fxml;
    exports com.ems.EmployeesManagementSystem;
}