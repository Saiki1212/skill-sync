package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.HttpServletRequestDetails;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import com.stpl.tech.ss_service.ss_service.service.SkillsDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(SSResourceUtil.BASE_PATH + SSResourceUtil.SKILL_DETAIL_ROOT_CONTEXT)
public class SkillDetailController {

    @Autowired
    private HttpServletRequestDetails httpServletRequestDetails;

    @Autowired
    private SkillsDetailService skillsDetailService;

    @PostMapping("create-category")
    public SkillCategoryDto createSkillCategory(@RequestBody SkillCategoryDto categoryDto) {
        return skillsDetailService.createSkillCategory(categoryDto);
    }

    @GetMapping("get-category")
    public List<SkillCategoryDto> getSkillCategoryById(@RequestParam(required = false, value = "categoryId") Integer categoryId) {
        return skillsDetailService.getSkillCategory(categoryId);
    }

    @PostMapping("create-skill")
    public SkillDetailDto createSkillDetail(@RequestBody SkillDetailDto skillDetailDto) {
        return skillsDetailService.createSkillDetail(skillDetailDto);
    }

    @GetMapping("get-skill")
    public List<SkillDetailDto> getSkillDetailsById(@RequestParam(required = false, value = "skillId")  Integer skillId) {
        return skillsDetailService.getSkillDetails(skillId);
    }

    @PostMapping("user/add/skill")
    public UserSkillMappingDto addSkillToAUser(@RequestBody UserSkillMappingDto mappingDto, HttpServletRequest request) {
        return skillsDetailService.addSkillToAUser(mappingDto, httpServletRequestDetails.getUserIdFromToken(request));
    }

    @GetMapping("user/get-skills")
    public List<UserSkillMappingDto> getAllUsersSkills(@RequestParam(required = false, value = "skillId") Integer skillId, HttpServletRequest request) {
        return skillsDetailService.getAllUsersSkills(skillId, httpServletRequestDetails.getUserIdFromToken(request));
    }

    @PostMapping("user/edit-skill")
    public UserSkillMappingDto editUserSkill(@RequestBody UserSkillMappingDto mappingDto){
        return skillsDetailService.editUserSkill(mappingDto);
    }

    @PostMapping("user/unmap-skill/{skillId}")
    public boolean removeUserSkillMapping(@PathVariable(value = "skillId") Integer skillId, HttpServletRequest request) {
        return skillsDetailService.removeUserSkill(skillId, httpServletRequestDetails.getUserIdFromToken(request));
    }

}
