package com.letsgotravel.myapp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.persistance.MemberMapper;
import com.letsgotravel.myapp.util.MailUtil;


@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mm;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private MailUtil mailUtil;
    
    public MemberServiceImpl(MemberMapper mm, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mm = mm;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	@Autowired
	public MemberServiceImpl(SqlSession sqlSession) {
		this.mm = sqlSession.getMapper(MemberMapper.class);
	}
	
	@Override
	public int memberInsert(MemberVo mv) {
		int value = mm.memberInsert(mv);
		
		return value;
	}
	@Override
	public int idCheck(String id) {
			int value = mm.idCheck(id);		
		return value;
	}

	@Override
	public int nicknameCheck(String nickname) {
		int value = mm.nicknameCheck(nickname);
		return value;
	}

	@Override
	public MemberVo LoginCheck(String id) {
		MemberVo mv = mm.LoginCheck(id);
		
		return mv;
	}

	@Override
	public MemberVo findId(String name, String phone) {
		MemberVo mv = mm.findId(name,phone);
		return mv;
	}  

	@Override
	public boolean updateProfile(String id, String nickname, String email, String phone) {
	    Map<String, Object> updateParams = new HashMap<>();
	    updateParams.put("id", id);

	    // 변경할 값이 하나라도 있어야만 UPDATE 실행
	    if (nickname != null && !nickname.trim().isEmpty()) updateParams.put("nickname", nickname);
	    if (email != null && !email.trim().isEmpty()) updateParams.put("email", email);
	    if (phone != null && !phone.trim().isEmpty()) updateParams.put("phone", phone);

	    if (updateParams.size() > 1) {  // id 외에 변경할 값이 있을 경우에만 실행
	        return mm.updateUserProfile(updateParams) > 0;
	    } else {
	        return false;  // 변경할 값이 없는 경우 false 반환
	    }
	}
    
    @Override
    public boolean changePassword(String id, String encryptedPassword) {
        return mm.changePassword(id, encryptedPassword) > 0;
    }
	
	@Override
	public boolean deleteAccount(String id) {
	    int result = mm.updateDelyn(id, "Y"); 
	    return result > 0;
	}
	
	@Override
	public String findPw(String id,String email) {
	    System.out.println("findPw 실행 - 입력된 ID: " + id + ", EMAIL: " + email);
		MemberVo mv = mm.findByEmail(id, email);
		
		if(mv != null) {
			String tempPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
			System.out.println("임시비밀번호"+tempPassword);
			try {
				mailUtil.sendMail(mv, tempPassword);
			} catch (MessagingException e) {
				e.printStackTrace();
				return "이메일 발송 중 오류가발생하였습니다.";
			} 
			
			String encryptedPassword = bCryptPasswordEncoder.encode(tempPassword);
	        System.out.println("암호화된 비밀번호: " + encryptedPassword);

			mv.setPassword(encryptedPassword);
			mm.tempPassword(mv);
			
			return "임시 비밀번호가 이메일로 방송되었습니다.";
		}else
	        System.out.println("입력한 아이디와 이메일이 일치하지 않음: ID=" + id + ", EMAIL=" + email);
	        return "등록된 이메일 주소가 다릅니다."; // 아이디와 이메일이 일치하지 않는 경우
	}
}
