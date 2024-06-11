package org.example.HW5.controller;



import jakarta.ws.rs.core.*;
import org.example.HW5.dao.EmployeeDAO;
import jakarta.ws.rs.*;

import org.example.HW5.dto.EmployeesDto;
import org.example.HW5.mappers.EmployeesMapper;
import org.example.HW5.models.Employees;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.HW5.dto.EmployeeFilterDto;
import org.example.exceptions.DataNotFoundException;


@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    public Response SELECT_ALL_EMPLOYEES(
            @BeanParam EmployeeFilterDto filter
    ) {

        try {
            GenericEntity<ArrayList<Employees>> employees = new GenericEntity<ArrayList<Employees>>(dao.SELECT_ALL_EMPLOYEES(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(employees)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response
                    .ok(employees, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{employee_id}")
    public Response  SELECT_ONE_EMPLOYEE(@PathParam("employee_id") int employee_id) throws SQLException{

        try {
            Employees employees = dao.selectEmployee(employee_id);
            if(employees == null ){

                throw new DataNotFoundException("employees " + employee_id + "Not found");
            }
            //headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML) {

            EmployeesDto dto = EmployeesMapper.INSTANCE.toDeptDto(employees);
            addLinks(dto);

            return Response.ok(dto).build();

            /* return Response
                    .ok(dto)
                    .type(MediaType.APPLICATION_JSON)
                    .build(); */

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(EmployeesDto dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI empUri = uriInfo.getAbsolutePathBuilder()
                .path(EmployeeController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(empUri.toString(), "employees");

    }

    @DELETE
    @Path("{employee_id}")
    public Response DELETE_JOB(@PathParam("employee_id") int employee_id) {

        try {
            dao.DELETE_EMPLOYEE(employee_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response
                .ok(employee_id)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    @POST
    public Response INSERT_JOB(Employees Employees) {

        try {
            dao.INSERT_EMPLOYEE(Employees);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(Employees.getEmployee_id() + "").build();
            return Response
                    .created(uri)
                    .cookie(cookie)
                    .header("Created by", "Sara")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{employee_id}")
    public Response UPDATE_JOB(@PathParam("employee_id") int employee_id, Employees employees) {

        try {
            employees.setJob_id(employee_id);
            dao.UPDATE_EMPLOYEE(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response
                .ok(employees)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }



}
