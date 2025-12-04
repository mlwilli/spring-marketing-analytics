package com.github.mlwilli.marketing.controller;

import com.github.mlwilli.marketing.dto.CampaignCreateRequest;
import com.github.mlwilli.marketing.dto.CampaignDto;
import com.github.mlwilli.marketing.dto.CampaignSummaryDto;
import com.github.mlwilli.marketing.dto.CampaignUpdateRequest;
import com.github.mlwilli.marketing.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<CampaignDto> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public CampaignDto getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }

    @PostMapping
    public ResponseEntity<CampaignDto> createCampaign(@Valid @RequestBody CampaignCreateRequest request) {
        CampaignDto created = campaignService.createCampaign(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public CampaignDto updateCampaign(@PathVariable Long id,
                                      @Valid @RequestBody CampaignUpdateRequest request) {
        return campaignService.updateCampaign(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/summary")
    public CampaignSummaryDto getCampaignSummary(@PathVariable Long id) {
        return campaignService.getCampaignSummary(id);
    }
}
