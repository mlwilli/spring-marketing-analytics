package com.github.mlwilli.marketing.service;

import com.github.mlwilli.marketing.dto.CampaignCreateRequest;
import com.github.mlwilli.marketing.dto.CampaignDto;
import com.github.mlwilli.marketing.dto.CampaignSummaryDto;
import com.github.mlwilli.marketing.dto.CampaignUpdateRequest;

import java.util.List;

public interface CampaignService {

    List<CampaignDto> getAllCampaigns();

    CampaignDto getCampaignById(Long id);

    CampaignDto createCampaign(CampaignCreateRequest request);

    CampaignDto updateCampaign(Long id, CampaignUpdateRequest request);

    void deleteCampaign(Long id);

    CampaignSummaryDto getCampaignSummary(Long id);
}
