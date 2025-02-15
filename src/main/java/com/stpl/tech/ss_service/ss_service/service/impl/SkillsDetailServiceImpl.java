package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.config.annotations.MasterTransactional;
import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.CommonDaoResource;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillDetailData;
import com.stpl.tech.ss_service.ss_service.service.SkillsDetailService;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.SkillDetailServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@MasterTransactional
public class SkillsDetailServiceImpl implements SkillsDetailService {

    @Autowired
    private CommonDaoResource commonDao;

    @Autowired
    private SkillDetailServiceMapper skillDetailServiceMapper;

    @Override
    public SkillCategoryDto createSkillCategory(SkillCategoryDto categoryDto) {
        SkillCategoryData categoryData = skillDetailServiceMapper.convertSkillCategoryDtoToData(categoryDto);
        categoryData = commonDao.add(categoryData);
        categoryDto.setSkillCategoryId(categoryData.getSkillCategoryId());
        categoryDto.setCategoryStatus(categoryData.getCategoryStatus());
        return categoryDto;
    }

    @Override
    public SkillCategoryDto getSkillCategoryById(Integer categoryId) {
        SkillCategoryData categoryData = commonDao.find(SkillCategoryData.class, categoryId);
        return skillDetailServiceMapper.convertSkillCategoryDataToDto(categoryData, true);
    }

    @Override
    public SkillDetailDto createSkillDetail(SkillDetailDto skillDetailDto) {
        SkillCategoryData categoryData = commonDao.find(SkillCategoryData.class, skillDetailDto.getSkillsCategory().getSkillCategoryId());
        SkillDetailData detailData = skillDetailServiceMapper.convertSkillDetailDtoToData(skillDetailDto, categoryData);
        commonDao.add(detailData);

        return skillDetailDto;
    }

    @Override
    public SkillDetailDto getSkillDetailsById(Integer skillId) {
        SkillDetailData detailData = commonDao.find(SkillDetailData.class, skillId);
        return skillDetailServiceMapper.convertSkillDetailDataToDto(detailData, true);
    }

}
