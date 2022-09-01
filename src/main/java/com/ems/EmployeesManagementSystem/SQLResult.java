package com.ems.EmployeesManagementSystem;

import java.sql.ResultSet;

public class SQLResult {
    public ResultSet resultSet;
    public boolean isErr;
    private int count = 0;

    public void result(ResultSet resultSet) {
        this.resultSet = resultSet;
        this.isErr = false;
    }

    public void count(int i) {
        this.count = i;
        this.isErr = false;
    }

    public void result() {
        this.isErr = true;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public int getCount() {
        return count;
    }
}
//cuz in java ur function can't return to diff values;