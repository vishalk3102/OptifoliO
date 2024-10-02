package com.optifolio.mapper;


import com.optifolio.dto.*;
import com.optifolio.models.Portfolio;
import com.optifolio.models.Position;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    //Mapping from  position entity to positionDto
    @Mapping(target = "totalBuyValue", ignore = true)
    @Mapping(target = "currentValue", ignore = true)
    @Mapping(target = "totalSellValue", ignore = true)
    @Mapping(target = "profitLoss", ignore = true)
    @Mapping(target = "netProfitLoss", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PositionDTO toPositionDTO(Position position);

    //Mapping from  positionDto  to position entity
    Position toPositionEntity(PositionDTO positionDTO);

    //Mapping from positionCreateDTO to position entity
    @Mapping(target = "optionId", ignore = true)
    @Mapping(target = "averageSellPrice", ignore = true)
    @Mapping(target = "totalBuyValue", ignore = true)
    @Mapping(target = "currentValue", ignore = true)
    @Mapping(target = "totalSellValue", ignore = true)
    @Mapping(target = "sellDate", ignore = true)
    @Mapping(target = "profitLoss", ignore = true)
    @Mapping(target = "taxCharges", ignore = true)
    @Mapping(target = "netProfitLoss", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Position toPositionEntity(PositionCreateDTO positionCreateDTO);

    //Update existing position  entity  from positionUpdateDTO
    @Mapping(target = "totalBuyValue", ignore = true)
    @Mapping(target = "currentValue", ignore = true)
    @Mapping(target = "totalSellValue", ignore = true)
    @Mapping(target = "profitLoss", ignore = true)
    @Mapping(target = "netProfitLoss", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updatePositionEntityFromDTO(PositionUpdateDTO positionUpdateDTO, @MappingTarget Position position);

    //Mapping from List of position entities to list of positionDTO
    List<PositionDTO> toPositionDTOS(List<Position> records);


    @AfterMapping
    default void calculateDerivedFields(@MappingTarget Position position) {
        // Calculate totalBuyValue
        if (position.getQuantity() != 0 && position.getAverageBuyPrice() != null) {
            position.setTotalBuyValue(position.getAverageBuyPrice().multiply(BigDecimal.valueOf(position.getQuantity())));
        }

        // Calculate totalSellValue
        if (position.getQuantity() != 0 && position.getAverageSellPrice() != null) {
            position.setTotalSellValue(position.getAverageSellPrice().multiply(BigDecimal.valueOf(position.getQuantity())));
        }

        // Calculate profitLoss
        if (position.getTotalSellValue() != null && position.getTotalBuyValue() != null) {
            position.setProfitLoss(position.getTotalSellValue().subtract(position.getTotalBuyValue()));
        }

        // Calculate netProfitLoss
        if (position.getProfitLoss() != null && position.getTaxCharges() != null) {
            position.setNetProfitLoss(position.getProfitLoss().subtract(position.getTaxCharges()));
        }

        // Set createdAt and updatedAt
        if (position.getCreatedAt() == null) {
            position.setCreatedAt(LocalDateTime.now());
        }
        position.setUpdatedAt(LocalDateTime.now());
    }
}
