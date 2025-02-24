package com.letsgotravel.myapp.service;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.RequestParam;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberService {
	public int memberInsert(MemberVo mv);
	public int idCheck(String id);
	public int nicknameCheck(String nickname);
	public MemberVo findId(String name, String phone);
	public MemberVo LoginCheck(String id);
	public boolean updateProfile(String id, String nickname, String email, String phone);
	public boolean changePassword(String id, String encrptedPassword);
	public boolean deleteAccount(String id);
	public String findPw(String id, String email) throws MessagingException;
}
