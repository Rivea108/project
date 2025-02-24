package com.letsgotravel.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.service.MemberService;
import com.letsgotravel.myapp.util.UserIp;

@Controller
@RequestMapping(value="/member/")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired(required=false)
	private UserIp userip;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "memberFind.do")
	public String memberFind() {
		logger.info("memberFind들어옴");
		return "WEB-INF/member/memberFind";
	}
	
	@RequestMapping(value = "findIdAction.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject findId(@RequestParam("name") String name, @RequestParam("phone") String phone) {
		//logger.info("findIdAction 들어옴");
	    //logger.info("이름: " + name + ", 연락처: " + phone);
	    MemberVo mv = memberService.findId(name, phone);  // 
		JSONObject obj = new JSONObject();
		
		  if (mv != null) {
		      obj.put("found", true);   
		      obj.put("id", mv.getId());   
		      obj.put("name", mv.getName());
		      logger.info("id,name: " + mv.getId() + mv.getName());
		  } else {
		      obj.put("found", false);
		  }

		  return obj;
	}

	
	@RequestMapping(value = "memberLogin.do")
	public String memberLogin() {
		logger.info("member로그인 들어옴");
		return "WEB-INF/member/memberLogin";
	}
	
	@RequestMapping(value = "loginAction.do", method = RequestMethod.POST)
	public String LoginAction(@RequestParam("id") String id, @RequestParam("password") String password,
			RedirectAttributes rttr, HttpSession session) {
			System.out.println("아이디" + id);
			System.out.println("비번" + password);
			  MemberVo mv = memberService.LoginCheck(id);

			    if (mv == null) { // 회원 정보가 없을 때
			        rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요.");
			        return "redirect:/member/memberLogin.do";
			    }

			    if ("Y".equals(mv.getDelyn())) { 
			        rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요.");
			        return "redirect:/member/memberLogin.do";
			    }

			    String reservedPassword = mv.getPassword();

			    if (bCryptPasswordEncoder.matches(password, reservedPassword)) {
			        System.out.println("비밀번호 일치");

			        session.setAttribute("midx", mv.getMidx());
			        session.setAttribute("memberId", mv.getId());
			        session.setAttribute("memberNickName", mv.getNickname());
			        session.setAttribute("memberEmail", mv.getEmail());
			        session.setAttribute("memberPhone", mv.getPhone());

			        logger.info("로그인 성공 midx 번호: " + mv.getMidx());

			        if (session.getAttribute("saveUrl") != null) {
			            return "redirect:" + session.getAttribute("saveUrl").toString();
			        } else {
			            return "redirect:/";
			        }

			    } else { 
			        rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요.");
			        return "redirect:/member/memberLogin.do";
			    }
		
	}
	
	@RequestMapping(value = "Logout.do", method = RequestMethod.GET)
	public String Logout(HttpSession session) {

		session.removeAttribute("midx");
		session.removeAttribute("nickname");
		session.removeAttribute("id");
		session.invalidate(); // 세션값 초기화

		return "redirect:/";
	}
	
	@RequestMapping(value = "memberSignUp.do")
	public String memberSignUp() {
		logger.info("memberSignUp들어옴");
		return "WEB-INF/member/memberSignUp";
	}
	
	@RequestMapping(value = "signupAction.do", method = RequestMethod.POST)
	public String signupAction(MemberVo mv) {
		//logger.info("signupAction들어옴");
		//logger.info("bCryptPasswordEncoder==>" + bCryptPasswordEncoder);

		String password_enc = bCryptPasswordEncoder.encode(mv.getPassword());
		mv.setPassword(password_enc);

		int value = memberService.memberInsert(mv);
		logger.info("value:" + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/";
		} else if (value == 0) {
			path = "redirect:/member/memberSignup.do";
		}
		return path;
	}
	
	@ResponseBody
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public JSONObject IdCheck(@RequestParam("id") String id) {
		
		int cnt = memberService.idCheck(id);

		JSONObject obj = new JSONObject();
		
		obj.put("cnt", cnt);
		logger.info("cnt" + cnt);

		return obj;
	}
	
	@ResponseBody
	@RequestMapping(value = "nicknameCheck.do", method = RequestMethod.POST)
	public JSONObject NicknameCheck(@RequestParam("nickname") String nickname) {
		
		int cnt = memberService.nicknameCheck(nickname);

		JSONObject obj = new JSONObject();
		
		obj.put("cnt", cnt);
		logger.info("cnt" + cnt);

		return obj;
	}
	
	@RequestMapping(value = "memberMypage.do")
	public String memberMypage(HttpSession session) {
		logger.info("memberMyPage들어옴");
		
	    String id = (String) session.getAttribute("memberId");
	    if (id != null) {
	        MemberVo mv = memberService.LoginCheck(id);
		
        if (mv != null) {
            session.setAttribute("midx", mv.getMidx());
            session.setAttribute("nickname", mv.getNickname());
            session.setAttribute("phone", mv.getPhone());
            session.setAttribute("email", mv.getEmail());

            logger.info("마이페이지 회원번호: " + mv.getMidx());
        } else {
            logger.warn("회원 정보를 찾을 수 없습니다.");
        }
    } else {
        logger.warn("로그인 정보가 없습니다.");
        return "redirect:/member/memberLogin.do"; 
    }
    return "WEB-INF/member/memberMypage";
	}
	
	@RequestMapping(value = "deleteAccount.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAccount(HttpSession session) {
	    Map<String, Object> response = new HashMap<>();
	    String id = (String) session.getAttribute("memberId");

	    if (id == null) {
	        response.put("success", false);
	        response.put("message", "로그인이 필요합니다.");
	        return response;
	    }

	    boolean isDeleted = memberService.deleteAccount(id);

	    if (isDeleted) {
	        session.invalidate(); // 세션 만료 (로그아웃)
	        response.put("success", true);
	    } else {
	        response.put("success", false);
	        response.put("message", "회원 탈퇴에 실패했습니다.");
	    }

	    return response;
	}
	
	@RequestMapping(value = "updateProfile.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProfile(
	        @RequestParam(value = "nickname", required = false) String nickname,
	        @RequestParam(value = "email", required = false) String email,
	        @RequestParam(value = "phone", required = false) String phone,
	        @RequestParam(value = "currentPassword", required = true) String currentPassword,
	        HttpSession session) {
	    //ogger.info("회원정보 변경 컨트롤러 들어옴");
	    //System.out.println("닉네임: " + nickname);
	    //System.out.println("이메일: " + email);
	    //System.out.println("전화번호: " + phone);
	    //System.out.println("현재 비밀번호: " + currentPassword);

	    Map<String, Object> response = new HashMap<>();
	    String id = (String) session.getAttribute("memberId");

	    if (id == null) {
	        response.put("success", false);
	        response.put("message", "로그인이 필요합니다.");
	        response.put("redirectUrl", "/member/memberLogin.do");
	        return response;
	    }

	    MemberVo mv = memberService.LoginCheck(id);

	    if (mv == null || !bCryptPasswordEncoder.matches(currentPassword, mv.getPassword())) {
	        response.put("success", false);
	        response.put("message", "현재 비밀번호가 일치하지 않습니다.");
	        return response;
	    }

	    if ((nickname == null || nickname.isEmpty()) && (email == null || email.isEmpty()) && (phone == null || phone.isEmpty()) && (currentPassword == null || currentPassword.isEmpty())) {
	        response.put("success", false);
	        response.put("message", "변경할 정보가 없습니다.");
	        return response;
	    }
	        boolean isUpdated = memberService.updateProfile(id, nickname, email, phone);

	        if (isUpdated) {
	            if (nickname != null && !nickname.isEmpty()) session.setAttribute("nickname", nickname);
	            if (email != null && !email.isEmpty()) session.setAttribute("email", email);
	            if (phone != null && !phone.isEmpty()) session.setAttribute("phone", phone);
	        }

	        response.put("success", isUpdated);
	        response.put("message", isUpdated ? "회원 정보가 변경되었습니다." : "회원 정보 변경에 실패했습니다.");

	        return response;
	    }


	
	@RequestMapping(value = "changePassword.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePassword(
	        @RequestParam(value = "currentPassword", required = false) String currentPassword,
	        @RequestParam(value = "newPassword", required = false) String newPassword,
	        HttpSession session) {
	    logger.info("비밀번호변경 컨트롤러 들어옴");


	    Map<String, Object> response = new HashMap<>();
	    String id = (String) session.getAttribute("memberId");

	    if (id == null) {
	        response.put("success", false);
	        response.put("message", "로그인이 필요합니다.");
	        response.put("redirectUrl", "/member/memberLogin.do");
	        return response;
	    }
	    //logger.info("아이디입니다 : "+id);

	    MemberVo mv = memberService.LoginCheck(id);
	    if (mv == null || !bCryptPasswordEncoder.matches(currentPassword, mv.getPassword())) {
	        response.put("success", false);
	        response.put("message", "현재 비밀번호가 일치하지 않습니다.");
	        return response;
	    }

	    String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
	    boolean isPasswordChanged = memberService.changePassword(id, encryptedPassword);
	    
	    if (isPasswordChanged) {
	        session.invalidate();
	    }

	    response.put("success", isPasswordChanged);
	    response.put("message", isPasswordChanged ? "비밀번호가 변경되었습니다. 다시 로그인해주세요." : "비밀번호 변경에 실패했습니다.");
	    return response;
	}
	
	@PostMapping("findPwAction.do")
	@ResponseBody
	public Map<String, Object> findPw(@RequestParam("id") String id,@RequestParam("email") String email){
		Map<String, Object> response = new HashMap<>();
		
        try {
            String result = memberService.findPw(id, email);
            if ("Success".equals(result)) {
                response.put("success", true);
                response.put("message", "임시 비밀번호가 이메일로 발송되었습니다.");
                System.out.println("비밀번호 찾기 요청: ID=" + id + ", EMAIL=" + email);
            } else {
                response.put("success", false);
                response.put("message", result); // "이메일이 존재하지 않습니다." 또는 "등록된 이메일 주소가 다릅니다."
            }
            
        } catch (MessagingException e) {
            response.put("success", false);
            response.put("message", "이메일 발송 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return response;
    }
	
	@RequestMapping(value = "tempLoginAction.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> tempLoginAction(
			@RequestParam("id") String id, 
			@RequestParam("password") String password,
	        HttpSession session) {
	    Map<String, Object> response = new HashMap<>();

	    System.out.println("임시 로그인 시도 - 아이디: " + id + ", 입력한 비밀번호: " + password);

	    MemberVo mv = memberService.LoginCheck(id);

	    if (mv == null) {
	        response.put("success", false);
	        response.put("message", "아이디가 존재하지 않습니다.");
	        return response;
	    }

	    if ("Y".equals(mv.getDelyn())) { 
	        response.put("success", false);
	        response.put("message", "해당 계정은 비활성화되었습니다.");
	        return response;
	    }

	    String storedPassword = mv.getPassword();
	    System.out.println("DB 저장된 암호화된 비밀번호: " + storedPassword);

	    if (bCryptPasswordEncoder.matches(password, storedPassword)) { 
	        System.out.println("임시 로그인 성공");

	        // 세션에 로그인 정보 저장
	        session.setAttribute("midx", mv.getMidx());
	        session.setAttribute("memberId", mv.getId());
	        session.setAttribute("memberNickName", mv.getNickname());
	        session.setAttribute("memberEmail", mv.getEmail());
	        session.setAttribute("memberPhone", mv.getPhone());

	        response.put("success", true);
	        response.put("redirectUrl", "/main.do"); // 로그인 후 이동할 URL
	        return response;
	    } else { 
	        response.put("success", false);
	        response.put("message", "임시 비밀번호가 일치하지 않습니다.");
	        return response;
	    }
	}
	
}
