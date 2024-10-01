package com.optifolio.services;

import com.optifolio.dto.*;
import com.optifolio.exceptions.CapitalRecordNotFoundException;
import com.optifolio.exceptions.PortfolioRecordNotFoundException;

import java.util.List;

public interface PortfolioService {

    //    FUNCTION TO GET  EXISTING  PORTFOLIO RECORD BY ID
    PortfolioDTO getPortfolioRecordById(String capitalTrackId) throws PortfolioRecordNotFoundException;

    //    FUNCTION TO GET  ALL  PORTFOLIO RECORDS
    List<PortfolioDTO> getAllPortfolioRecords();

    //    FUNCTION TO ADD NEW PORTFOLIO RECORD
    PortfolioDTO addPortfolioRecord(PortfolioCreateDTO portfolioCreateDTO);

    //    FUNCTION TO UPDATE EXISTING  PORTFOLIO RECORD
    PortfolioDTO updatePortfolioRecord(PortfolioUpdateCTO portfolioUpdateCTO) throws CapitalRecordNotFoundException;

    //    FUNCTION TO DELETE EXISTING  PORTFOLIO RECORD
    PortfolioDTO deletePortfolioRecord(String holdingId) throws CapitalRecordNotFoundException;
}
