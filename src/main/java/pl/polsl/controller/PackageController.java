package pl.polsl.controller;

import io.swagger.annotations.*;
import org.apache.http.HttpHeaders;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.*;
import pl.polsl.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPException;
import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private InPostService inPost;

    @Autowired
    private DHLService dhl;

    @Autowired
    private FedexService fedex;

    @Autowired
    private UPSService ups;

    @Autowired
    private PocztaPolskaService pocztaPolska;

    @Autowired
    private CommonsService commons;

    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by dhl code", nickname = "getPackage", notes = "", tags = {"DHL",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/dhl/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<DHL> getDHLPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) {
        return new ResponseEntity<>(dhl.getPackage(code, commons.getUserFromJWT(token)), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get pall dhl packages", nickname = "getPackage", notes = "", tags = {"DHL",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/dhl",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<DHL>> getDHLPackage() {
        return new ResponseEntity<>(dhl.getAll(), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by fedex code", nickname = "getPackage", notes = "", tags = {"Fedex",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/fedex/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Fedex> getFedexPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) {
        return new ResponseEntity<>(fedex.getPackage(code, commons.getUserFromJWT(token)), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get all fedex packages", nickname = "getPackage", notes = "", tags = {"Fedex",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/fedex",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Fedex>> getFedexAllPackages() {
        return new ResponseEntity<>(fedex.getAll(), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by ups code", nickname = "getPackage", notes = "", tags = {"UPS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/ups/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<UPS> getUPSPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) throws Exception {
        return new ResponseEntity<>(ups.getPackage(code, commons.getUserFromJWT(token)), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get all ups packages", nickname = "getPackage", notes = "", tags = {"UPS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/ups",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<UPS>> getUPSAllPackages() {
        return new ResponseEntity<>(ups.getAll(), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by inpost code", nickname = "getPackage", notes = "", tags = {"InPost",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/inpost/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<InPost> getInPostPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) throws Exception {
        return new ResponseEntity<>(inPost.getPackage(code, commons.getUserFromJWT(token)), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
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


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by Poczta Polska code", nickname = "getPackage", notes = "", tags = {"PocztaPolska",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/pocztapolska/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<PocztaPolska> getPocztaPolskaPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) throws SOAPException, JSONException {
        return new ResponseEntity<>(pocztaPolska.getPackage(code, commons.getUserFromJWT(token)), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get all Poczta Polska packages", nickname = "getPackages", notes = "", tags = {"PocztaPolska",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/pocztapolska",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<PocztaPolska>> getPocztaPolskaAllPackages() throws JSONException {
        return new ResponseEntity<>(pocztaPolska.getAll(), HttpStatus.OK);
    }
}
