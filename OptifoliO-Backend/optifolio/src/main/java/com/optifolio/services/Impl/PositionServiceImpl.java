package com.optifolio.services.Impl;

import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PortfolioRecordAlreadyExistException;
import com.optifolio.exceptions.PositionRecordAlreadyExistException;
import com.optifolio.exceptions.PositionRecordNotFoundException;
import com.optifolio.mapper.PositionMapper;
import com.optifolio.models.Portfolio;
import com.optifolio.models.Position;
import com.optifolio.repositories.PositionRepository;
import com.optifolio.services.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private PositionRepository positionRepository;


    //    FUNCTION TO GET  EXISTING  POSITION RECORD BY ID
    @Override
    public PositionDTO getPositionRecordById(String tradingSymbol) throws PositionRecordNotFoundException {
        Position record=positionRepository.findByTradingSymbol(tradingSymbol).orElseThrow(PositionRecordNotFoundException::new);
        return  positionMapper.toPositionDTO(record);
    }


    //    FUNCTION TO GET  ALL  POSITION RECORDS
    @Override
    public List<PositionDTO> getAllPositionRecords() {
        List<Position> records=positionRepository.findAll();
        if(records.isEmpty())
        {
            return List.of();
        }
        return positionMapper.toPositionDTOS(records);
    }

    //    FUNCTION TO ADD NEW POSITION RECORD
    @Override
    public PositionDTO addPositionRecord(PositionCreateDTO positionCreateDTO) throws PositionRecordAlreadyExistException {
        Optional<Position> existingPositionRecord= positionRepository.findByTradingSymbol(positionCreateDTO.getTradingSymbol());
        if(existingPositionRecord.isPresent())
        {
            throw  new PositionRecordAlreadyExistException();
        }
        Position positionRecord=positionMapper.toPositionEntity(positionCreateDTO);
        positionMapper.calculateDerivedFields(positionRecord);
        Position savedPositionRecord= positionRepository.save(positionRecord);
        return positionMapper.toPositionDTO(savedPositionRecord);
    }


    //    FUNCTION TO UPDATE EXISTING  POSITION RECORD
    @Override
    public PositionDTO updatePositionRecord(PositionUpdateDTO positionUpdateDTO) throws PositionRecordAlreadyExistException {
        Position existingPositionRecord=positionRepository.findByTradingSymbol(positionUpdateDTO.getTradingSymbol()).orElseThrow(PositionRecordAlreadyExistException::new);
        positionMapper.updatePositionEntityFromDTO(positionUpdateDTO,existingPositionRecord);
        positionMapper.calculateDerivedFields(existingPositionRecord);
        Position updatedRecord=positionRepository.save(existingPositionRecord);
        return  positionMapper.toPositionDTO(updatedRecord);
    }

    //    FUNCTION TO DELETE EXISTING  POSITION RECORD
    @Override
    public PositionDTO deletePositionRecord(String tradingSymbol) throws PositionRecordNotFoundException {
        Position positionRecord=positionRepository.findByTradingSymbol(tradingSymbol).orElseThrow(PositionRecordNotFoundException::new);
        positionRepository.delete(positionRecord);
        return  positionMapper.toPositionDTO(positionRecord);
    }
}
