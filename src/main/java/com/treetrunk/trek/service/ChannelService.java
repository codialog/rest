package com.treetrunk.trek.service;

import com.treetrunk.trek.model.Channel;

import java.util.List;

public interface ChannelService {

    Channel create(Channel channel);

    Channel update(Channel updateChannel, Channel channel);

    void delete(Long id);

    List<Channel> getAll();

    Channel findById(Long id);
}
