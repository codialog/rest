package com.treetrunk.trek.service.impl;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.repository.CrossRepository;
import com.treetrunk.trek.service.CrossService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossServiceImpl implements CrossService {
    private final CrossRepository crossRepository;

    @Autowired
    public CrossServiceImpl(CrossRepository crossRepository) {
        this.crossRepository = crossRepository;
    }

    @Override
    public Cross create(Cross cross) {
        return crossRepository.save(cross);
    }

    @Override
    public Cross update(Cross updateCross, Cross cross) {
        BeanUtils.copyProperties(cross, updateCross, "id", "created");
        return crossRepository.save(updateCross);
    }

    @Override
    public void delete(Long id) {
        crossRepository.deleteById(id);
    }

    @Override
    public List<Cross> getAll() {
        return crossRepository.findAll();
    }

    @Override
    public Cross findById(Long id) {
        return crossRepository.findById(id).orElse(null);
    }


}
