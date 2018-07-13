package com.referazi.dao;

import com.referazi.models.Provider;
import com.referazi.models.Seeker;

import java.util.List;

public interface ProviderDao
{
    void registerProvider(Provider provider);
    Provider findProviderByUserId(Integer userId);
    List<Provider> findAllProviders();
    void updateProvider(Provider provider);
    void deleteProvider(Integer userId);
}