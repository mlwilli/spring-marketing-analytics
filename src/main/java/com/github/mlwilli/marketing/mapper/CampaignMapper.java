package com.github.mlwilli.marketing.mapper;

import com.github.mlwilli.marketing.dto.CampaignCreateRequest;
import com.github.mlwilli.marketing.dto.CampaignDto;
import com.github.mlwilli.marketing.dto.CampaignSummaryDto;
import com.github.mlwilli.marketing.dto.CampaignUpdateRequest;
import com.github.mlwilli.marketing.entity.Campaign;
import com.github.mlwilli.marketing.entity.CampaignStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CampaignMapper {

    private CampaignMapper() {
    }

    public static CampaignDto toDto(Campaign entity) {
        if (entity == null) {
            return null;
        }
        return new CampaignDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getBudget(),
                entity.getStatus(),
                entity.getImpressions(),
                entity.getClicks(),
                entity.getConversions(),
                entity.getCost(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static Campaign fromCreateRequest(CampaignCreateRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setBudget(request.getBudget());
        CampaignStatus status = request.getStatus() != null ? request.getStatus() : CampaignStatus.PLANNED;
        campaign.setStatus(status);
        campaign.setImpressions(request.getImpressions() != null ? request.getImpressions() : 0L);
        campaign.setClicks(request.getClicks() != null ? request.getClicks() : 0L);
        campaign.setConversions(request.getConversions() != null ? request.getConversions() : 0L);
        campaign.setCost(request.getCost());
        return campaign;
    }

    public static void updateEntityFromRequest(CampaignUpdateRequest request, Campaign campaign) {
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setBudget(request.getBudget());
        if (request.getStatus() != null) {
            campaign.setStatus(request.getStatus());
        }
        if (request.getImpressions() != null) {
            campaign.setImpressions(request.getImpressions());
        }
        if (request.getClicks() != null) {
            campaign.setClicks(request.getClicks());
        }
        if (request.getConversions() != null) {
            campaign.setConversions(request.getConversions());
        }
        campaign.setCost(request.getCost());
    }

    public static CampaignSummaryDto toSummaryDto(Campaign entity) {
        long impressions = entity.getImpressions();
        long clicks = entity.getClicks();
        long conversions = entity.getConversions();
        BigDecimal cost = entity.getCost();

        double ctr = 0.0;
        double conversionRate = 0.0;
        if (impressions > 0) {
            ctr = (clicks * 100.0) / impressions;
        }
        if (clicks > 0) {
            conversionRate = (conversions * 100.0) / clicks;
        }

        BigDecimal cpc = null;
        BigDecimal cpa = null;
        if (cost != null) {
            if (clicks > 0) {
                cpc = cost.divide(BigDecimal.valueOf(clicks), 4, RoundingMode.HALF_UP);
            }
            if (conversions > 0) {
                cpa = cost.divide(BigDecimal.valueOf(conversions), 4, RoundingMode.HALF_UP);
            }
        }

        return new CampaignSummaryDto(
                entity.getId(),
                entity.getName(),
                entity.getStatus(),
                impressions,
                clicks,
                conversions,
                cost,
                ctr,
                conversionRate,
                cpc,
                cpa
        );
    }
}
