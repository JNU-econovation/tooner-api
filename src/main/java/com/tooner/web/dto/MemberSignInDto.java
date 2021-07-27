package com.tooner.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignInDto {
    private String userName;
    private String password;

    @Builder
    public MemberSignInDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
