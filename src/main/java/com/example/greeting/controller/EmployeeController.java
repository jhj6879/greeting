package com.example.greeting.controller;

import com.example.greeting.dto.EmployeeDto;
import com.example.greeting.dto.PostDto;
import com.example.greeting.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 사원관리
    @GetMapping("/employee")
    public String Employee(Model model){
        List<EmployeeDto> list = employeeService.selectEmployeeList();
        model.addAttribute("list", list);
        return "employee";
    }

    // 부서관리
    @GetMapping("/department")
    public String Department(){
        return "department";
    }

    // 직책관리
    @GetMapping("/position")
    public String Position(){
        return "position";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    //사용자 로그인 후 회원정보 요청
//    @PreAuthorize("isAuthenticated()")//로그인되지 않은 사용자가 회원정보를 선택했을 때 로그인할 수 있도록 하는 어노테이션 - 로그인이 필요한 기능들에
//    @GetMapping("/member")
//    public ModelAndView getMemberInfo(Principal principal) { //세션에 기록된 userid를 가져옴
//        ModelAndView mav = new ModelAndView("member"); //모델과 뷰를 한꺼번에 제어하는 클래스 1)뷰를 넘겨줌
//        MemberDto dto = new MemberDto();
//        dto = memberService.getMemberInfo(principal.getName());
//        mav.addObject("member", dto);
//
//        //게시판 메뉴
////		List<BoardDto> menu = service.getBoardMenu();
////		mav.addObject("menu", menu);
//        return mav;
//    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String JoinPage(){
        return "join";
    }

    // 회원가입 정보 DB로 넘기기
    @PostMapping("/join") //DB에 저장
    public String joinAply(EmployeeDto dto) {
        employeeService.create(dto);
        return "login";
    }

    @GetMapping("/checkid")
    @ResponseBody
    public String checkId(@RequestParam(value="data") String user_id) {
        return String.valueOf(employeeService.memberId(user_id));
    }

}