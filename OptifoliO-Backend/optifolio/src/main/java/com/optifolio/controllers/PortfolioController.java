package com.optifolio.controllers;


import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PortfolioRecordAlreadyExistException;
import com.optifolio.exceptions.PortfolioRecordNotFoundException;
import com.optifolio.exceptions.UserNotFoundException;
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
    @GetMapping("/get-portfolio-record/{tradingSymbol}")
    public ResponseEntity<PortfolioDTO> getPortfolioRecordById(@PathVariable String tradingSymbol) throws PortfolioRecordNotFoundException {
        PortfolioDTO record=portfolioService.getPortfolioRecordById(tradingSymbol);
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }


    //    ADD NEW CAPITAL RECORD
    @PostMapping("/add-portfolio-record")
    public ResponseEntity<PortfolioDTO> addPortfolioRecord(@RequestBody PortfolioCreateDTO portfolioCreateDTO) throws PortfolioRecordAlreadyExistException {
        PortfolioDTO savedPortfolioRecord=portfolioService.addPortfolioRecord(portfolioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPortfolioRecord);
    }


    //    UPDATE EXISTING  CAPITAL RECORD
    @PutMapping("/update-portfolio-record/{tradingSymbol}")
    public ResponseEntity<PortfolioDTO> updatePortfolioRecord(@RequestBody PortfolioUpdateDTO portfolioUpdateDTO) throws PortfolioRecordNotFoundException {
        PortfolioDTO savedPortfolioCapitalRecord=portfolioService.updatePortfolioRecord(portfolioUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedPortfolioCapitalRecord);
    }

    //    DELETE EXISTING  CAPITAL RECORD
    @DeleteMapping("/delete-portfolio-record/{tradingSymbol}")
    public ResponseEntity<PortfolioDTO> deletePortfolioRecord(@PathVariable String tradingSymbol) throws PortfolioRecordNotFoundException {
        PortfolioDTO deletePortfolioRecord=portfolioService.deletePortfolioRecord(tradingSymbol);
        return ResponseEntity.status(HttpStatus.OK).body(deletePortfolioRecord);
    }
}
