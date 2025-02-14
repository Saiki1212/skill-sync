package com.stpl.tech.ss_service.ss_service.resource.controller;

import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import com.stpl.tech.ss_service.ss_service.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SSResourceUtil.BASE_PATH + SSResourceUtil.SKILL_DETAIL_ROOT_CONTEXT)
public class SkillDetailController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping("create-category")
    public SkillCategoryDto createSkillCategory(@RequestBody SkillCategoryDto skillCategoryDto) {
        return skillsService.createSkillCategory(skillCategoryDto);
    }

}
