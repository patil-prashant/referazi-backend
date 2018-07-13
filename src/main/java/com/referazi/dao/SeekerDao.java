package com.referazi.dao;

import com.referazi.models.Seeker;

import java.util.List;

public interface SeekerDao
{
    void registerSeeker(Seeker seeker);
    Seeker findSeekerByUserId(Integer userId);
    List<Seeker> findAllSeekers();
    void updateSeeker(Seeker seeker);
    void deleteSeeker(Integer userId);
}