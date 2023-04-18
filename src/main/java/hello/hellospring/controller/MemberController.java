package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller 어노테이션이 붙어있으면 springboot 처음 실행시킬 때 컨테이너에 MemberController 객체를 생성해서 가지고있음
@Controller
public class MemberController {

    private final MemberService memberService;

    //DI autowired로 묶여있으면 controller가 생성될 때 spring bean에 등록되어있는 service 객체를 넣어줌
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
        //setter 생성자로 DI 할 경우 이렇게 아무렇게나 호출이 가능함(호출이 필요하지않은 경우에는 호출하지않도록 만드는게 좋음 따라서 setter 생성자는 사용x)
        //memberService.setMemberRepository();
    }
}
