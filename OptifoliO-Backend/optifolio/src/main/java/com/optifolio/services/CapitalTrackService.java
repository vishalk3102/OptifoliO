package com.optifolio.services;

import com.optifolio.dto.CapitalTrackCreateDTO;
import com.optifolio.dto.CapitalTrackDTO;
import com.optifolio.dto.CapitalTrackUpdateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.exceptions.CapitalRecordNotFoundException;

import java.util.List;

public interface CapitalTrackService {

    CapitalTrackDTO getCapitalRecordById(String capitalTrackId) throws CapitalRecordNotFoundException;

    List<CapitalTrackDTO> getAllCapitalRecords();
    CapitalTrackDTO addCapitalRecord(CapitalTrackCreateDTO capitalTrackCreateDTO);

    CapitalTrackDTO updateCapitalRecord(CapitalTrackUpdateDTO capitalTrackUpdateDTO) throws CapitalRecordNotFoundException;

    CapitalTrackDTO deleteCapitalRecord(String capitalTrackId) throws CapitalRecordNotFoundException;
}
