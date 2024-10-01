package com.optifolio.controllers;


import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PortfolioRecordNotFoundException;
import com.optifolio.exceptions.UserNotFoundException;
import com.optifolio.services.CapitalTrackService;
import com.optifolio.services.PortfolioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = UserController.PATH,produces = MediaType.APPLICATION_JSON_VALUE)
public class PortfolioController {
    public  static final String PATH = "/api/v1/portfolio";

    @Autowired
    private PortfolioService portfolioService;


    @GetMapping("/get-all-portfolio-records")
    public ResponseEntity<List<PortfolioDTO>> getAllCapitalRecords(){
        List<PortfolioDTO> records=portfolioService.getAllPortfolioRecords();
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }
    @GetMapping("/get-portfolio-record/{holdingId}")
    public ResponseEntity<PortfolioDTO> getPortfolioRecordById(@PathVariable String holdingId) throws UserNotFoundException, CapitalRecordNotFoundException, PortfolioRecordNotFoundException {
        PortfolioDTO record=portfolioService.getPortfolioRecordById(holdingId);
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }


    //    ADD NEW CAPITAL RECORD
    @PostMapping("/add-portfolio-record")
    public ResponseEntity<PortfolioDTO> addPortfolioRecord(@RequestBody PortfolioCreateDTO portfolioCreateDTO)  {
        PortfolioDTO savedPortfolioRecord=portfolioService.addPortfolioRecord(portfolioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPortfolioRecord);
    }


    //    UPDATE EXISTING  CAPITAL RECORD
    @PutMapping("/update-portfolio-record/{holdingId}")
    public ResponseEntity<PortfolioDTO> updatePortfolioRecord(@RequestBody PortfolioUpdateCTO portfolioUpdateCTO) throws CapitalRecordNotFoundException {
        PortfolioDTO savedPortfolioCapitalRecord=portfolioService.updatePortfolioRecord(portfolioUpdateCTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedPortfolioCapitalRecord);
    }

    //    DELETE EXISTING  CAPITAL RECORD
    @DeleteMapping("/delete-portfolio-record/{holdingId}")
    public ResponseEntity<PortfolioDTO> deletePortfolioRecord(@PathVariable String capitalTrackId) throws CapitalRecordNotFoundException {
        PortfolioDTO deletePortfoliorecord=portfolioService.deletePortfolioRecord(capitalTrackId);
        return ResponseEntity.status(HttpStatus.OK).body(deletePortfoliorecord);
    }
}
