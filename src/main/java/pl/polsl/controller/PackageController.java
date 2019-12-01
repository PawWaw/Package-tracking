package pl.polsl.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.dhlModels.DHL;
import pl.polsl.model.dhlModels.DHLDetail;
import pl.polsl.model.fedexModels.Fedex;
import pl.polsl.model.inPostModels.InPost;
import pl.polsl.model.inPostModels.InPostDataList;
import pl.polsl.model.inPostModels.InPostDetails;
import pl.polsl.model.pocztaPolskaModels.PocztaPolska;
import pl.polsl.model.pocztaPolskaModels.PocztaPolskaEvent;
import pl.polsl.model.upsModels.UPS;
import pl.polsl.service.*;

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
    private UnknownService unknownService;

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
    public ResponseEntity<List<DHLDetail>> getDHLPackage(@RequestHeader("authorization") String token) {
        return new ResponseEntity<>(dhl.getAll(commons.getUserFromJWT(token)), HttpStatus.OK);
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
    public ResponseEntity<Fedex> getFedexPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) throws Exception {
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
    public ResponseEntity<List<InPostDetails>> getInPostAllPackages(@RequestHeader("authorization") String token) {
        List<InPostDetails> list = inPost.getAll(commons.getUserFromJWT(token));
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
    public ResponseEntity<List<PocztaPolskaEvent>> getPocztaPolskaAllPackages(@RequestHeader("authorization") String token) throws JSONException {
        return new ResponseEntity<>(pocztaPolska.getAll(commons.getUserFromJWT(token)), HttpStatus.OK);
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get package by unknown code", nickname = "getPackage", notes = "", tags = {"PocztaPolska",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "Package not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @RequestMapping(value = "/unknown/{code}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Object> getUnknownPackage(@ApiParam(value = "", required = true) @PathVariable("code") String code, @RequestHeader("authorization") String token) throws SOAPException, JSONException {
        return new ResponseEntity<>(unknownService.getUnknown(code, commons.getUserFromJWT(token)), HttpStatus.OK);
    }
}
