package pl.polsl.controller;

import io.swagger.annotations.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.*;
import pl.polsl.service.*;

import javax.xml.soap.SOAPException;
import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {

    private final InPostService inPost;

    private final GLSService gls;

    private final DHLService dhl;

    private final FedexService fedex;

    private final UPSService ups;

    private final PocztaPolskaService pocztaPolska;

    public PackageController(InPostService inPost, GLSService gls, DHLService dhl, FedexService fedex, UPSService ups, PocztaPolskaService pocztaPolska) {
        this.inPost = inPost;
        this.gls = gls;
        this.dhl = dhl;
        this.fedex = fedex;
        this.ups = ups;
        this.pocztaPolska = pocztaPolska;
    }

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
    public ResponseEntity<DHL> getDHLPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        return new ResponseEntity<>(dhl.getPackage(), HttpStatus.OK);
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
    public ResponseEntity<Fedex> getFedexPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) {
        return new ResponseEntity<>(fedex.getPackage(code), HttpStatus.OK);
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
    public ResponseEntity<UPS> getUPSPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(ups.getPackage(code), HttpStatus.OK);
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
    @ApiOperation(value = "Get package by gls code", nickname = "getPackage", notes = "", tags = {"GLS",})
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


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get all gls packages", nickname = "getPackage", notes = "", tags = {"GLS",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/gls",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<GLS>> getGLSAllPackage() {
        return new ResponseEntity<>(gls.getAll(), HttpStatus.OK);
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
    public ResponseEntity<InPost> getInPostPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) throws Exception {
        return new ResponseEntity<>(inPost.getPackage(code), HttpStatus.OK);
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
    public ResponseEntity<PocztaPolska> getPocztaPolskaPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code) throws SOAPException, JSONException {
        return new ResponseEntity<>(pocztaPolska.getPackage(code), HttpStatus.OK);
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
