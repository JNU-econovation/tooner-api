package com.tooner.web;

import com.tooner.service.ReviewArticleService;
import com.tooner.web.dto.ReviewArticleResponseDto;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import com.tooner.web.dto.ReviewArticleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewArticleApiController {

    private final ReviewArticleService reviewArticleService;

    @PostMapping("/reviews")
    public Long save(@RequestBody ReviewArticleSaveRequestDto requestDto) {
        return reviewArticleService.save(requestDto);
    }

    @PutMapping("/reviews/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewArticleUpdateRequestDto requestDto) {
        return reviewArticleService.update(id, requestDto);
    }

    @GetMapping("/reviews/{id}")
    public ReviewArticleResponseDto findById(@PathVariable Long id) {
        return reviewArticleService.findById(id);
    }

    @DeleteMapping("/reviews/{id}")
    public Long delete(@PathVariable Long id) {
        reviewArticleService.delete(id);
        return id;
    }
}
