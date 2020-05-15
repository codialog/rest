package com.treetrunk.trek;

import com.treetrunk.trek.model.Server;
import com.treetrunk.trek.service.ServerService;
import com.treetrunk.trek.validator.MessageService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TrekApplicationServerTests {

    @Autowired
    private MockMvc mockMvc;
    private String testUrl = "/api";
    private String serverApi = "/server";

    private final ServerService serverService;
    private final MessageService messageService;

    @Autowired
    TrekApplicationServerTests(ServerService serverService, MessageService messageService) {
        this.serverService = serverService;
        this.messageService = messageService;
    }

    String serverName = "test_name";
    String serverAddress = "test_address";
    String idField = "id";
    String nameField = "name";
    String addressField = "address";

    @Test
    void createServer() throws Exception {
        this.mockMvc.perform(post(testUrl + serverApi)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject()
                        .put(nameField, serverName)
                        .put(addressField, serverAddress)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        serverService.delete(serverService.findByName(serverName).getId());
    }

    @Test
    void selectServer() throws Exception {
        Server server = serverService.create(new Server(serverName, serverAddress));
        this.mockMvc.perform(get(testUrl + serverApi + "/" + server.getId()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString(String.valueOf(serverName))))
                .andExpect(content().string(containsString(String.valueOf(serverAddress))));
        serverService.delete(server.getId());
    }

    @Test
    void updateServer() throws Exception {
        String updateName = "test_server_name";
        String updateAddress = "test_server_address";
        Server server = serverService.create(new Server(serverName, serverAddress));
        this.mockMvc.perform(put(testUrl + serverApi + "/{id}", server.getId())
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject()
                        .put(idField, server.getId())
                        .put(nameField, updateName)
                        .put(addressField, updateAddress)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        serverService.delete(server.getId());
    }

    @Test
    void validateDuplicateServerName() throws Exception {
        Server server = serverService.create(new Server(serverName, serverAddress));
        this.mockMvc.perform(post(testUrl + serverApi)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject()
                        .put(nameField, serverName)
                        .put(addressField, serverAddress)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(String.valueOf
                        (messageService.getMessage("server.name.inUse", null)))));
        serverService.delete(server.getId());
    }

    @Test
    void validateNullServerName() throws Exception {
        this.mockMvc.perform(post(testUrl + serverApi)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject()
                        .put(nameField, "")
                        .put(addressField, serverAddress)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(String.valueOf(
                        messageService.getMessage("server.name.notNull", null)))));
    }

    @Test
    void validateMaxLengthServerName() throws Exception {
        String maxLengthName = "";
        int length = 100;
        while (maxLengthName.length() <= length) maxLengthName += "tes";
        this.mockMvc.perform(post(testUrl + serverApi)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject()
                        .put(nameField, maxLengthName)
                        .put(addressField, serverAddress)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(String.valueOf(
                        messageService.getMessage("server.name.maxSize", new Object[]{length})))));
    }

    @Test
    void validateMinLengthServerName() throws Exception {
        String minLengthName = "j";
        int length = 1;
        this.mockMvc.perform(post(testUrl + serverApi)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject()
                        .put(nameField, minLengthName)
                        .put(addressField, serverAddress)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString(String.valueOf(
                        messageService.getMessage("server.name.minSize", new Object[]{length})))));
    }

    @Test
    void deleteServer() throws Exception {
        Server server = serverService.create(new Server(serverName, serverAddress));
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(testUrl + serverApi + "/" + server.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}