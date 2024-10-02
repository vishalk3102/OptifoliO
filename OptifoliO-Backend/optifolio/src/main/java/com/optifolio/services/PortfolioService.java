package com.optifolio.services;

import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PortfolioRecordAlreadyExistException;
import com.optifolio.exceptions.PortfolioRecordNotFoundException;

import java.util.List;

public interface PortfolioService {

    //    FUNCTION TO GET  EXISTING  PORTFOLIO RECORD BY ID
    PortfolioDTO getPortfolioRecordById(String tradingSymbol) throws PortfolioRecordNotFoundException;

    //    FUNCTION TO GET  ALL  PORTFOLIO RECORDS
    List<PortfolioDTO> getAllPortfolioRecords();

    //    FUNCTION TO ADD NEW PORTFOLIO RECORD
    PortfolioDTO addPortfolioRecord(PortfolioCreateDTO portfolioCreateDTO) throws PortfolioRecordAlreadyExistException;

    //    FUNCTION TO UPDATE EXISTING  PORTFOLIO RECORD
    PortfolioDTO updatePortfolioRecord(PortfolioUpdateDTO portfolioUpdateDTO)throws PortfolioRecordNotFoundException;

    //    FUNCTION TO DELETE EXISTING  PORTFOLIO RECORD
    PortfolioDTO deletePortfolioRecord(String tradingSymbol) throws PortfolioRecordNotFoundException;
}
