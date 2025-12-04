package com.github.mlwilli.marketing.dto;

import com.github.mlwilli.marketing.entity.CampaignStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CampaignCreateRequest {

    @NotBlank
    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @PositiveOrZero
    private BigDecimal budget;

    private CampaignStatus status;

    @PositiveOrZero
    private Long impressions;

    @PositiveOrZero
    private Long clicks;

    @PositiveOrZero
    private Long conversions;

    @PositiveOrZero
    private BigDecimal cost;

    public CampaignCreateRequest() {
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public Long getImpressions() {
        return impressions;
    }

    public Long getClicks() {
        return clicks;
    }

    public Long getConversions() {
        return conversions;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public void setImpressions(Long impressions) {
        this.impressions = impressions;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public void setConversions(Long conversions) {
        this.conversions = conversions;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
