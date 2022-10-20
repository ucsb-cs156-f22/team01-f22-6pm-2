package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="Zip Code info from https://www.zippopotam.us/")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipcodeQueryService;

    @ApiOperation(value="Get the location of a zip code", notes="Website: https://www.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getCountryCodes(
        @ApiParam("zipcode, e.g. 93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipCode: zipcode={}", zipcode);
        String result = zipcodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }
}
