package com.treetrunk.trek.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.treetrunk.trek.model.Cross;
import com.treetrunk.trek.model.Views;
import com.treetrunk.trek.service.CrossService;
import com.treetrunk.trek.validator.CrossValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/cross")
public class CrossController extends AbstractController<Cross, CrossService> {

    private final ApplicationEventPublisher eventPublisher;
    private final CrossValidator crossValidator;

    @Autowired
    public CrossController(CrossService service,
                           ApplicationEventPublisher eventPublisher,
                           CrossValidator crossValidator) {
        super(service);
        this.eventPublisher = eventPublisher;
        this.crossValidator = crossValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(crossValidator);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<List<Cross>> getAll() {
        return super.getAll();
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Cross> findById(@PathVariable(name = "id") Long id) {
        return super.findById(id);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Cross> create(@RequestBody @Valid Cross cross) {
        return super.create(cross);
    }

    @JsonView(Views.Cross.class)
    @Override
    public ResponseEntity<Cross> update(@PathVariable(name = "id") Long id, @RequestBody Cross customCross) {
        return super.update(id, customCross);
    }
}
