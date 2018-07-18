package com.referazi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSkill {
    private Integer userId;
    private String profileType;
    private String skillName;

    public UserSkill(Integer userId, String profileType, String skillName) {
        this.userId = userId;
        this.profileType = profileType;
        this.skillName = skillName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "userId=" + userId +
                ", profileType='" + profileType + '\'' +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
