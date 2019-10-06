package pl.polsl.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.User;

import javax.validation.Valid;

@RestController
@Api(value = "User", description = "REST API for users", tags = { "User" })
public class UserController {



    @ApiOperation(value = "Create user", nickname = "createUser", notes = "This can only be done by the logged in user.", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/user",
            produces = { "application/xml", "application/json" },
            method = RequestMethod.POST)
    public void createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User body) {
        return;
    }

    @ApiOperation(value = "Delete user", nickname = "deleteUser", notes = "This can only be done by the logged in user.", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/user/{id}",
            produces = { "application/xml", "application/json" },
            method = RequestMethod.DELETE)
    public void deleteUser(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("id") String id) {
        return;
    }

    @ApiOperation(value = "Get user by user id", nickname = "getUserById", notes = "", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/user/{id}",
            produces = { "application/xml", "application/json" },
            method = RequestMethod.GET)
    public void getUserById(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        return;
    }

    @ApiOperation(value = "Updated user", nickname = "updateUser", notes = "This can only be done by the logged in user.", tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/user/{id}",
            produces = { "application/xml", "application/json" },
            method = RequestMethod.PUT)
    public void updateUser(@ApiParam(value = "name that need to be updated",required=true) @PathVariable("id") String id,@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body) {
        return;
    }
}
