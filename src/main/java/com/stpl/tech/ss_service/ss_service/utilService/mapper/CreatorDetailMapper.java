package com.stpl.tech.ss_service.ss_service.utilService.mapper;

import com.stpl.tech.ss_service.ss_service.modal.dto.creator.CreatorDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.creator.CreatorDetailData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreatorDetailMapper {

    CreatorDetailMapper INSTANCE = Mappers.getMapper(CreatorDetailMapper.class);

    @Mapping(source = "creatorId", target = "id")
    @Mapping(source = "biography", target = "bio")
    @Mapping(target = "userBaseDetailData", ignore = true)
    CreatorDetailDto creatorDetailDataToDto(CreatorDetailData data);

    @Mapping(source = "id", target = "creatorId")
    @Mapping(source = "bio", target = "biography")
    @Mapping(target = "userBaseDetailData", ignore = true)
    CreatorDetailData creatorDetailDtoToData(CreatorDetailDto dto);

}
