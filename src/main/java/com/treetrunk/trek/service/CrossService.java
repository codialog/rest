package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Cross;

import java.util.List;

public interface CrossService {

    Cross create(Cross cross);

    Cross update(Cross updateCross, Cross cross);

    void delete(Long id);

    List<Cross> getAll();

    Cross findById(Long id);
}
