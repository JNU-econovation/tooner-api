package com.tooner.service;

import com.tooner.domain.member.Member;
import com.tooner.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String userName) throws UsernameNotFoundException {
        return memberRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
    }
}
