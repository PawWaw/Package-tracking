package pl.polsl.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.User;
import pl.polsl.service.UserService;

import javax.validation.Valid;

@RestController
@Api(value = "User", description = "REST API for users", tags = {"User"})
public class UserController {

    @Autowired
    private UserService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Login user", nickname = "loginUser", notes = "This can only be done by the logged in user.", tags = {"User",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/user/login",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(@ApiParam(value = "Created user object", required = true) @Valid @RequestBody User body) {
        if(service.loginUser(body))
            return new ResponseEntity<String>(service.generateToken(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Create user", nickname = "createUser", notes = "This can only be done by the logged in user.", tags = {"User",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/user/registration",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@ApiParam(value = "Created user object", required = true) @Valid @RequestBody User body) {
        if(!service.checkIfExists(body.getUsername()))
        {
            if(!service.createUser(body))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Delete user", nickname = "deleteUser", notes = "This can only be done by the logged in user.", tags = {"User",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/user/{code}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@ApiParam(value = "The name that needs to be deleted", required = true) @PathVariable("code") String code) {
        service.deleteUser(code);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get user by user code", nickname = "getUser", notes = "", tags = {"User",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/user/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        return new ResponseEntity<User>(service.getUser(code), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Updated user", nickname = "updateUser", notes = "This can only be done by the logged in user.", tags = {"User",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/user/{code}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@ApiParam(value = "name that need to be updated", required = true) @PathVariable("code") String code, @ApiParam(value = "Updated user object", required = true) @Valid @RequestBody User body) {
        service.modifyUser(body);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
}
