package com.stpl.tech.ss_service.ss_service.service.impl;

import com.stpl.tech.ss_service.ss_service.config.annotations.CustomNotNull;
import com.stpl.tech.ss_service.ss_service.config.annotations.MasterTransactional;
import com.stpl.tech.ss_service.ss_service.dbDomain.abstractRepo.CommonDaoResource;
import com.stpl.tech.ss_service.ss_service.dbDomain.dao.SkillDetailDao;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillCategoryDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillDetailDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.SkillEndorsementDto;
import com.stpl.tech.ss_service.ss_service.modal.dto.UserSkillMappingDto;
import com.stpl.tech.ss_service.ss_service.modal.entity.UserBaseDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillCategoryData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillDetailData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.SkillEndorsementData;
import com.stpl.tech.ss_service.ss_service.modal.entity.skillsEntity.UserSkillMappingData;
import com.stpl.tech.ss_service.ss_service.modal.enums.SkillStatus;
import com.stpl.tech.ss_service.ss_service.modal.enums.UserSkillStatus;
import com.stpl.tech.ss_service.ss_service.service.SkillsDetailService;
import com.stpl.tech.ss_service.ss_service.utilService.mapper.SkillDetailServiceMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@MasterTransactional
public class SkillsDetailServiceImpl implements SkillsDetailService {

    @Autowired
    private CommonDaoResource commonDao;

    @Autowired
    private SkillDetailServiceMapper skillDetailServiceMapper;

    @Autowired
    private SkillDetailDao skillDetailDao;


    @Override
    public SkillCategoryDto createSkillCategory(SkillCategoryDto categoryDto) {
        SkillCategoryData categoryData = skillDetailServiceMapper.convertSkillCategoryDtoToData(categoryDto);
        categoryData = commonDao.add(categoryData);
        categoryDto.setSkillCategoryId(categoryData.getSkillCategoryId());
        categoryDto.setCategoryStatus(categoryData.getCategoryStatus());
        return categoryDto;
    }

    @Override
    public List<SkillCategoryDto> getSkillCategory(Integer categoryId) {
        List<SkillCategoryDto> categoryDtoList = new ArrayList<>();
        List<SkillCategoryData> skillCategoryDataList = new ArrayList<>();
        if(categoryId != null) {
            skillCategoryDataList.add(commonDao.find(SkillCategoryData.class, categoryId));
        } else {
            skillCategoryDataList.addAll(commonDao.findAll(SkillCategoryData.class));
        }
        for(SkillCategoryData data : skillCategoryDataList) {
            categoryDtoList.add(skillDetailServiceMapper.convertSkillCategoryDataToDto(data, true));
        }
        return categoryDtoList;
    }

    @Override
    public SkillDetailDto createSkillDetail(SkillDetailDto skillDetailDto) {
        SkillCategoryData categoryData = commonDao.find(SkillCategoryData.class, skillDetailDto.getSkillsCategory().getSkillCategoryId());
        SkillDetailData detailData = skillDetailServiceMapper.convertSkillDetailDtoToData(skillDetailDto, categoryData);
        commonDao.add(detailData);

        return skillDetailDto;
    }

    @Override
    public List<SkillDetailDto> getSkillDetails(Integer skillId) {
        List<SkillDetailData> skillDetailData = new ArrayList<>();
        List<SkillDetailDto> detailDtoList = new ArrayList<>();
        if(skillId != null) {
            skillDetailData.add(commonDao.find(SkillDetailData.class, skillId));
        } else {
            skillDetailData.addAll(commonDao.findAll(SkillDetailData.class));
        }

        for(SkillDetailData data : skillDetailData) {
            detailDtoList.add(skillDetailServiceMapper.convertSkillDetailDataToDto(data, true));
        }
        return detailDtoList;
    }

    @Override
    public UserSkillMappingDto addSkillToAUser(UserSkillMappingDto mappingDto, @CustomNotNull("userId") Integer userId) {
        if(mappingDto.getSkillDetailDto() == null || mappingDto.getSkillDetailDto().getSkillId() == null) {
            throw new RuntimeException("skill details not found to add skill to user : " + userId);
        }

        Integer isAlreadySkillAddedToUser = skillDetailDao.isAlreadySkillAddedToUser(userId, mappingDto.getSkillDetailDto().getSkillId(), UserSkillStatus.IN_ACTIVE);

        if(isAlreadySkillAddedToUser != null) {
            UserSkillMappingData skillMappingData = commonDao.find(UserSkillMappingData.class, isAlreadySkillAddedToUser);
            skillMappingData.setUserSkillStatus(UserSkillStatus.ACTIVE);
        }

        UserBaseDetailData userData = commonDao.find(UserBaseDetailData.class, userId);
        SkillDetailData skillDetailData = commonDao.find(SkillDetailData.class, mappingDto.getSkillDetailDto().getSkillId());

        UserSkillMappingData skillMappingData = new UserSkillMappingData();
        skillMappingData.setSkill(skillDetailData);
        skillMappingData.setUser(userData);
        skillMappingData.setUserSkillStatus(UserSkillStatus.ACTIVE);
        skillMappingData.setProficiencyLevel(mappingDto.getProficiencyLevel());
        skillMappingData = commonDao.add(skillMappingData);

        return skillDetailServiceMapper.convertUserSkillMappingDataToDto(skillMappingData);
    }

    @Override
    public List<UserSkillMappingDto> getAllUsersSkills(Integer skillId, Integer userId) {
        List<UserSkillMappingData> mappingData = skillDetailDao.findByUserIdAndSkillIdAndStatus(userId, skillId, List.of(UserSkillStatus.DELETED));

        List<UserSkillMappingDto> mappingDtoList = new ArrayList<>();
        for(UserSkillMappingData data : mappingData) {
            mappingDtoList.add(skillDetailServiceMapper.convertUserSkillMappingDataToDto(data));
        }

        return mappingDtoList;
    }

    @Override
    public UserSkillMappingDto editUserSkill(UserSkillMappingDto mappingDto) {

        if(mappingDto.getUserSkillMappingId() == null) {
            throw new RuntimeException("user skill mapping id not found");
        }
        UserSkillMappingData userSkillMappingData = commonDao.find(UserSkillMappingData.class, mappingDto.getUserSkillMappingId());
        // TODO add null check here
        userSkillMappingData.setProficiencyLevel(mappingDto.getProficiencyLevel());
        userSkillMappingData.setUserSkillStatus(mappingDto.getUserSkillStatus());
        userSkillMappingData = commonDao.add(userSkillMappingData);

        return skillDetailServiceMapper.convertUserSkillMappingDataToDto(userSkillMappingData);
    }

    @Override
    public boolean removeUserSkill(Integer skillId, Integer userId) {
        if(skillId == null || userId == null) {
            throw new RuntimeException("Required field are null");
        }
        return skillDetailDao.removeUserSkill(skillId, userId, UserSkillStatus.DELETED);
    }

    @Override
    public boolean endorseASkill(SkillEndorsementDto endorsementDto, @CustomNotNull("userId") Integer userId) {
        if(endorsementDto.getUserSkillMapping() == null || endorsementDto.getUserSkillMapping().getUserSkillMappingId() == null) {
            throw new RuntimeException("skill details not found to endorse by user : " + userId);
        }
        UserBaseDetailData userData = commonDao.find(UserBaseDetailData.class, userId);
        UserSkillMappingData skillMappingData = commonDao.find(UserSkillMappingData.class, endorsementDto.getUserSkillMapping().getUserSkillMappingId());

        SkillEndorsementData endorsementData = new SkillEndorsementData();
        endorsementData.setEndorsedByUser(userData);
        endorsementData.setUserSkillMapping(skillMappingData);
        endorsementData.setEndorsementComment(endorsementDto.getEndorsementComment());
        endorsementData.setEndorsementStatus(SkillStatus.ACTIVE);
        commonDao.add(endorsementData);

        return true;
    }

    @Override
    public boolean removeAEndorsedSkill(@CustomNotNull("endorseId") Integer endorseId, @CustomNotNull("userId") Integer userId) {
        return skillDetailDao.removeAEndorsedSkill(endorseId, userId);
    }

}
