<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String msg= "";
if (request.getAttribute("msg") != null){
 msg = (String)request.getAttribute("msg");
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/jejugothic.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Login.css">
</head>
<script>
<%if (msg !=""){out.println("alert('"+msg+"')");}%>
//아이디 비밀번호 유효성 검사
function check(){
	//이름으로 객체찾기
	let id = document.getElementsByName("id");
	let password = document.getElementsByName("password");
/* 	alert(memberId[0].value);
	alert(memberPassword[0].value); */
	
	if (id[0].value ==""){
		alert("아이디를 입력해주세요");
		id[0].focus();
		return;
	}else if (password[0].value ==""){
		alert("비밀번호를 입력해주세요");
		password[0].focus();
		return;
	}
	
	var fm = document.frm;
	fm.action ="<%=request.getContextPath()%>/member/loginAction.do"; 
	fm.method ="post";
	fm.submit();
	
	return;
}
</script>
<body>

   <nav class="breadcrumb">
     <span>홈</span> &gt; <span>회원가입</span>
   </nav>
    <div class="page">
    <%@ include file="/WEB-INF/header1.jsp" %>
        <div class="container">
            <div class="image-container">
                <img src="${pageContext.request.contextPath}/resources/images/login_main.png" alt="로그인 이미지">
            </div>
            <div class="slogan">여행을 즐기는 쉬운 방법<br>LecoTrip</div>
            <div class="login-info">
            <form name="frm" style="display:ruby;">
                <div class="login-id">
                    <div class="input-group">
                        <label class="input-label">아이디</label>
                        <input type="text" name="id" class="input-box" placeholder="아이디를 입력해주세요">
                    </div>
                </div>
                <div class="login-pw">
                    <div class="input-group">
                        <label class="input-label">비밀번호</label>
                        <input type="password" name="password" class="input-box" placeholder="비밀번호를 입력해주세요">
                    </div>
                    <button class="login-btn" type="button" onclick="check();">로그인</button>
                </div>
                </form>
            </div>
            <div class="links" style="margin: 20px;">
                <a href="${pageContext.request.contextPath}/member/memberFind.do" style="color:#A39C9C;">아이디/비밀번호 찾기</a>
            </div>
            <div class="links" style="color:#333;">아직 회원이 아니신가요? <a href="${pageContext.request.contextPath}/member/memberSignUp.do" class="signup">회원가입</a></div>
<%--             <div class="separator-container">
                <div class="separator"></div>
                <div class="separator-text">OR</div>
                <div class="separator"></div>
            </div>
            <div class="social-icons">
                <img src="${pageContext.request.contextPath}/resources/images/login_kakaolink_btn.png" alt="카카오 로그인">
                <img src="${pageContext.request.contextPath}/resources/images/login_naver_btn.png" alt="네이버 로그인">
                <img src="${pageContext.request.contextPath}/resources/images/login_google_btn.png" alt="구글 로그인">
            </div> --%>
        </div>
        <hr>
          <%@ include file="/WEB-INF/footer.jsp" %>
    </div>

</body>
</html>
