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
    @GetMapping("/get-position-record/{optionId}")
    public ResponseEntity<PositionDTO> getPositionRecordById(@PathVariable String optionId) throws UserNotFoundException, CapitalRecordNotFoundException, PortfolioRecordNotFoundException, PositionRecordNotFoundException {
        PositionDTO record=positionService.getPositionRecordById(optionId);
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }


    //    ADD NEW CAPITAL RECORD
    @PostMapping("/add-position-record")
    public ResponseEntity<PositionDTO> addPositionRecord(@RequestBody PositionCreateDTO positionCreateDTO)  {
        PositionDTO savedPositionRecord=positionService.addPositionRecord(positionCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPositionRecord);
    }


    //    UPDATE EXISTING  CAPITAL RECORD
    @PutMapping("/update-position-record/{optionId}")
    public ResponseEntity<PositionDTO> updatePositionRecord(@RequestBody PositionUpdateDTO positionUpdateDTO) throws CapitalRecordNotFoundException, PositionRecordAlreadyExistException {
        PositionDTO savedPositionCapitalRecord=positionService.updatePositionRecord(positionUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedPositionCapitalRecord);
    }

    //    DELETE EXISTING  CAPITAL RECORD
    @DeleteMapping("/delete-position-record/{optionId}")
    public ResponseEntity<PositionDTO> deletePositionRecord(@PathVariable String optionId) throws CapitalRecordNotFoundException {
        PositionDTO deletePositionrecord=positionService.deletePositionRecord(optionId);
        return ResponseEntity.status(HttpStatus.OK).body(deletePositionrecord);
    }
}
