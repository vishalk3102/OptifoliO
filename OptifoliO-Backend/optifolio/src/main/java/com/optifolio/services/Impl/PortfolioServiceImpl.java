package com.optifolio.services.Impl;

import com.optifolio.dto.*;
import com.optifolio.exceptions.PortfolioRecordAlreadyExistException;
import com.optifolio.exceptions.PortfolioRecordNotFoundException;
import com.optifolio.mapper.PortfolioMapper;
import com.optifolio.models.Portfolio;
import com.optifolio.repositories.PortfolioRepository;
import com.optifolio.services.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Autowired
    private PortfolioRepository portfolioRepository;


    //    FUNCTION TO GET  EXISTING  PORTFOLIO RECORD BY ID
    @Override
    public PortfolioDTO getPortfolioRecordById(String tradingSymbol) throws PortfolioRecordNotFoundException {
        Portfolio record=portfolioRepository.findByTradingSymbol(tradingSymbol).orElseThrow(PortfolioRecordNotFoundException::new);
        return  portfolioMapper.toPortfolioDTO(record);
    }


    //    FUNCTION TO GET  ALL  PORTFOLIO RECORDS
    @Override
    public List<PortfolioDTO> getAllPortfolioRecords() {
        List<Portfolio> records=portfolioRepository.findAll();
        if(records.isEmpty())
        {
            return List.of();
        }
        return portfolioMapper.toPortfolioDTOS(records);
    }

    //    FUNCTION TO ADD NEW PORTFOLIO RECORD
    @Override
    public PortfolioDTO addPortfolioRecord(PortfolioCreateDTO portfolioCreateDTO) throws PortfolioRecordAlreadyExistException {
        Portfolio portfolioRecord=portfolioMapper.toPortfolioEntity(portfolioCreateDTO);
        Optional<Portfolio> existingPortfolioRecord= portfolioRepository.findByTradingSymbol(portfolioCreateDTO.getTradingSymbol());
        if(existingPortfolioRecord.isPresent())
        {
            throw  new PortfolioRecordAlreadyExistException();
        }
        portfolioMapper.calculateDerivedFields(portfolioRecord);
        Portfolio savedPortfolioRecord= portfolioRepository.save(portfolioRecord);
        return portfolioMapper.toPortfolioDTO(savedPortfolioRecord);
    }


    //    FUNCTION TO UPDATE EXISTING  PORTFOLIO RECORD
    @Override
    public PortfolioDTO updatePortfolioRecord(PortfolioUpdateDTO portfolioUpdateDTO) throws PortfolioRecordNotFoundException {
        Portfolio existingPortfolioRecord=portfolioRepository.findByTradingSymbol(portfolioUpdateDTO.getTradingSymbol()).orElseThrow(PortfolioRecordNotFoundException::new);
        portfolioMapper.updatePortfolioEntityFromDTO(portfolioUpdateDTO,existingPortfolioRecord);
        portfolioMapper.calculateDerivedFields(existingPortfolioRecord);
        Portfolio updatedRecord=portfolioRepository.save(existingPortfolioRecord);
        return  portfolioMapper.toPortfolioDTO(updatedRecord);
    }

    //    FUNCTION TO DELETE EXISTING  PORTFOLIO RECORD
    @Override
    public PortfolioDTO deletePortfolioRecord(String tradingSymbol) throws PortfolioRecordNotFoundException {
        Portfolio portfolioRecord=portfolioRepository.findByTradingSymbol(tradingSymbol).orElseThrow(PortfolioRecordNotFoundException::new);
        portfolioRepository.delete(portfolioRecord);
        return  portfolioMapper.toPortfolioDTO(portfolioRecord);
    }
}
