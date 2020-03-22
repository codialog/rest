package com.treetrunk.trek.controller;

import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.service.CrossService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cross")
public class CrossController extends AbstractController<Cross, CrossService> {

    public CrossController(CrossService service) {
        super(service);
    }
}
