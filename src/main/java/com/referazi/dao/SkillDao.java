package com.referazi.dao;

import com.referazi.models.Skill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkillDao {

    void insertSkill(String name);

    List<Skill> getSkills();

    void insertProviderSkills(@Param("userId") Integer userId, @Param("skillId")Integer skillId);

    List<Skill> getProviderSkills(@Param("userId") Integer userId);

    void deleteProviderSkills(@Param("userId") Integer userId);

    void insertSeekerSkills(@Param("userId") Integer userId, @Param("skillId")Integer skillId);

    List<Skill> getSeekerSkills(@Param("userId") Integer userId);

    void deleteSeekerSkills(@Param("userId") Integer userId);

}
