package com.stpl.tech.ss_service.ss_service.utilService.mapper;

import com.stpl.tech.ss_service.ss_service.modal.dto.creator.AvailableTimeSlotsDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.creator.CreatorDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.creator.TypeOfCommunicationDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.AvailableTimeSlotsData;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.CreatorDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.TypeOfCommunicationData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreatorDetailMapper {

    CreatorDetailMapper INSTANCE = Mappers.getMapper(CreatorDetailMapper.class);

    @Mapping(source = "creatorId", target = "id")
    @Mapping(source = "biography", target = "bio")
    @Mapping(target = "typeOfCommunicationDtoSet", ignore = true)
    @Mapping(source = "userBaseDetailData", target = "userBasicDataDto")
    CreatorDetailDto creatorDetailDataToDto(CreatorDetailData data);

    @Mapping(source = "id", target = "creatorId")
    @Mapping(source = "bio", target = "biography")
    @Mapping(target = "typeOfCommunicationDataSet", ignore = true)
    @Mapping(target = "userBaseDetailData", ignore = true)
    CreatorDetailData creatorDetailDtoToData(CreatorDetailDto dto);

    @Mapping(source = "creatorDetailData", target = "creatorDetailDto")
    @Mapping(target = "availableTimeSlotsDtoSet", ignore = true)
    TypeOfCommunicationDto convertDataToDto(TypeOfCommunicationData typeOfCommunicationData);

    @Mapping(target = "creatorDetailData", ignore = true)
    @Mapping(target = "availableTimeSlotsDataSet", ignore = true)
    TypeOfCommunicationData convertDtoToData(TypeOfCommunicationDto typeOfCommunicationDto);

    @Mapping(target = "typeOfCommunicationData", ignore = true)
    AvailableTimeSlotsData convertDtoToData(AvailableTimeSlotsDto availableTimeSlotsDto);

    AvailableTimeSlotsDto convertDataToDto(AvailableTimeSlotsData availableTimeSlotsData);


}
