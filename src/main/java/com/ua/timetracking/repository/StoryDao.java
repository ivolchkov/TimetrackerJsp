package com.ua.timetracking.repository;

import com.ua.timetracking.entity.StoryEntity;

import java.util.List;

public interface StoryDao extends CrudRepository<Integer, StoryEntity> {

    List<StoryEntity> findByUser(Integer id, Integer offset, Integer amount);

    List<StoryEntity> findWithoutUser(Integer offset, Integer amount);

    Integer findAmountOfRowsWithoutUser();

    Integer findAmountOfRowsByUser(Integer id);

    void updateUserId(StoryEntity story);
}
