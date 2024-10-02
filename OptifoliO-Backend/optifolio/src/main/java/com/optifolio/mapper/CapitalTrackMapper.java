package com.optifolio.mapper;


import com.optifolio.dto.*;
import com.optifolio.models.CapitalTrack;
import com.optifolio.models.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CapitalTrackMapper {

    //Mapping from  capitalTrack entity to capitalTrackDTO
    CapitalTrackDTO toCapitalTrackDTO(CapitalTrack capitalTrack);

    //Mapping from  capitalTrackDTO  to capitalTrack entity
    @Mapping(target = "capitalTrackId", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "amountChange", constant = "0")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    CapitalTrack toCapitalTrackEntity(CapitalTrackDTO capitalTrackDTO);

    //Mapping from UserCreateDTO to capitalTrack entity
    CapitalTrack toCapitalTrackEntity(CapitalTrackCreateDTO capitalTrackCreateDTO);

    //Update existing capitalTrack  entity  from capitalTrackUpdateDTO
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "amountChange", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateCapitalTrackEntityFromDTO(CapitalTrackUpdateDTO capitalTrackUpdateDTO, @MappingTarget CapitalTrack capitalTrack);

    //Mapping from List of capitalTrack entities to list of capitalTrackDTO
    List<CapitalTrackDTO> toCapitalTrackDTOS(List<CapitalTrack> records);



    @AfterMapping
    default void calculateTotalAmountForCreate(@MappingTarget CapitalTrack capitalTrack) {
        BigDecimal investedAmount = capitalTrack.getInvestedAmount();
        BigDecimal freeAmount = capitalTrack.getFreeAmount();
        if (investedAmount != null && freeAmount != null) {
            capitalTrack.setTotalAmount(investedAmount.add(freeAmount));
        } else {
            capitalTrack.setTotalAmount(BigDecimal.ZERO);
        }

        // Ensure amountChange is set to ZERO if it's null
        if (capitalTrack.getAmountChange() == null) {
            capitalTrack.setAmountChange(BigDecimal.ZERO);
        }
    }

    @AfterMapping
    default void calculateTotalAmountAndChangeForUpdate(@MappingTarget CapitalTrack capitalTrack, CapitalTrackUpdateDTO updateDTO) {
        BigDecimal oldTotalAmount = capitalTrack.getTotalAmount() != null ? capitalTrack.getTotalAmount() : BigDecimal.ZERO;
        BigDecimal newTotalAmount = updateDTO.getInvestedAmount().add(updateDTO.getFreeAmount());

        capitalTrack.setTotalAmount(newTotalAmount);
        capitalTrack.setAmountChange(newTotalAmount.subtract(oldTotalAmount));
    }

}
