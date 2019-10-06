package pl.polsl.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Delivery;
import pl.polsl.service.DeliveryService;

import javax.validation.Valid;

@RestController
@Api(value = "Delivery", description = "REST API for deliveries", tags = { "Delivery" })
public class DeliveryController {

//    @Autowired
//    DeliveryService service;

    @ApiOperation(value = "Add delivery status", nickname = "adDelivery", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery",
            produces = { "application/xml", "application/json" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    public void createDelivery(@ApiParam(value = "Delivery object that needs to be added to the db" ,required=true )  @Valid @RequestBody Delivery body) {
        //service.createDelivery(body);
        return;
    }

    @ApiOperation(value = "Delete delivery by its unique id", nickname = "deliveryIdDelete", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery/{id}",
            produces = { "application/xml", "application/json" },
            method = RequestMethod.DELETE)
    public void deliveryIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        return;
    }

    @ApiOperation(value = "Finds delivery by its unique id", nickname = "deliveryIdGet", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery/{id}",
            produces = { "application/xml", "application/json" },
            method = RequestMethod.GET)
    public void deliveryIdGet(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        return;
    }

    @ApiOperation(value = "Update specific delivery", nickname = "deliveryIdPut", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery/{id}",
            produces = { "application/xml", "application/json" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.PUT)
    public void deliveryIdPut(@ApiParam(value = "",required=true) @PathVariable("id") String id,@ApiParam(value = "Updated delivery object" ,required=true )  @Valid @RequestBody Delivery body) {
        return;
    }

    @ApiOperation(value = "Get all active deliveries", nickname = "getAllDeliveries", notes = "", tags={ "Delivery", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Delivery not found"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/delivery",
            produces = { "application/xml", "application/json" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.GET)
    public void getAllDeliveries() {
        return;
    }
}
