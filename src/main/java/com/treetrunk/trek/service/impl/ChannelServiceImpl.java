package com.treetrunk.trek.service.impl;

import com.treetrunk.trek.model.Channel;
import com.treetrunk.trek.model.Module;
import com.treetrunk.trek.repository.ChannelRepository;
import com.treetrunk.trek.service.ChannelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Channel create(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Channel update(Channel updateChannel, Channel channel) {
        BeanUtils.copyProperties(channel, updateChannel, "id", "created");
        return channelRepository.save(updateChannel);
    }

    @Override
    public void delete(Long id) {
        channelRepository.deleteById(id);
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel findById(Long id) {
        return channelRepository.findById(id).orElse(null);
    }

    public List<Channel> findByCrossId(Long crossId) {
        return channelRepository.findByCrossId(crossId);
    }


}
