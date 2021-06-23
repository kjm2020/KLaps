package com.kds.cop.klap.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll(){
        List<Member> member = new ArrayList<>();
        memberRepository.findAll().forEach(e -> member.add(e));

        return member;
    }
    public Optional<Member> findById(Long mbrNo) {
        Optional<Member> member = memberRepository.findById(mbrNo);
        return member;
    }

    public void deleteById(Long mbrNo) {
        memberRepository.deleteById(mbrNo);
    }

    public Member save(Member member) {
        memberRepository.save(member); return member;
    }

    public void updateById(Long mbrNo, Member member) {
        Optional<Member> e = memberRepository.findById(mbrNo);
        if (e.isPresent()) {
            e.get().setMbrNo(member.getMbrNo());
            e.get().setId(member.getId());
            e.get().setName(member.getName());
            memberRepository.save(member);
        }
    }


}
