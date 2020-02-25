package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Channel;
import com.treetrunk.trek.service.impl.ChannelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/channel")
public class ChannelController {

    private final ChannelServiceImpl channelService;

    public ChannelController(ChannelServiceImpl channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public List<Channel> getAll() {
        return channelService.getAll();
    }

    @GetMapping("{id}")
    private ResponseEntity<Channel> findById(@PathVariable(name = "id") Long id) {
        Channel channel = channelService.findById(id);
        if (channel == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }

    @PostMapping
    public Channel create(@RequestBody Channel channel) {
        channel.setCreated(new Date());
        return channelService.create(channel);
    }

    @PutMapping("{id}")
    public Channel update(@PathVariable(name = "id") Long id,
                          @RequestBody Channel channel) {
        channel.setUpdated(new Date());
        Channel updateChannel = channelService.findById(id);
        return channelService.update(updateChannel, channel);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        channelService.delete(id);
    }

}
