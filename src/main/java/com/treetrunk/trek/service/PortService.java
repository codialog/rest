package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Port;
import com.treetrunk.trek.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortService extends AbstractService<Port, PortRepository> {

    @Autowired
    public PortService(PortRepository repository) {
        super(repository);
    }

//    @Override
//    public Port create(Port port) {
//        if (!portNumberExistsInModule(port)) {
//            return super.create(port);
//        } else return null;
//    }
//
//    @Override
//    public Port update(Port port, Port customPort) {
//        if (customPort.getNumber() != port.getNumber() && !portNumberExistsInModule(customPort)) {
//            return super.update(port, customPort);
//        }
//        return null;
//    }
//
//    public Server findByNumber(int number) {
//        return repository.findByNumber(number);
//    }
//
//    public Boolean portNumberExistsInModule(Port port){
//        for (Port currentPort : port.getModule().getPorts()) {
//            if (!port.equals(currentPort) && currentPort.getNumber() == port.getNumber()) {
//                return true;
//            }
//        } return false;
//    }
}
