package pl.polsl.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.service.allegroService;
import pl.polsl.model.Delivery;
import pl.polsl.service.DeliveryService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DeliveryController {

    private boolean bool = false;
    private allegroService allegro = new allegroService();

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

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get token", nickname = "getToken", notes = "", tags={ "Allegro", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "/allegro/me",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity giveToken() throws Exception {
        if(bool)
            return new ResponseEntity<>(allegro.getMe(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get account data", nickname = "getMe", notes = "", tags={ "Allegro", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "allegro",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity getMe(@RequestParam("code") String code) throws Exception {
        bool = allegro.getUserToken(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Check if token is set", nickname = "checkToken", notes = "", tags={ "Allegro", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "allegro/checkToken",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkToken() {
        return bool ? new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Erase token", nickname = "eraseToken", notes = "", tags={ "Allegro", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 405, message = "Invalid input"),
            @ApiResponse(code = 500, message = "Internal error") })
    @RequestMapping(value = "allegro/erase",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<Boolean> eraseToken() {
        bool = false;
        return new ResponseEntity<Boolean>(false, HttpStatus.OK);
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
