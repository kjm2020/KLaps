package com.kds.cop.klap.member;

import com.kds.cop.klap.common.CommonController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController extends CommonController<Member,Long> {
    // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;
//    public MemberController(MemberService service) {
//        this.service = service;
//    }
    // 모든 회원조회
    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Member>> getAllmebers() {
        List<Member> member = memberService.findAll();
        return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
    }

    // 회원번호로 한명의 회원 조회
    @GetMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MemberVo> getMember(@PathVariable("mbrNo") Long mbrNo) {
        Optional<MemberVo> member = memberService.findById(mbrNo);
        return new ResponseEntity<MemberVo>(member.get(), HttpStatus.OK);
    }

    // 회원번호로 회원 삭제
    @DeleteMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteMember(@PathVariable("mbrNo") Long mbrNo) {
        memberService.deleteById(mbrNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // 회원번호로 회원 수정(mbrNo로 회원을 찾아 Member 객체의 id, name로 수정함)
    @PutMapping(value = "/{mbrNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Member> updateMember(@PathVariable("mbrNo") Long mbrNo, Member member) {
        memberService.updateById(mbrNo, member);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    // 회원 입력
    @PostMapping public ResponseEntity<Member> save(Member member) {
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    // 회원 입력
    @RequestMapping(value="/saveMember", method = RequestMethod.GET)
    public ResponseEntity<Member> save(HttpServletRequest req, Member member){
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }


//    @PostMapping("/search")
//    public Member selectByName(@RequestBody String memberName) {
//        log.debug("MemberController.selectByName - {}",memberName);
//        return service.findByName(memberName);
//    }
}



