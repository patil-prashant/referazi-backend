package apis.mapper;

import apis.models.Skill;
import apis.models.UserProfile;

import java.util.List;

public interface SkillMapper {
    List<Skill> getUserSkills(Integer userId);
    void addUserSkill(Skill skill);
}
