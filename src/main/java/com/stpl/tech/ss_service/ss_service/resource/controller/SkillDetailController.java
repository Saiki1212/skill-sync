package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.HttpServletRequestDetails;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import com.stpl.tech.ss_service.ss_service.service.SkillsDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public SkillCategoryDto getSkillCategoryById(@RequestParam Integer categoryId) {
        return skillsDetailService.getSkillCategoryById(categoryId);
    }

    @PostMapping("create-skill")
    public SkillDetailDto createSkillDetail(@RequestBody SkillDetailDto skillDetailDto) {
        return skillsDetailService.createSkillDetail(skillDetailDto);
    }

    @GetMapping("get-skill")
    public SkillDetailDto getSkillDetailsById(@RequestParam Integer skillId) {
        return skillsDetailService.getSkillDetailsById(skillId);
    }

}
