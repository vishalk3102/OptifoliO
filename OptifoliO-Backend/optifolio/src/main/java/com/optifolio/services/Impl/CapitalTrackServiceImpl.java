package com.optifolio.services.Impl;

import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.mapper.CapitalTrackMapper;
import com.optifolio.models.CapitalTrack;
import com.optifolio.repositories.CapitalTrackRepository;
import com.optifolio.services.CapitalTrackService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class CapitalTrackServiceImpl implements CapitalTrackService {

    @Autowired
    private CapitalTrackMapper capitalTrackMapper;

    @Autowired
    private CapitalTrackRepository capitalTrackRepository;


//    FUNCTION TO GET  EXISTING  CAPITAL RECORD BY ID
    @Override
    public CapitalTrackDTO getCapitalRecordById(String capitalTrackId) throws CapitalRecordNotFoundException {
        CapitalTrack record=capitalTrackRepository.findById(capitalTrackId).orElseThrow(CapitalRecordNotFoundException::new);
        return  capitalTrackMapper.toCapitalTrackDTO(record);
    }


//    FUNCTION TO GET  ALL  CAPITAL RECORDS
    @Override
    public List<CapitalTrackDTO> getAllCapitalRecords() {
        List<CapitalTrack> records=capitalTrackRepository.findAll();
        if(records.isEmpty())
        {
            return List.of();
        }
        return capitalTrackMapper.toCapitalTrackDTOS(records);
    }

    //    FUNCTION TO ADD NEW CAPITAL RECORD
    @Override
    public CapitalTrackDTO addCapitalRecord(CapitalTrackCreateDTO capitalTrackCreateDTO) {
        CapitalTrack capitalRecord=capitalTrackMapper.toCapitalTrackEntity(capitalTrackCreateDTO);
        CapitalTrack savedCapitalRecord= capitalTrackRepository.save(capitalRecord);
        return capitalTrackMapper.toCapitalTrackDTO(savedCapitalRecord);
    }


//    FUNCTION TO UPDATE EXISTING  CAPITAL RECORD
    @Override
    public CapitalTrackDTO updateCapitalRecord(CapitalTrackUpdateDTO capitalTrackUpdateDTO) throws CapitalRecordNotFoundException {
        CapitalTrack existingCapitalRecord=capitalTrackRepository.findById(capitalTrackUpdateDTO.getCapitalTrackId()).orElseThrow(CapitalRecordNotFoundException::new);
        capitalTrackMapper.updateCapitalTrackEntityFromDTO(capitalTrackUpdateDTO,existingCapitalRecord);
        CapitalTrack updatedRecord=capitalTrackRepository.save(existingCapitalRecord);
        return  capitalTrackMapper.toCapitalTrackDTO(updatedRecord);
    }

//    FUNCTION TO DELETE EXISTING  CAPITAL RECORD
    @Override
    public CapitalTrackDTO deleteCapitalRecord(String capitalTrackId) throws CapitalRecordNotFoundException {
       CapitalTrack capitalRecprd=capitalTrackRepository.findById(capitalTrackId).orElseThrow(CapitalRecordNotFoundException::new);
       capitalTrackRepository.delete(capitalRecprd);
       return  capitalTrackMapper.toCapitalTrackDTO(capitalRecprd);
    }
}
