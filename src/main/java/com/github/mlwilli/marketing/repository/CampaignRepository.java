package com.github.mlwilli.marketing.repository;

import com.github.mlwilli.marketing.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
