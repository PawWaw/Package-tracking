package pl.polsl.controller;

import io.swagger.annotations.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Delivery;
import pl.polsl.service.DeliveryService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "Delivery", description = "REST API for deliveries", tags = { "Delivery" })
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Add delivery status", nickname = "addDelivery", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Delivery> createDelivery(@ApiParam(value = "Delivery object that needs to be added to the db" ,required=true )  @Valid @RequestBody Delivery body) {
        service.createDelivery(body);
        return new ResponseEntity<Delivery>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/allegro",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity giveToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Delete delivery by its unique code", nickname = "deleteDelivery", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery/{code}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    public ResponseEntity<Delivery> deleteDelivery(@ApiParam(value = "",required=true) @PathVariable("code") String code) {
        service.deleteDelivery(code);
        return new ResponseEntity<Delivery>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Finds delivery by its unique code", nickname = "getDelivery", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery/{code}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<Delivery> getDelivery(@ApiParam(value = "",required=true) @PathVariable("code") String code) {
        return new ResponseEntity<Delivery>(service.getDelivery(code), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Update specific delivery", nickname = "modifyDelivery", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery/{code}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    public ResponseEntity<Delivery> modifyDelivery(@ApiParam(value = "",required=true) @PathVariable("code") String code,@ApiParam(value = "Updated delivery object" ,required=true )  @Valid @RequestBody Delivery body) {
        service.modifyDelivery(body);
        return new ResponseEntity<Delivery>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get all active deliveries", nickname = "getAllDeliveries", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return new ResponseEntity<List<Delivery>>(service.getAllDeliveries(), HttpStatus.OK);
    }
}
