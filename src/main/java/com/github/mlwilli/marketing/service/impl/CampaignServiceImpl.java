package com.github.mlwilli.marketing.service.impl;

import com.github.mlwilli.marketing.dto.CampaignCreateRequest;
import com.github.mlwilli.marketing.dto.CampaignDto;
import com.github.mlwilli.marketing.dto.CampaignSummaryDto;
import com.github.mlwilli.marketing.dto.CampaignUpdateRequest;
import com.github.mlwilli.marketing.entity.Campaign;
import com.github.mlwilli.marketing.exception.ResourceNotFoundException;
import com.github.mlwilli.marketing.mapper.CampaignMapper;
import com.github.mlwilli.marketing.repository.CampaignRepository;
import com.github.mlwilli.marketing.service.CampaignService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampaignDto> getAllCampaigns() {
        return campaignRepository.findAll()
                .stream()
                .map(CampaignMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CampaignDto getCampaignById(Long id) {
        Campaign campaign = findByIdOrThrow(id);
        return CampaignMapper.toDto(campaign);
    }

    @Override
    public CampaignDto createCampaign(CampaignCreateRequest request) {
        Campaign toSave = CampaignMapper.fromCreateRequest(request);
        Campaign saved = campaignRepository.save(toSave);
        return CampaignMapper.toDto(saved);
    }

    @Override
    public CampaignDto updateCampaign(Long id, CampaignUpdateRequest request) {
        Campaign existing = findByIdOrThrow(id);
        CampaignMapper.updateEntityFromRequest(request, existing);
        Campaign saved = campaignRepository.save(existing);
        return CampaignMapper.toDto(saved);
    }

    @Override
    public void deleteCampaign(Long id) {
        Campaign existing = findByIdOrThrow(id);
        campaignRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public CampaignSummaryDto getCampaignSummary(Long id) {
        Campaign campaign = findByIdOrThrow(id);
        return CampaignMapper.toSummaryDto(campaign);
    }

    private Campaign findByIdOrThrow(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign with id " + id + " not found"));
    }
}
