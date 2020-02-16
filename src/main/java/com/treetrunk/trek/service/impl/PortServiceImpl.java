package com.treetrunk.trek.service.impl;

import com.treetrunk.trek.model.port.Port;
import com.treetrunk.trek.repository.PortRepository;
import com.treetrunk.trek.service.PortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortServiceImpl implements PortService {
    private final PortRepository portRepository;

    @Autowired
    public PortServiceImpl(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public Port create(Port port) {
        return portRepository.save(port);
    }

    @Override
    public Port update(Port updatePort, Port port) {
        BeanUtils.copyProperties(port, updatePort, "id");
        return portRepository.save(updatePort);
    }

    @Override
    public void delete(Long id) {
        portRepository.deleteById(id);
    }

    @Override
    public List<Port> getAll() {
        List<Port> portList = portRepository.findAll();
        return portList;
    }

    @Override
    public Port findById(Long id) {
        return portRepository.findById(id).orElse(null);
    }


}
