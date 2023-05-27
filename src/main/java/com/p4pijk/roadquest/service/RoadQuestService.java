package com.p4pijk.roadquest.service;

import java.util.List;

public interface RoadQuestService<T> {
    List<T> findAll();

    T findById(int id);

    T save(T newObj);

    void deleteById(int id);
}
