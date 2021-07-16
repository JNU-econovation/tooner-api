package com.tooner.service;

import com.tooner.domain.reviewarticle.ReviewArticle;
import com.tooner.domain.reviewarticle.ReviewArticleRepository;
import com.tooner.web.dto.ReviewArticleResponseDto;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import com.tooner.web.dto.ReviewArticleUpdateRequestDto;
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

    @Transactional
    public Long update(Long id, ReviewArticleUpdateRequestDto requestDto) {
        ReviewArticle reviewArticle = reviewArticleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        reviewArticle.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getRating());

        return id;
    }

    @Transactional
    public ReviewArticleResponseDto findById(Long id) {
        ReviewArticle entity = reviewArticleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new ReviewArticleResponseDto(entity);
    }
}
