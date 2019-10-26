package pl.polsl.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.InPost;
import pl.polsl.model.PocztaPolska;
import pl.polsl.service.InPostService;
import pl.polsl.service.PocztaPolskaService;

import javax.xml.soap.SOAPMessage;

@RestController
@Api(value = "InPost", description = "REST API for InPost packages", tags = {"InPost"})
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private InPostService inpost;

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by inpost code", nickname = "getPackage", notes = "", tags = {"InPost",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/inpost/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<InPost> getInPostPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) throws Exception {
        return new ResponseEntity<InPost>(inpost.getPackage(code), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by Poczta Polska code", nickname = "getPackage", notes = "", tags = {"PocztaPolska",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/pocztapolska/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<SOAPMessage> getPocztaPolskaPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        PocztaPolskaService service = new PocztaPolskaService();
        return new ResponseEntity<SOAPMessage>(service.getPackage(code), HttpStatus.OK);
    }
}
