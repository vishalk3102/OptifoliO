package com.optifolio.controllers;


import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.UserAlreadyExistException;
import com.optifolio.exceptions.UserNotFoundException;
import com.optifolio.models.CapitalTrack;
import com.optifolio.services.CapitalTrackService;
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
public class CapitalTrackController {

    public  static final String PATH = "/api/v1/capital-track";

    @Autowired
    private CapitalTrackService capitalTrackService;


    @GetMapping("/get-all-capital-records")
    public ResponseEntity<List<CapitalTrackDTO>> getAllCapitalRecords(){
        List<CapitalTrackDTO> records=capitalTrackService.getAllCapitalRecords();
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }
    @GetMapping("/get-capital-record/{capitalTrackId}")
    public ResponseEntity<CapitalTrackDTO> getCapitalRecordById(@PathVariable String capitalTrackId) throws UserNotFoundException, CapitalRecordNotFoundException {
        CapitalTrackDTO record=capitalTrackService.getCapitalRecordById(capitalTrackId);
        return ResponseEntity.status(HttpStatus.OK).body(record);
    }


//    ADD NEW CAPITAL RECORD
    @PostMapping("/add-capital")
    public ResponseEntity<CapitalTrackDTO> addCapitalRecord(@RequestBody CapitalTrackCreateDTO capitalTrackCreateDTO)  {
        CapitalTrackDTO savedCapitalRecord=capitalTrackService.addCapitalRecord(capitalTrackCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCapitalRecord);
    }


//    UPDATE EXISTING  CAPITAL RECORD
    @PutMapping("/update-capital/{capitalTrackId}")
    public ResponseEntity<CapitalTrackDTO> updateCapitalRecord(@RequestBody CapitalTrackUpdateDTO capitalTrackUpdateDTO) throws CapitalRecordNotFoundException {
        CapitalTrackDTO savedCapitalRecord=capitalTrackService.updateCapitalRecord(capitalTrackUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedCapitalRecord);
    }

//    DELETE EXISTING  CAPITAL RECORD
    @DeleteMapping("/delete-capital/{capitalTrackId}")
    public ResponseEntity<CapitalTrackDTO> deleteCapitalRecord(@PathVariable String capitalTrackId) throws CapitalRecordNotFoundException {
        CapitalTrackDTO deleteCapitalrecord=capitalTrackService.deleteCapitalRecord(capitalTrackId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteCapitalrecord);
    }
}
