package com.tooner.service;

import com.tooner.domain.member.Member;
import com.tooner.domain.member.MemberRepository;
import com.tooner.domain.member.Role;
import com.tooner.web.dto.MemberSignInDto;
import com.tooner.web.dto.MemberSignUpDto;
import com.tooner.web.dto.MessageResponseDto;
import com.tooner.web.exception.DuplicatedUserNameException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Member loadUserByUsername(String userName) throws UsernameNotFoundException {
        //여기서 return된 UserDetails는 SecurityContext의 Authentication에 등록되어 인증 정보를 갖고 있는다.
        return memberRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
    }

    @Transactional
    public Long save(MemberSignUpDto memberSignUpDto) {

        validateDuplicatedUserName(memberSignUpDto.getUserName());

        return memberRepository.save(Member.builder()
                .userName(memberSignUpDto.getUserName())
                .password(encoder().encode(memberSignUpDto.getPassword()))
                .alias(memberSignUpDto.getAlias())
                .profileImage(memberSignUpDto.getProfileImage())
                .bio(memberSignUpDto.getBio())
                .age(memberSignUpDto.getAge())
                .gender(memberSignUpDto.getGender())
                .role(Role.USER)
                .keywords(memberSignUpDto.getKeywords())
                .reviewArticles(memberSignUpDto.getReviewArticles())
                .build()).getId();
    }

    @Transactional
    public Long signIn(MemberSignInDto memberSignInDto) {
        Member member = loadUserByUsername(memberSignInDto.getUserName());

        if(encoder().matches(memberSignInDto.getPassword(), member.getPassword())) {
            return member.getId();
        }
        throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }

    private void validateDuplicatedUserName(String userName) throws DuplicatedUserNameException {
        log.info("회원가입 중복 체크 시작");
        memberRepository.findByUserName(userName).ifPresent((member) -> {
            throw new DuplicatedUserNameException(member.getUsername());
        });
        log.info("회원가입 중복 체크 종료");
    }
}
