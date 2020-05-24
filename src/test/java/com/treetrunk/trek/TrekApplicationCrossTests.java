package com.treetrunk.trek;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.treetrunk.trek.model.*;
import com.treetrunk.trek.service.CrossService;
import com.treetrunk.trek.service.ServerService;
import com.treetrunk.trek.validator.MessageService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TrekApplicationCrossTests {

    @Autowired
    private MockMvc mockMvc;
    private String testUrl = "/api";
    private String crossApi = "/cross";

    private final CrossService crossService;
    private final ServerService serverService;
    private final MessageService messageService;

    @Autowired
    TrekApplicationCrossTests(CrossService crossService, ServerService serverService, MessageService messageService) {
        this.crossService = crossService;
        this.serverService = serverService;
        this.messageService = messageService;
    }

    String testName = "test_name";
    String serverAddress = "test_address";

    public Cross buildTestCross(Server server, Integer amountModules, Integer amountPorts) {
        Set<Module> modules = new HashSet<>();
        for (int i = 1; i <= amountModules; i++) {
            Set<Port> ports = new HashSet<>();
            for (int j = 1; j <= amountPorts; j++) {
                ports.add(new Port(j, Status.NOT_ACTIVE, null, null, null, null, null, null));
            }
            modules.add(new Module(i, amountPorts, ports, null));
        }
        Cross cross = new Cross(testName, amountModules, modules, server);
        return cross;
    }

    @Test
    void createCross() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            Integer amountModules = 1;
            Integer amountPorts = 1;
            String jsonObjectCross = objectMapper
                    .writerWithView(Views.Cross.class)
                    .writeValueAsString(buildTestCross(server, amountModules, amountPorts));

            MvcResult mvcResult = this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(String.valueOf(jsonObjectCross))
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();

            String responseAsString = mvcResult.getResponse().getContentAsString();
            Cross crossResponse = objectMapper.readValue(responseAsString, Cross.class);
            assert crossResponse.getName().equals(testName) : "Actual cross name: " + crossResponse.getName() + ", expected: " + testName;
            assert crossResponse.getModules().size() == amountModules : "Actual amount modules: " + crossResponse.getModules().size() + ",expected: " + amountModules;
            assert crossResponse.getServer().getName().equals(testName) : "Actual server name: " + crossResponse.getServer().getName() + ", expected: " + testName;
            assert crossResponse.getServer().getAddress().equals(serverAddress) : "Actual server address: " + crossResponse.getServer().getAddress() + "expected: " + serverAddress;
            for (Module module : crossResponse.getModules()) {
                assert module.getPorts().size() == amountPorts : "Actual amount ports: " + module.getPorts().size() + ", expected: " + amountPorts;
            }
        } finally {
            serverService.delete(serverService.findByName(testName).getId());
        }
    }

    @Test
    void selectCross() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Integer amountModules = 2;
            Integer amountPorts = 5;
            Cross cross = crossService.create(buildTestCross(server, amountModules, amountPorts));

            MvcResult mvcResult = this.mockMvc.perform(get(testUrl + crossApi + "/" + cross.getId()))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection())
                    .andReturn();
            String responseAsString = mvcResult.getResponse().getContentAsString();
            Cross crossResponse = objectMapper.readValue(responseAsString, Cross.class);
            assert crossResponse.getName().equals(testName) : "Actual cross name: " + crossResponse.getName() + ", expected: " + testName;
            assert crossResponse.getModules().size() == amountModules : "Actual amount modules: " + crossResponse.getModules().size() + ",expected: " + amountModules;
            assert crossResponse.getServer().getName().equals(testName) : "Actual server name: " + crossResponse.getServer().getName() + ", expected: " + testName;
            assert crossResponse.getServer().getAddress().equals(serverAddress) : "Actual server address: " + crossResponse.getServer().getAddress() + "expected: " + serverAddress;
            for (Module module : crossResponse.getModules()) {
                assert module.getPorts().size() == amountPorts : "Actual amount ports: " + module.getPorts().size() + ", expected: " + amountPorts;
            }
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void updateCrossModulePortField() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        Cross cross = crossService.create(buildTestCross(server, 1, 1));
        try {
            String updateCrossName = "crossTestName";
            Integer updateAmountModules = 2;
            Integer updateAmountPorts = 2;
            Integer updatePortNumber = 2;
            String equipment = "equipment";
            String service = "service";
            String comment = "comment";
            cross.setName(updateCrossName);
            cross.setAmountModuleSlots(updateAmountModules);
            Set<Module> modules = cross.getModules();
            for (Module module : modules) {
                module.setAmountPortSlots(updateAmountPorts);
                Set<Port> ports = module.getPorts();
                for (Port port : ports) {
                    port.setNumber(updatePortNumber);
                    port.setStatus(Status.END_POINT);
                    port.setEndPoint(equipment);
                    port.setService(service);
                    port.setComment(comment);
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);


            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(testUrl + crossApi + "/{id}", cross.getId())
                    .contentType(APPLICATION_JSON)
                    .content(String.valueOf(jsonObjectCross))
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            String responseAsString = mvcResult.getResponse().getContentAsString();
            Cross crossResponse = objectMapper.readValue(responseAsString, Cross.class);
            assert crossResponse.getName().equals(updateCrossName) : "Actual cross name: " + crossResponse.getName() + ", expected: " + updateCrossName;
            assert crossResponse.getEmptyModuleSlots() == 1 : "Actual empty modules: " + crossResponse.getEmptyModuleSlots() + ", expected: 1";
            for (Module module : crossResponse.getModules()) {
                for (Port port : module.getPorts()) {
                    assert port.getNumber() == updatePortNumber : "Actual port number: " + port.getNumber() + ", expected: " + updatePortNumber;
                    assert port.getStatus().equals(Status.END_POINT) : "Actual port status: " + port.getStatus() + ", expected: " + Status.END_POINT;
                    assert port.getEndPoint().equals(equipment) : "Actual port end point: " + port.getEndPoint() + ", expected: " + equipment;
                    assert port.getService().equals(service) : "Actual port service: " + port.getService() + ", expected: " + service;
                    assert port.getComment().equals(comment) : "Actual port comment: " + port.getComment() + ", expected: " + comment;
                }
            }
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void updateModulePortAdd() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = crossService.create(buildTestCross(server, 1, 1));

            cross.setAmountModuleSlots(2);
            cross.getModules()
                    .forEach(module -> {
                        module.setAmountPortSlots(2);
                        module.addPort(new Port(2, Status.NOT_ACTIVE, null, null, null, null, null, null));
                    });
            Set<Port> ports = new HashSet<>();
            ports.add(new Port(1, Status.NOT_ACTIVE, null, null, null, null, null, null));
            ports.add(new Port(2, Status.NOT_ACTIVE, null, null, null, null, null, null));
            cross.addModule(new Module(2, 2, ports, null));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);

            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(testUrl + crossApi + "/{id}", cross.getId())
                    .contentType(APPLICATION_JSON)
                    .content(String.valueOf(jsonObjectCross))
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();

            String responseAsString = mvcResult.getResponse().getContentAsString();
            Cross crossResponse = objectMapper.readValue(responseAsString, Cross.class);
            assert cross.getEmptyModuleSlots() == 0 : "Actual empty modules: " + cross.getAmountModuleSlots() + ", expected: 0";
            for (Module module : crossResponse.getModules())
                assert module.getEmptyPortSlots() == 0 : "Actual empty ports: " + module.getEmptyPortSlots() + ", expected: 0";
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void updateModulePortDelete() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = crossService.create(buildTestCross(server, 2, 2));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            cross.getModules().removeIf(module -> module.getNumber() == 2);
            cross.getModules().forEach(module -> module.getPorts().removeIf(port -> port.getNumber() == 2));
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);
            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(testUrl + crossApi + "/{id}", cross.getId())
                    .contentType(APPLICATION_JSON)
                    .content(String.valueOf(jsonObjectCross))
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            String responseAsString = mvcResult.getResponse().getContentAsString();
            Cross crossResponse = objectMapper.readValue(responseAsString, Cross.class);
            assert crossResponse.getEmptyModuleSlots() == 1 :
                    "Actual empty modules: " + crossResponse.getEmptyModuleSlots() + ", expected: 1";
            crossResponse.getModules()
                    .forEach(module -> {
                        assert module.getEmptyPortSlots() == 1 :
                                "Actual empty ports: " + module.getEmptyPortSlots() + ", expected: 1";
                    });

        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void validateDuplicateCrossModulePort() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            crossService.create(buildTestCross(server, 1, 1));
            Cross duplicateCross = buildTestCross(server, 1, 1);
            duplicateCross.setAmountModuleSlots(2);
            Set<Port> ports = new HashSet<>();
            ports.add(new Port(1, Status.NOT_ACTIVE, null, null, null, null, null, null));
            ports.add(new Port(1, Status.NOT_ACTIVE, null, null, null, null, null, null));
            duplicateCross.addModule(new Module(1, 2, ports, null));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(duplicateCross);

            this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(jsonObjectCross)
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString(String.valueOf
                            (messageService.getMessage("cross.name.inUse", null)))))
                    .andExpect(content().string(containsString(String.valueOf
                            (messageService.getMessage("module.number.inUse", null)))))
                    .andExpect(content().string(containsString(String.valueOf
                            (messageService.getMessage("port.number.inUse", new Object[]{1})))));
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void validateNullCrossModulePort() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Set<Module> modules = new HashSet<>();
            Set<Port> ports = new HashSet<>();
            ports.add(new Port(777, Status.NOT_ACTIVE, null, null, null, null, null, null));
            modules.add(new Module(777, 1, ports, null));
            Cross cross = new Cross("777", 1, modules, server);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);
            JSONObject jsonObject = new JSONObject(jsonObjectCross);
            jsonObject.put("name", jsonObject.get("id"));
            ((JSONArray) jsonObject.get("modules"))
                    .getJSONObject(0)
                    .put("number", jsonObject.get("id"));
            ((JSONArray)
                    (((JSONArray) jsonObject.get("modules"))
                            .getJSONObject(0).get("ports")))
                    .getJSONObject(0)
                    .put("number", jsonObject.get("id"));

            this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(String.valueOf(jsonObject))
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("cross.name.notNull", null)))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("module.number.notNull", null)))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("port.number.notNull", null)))));
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void validateMaxLengthCrossName() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = buildTestCross(server, 1, 1);
            StringBuilder maxLengthName = new StringBuilder();
            int length = 50;
            while (maxLengthName.length() <= length) maxLengthName.append("tes");
            cross.setName(maxLengthName.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);

            this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(jsonObjectCross)
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("cross.name.maxSize", new Object[]{length})))));
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void validateMinLengthCrossName() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = buildTestCross(server, 1, 1);
            String minLengthName = "j";
            cross.setName(minLengthName);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);

            this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(jsonObjectCross)
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("cross.name.minSize", new Object[]{2})))));
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void validatePositiveModulePortNumber() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = buildTestCross(server, 1, 1);
            cross.setAmountModuleSlots(-1);
            cross.getModules().forEach(module -> {
                module.setAmountPortSlots(-1);
                module.setNumber(-1);
                module.getPorts().forEach(port -> {
                    port.setNumber(-1);
                });
            });
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);

            this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(jsonObjectCross)
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("cross.modules.positive", null)))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("module.number.positive", null)))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("module.ports.positive", null)))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("port.number.positive", null)))))
            ;
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void validateModulePortLimit() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = buildTestCross(server, 2, 2);
            cross.setAmountModuleSlots(1);
            cross.getModules().forEach(module -> {
                module.setAmountPortSlots(1);
            });
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String jsonObjectCross = objectMapper.writerWithView(Views.Cross.class).writeValueAsString(cross);

            this.mockMvc.perform(post(testUrl + crossApi)
                    .contentType(APPLICATION_JSON)
                    .content(jsonObjectCross)
                    .accept(APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("cross.modules.limit", new Object[]{cross.getAmountModuleSlots()})))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("module.number.limit", new Object[]{cross.getAmountModuleSlots()})))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("module.ports.limit", new Object[]{1, 1})))))
                    .andExpect(content().string(containsString(String.valueOf(
                            messageService.getMessage("port.number.limit", new Object[]{1})))));
        } finally {
            serverService.delete(server.getId());
        }
    }

    @Test
    void deleteCross() throws Exception {
        Server server = serverService.create(new Server(testName, serverAddress));
        try {
            Cross cross = crossService.create(buildTestCross(server, 1, 1));
            this.mockMvc.perform(MockMvcRequestBuilders
                    .delete(testUrl + crossApi + "/" + cross.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } finally {
            serverService.delete(server.getId());
        }
    }
}
