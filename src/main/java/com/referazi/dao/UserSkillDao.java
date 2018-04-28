package com.referazi.dao;

import com.referazi.models.Skill;
import com.referazi.models.UserSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSkillDao {
    List<String> getUserSkills(@Param("userId") Integer userId, @Param("profileType") String profileType);

    void addUserSkill(UserSkill userSkill);
}
