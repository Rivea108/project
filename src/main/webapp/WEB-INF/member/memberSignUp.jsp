<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 페이지</title>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
       <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SignUp.css">
       <script src="https://code.jquery.com/jquery-latest.min.js"></script>
       <script>
       const email = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;
       
       function check(){

    		//유효성 검사하기	
    		var sc = document.signupcheck;	
    		
    		if (sc.id.value ==""){
    			alert("아이디를 입력해주세요");
    			sc.id.focus();
    			return;
    		}else if (sc.password.value ==""){		
    			alert("비밀번호를 입력해주세요");
    			sc.password.focus();
    			return;
    		}else if (sc.password2.value ==""){
    			alert("비밀번호2를 입력해주세요");
    			sc.password2.focus();
    			return;
    		}else if (sc.password.value != sc.password2.value){
    			alert("비밀번호가 일치하지 않습니다.");
    			sc.password2.value="";
    			sc.password2.focus();
    			return;
    		}else if (sc.nickname.value ==""){
    			alert("닉네임을 입력해주세요");
    			sc.nickname.focus();
    			return;
    		}else if (sc.name.value ==""){
    			alert("이름을 입력해주세요");
    			sc.name.focus();
    			return;
    		}else if (sc.phone.value ==""){
    			alert("연락처를 입력해주세요");
    			sc.phone.focus();
    			return;
    		}else if (sc.email.value ==""){
    			alert("이메일을 입력해주세요");
    			sc.email.focus();
    			return;
    		}else if (email.test(sc.email.value)== false){
    			alert("이메일 형식이 올바르지 않습니다.");
    			sc.email.value="";
    			sc.email.focus();
    			return;
    		}else if (sc.btn-id.value=="N"){
    			alert("아이디 중복체크를 해주세요");
    			sc.id.focus();
    			return;		 	
			}else if (sc.btn-nickname.value=="N"){
    			alert("닉네임 중복체크를 해주세요");
    			sc.nickname.focus();
    			return;		
    		} 	
    		
    		var ans = confirm("저장하시겠습니까?");
    		
    		if (ans == true){
    			sc.action="<%=request.getContextPath()%>/member/signupAction.do";
    			sc.method="post"; 
    			sc.submit();
    		}
    		return;
    	}
    	  
    	$(document).ready(function(){
    		
    		$("#btn-id").click(function(){
    			//alert("중복체크버튼 클릭");	
    			let id = $("#id").val();
    			if (id ==""){
    				alert("아이디를 입력해주세요");
    				return;
    			}
    	    			$.ajax({
    	    				type :  "post",    //전송방식
    	    				url : "<%=request.getContextPath()%>/member/idCheck.do",
    					dataType : "json", // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
    					data : {"id" : id},
    					success : function(result) { //결과가 넘어와서 성공했을 받는 영역
    		                console.log("서버 응답:", result);  // 디버깅용 로그 추가
    						if (result.cnt == 0) {
    							alert("사용할수 있는 아이디입니다.");
    							$("#btn-id").val("Y");
    						} else {
    							alert("사용할수 없는 아이디입니다.");
    							$("#id").val(""); 
    						}
    					},
    					error : function() { 					
    						alert("전송실패");
    					}
    				});
    			});
    		
    		 $("#btn-nickname").click(function() {
    		        let nickname = $("#nickname").val();
    		        if (nickname === "") {
    		            alert("닉네임을 입력해주세요");
    		            return;
    		        }

    		        $.ajax({
    		            type: "POST",
    		            url: "<%=request.getContextPath()%>/member/nicknameCheck.do",
    		            dataType: "json",
    		            data: { "nickname": nickname },
    		            success: function(result) {
    		                console.log("닉네임 중복 확인 결과:", result);
    		                if (result.cnt === 0) {
    		                    alert("사용할 수 있는 닉네임입니다.");
    		                    $("#btn-nickname").val("Y");
    		                } else {
    		                    alert("사용할 수 없는 닉네임입니다.");
    		                    $("#nickname").val("");
    		                }
    		            },
    		            error: function() {
    		                alert("닉네임 중복 확인 전송 실패");
    		            }
    		        });
    		    });
    		});
    	</script>
</head>
<body>
   <nav class="breadcrumb">
     <span>홈</span> &gt; <span>아이디/비밀번호 찾기</span>
   </nav>
    <div class="page">
   <%@ include file="/WEB-INF/header1.jsp" %>
    <div class="page-container">
   <form name="signupcheck">
        <div class="container">
        <div class="form-container">
            <h2>LecoTrip 회원 가입</h2>
            <div class="input-group">
                <label class="input-label" for="id">아이디</label>
                <input type="text" class="input-box short" id="id" name="id" value="" placeholder="아이디를 입력해주세요">
                <button class="check-btn" id="btn-id" name="btn-id" value="N" type="button">중복확인</button>
            </div>
            <div class="input-group">
                <label class="input-label" for="password">비밀번호</label>
                <input type="password" class="input-box long" id="password" name="password" placeholder="비밀번호를 입력해주세요">
            </div>
            <div class="input-group">
                <label class="input-label" for="password2">비밀번호 확인</label>
                <input type="password" class="input-box long" id="password2" name="password2" placeholder="비밀번호를 입력해주세요">
            </div>
            <div class="input-group">
                <label class="input-label" for="nickname">닉네임</label>
                <input type="text" class="input-box short" id="nickname" name="nickname"  placeholder="닉네임을 입력해주세요">
                <button class="check-btn" id="btn-nickname" name="btn-nickname" value="N" type="button">중복확인</button>
            </div>
            <div class="input-group">
                <label class="input-label" for="name">이름</label>
                <input type="text" class="input-box long" id="name" name="name" placeholder="이름을 입력해주세요">
            </div>
            <div class="input-group">
                <label class="input-label" for="phone">연락처</label>
                <input type="text" class="input-box long" id="phone" name="phone" placeholder="연락처를 입력해주세요">
            </div>
            <div class="input-group">
                <label class="input-label" for="email">이메일</label>
                <input type="email" class="input-box long" id="email" name="email" placeholder="이메일을 입력해주세요">
            </div>
            <div class="btn-group">
                <button class="btn btn-primary" onclick="check();" type="button">회원가입</button>
                <button class="btn btn-secondary" onclick="window.location.href='${pageContext.request.contextPath}/member/memberLogin.do'">취소</button>
            </div>
        </div>
        <div class="image-banner"></div>
    </div>
    </form>
        <div class="image-container">
            <img src="${pageContext.request.contextPath}/resources/images/Signup_star1.png" alt="추가 이미지" style="position: absolute; top: -65px; left: 400px; width: 120px; height: 100px; transform:rotate(0deg);">
    		<img src="${pageContext.request.contextPath}/resources/images/Signup_water1.png" alt="추가 이미지" style="position: absolute; top: -70px; right: -80px; width: 100px; height: 90px; transform:rotate(20deg);">
    		<img src="${pageContext.request.contextPath}/resources/images/Signup_ribbon1.png" alt="추가 이미지" style="position: absolute; bottom: -20px; left: -90px; width: 200px; height: 100px; transform:rotate(40deg);">
    		<img src="${pageContext.request.contextPath}/resources/images/Signup_hat1.png" alt="추가 이미지" style="position: absolute; bottom: 35px; left: 160px; width: 80px; height: 60px; transform:rotate(-5deg);">
        </div>
   	</div>
<%@ include file="/WEB-INF/footer.jsp" %>
</div>
</body>
</html>