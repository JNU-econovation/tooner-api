package com.tooner.service;

import com.tooner.domain.reviewarticle.ReviewArticleRepository;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewArticleService {
    private final ReviewArticleRepository reviewArticleRepository;

    @Transactional
    public Long save(ReviewArticleSaveRequestDto requestDto) {
        return reviewArticleRepository.save(requestDto.toEntity()).getId();
    }
}
