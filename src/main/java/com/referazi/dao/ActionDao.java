package com.referazi.dao;

import com.referazi.models.Action;
import com.referazi.models.Interest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActionDao {

    List<Action> getActions();
    Action getActionById(Integer actionId);

}
