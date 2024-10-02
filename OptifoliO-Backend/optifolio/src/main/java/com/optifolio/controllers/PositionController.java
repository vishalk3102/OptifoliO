package com.optifolio.controllers;

import com.optifolio.dto.*;
import com.optifolio.exceptions.*;
import com.optifolio.services.PortfolioService;
import com.optifolio.services.PositionService;
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
public class PositionController {
    public  static final String PATH = "/api/v1/position";

    @Autowired
    private PositionService positionService;


    @GetMapping("/get-all-position-records")
    public ResponseEntity<List<PositionDTO>> getAllPositionRecords(){
        List<PositionDTO> records=positionService.getAllPositionRecords();
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }
    @GetMapping("/get-position-record/{tradingSymbol}")
    public ResponseEntity<PositionDTO> getPositionRecordById(@PathVariable String tradingSymbol) throws  PositionRecordNotFoundException {
        PositionDTO record=positionService.getPositionRecordById(tradingSymbol);
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }


    //    ADD NEW CAPITAL RECORD
    @PostMapping("/add-position-record")
    public ResponseEntity<PositionDTO> addPositionRecord(@RequestBody PositionCreateDTO positionCreateDTO) throws PositionRecordAlreadyExistException {
        PositionDTO savedPositionRecord=positionService.addPositionRecord(positionCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPositionRecord);
    }


    //    UPDATE EXISTING  CAPITAL RECORD
    @PutMapping("/update-position-record/{tradingSymbol}")
    public ResponseEntity<PositionDTO> updatePositionRecord(@RequestBody PositionUpdateDTO positionUpdateDTO) throws  PositionRecordAlreadyExistException {
        PositionDTO savedPositionCapitalRecord=positionService.updatePositionRecord(positionUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedPositionCapitalRecord);
    }

    //    DELETE EXISTING  CAPITAL RECORD
    @DeleteMapping("/delete-position-record/{tradingSymbol}")
    public ResponseEntity<PositionDTO> deletePositionRecord(@PathVariable String tradingSymbol) throws PositionRecordNotFoundException {
        PositionDTO deletePositionRecord=positionService.deletePositionRecord(tradingSymbol);
        return ResponseEntity.status(HttpStatus.OK).body(deletePositionRecord);
    }
}
