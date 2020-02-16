package com.treetrunk.trek;

import com.treetrunk.trek.controller.port.PortController;
import com.treetrunk.trek.controller.port.Status;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrekApplicationTests {

    String status = "status";
    @Autowired
    private MockMvc mockMvc;
    private String testUrl = "/port";
    @Autowired
    private PortController portController;

    @Test
    void content_port() throws Exception {
        this.mockMvc.perform(get(testUrl))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(Status.TRANSIT))));
    }

    @Test
    void selected_port() throws Exception {
        int id = 0;
        this.mockMvc.perform(get(testUrl + "/" + id))
                .andDo(print())
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(Status.TRANSIT))));
    }

    @Test
    void create_ports() throws Exception {
        int id = 3;
        this.mockMvc.perform(post(testUrl)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject().put(status, Status.TRANSIT)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get(testUrl + "/" + id))
                .andDo(print())
                .andExpect(content().string(containsString(String.valueOf(Status.TRANSIT))));
    }

    @Test
    void delete_port() throws Exception {
        int id = 3;
        this.mockMvc.perform(post(testUrl)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject().put(status, Status.TRANSIT)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(testUrl + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update_port() throws Exception {
        int id = 2;
        this.mockMvc.perform(put(testUrl + "/{id}", id)
                .contentType(APPLICATION_JSON)
                .content(String.valueOf(new JSONObject().put(status, Status.TRANSIT)))
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        this.mockMvc.perform(get(testUrl + "/" + id))
                .andDo(print())
                .andExpect(content().string(containsString(String.valueOf(Status.TRANSIT))));
    }
}
