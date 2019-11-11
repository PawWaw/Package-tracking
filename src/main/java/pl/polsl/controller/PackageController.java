package pl.polsl.controller;

import io.swagger.annotations.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.*;
import pl.polsl.service.*;

import javax.xml.soap.SOAPException;
import java.util.List;

@RestController
@Api(value = "InPost", description = "REST API for InPost packages", tags = {"InPost"})
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private InPostService inPost;

    @Autowired
    private GLSService gls;

    @Autowired
    private DHLService dhl;

    @Autowired
    private FedexService fedex;

    @Autowired
    private UPSService ups;

    @Autowired
    private PocztaPolskaService pocztaPolska;

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by gls code", nickname = "getPackage", notes = "", tags = {"CLS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/dhl/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<DHL> getDHLPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        return new ResponseEntity<>(dhl.getPackage(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by gls code", nickname = "getPackage", notes = "", tags = {"CLS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/fedex/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<String> getFedexPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        String message = fedex.getPackage(code);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by gls code", nickname = "getPackage", notes = "", tags = {"CLS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/ups/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<UPS> getUPSPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(ups.getPackage(code), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by gls code", nickname = "getPackage", notes = "", tags = {"CLS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/gls/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<GLS> getGLSPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        return new ResponseEntity<>(gls.getPackage(), HttpStatus.OK);
    }

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
        return new ResponseEntity<>(inPost.getPackage(code), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get all inpost packages from DB", nickname = "getPackages", notes = "", tags = {"InPost",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/inpost",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<InPost>> getInPostAllPackages() {
        List<InPost> list = inPost.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
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
    public ResponseEntity<PocztaPolska> getPocztaPolskaPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) throws SOAPException, JSONException {
        return new ResponseEntity<>(pocztaPolska.getPackage(code), HttpStatus.OK);
    }
}
