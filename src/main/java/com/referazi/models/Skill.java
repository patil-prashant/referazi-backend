package com.referazi.models;

public class Skill {
    private String profileType;
    private String skillName;

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
        return "Skill{" +
                "profileType='" + profileType + '\'' +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
