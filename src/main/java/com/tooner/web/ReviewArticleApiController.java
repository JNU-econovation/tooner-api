package com.tooner.web;

import com.tooner.service.ReviewArticleService;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import com.tooner.web.dto.ReviewArticleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api")
public class ReviewArticleApiController {

    private final ReviewArticleService reviewArticleService;

    @PostMapping("/api/reviews")
    public Long save(@RequestBody ReviewArticleSaveRequestDto requestDto) {
        return reviewArticleService.save(requestDto);
    }

    @PutMapping("/api/reviews/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewArticleUpdateRequestDto requestDto) {
        return reviewArticleService.update(id, requestDto);
    }
}
