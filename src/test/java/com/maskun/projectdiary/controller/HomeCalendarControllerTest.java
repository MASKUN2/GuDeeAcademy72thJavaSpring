package com.maskun.projectdiary.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.YearMonth;

@SpringBootTest
@AutoConfigureMockMvc
class HomeCalendarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 날짜파싱에_실패하고_리다이렉트된다() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home/xxxx"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home/"+ YearMonth.now().toString()));

    }
}