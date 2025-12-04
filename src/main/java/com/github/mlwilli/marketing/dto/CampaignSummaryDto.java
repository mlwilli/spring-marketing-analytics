package com.github.mlwilli.marketing.dto;

import com.github.mlwilli.marketing.entity.CampaignStatus;

import java.math.BigDecimal;

public class CampaignSummaryDto {

    private Long id;
    private String name;
    private CampaignStatus status;
    private long impressions;
    private long clicks;
    private long conversions;
    private BigDecimal cost;
    private double ctr;             // Click-through rate (%)
    private double conversionRate;  // Conversion rate (%)
    private BigDecimal cpc;         // Cost per click
    private BigDecimal cpa;         // Cost per acquisition (conversion)

    public CampaignSummaryDto() {
    }

    public CampaignSummaryDto(Long id,
                              String name,
                              CampaignStatus status,
                              long impressions,
                              long clicks,
                              long conversions,
                              BigDecimal cost,
                              double ctr,
                              double conversionRate,
                              BigDecimal cpc,
                              BigDecimal cpa) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.cost = cost;
        this.ctr = ctr;
        this.conversionRate = conversionRate;
        this.cpc = cpc;
        this.cpa = cpa;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public long getImpressions() {
        return impressions;
    }

    public long getClicks() {
        return clicks;
    }

    public long getConversions() {
        return conversions;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public double getCtr() {
        return ctr;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public BigDecimal getCpc() {
        return cpc;
    }

    public BigDecimal getCpa() {
        return cpa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public void setImpressions(long impressions) {
        this.impressions = impressions;
    }

    public void setClicks(long clicks) {
        this.clicks = clicks;
    }

    public void setConversions(long conversions) {
        this.conversions = conversions;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public void setCpc(BigDecimal cpc) {
        this.cpc = cpc;
    }

    public void setCpa(BigDecimal cpa) {
        this.cpa = cpa;
    }
}
