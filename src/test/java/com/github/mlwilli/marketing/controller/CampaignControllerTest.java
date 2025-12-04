package com.github.mlwilli.marketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mlwilli.marketing.dto.CampaignCreateRequest;
import com.github.mlwilli.marketing.dto.CampaignDto;
import com.github.mlwilli.marketing.entity.CampaignStatus;
import com.github.mlwilli.marketing.service.CampaignService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CampaignController.class)
class CampaignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignService campaignService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCampaigns_returnsOk() throws Exception {
        CampaignDto dto = new CampaignDto(
                1L,
                "Test Campaign",
                "Desc",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 2, 1),
                new BigDecimal("1000.00"),
                CampaignStatus.ACTIVE,
                1000,
                100,
                10,
                new BigDecimal("500.00"),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Mockito.when(campaignService.getAllCampaigns()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/campaigns"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test Campaign")));
    }

    @Test
    void createCampaign_validRequest_returnsCreated() throws Exception {
        CampaignCreateRequest request = new CampaignCreateRequest();
        request.setName("New Campaign");
        request.setBudget(new BigDecimal("2000.00"));

        CampaignDto created = new CampaignDto(
                10L,
                "New Campaign",
                null,
                null,
                null,
                new BigDecimal("2000.00"),
                CampaignStatus.PLANNED,
                0,
                0,
                0,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Mockito.when(campaignService.createCampaign(any(CampaignCreateRequest.class))).thenReturn(created);

        mockMvc.perform(
                        post("/api/campaigns")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.name", is("New Campaign")));
    }
}
