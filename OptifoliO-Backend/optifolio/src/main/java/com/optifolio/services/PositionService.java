package com.optifolio.services;

import com.optifolio.dto.PositionCreateDTO;
import com.optifolio.dto.PositionDTO;
import com.optifolio.dto.PositionUpdateDTO;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PositionRecordAlreadyExistException;
import com.optifolio.exceptions.PositionRecordNotFoundException;

import java.util.List;

public interface PositionService {
    //    FUNCTION TO GET  EXISTING  POSITION RECORD BY ID
    PositionDTO getPositionRecordById(String optionId) throws PositionRecordNotFoundException;

    //    FUNCTION TO GET  ALL  POSITION RECORDS
    List<PositionDTO> getAllPositionRecords();

    //    FUNCTION TO ADD NEW POSITION RECORD
    PositionDTO addPositionRecord(PositionCreateDTO positionCreateDTO) throws PositionRecordAlreadyExistException;

    //    FUNCTION TO UPDATE EXISTING  POSITION RECORD
    PositionDTO updatePositionRecord(PositionUpdateDTO positionUpdateDTO) throws PositionRecordAlreadyExistException;

    //    FUNCTION TO DELETE EXISTING  POSITION RECORD
    PositionDTO deletePositionRecord(String optionId) throws PositionRecordNotFoundException;
}
