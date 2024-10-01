package com.optifolio.services.Impl;

import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PortfolioRecordNotFoundException;
import com.optifolio.mapper.PortfolioMapper;
import com.optifolio.models.Portfolio;
import com.optifolio.repositories.PortfolioRepository;
import com.optifolio.services.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Autowired
    private PortfolioRepository portfolioRepository;


    //    FUNCTION TO GET  EXISTING  PORTFOLIO RECORD BY ID
    @Override
    public PortfolioDTO getPortfolioRecordById(String capitalTrackId) throws PortfolioRecordNotFoundException {
        Portfolio record=portfolioRepository.findById(capitalTrackId).orElseThrow(PortfolioRecordNotFoundException::new);
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
    public PortfolioDTO addPortfolioRecord(PortfolioCreateDTO portfolioCreateDTO) {
        Portfolio portfolioRecord=portfolioMapper.toPortfolioEntity(portfolioCreateDTO);
        Portfolio savedPortfolioRecord= portfolioRepository.save(portfolioRecord);
        return portfolioMapper.toPortfolioDTO(savedPortfolioRecord);
    }


    //    FUNCTION TO UPDATE EXISTING  PORTFOLIO RECORD
    @Override
    public PortfolioDTO updatePortfolioRecord(PortfolioUpdateCTO portfolioUpdateCTO) throws CapitalRecordNotFoundException {
        Portfolio existingPortfolioRecord=portfolioRepository.findById(portfolioUpdateCTO.getHoldingId()).orElseThrow(CapitalRecordNotFoundException::new);
        portfolioMapper.updatePortfolioEntityFromDTO(portfolioUpdateCTO,existingPortfolioRecord);
        Portfolio updatedRecord=portfolioRepository.save(existingPortfolioRecord);
        return  portfolioMapper.toPortfolioDTO(updatedRecord);
    }

    //    FUNCTION TO DELETE EXISTING  PORTFOLIO RECORD
    @Override
    public PortfolioDTO deletePortfolioRecord(String holdingId) throws CapitalRecordNotFoundException {
        Portfolio PortfolioRecprd=portfolioRepository.findById(holdingId).orElseThrow(CapitalRecordNotFoundException::new);
        portfolioRepository.delete(PortfolioRecprd);
        return  portfolioMapper.toPortfolioDTO(PortfolioRecprd);
    }
}
