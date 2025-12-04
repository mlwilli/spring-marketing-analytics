package com.github.mlwilli.marketing.service;

import com.github.mlwilli.marketing.dto.CampaignCreateRequest;
import com.github.mlwilli.marketing.dto.CampaignDto;
import com.github.mlwilli.marketing.dto.CampaignSummaryDto;
import com.github.mlwilli.marketing.dto.CampaignUpdateRequest;
import com.github.mlwilli.marketing.entity.Campaign;
import com.github.mlwilli.marketing.entity.CampaignStatus;
import com.github.mlwilli.marketing.exception.ResourceNotFoundException;
import com.github.mlwilli.marketing.repository.CampaignRepository;
import com.github.mlwilli.marketing.service.impl.CampaignServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampaignServiceImplTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignServiceImpl campaignService;

    private Campaign sampleCampaign;

    @BeforeEach
    void setUp() {
        sampleCampaign = new Campaign();
        sampleCampaign.setId(1L);
        sampleCampaign.setName("Test Campaign");
        sampleCampaign.setDescription("Desc");
        sampleCampaign.setStartDate(LocalDate.of(2025, 1, 1));
        sampleCampaign.setEndDate(LocalDate.of(2025, 2, 1));
        sampleCampaign.setBudget(new BigDecimal("1000.00"));
        sampleCampaign.setStatus(CampaignStatus.ACTIVE);
        sampleCampaign.setImpressions(1000);
        sampleCampaign.setClicks(100);
        sampleCampaign.setConversions(10);
        sampleCampaign.setCost(new BigDecimal("500.00"));
        sampleCampaign.setCreatedAt(LocalDateTime.now());
        sampleCampaign.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void getAllCampaigns_returnsDtos() {
        when(campaignRepository.findAll()).thenReturn(List.of(sampleCampaign));

        List<CampaignDto> result = campaignService.getAllCampaigns();

        assertEquals(1, result.size());
        CampaignDto dto = result.get(0);
        assertEquals(sampleCampaign.getId(), dto.getId());
        assertEquals(sampleCampaign.getName(), dto.getName());
        verify(campaignRepository, times(1)).findAll();
    }

    @Test
    void getCampaignById_existingId_returnsDto() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(sampleCampaign));

        CampaignDto dto = campaignService.getCampaignById(1L);

        assertEquals(1L, dto.getId());
        assertEquals("Test Campaign", dto.getName());
    }

    @Test
    void getCampaignById_missingId_throws() {
        when(campaignRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> campaignService.getCampaignById(99L));
    }

    @Test
    void createCampaign_savesEntity() {
        CampaignCreateRequest request = new CampaignCreateRequest();
        request.setName("New Campaign");
        request.setBudget(new BigDecimal("2000.00"));
        request.setImpressions(0L);
        request.setClicks(0L);
        request.setConversions(0L);

        when(campaignRepository.save(any(Campaign.class))).thenAnswer(invocation -> {
            Campaign c = invocation.getArgument(0);
            c.setId(10L);
            c.setCreatedAt(LocalDateTime.now());
            c.setUpdatedAt(LocalDateTime.now());
            return c;
        });

        CampaignDto result = campaignService.createCampaign(request);

        assertNotNull(result.getId());
        assertEquals("New Campaign", result.getName());

        ArgumentCaptor<Campaign> captor = ArgumentCaptor.forClass(Campaign.class);
        verify(campaignRepository).save(captor.capture());
        assertEquals("New Campaign", captor.getValue().getName());
    }

    @Test
    void updateCampaign_updatesFields() {
        when(campaignRepository.findById(1L)).thenReturn(Optional.of(sampleCampaign));
        when(campaignRepository.save(any(Campaign.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CampaignUpdateRequest request = new CampaignUpdateRequest();
        request.setName("Updated Name");
        request.setDescription("Updated desc");
        request.setBudget(new BigDecimal("1500.00"));
        request.setStatus(CampaignStatus.PAUSED);
        request.setImpressions(2000L);
        request.setClicks(150L);
        request.setConversions(25L);
        request.setCost(new BigDecimal("700.00"));

        CampaignDto dto = campaignService.updateCampaign(1L, request);

        assertEquals("Updated Name", dto.getName());
        assertEquals(CampaignStatus.PAUSED, dto.getStatus());
        assertEquals(2000L, dto.getImpressions());
        verify(campaignRepository).save(any(Campaign.class));
    }

    @Test
    void getCampaignSummary_computesMetrics() {
        sampleCampaign.setImpressions(1000);
        sampleCampaign.setClicks(100);
        sampleCampaign.setConversions(10);
        sampleCampaign.setCost(new BigDecimal("500.00"));

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(sampleCampaign));

        CampaignSummaryDto summary = campaignService.getCampaignSummary(1L);

        assertEquals(1L, summary.getId());
        assertEquals(10.0, summary.getCtr(), 0.0001);
        assertEquals(10.0, summary.getConversionRate(), 0.0001);
        assertNotNull(summary.getCpc());
        assertNotNull(summary.getCpa());
    }
}
