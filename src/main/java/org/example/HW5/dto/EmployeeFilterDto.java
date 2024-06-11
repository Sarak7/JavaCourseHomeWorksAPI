package org.example.HW5.dto;

import jakarta.ws.rs.QueryParam;

public class EmployeeFilterDto {

    private @QueryParam("job_id") Integer job_id;
    private @QueryParam("hire_date") String hire_date;

    public EmployeeFilterDto() {
    }

    public EmployeeFilterDto(String hire_date, Integer job_id) {
        this.hire_date = hire_date;
        this.job_id = job_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        return "EmployeeFilterDto{" +
                "job_id=" + job_id +
                ", hire_date='" + hire_date + '\'' +
                '}';
    }
}
