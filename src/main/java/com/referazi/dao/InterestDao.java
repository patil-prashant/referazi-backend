package com.referazi.dao;

import com.referazi.models.Interest;
import com.referazi.models.Skill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterestDao {

    void insertInterest(String name);

    List<Interest> getInterests();

    void insertBloggerInterest(@Param("userId") Integer userId, @Param("interestId")Integer interestId);

    List<Interest> getBloggerInterests(@Param("userId") Integer userId);

    void deleteBloggerInterests(@Param("userId") Integer userId);
}
