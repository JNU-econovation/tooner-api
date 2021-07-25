package com.tooner.web.dto;

import com.tooner.domain.Gender;
import com.tooner.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpDto {
    private String userName;
    private String password;
    private String alias;
    private String profileImage;
    private String bio;
    private Integer age;
    private Gender gender;
    private Role role;

    @Builder
    public MemberSignUpDto(String userName, String password, String alias, String profileImage, String bio, Integer age, Gender gender, Role role) {
        this.userName = userName;
        this.password = password;
        this.alias = alias;
        this.profileImage = profileImage;
        this.bio = bio;
        this.age = age;
        this.gender = gender;
        this.role = role;
    }
}
