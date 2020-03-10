package com.treetrunk.trek.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Connection {

    private Cross cross;
    private List <Module> modules;
    private List <Channel> channels;

    public Cross getCross() {
        return cross;
    }

    public void setCross(Cross cross) {
        this.cross = cross;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public Map<Long, List<Port>> getPorts() {
        return ports;
    }

    public void setPorts(Map<Long, List<Port>> ports) {
        this.ports = ports;
    }

    private Map<Long, List<Port>> ports;
}
