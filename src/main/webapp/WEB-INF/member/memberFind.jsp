<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String msg=""; if(request.getAttribute("msg")!=null){
	msg = (String)request.getAttribute("msg");}%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Find.css">
  </head>

  <script>
  <%if(msg !=""){out.println("alert('"+msg+"')");}%>
  
  function findId(){
	  let name = document.getElementById("name").value;
	  let phone = document.getElementById("phone").value;
	  
	  if(name ===""){
		  alert("이름을 입력해주세요");
		  document.getElementById("name").focus();
		  return;
	  }
	  if(phone ===""){
		  alert("연락처를 입력해주세요");
		  document.getElementById("phone").focus();
		  return;
	  }
	  
	    $.ajax({
	        type: "POST",
	        url: "<%=request.getContextPath()%>/member/findIdAction.do",
	        dataType: "json",
	        data: {"name": name, "phone": phone},
	        success: function (result) {
	        	console.log("서버응답: ", result);
	            if (result.found) {
	                openPopup(result.id,result.name);
	            } else {
	                alert("회원정보가 없습니다.");  
	            }
	        },
	        error: function () {
	            alert("전송실패");
	        }
	    });
	}
  function openPopup(id, name) {
	    console.log("팝업 데이터:", id, name); 

	    let popup = window.open("", "popup", "width=450, height=300, resizable=no, scrollbars=no");

	    if (popup) {
	        popup.document.open();
	        popup.document.write(`
	            <!DOCTYPE html>
	            <html lang="ko">
	            <head>
	                <meta charset="UTF-8">
	                <title>아이디 찾기 팝업</title>
	                <style>
	                    body { font-family: 'Jeju Gothic', sans-serif; background-color: #F8F8FF; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
	                    .popup { background: #FFFFFF; width: 400px; padding: 20px; border-radius: 10px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); text-align: center; }
	                    .popup h3 { font-size: 20px; color: #333; margin-bottom: 10px; }
	                    .name-text { font-size: 18px; color: #555; display:flex; justify-content: center; align-items: center; text-align: center; gap: 5px; }
	                    .close-btn { background: none; border: 1px solid #3B8EEF; color: #3B8EEF; padding: 8px 20px; border-radius: 20px; cursor: pointer; }
	                	.name-text {display:flex; text-align:center}
	                </style>
	            </head>
	            <body>
	                <div class="popup">
	                    <h3>아이디 찾기 결과</h3>
	                   <div class="name-text">
	                    <div id="userName"></div> 님의 아이디는 아래와 같습니다.
	                    </div>
	                    <div id="userId" style="font-weight: bold; font-size: 22px; color: #4FA3C4; margin: 15px 0;"></div>
	                    <button style="padding: 5px 15px; border: 1px solid #3B8EEF; background: none; color: #3B8EEF; border-radius: 20px; cursor: pointer;" onclick="window.close()">닫기</button>
	                </div>
	            </body>
	            </html>
	        `);

	        popup.document.close();  

	        popup.onload = function () {
	            popup.document.getElementById("userName").innerText = name;
	            popup.document.getElementById("userId").innerText = id;
	        };
	    } else {
	        alert("전송오류");
	    }
	}
$(document).ready(function () {
	    // 이메일로 임시 비밀번호 전송 (비밀번호 찾기)
	    $(".email-send-btn").click(function () {
	        let id = $("#id").val();
	        let email = $("#email").val();

	        if (id === "") {
	            alert("아이디를 입력해주세요.");
	            return;
	        }

	        if (email === "") {
	            alert("이메일을 입력해주세요.");
	            return;
	        }

	        $.ajax({
	            type: "POST",
	            url: "<%=request.getContextPath()%>/member/findPwAction.do",
	            data: { id: id, email: email },
	            dataType: "json",
	            success: function (response) {
	                console.log("응답:", response);
	                if (response.success) {
	                    alert("임시 비밀번호가 이메일로 발송되었습니다.");
	                } else {
	                    alert(response.message);
	                }
	            },
	            error: function (xhr) {
	                console.error("비밀번호 찾기 오류:", xhr.responseText);
	                alert("비밀번호 찾기 중 오류가 발생했습니다.");
	            }
	        });
	    });
	    $(".find-password-btn").click(function (e) {
	        e.preventDefault();
	        let id = $("#id").val();
	        let tempPassword = $("#auth-code").val(); // 인증번호 입력칸 = 임시 비밀번호
	        if (id === "") {
	            alert("아이디를 입력해주세요.");
	            return;
	        }

	        if (tempPassword === "") {
	            alert("이메일로 받은 임시 비밀번호를 입력해주세요.");
	            return;
	        }

	        $.ajax({
	            type: "POST",
	            url: "<%=request.getContextPath()%>/member/tempLoginAction.do",
	            data: { id: id, password: tempPassword },  // 로그인 액션 호출
	            dataType: "json",
	            success: function (response) {
	                console.log("로그인 응답:", response);
	                if (response.success) {
	                    alert("임시 로그인 성공! 메인 페이지로 이동합니다.");
	                    window.location.href = "<%=request.getContextPath()%>" + response.redirectUrl; // 메인 페이지 이동
	                } else {
	                    alert(response.message); // "임시 비밀번호가 일치하지 않습니다."
	                }
	            },
	            error: function (xhr) {
	                console.error("로그인 오류:", xhr.responseText);
	                alert("로그인 중 오류가 발생했습니다.");
	            }
	        });
	    });
	});

  </script>
  <body>
   <%@ include file="/WEB-INF/header1.jsp" %>
   <nav class="breadcrumb"> 
     <span>홈</span> &gt; <span>아이디/비밀번호 찾기</span>
   </nav>
    
    <main class="main-container">
      <div class="content">
        <!-- 왼쪽: 아이디 / 비밀번호 찾기 -->
        <section class="form-section">
          <!-- 아이디 찾기 -->
          <h1 class="section-title1">
            <span class="underline">아이디 찾기</span>
          </h1>
          <form name="frm">
            <div class="form-group-wrapper group-1">
              <!-- 이름 -->
              <div class="form-group">
                <label for="name" >이름</label>
                <input
                  type="text"
                  id="name"
                  placeholder="회원정보상 입력된 이름을 입력해주세요"
                />
              </div>
              <!-- 연락처 -->
              <div class="form-group">
                <label for="contact">연락처</label>
                <input
                  type="text"
                  id="phone"
                  placeholder="회원정보상 입력된 연락처를 입력해주세요"
                />
              </div>
            </div>
           <button type="button" onclick="findId();" class="find-id-btn">아이디 찾기</button>
          </form>

          <!-- 비밀번호 찾기 -->
          <h1 class="section-title2">
            <span class="underline">비밀번호 찾기</span>
          </h1>
          <form>
            <div class="form-group-wrapper group-2">
              <!-- 아이디 -->
              <div class="form-group">
                <label for="id">아이디</label>
                <input
                  type="text"
                  id="id"
                  placeholder="아이디를 입력해주세요"
                />
              </div>
              <!-- 이메일 -->
              <div class="form-group email-group">
                <label for="email">이메일</label>
                <div class="input-btn-group">
                  <input
                    type="email"
                    id="email"
                    placeholder="이메일을 입력해주세요"
                  />
                  <button type="button" class="email-send-btn">
                    이메일 발송
                  </button>
                </div>
              </div>
              <!-- 인증번호 -->
              <div class="form-group">
                <label for="auth-code">인증번호</label>
                <input
                  type="password"
                  id="auth-code"
                  placeholder="인증번호를 입력해주세요"
                />
              </div>
            </div>
            <div class="button-group">
              <button type="submit" class="find-password-btn">
                비밀번호 찾기
              </button>
              <button type="button" class="cancel-btn" onclick="history.back();">취소</button>
            </div>
          </form>
        </section>

        <!-- 오른쪽: 이미지 -->
        <section class="image-section">
          <img
            src="https://drive.google.com/thumbnail?id=1k82Hp-4GV3C6FR94fqjsIAS5tJTb6W7p"
            alt="이미지"
            class="thumbnail-img"
          />
        </section>

        <!-- 리본, 깃털, 수풀 이미지 컨테이너 -->
        <div class="image-container">
          <img
            src="https://drive.google.com/thumbnail?id=1adqmv3G7bY8NZ2CbCv9hcpl-wk_ak2wm"
            alt="리본 이미지"
            class="ribbon-img"
          />
          <img
            src="https://drive.google.com/thumbnail?id=1nNeFQETVQaj1Yd6QWKuqMjFYLC9oGs0e"
            alt="깃털 이미지"
            class="feather-img"
          />
          <img
            src="https://drive.google.com/thumbnail?id=17JAxZ7KmoLVQb_07R5RCpHppsiI7LSvC"
            alt="수풀 이미지"
            class="grass-img"
          />
        </div>
      </div>
    </main>
    
	<%@ include file="/WEB-INF/footer.jsp" %>
  </body>
</html>
