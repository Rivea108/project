<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String nickname = (String) session.getAttribute("nickname");
    String phone = (String) session.getAttribute("phone");
    String email = (String) session.getAttribute("email");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 변경</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/jejugothic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/MyPage.css">
     <script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function() {
    $(".update").click(function() {
        let currentPassword = $("#currentPassword").val();
        let newPassword = $("#newPassword").val();
        let confirmPassword = $("#confirmPassword").val();
        let nickname = $("#nickname").val();
        let phone = $("#phone").val();
        let email = $("#email").val();

        if (nickname === "" && phone === "" && email === "" && newPassword === "") {
            alert("변경할 정보가 없습니다.");
            return;
        }

        if (newPassword !== "" && currentPassword === "") {
            alert("현재 비밀번호를 입력해주세요.");
            $("#currentPassword").focus();
            return;
        }

        if (newPassword !== "" && newPassword !== confirmPassword) {
            alert("비밀번호가 일치하지 않습니다.");
            $("#confirmPassword").val("");
            $("#confirmPassword").focus();
            return;
        }

        let updateUserInfo = {
            nickname: nickname,
            phone: phone,
            email: email,
            currentPassword: currentPassword
        };

        let updatePasswordInfo = {
            currentPassword: currentPassword,
            newPassword: newPassword
        };

        let hasProfileUpdate = nickname !== "" || phone !== "" || email !== "";
        let hasPasswordChange = newPassword !== "";

        if (hasProfileUpdate) {
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/member/updateProfile.do",
                dataType: "json",
                data: updateUserInfo,
                success: function(result) {
                    if (!result.success) {
                        alert(result.message);
                    } else {
                        alert("회원 정보가 수정되었습니다.");
                        location.reload();
                    }
                },
                error: function () {
                    alert("회원 정보 변경 중 오류가 발생했습니다.");
                }
            });
        }

        if (hasPasswordChange) {
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/member/changePassword.do",
                dataType: "json",
                data: updatePasswordInfo,
                success: function(result) {
                    if (result.success) {
                        alert("비밀번호가 변경되었습니다. 다시 로그인해주세요.");
                        window.location.href = "<%=request.getContextPath()%>/member/memberLogin.do";
                    } else {
                        alert(result.message);
                    }
                },
                error: function () {
                    alert("비밀번호 변경 중 오류가 발생했습니다.");
                }
            });
        }
    });
});


$(document).ready(function() {
    $(".delete").click(function() {
        if (!confirm("정말 탈퇴하시겠습니까?")) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath()%>/member/deleteAccount.do",
            dataType: "json",
            success: function(result) {
                if (result.success) {
                    alert("회원 탈퇴가 완료되었습니다.");
                    window.location.href = "<%=request.getContextPath()%>/member/memberLogin.do"; // 로그인 페이지로 리다이렉트
                } else {
                    alert(result.message);
                }
            },
            error: function() {
                alert("회원 탈퇴 요청 중 오류가 발생했습니다.");
            }
        });
    });
});


$(document).ready(function(){
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
    <div class="container">
        <div class="banner">
            <img class="icon" src="${pageContext.request.contextPath}/resources/images/Mypage_icon.png" alt="">
        </div>
        <div class="form">
            <h2>회원 정보 변경</h2>
        <div class="password-change">
            <label>비밀번호 변경</label>
            <input type="password" id="newPassword" name="newPassword" placeholder="비밀번호를 입력해주세요">
            <label>비밀번호 확인</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 다시한번 입력해주세요">
        </div>
        <div class="profile-modify">
            <label for="nickname">닉네임 변경</label>
            <div style="display: flex; align-items: center; justify-content: center;">
                <input type="text" class="short-input" id="nickname" name="nickname" placeholder="<%= nickname %>">
                <button class="check-btn" id="btn-nickname" name="btn-nickname" value="N" type="button">중복확인</button>
            </div>
            <label>연락처 변경</label>
            <input type="text" id="phone" name="phone" placeholder="<%= phone %>">
            <label>이메일 변경</label>
            <input type="email" id="email" name="email" placeholder="<%= email %>">
            <label>현재 비밀번호</label>
            <input type="password" id="currentPassword" name="currentPassword" placeholder="회원 정보를 변경 하려면 현재 비밀번호를 입력해주세요">
        </div>
        </div>
        <div class="buttons">
            <div class="update-back">
                <button class="update">수정</button>
                <button class="back" type="button" onclick="history.back();">취소</button>
            </div>
            <button class="delete">회원탈퇴</button>
        </div>
        <div class="image-container">
            <img src="${pageContext.request.contextPath}/resources/images/Mypage_blue.png" alt="추가 이미지" style="position: absolute; top: 130px; right: -70px; width: 190px; height: 190px; transform:rotate(20deg);">
            <img src="${pageContext.request.contextPath}/resources/images/Mypage_green.png" alt="추가 이미지" style="position: absolute; top: -50px; left: -80px; width: 150px; height: 150px; transform:rotate(20deg);">
            <img src="${pageContext.request.contextPath}/resources/images/Mypage_pink.png" alt="추가 이미지" style="position: absolute; bottom: -50px; left: -100px; width: 180px; height: 180px; transform:rotate(40deg);">
        </div> 
      </div>
      <%@ include file="/WEB-INF/footer.jsp" %>
    </div>

</body>
</html>