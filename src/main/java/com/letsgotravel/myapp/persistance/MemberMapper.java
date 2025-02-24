package com.letsgotravel.myapp.persistance;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberMapper {
	public int memberInsert(MemberVo mv); //회원가입
	public int idCheck(String id); // 아이디 체크
	public int nicknameCheck(String nickname); //닉네임 체크
	public MemberVo LoginCheck(String id); // 로그인 체크
	public MemberVo findId(@Param("name")String name, @Param("phone")String phone); // session에 담기위한 find
	public int changePassword(@Param("id") String id, @Param("password") String password); // mypage - 비밀번호 변경
    public int updateUserProfile(Map<String, Object> updateParams); // mypage -회원정보 변경
    public int updateDelyn(@Param("id") String id, @Param("delyn") String delyn); // 회원정보삭제
    public MemberVo findByEmail(@Param("id") String id, @Param("email") String email); // 비밀번호찾기 - 이메일연동
    public void tempPassword(MemberVo mv);//임시비밀번호 입력

}
