package com.tooner.web;

import com.tooner.service.ReviewArticleService;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api")
public class ReviewArticleApiController {

    private final ReviewArticleService reviewArticleService;

    @PostMapping("/api/reviews")
    public Long save(@RequestBody ReviewArticleSaveRequestDto requestDto) {
        return reviewArticleService.save(requestDto);
    }
}
