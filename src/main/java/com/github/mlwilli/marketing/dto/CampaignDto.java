package com.github.mlwilli.marketing.dto;

import com.github.mlwilli.marketing.entity.CampaignStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CampaignDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal budget;
    private CampaignStatus status;
    private long impressions;
    private long clicks;
    private long conversions;
    private BigDecimal cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CampaignDto() {
    }

    public CampaignDto(Long id,
                       String name,
                       String description,
                       LocalDate startDate,
                       LocalDate endDate,
                       BigDecimal budget,
                       CampaignStatus status,
                       long impressions,
                       long clicks,
                       long conversions,
                       BigDecimal cost,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.status = status;
        this.impressions = impressions;
        this.clicks = clicks;
        this.conversions = conversions;
        this.cost = cost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
