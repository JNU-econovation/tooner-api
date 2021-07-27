package com.tooner.web;

import com.tooner.service.MemberService;
import com.tooner.web.dto.MemberSignInDto;
import com.tooner.web.dto.MemberSignUpDto;
import com.tooner.web.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.util.resources.cldr.sg.CurrencyNames_sg;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MessageResponseDto> signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        memberService.save(memberSignUpDto);
        return ResponseEntity.ok(new MessageResponseDto("회원가입 성공"));
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> singIn(@RequestBody MemberSignInDto memberSignInDto) {
        memberService.signIn(memberSignInDto);
        return ResponseEntity.ok(new MessageResponseDto("로그인 성공"));
    }
}
