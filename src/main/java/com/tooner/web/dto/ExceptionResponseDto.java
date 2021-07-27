package com.tooner.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExceptionResponseDto {
    private String errorMessage;

    public ExceptionResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
