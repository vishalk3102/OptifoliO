package com.optifolio.mapper;


import com.optifolio.dto.*;
import com.optifolio.models.CapitalTrack;
import com.optifolio.models.Portfolio;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    //Mapping from  portfolio entity to portfolioDto
    @Mapping(target = "totalBuyValue", ignore = true)
    @Mapping(target = "cuurentValue", ignore = true)
    @Mapping(target = "totalSellValue", ignore = true)
    @Mapping(target = "profitLoss", ignore = true)
    @Mapping(target = "netProfitLoss", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PortfolioDTO toPortfolioDTO(Portfolio portfolio);

    //Mapping from  portfolioDto  to portfolio entity
    Portfolio toPortfolioEntity(PortfolioDTO portfolioDTO);

    //Mapping from PortfolioCreateDTO to Portfolio entity
    @Mapping(target = "holdingId", ignore = true)
    @Mapping(target = "averageSellPrice", ignore = true)
    @Mapping(target = "totalBuyValue", ignore = true)
    @Mapping(target = "cuurentValue", ignore = true)
    @Mapping(target = "totalSellValue", ignore = true)
    @Mapping(target = "sellDate", ignore = true)
    @Mapping(target = "profitLoss", ignore = true)
    @Mapping(target = "taxCharges", ignore = true)
    @Mapping(target = "netProfitLoss", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Portfolio toPortfolioEntity(PortfolioCreateDTO portfolioCreateDTO);

    //Update existing portfolio  entity  from portfolioUpdateDTO
    @Mapping(target = "totalBuyValue", ignore = true)
    @Mapping(target = "cuurentValue", ignore = true)
    @Mapping(target = "totalSellValue", ignore = true)
    @Mapping(target = "profitLoss", ignore = true)
    @Mapping(target = "netProfitLoss", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updatePortfolioEntityFromDTO(PortfolioUpdateCTO portfolioUpdateCTO, @MappingTarget Portfolio portfolio);

    //Mapping from List of portfolio entities to list of portfolioDTO
    List<PortfolioDTO> toPortfolioDTOS(List<Portfolio> records);



    @AfterMapping
    default void calculateDerivedFields(@MappingTarget Portfolio portfolio) {
        // Calculate totalBuyValue
        if (portfolio.getQuantity() != 0 && portfolio.getAverageBuyPrice() != null) {
            portfolio.setTotalBuyValue(portfolio.getAverageBuyPrice().multiply(BigDecimal.valueOf(portfolio.getQuantity())));
        }

        // Calculate totalSellValue
        if (portfolio.getQuantity() != 0 && portfolio.getAverageSellPrice() != null) {
            portfolio.setTotalSellValue(portfolio.getAverageSellPrice().multiply(BigDecimal.valueOf(portfolio.getQuantity())));
        }

        // Calculate profitLoss
        if (portfolio.getTotalSellValue() != null && portfolio.getTotalBuyValue() != null) {
            portfolio.setProfitLoss(portfolio.getTotalSellValue().subtract(portfolio.getTotalBuyValue()));
        }

        // Calculate netProfitLoss
        if (portfolio.getProfitLoss() != null && portfolio.getTaxCharges() != null) {
            portfolio.setNetProfitLoss(portfolio.getProfitLoss().subtract(portfolio.getTaxCharges()));
        }

        // Set createdAt and updatedAt
        if (portfolio.getCreatedAt() == null) {
            portfolio.setCreatedAt(LocalDateTime.now());
        }
        portfolio.setUpdatedAt(LocalDateTime.now());
    }
}
