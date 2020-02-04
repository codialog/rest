package com.treetrunk.trek.controller.port;

import com.treetrunk.trek.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("port")
public class PortController {
    private int counter = 3;

    private List<Map<String, String>> ports = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "0");
            put("status", String.valueOf(Status.TRANSIT));
        }});
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("status", String.valueOf(Status.NOT_ACTIVE));
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("status", String.valueOf(Status.NOT_ACTIVE));
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return ports;
    }

    @GetMapping("{id}")
    private Map<String, String> getPort(@PathVariable String id) {
        return getById(id);
    }

    private Map<String, String> getById(@PathVariable String id) {
        return ports.stream()
                .filter(port -> port.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> port) {
        port.put("id", String.valueOf(counter++));
        ports.add(port);
        return port;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> port) {
        Map<String, String> updatePort = getById(id);
        updatePort.putAll(port);
        updatePort.put("id", id);
        return updatePort;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> port = getById(id);
        ports.remove(port);
        counter--;

    }

}
