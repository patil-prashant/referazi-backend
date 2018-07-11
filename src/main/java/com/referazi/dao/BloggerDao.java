package com.referazi.dao;

import com.referazi.models.Blogger;
import com.referazi.models.User;

import java.util.List;

public interface BloggerDao
{
    void registerBlogger(Blogger blogger);
    Blogger findBloggerByUserId(Integer id);
    List<Blogger> findAllBloggers();
}