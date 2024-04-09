package com.jshi.laughtale.member.service;

import com.jshi.laughtale.ebbinghaus.EbbinghausUtils;
import com.jshi.laughtale.member.domain.Member;
import com.jshi.laughtale.member.dto.MemberCheck;
import com.jshi.laughtale.member.dto.MemberUpdate;
import com.jshi.laughtale.member.exception.MemberNotFoundException;
import com.jshi.laughtale.member.mapper.MemberMapper;
import com.jshi.laughtale.member.repository.MemberRepository;
import com.jshi.laughtale.security.details.CustomUserDetails;
import com.jshi.laughtale.security.jwt.JwtProcessor;
import com.jshi.laughtale.wordhistory.domain.WordHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final EbbinghausUtils ebbinghausUtil;
    private final JwtProcessor jwtProcessor;

    public Map<String, String> login(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        String jwtToken = jwtProcessor.createJwtToken(member.getEmail(), member.getRole().value());
        return Map.of("accessToken", jwtToken);
    }

    public MemberCheck.Response check(Long memberId) {
        return MemberMapper.toCheckResponse(
                memberRepository.findById(memberId)
                        .orElseThrow(MemberNotFoundException::new));
    }

    public void update(CustomUserDetails customUserDetails, MemberUpdate.Request update) {
        Member member = memberRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        Optional.ofNullable(update.getPassword()).ifPresent(member::updatePassword);
        Optional.ofNullable(update.getNickname()).ifPresent(member::updateNickname);
    }

    public void updateRole(MemberUpdate.RoleRequest updateRole) {
        Member member = memberRepository.findByEmail(updateRole.getEmail()).orElseThrow(MemberNotFoundException::new);
        member.updateRole(updateRole.getRole());
    }

    public void withdraw(CustomUserDetails customUserDetails) {
        Member member = memberRepository.findByEmail(customUserDetails.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }

    public int getMemberLevel(List<WordHistory> wordHistoryList) {
        //사용자의 실력 추정 값을 반환
        double sum = 0.0;
        for (WordHistory wordHistory : wordHistoryList) {
            int wordLevel = wordHistory.getWordData().getLevel();
            sum += wordLevel * ebbinghausUtil.calculateMemory(wordHistory.getStudyDate(), wordHistory.getStudyCnt());
            // log.info("sum : " + sum);
        }
        log.info("계산된 회원 실력 : " + (((sum / wordHistoryList.size()) / 100) + 1));
        return (int) Math.round(((sum / wordHistoryList.size()) / 100) + 1);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

}