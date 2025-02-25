package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.HttpServletRequestDetails;
import com.stpl.tech.ss_service.ss_service.modal.dto.creator.AvailableTimeSlotsDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.creator.CreatorDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.creator.TypeOfCommunicationDto;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import com.stpl.tech.ss_service.ss_service.service.CreatorDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SSResourceUtil.BASE_PATH + SSResourceUtil.CREATOR_DETAIL_ROOT_CONTEXT)
public class CreatorDetailController {

    @Autowired
    private HttpServletRequestDetails servletRequestDetails;

    @Autowired
    private CreatorDetailService creatorDetailService;

    @PostMapping("creator")
    public boolean createCreatorAccount(@RequestBody CreatorDetailDto creatorDto, HttpServletRequest request) {
        return creatorDetailService.createCreatorAccount(creatorDto, servletRequestDetails.getUserIdFromToken(request));
    }

    @PostMapping("edit-creator")
    public boolean editCreator(@RequestBody CreatorDetailDto creatorDto) {
        return creatorDetailService.editCreator(creatorDto);
    }

    @PostMapping("add/comm")
    public boolean addTypeOfCommunication(@RequestBody TypeOfCommunicationDto communicationDto) {
        return creatorDetailService.addTypeOfCommunication(communicationDto);
    }

    @PostMapping("edit/comm")
    public boolean editTypeOfCommunication(@RequestBody TypeOfCommunicationDto communicationDto) {
        return creatorDetailService.editTypeOfCommunication(communicationDto);
    }

    @PostMapping("add/time-slot")
    public boolean addAvailabilityTimeSlot(@RequestBody AvailableTimeSlotsDto availableTimeSlotsDto) {
        return creatorDetailService.addAvailabilityTimeSlot(availableTimeSlotsDto);
    }

    @PostMapping("edit/time-slot")
    public boolean editAvailabilityTimeSlot(@RequestBody AvailableTimeSlotsDto availableTimeSlotsDto) {
        return creatorDetailService.editAvailabilityTimeSlot(availableTimeSlotsDto);
    }

}
