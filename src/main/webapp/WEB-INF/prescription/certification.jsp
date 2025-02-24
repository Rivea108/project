<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>본인인증</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/auth.css">
  <!-- jQuery를 CDN에서 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  
</head>
<body>
   <%@ include file="/WEB-INF/header1.jsp" %>
   
  <nav class="breadcrumb">
    <span>홈</span> &gt; <span>처방전</span> &gt; <span>본인인증</span>
  </nav>
   
   <h1 class="auth">본인인증</h1>
  <main class="authmain">
  <!-- 본인인증 폼 추가 -->
    <form id="certification-form" action="${pageContext.request.contextPath}/prescription/processCertification.do" method="post">
    <!-- Step 1 -->
  <div class="identy">
    <div id="step-1" class="step step-1">
      <label for="id-front">주민번호</label>
      <div class="id-number">
        <input type="text" id="id-number-front" name="idNumberFront" class="id-front" placeholder="주민번호 앞자리" maxlength="6">
        <p class="hyphen">⎯</p>
        <input type="password" id="id-number-back" name="idNumberBack" class="id-back" placeholder="주민번호 뒷자리" maxlength="7">
      </div>
      <label for="name">이름</label>
      <input type="text" id="name" name="name" class="name" placeholder="이름입력">
    </div>
  </div>

    <!-- Step 2 -->
    <div id="step-2" class="step hidden step-2">
      <label for="phone-number">전화번호</label>
      <select id="telecom" name="telecom" class="tel-company" required>
        <option value="0">SKT</option>
        <option value="1">KT</option>
        <option value="2">U+</option>
        <option value="3">알뜰(SKT)</option>
        <option value="4">알뜰(KT)</option>
        <option value="5">알뜰(U+)</option>
      </select>
      
      <input type="text" id="phoneNumber" class="sms-code" name="phoneNumber" placeholder="전화번호 입력 -없이 입력해주세요">
    </div>

    <!-- Step 3 -->
    <div id="step-3" class="step hidden">
     <label for="secureNo" class="captcha">보안 문자</label>
      <div class="captcha-container">
       <img id="secureNoImage" src="" alt="보안 문자 이미지">
        <button type="button" class="reflash" id="refresh-secureNo"><img src="${pageContext.request.contextPath}/resources/images/capcha_reload.png" class="reflash-img"></button>
        <input type="text" id="captcha" class="captcha-input" placeholder="보안문자 입력">
      </div>
      <button type="button" class="check-captcha" id="secure-submit">확인</button>
    </div>

    <!-- Step 4 -->
    <div id="step-4" class="step hidden step-4">
      <label for="sms-auth-number">SMS 인증번호</label>
      <input type="text" id="sms-auth-number" class ="sms-code" placeholder="인증번호 입력">
      <button class="verify-sms" type="button" id="verify-sms" style ="padding : 0">SMS 인증 확인</button>
      <p class="auth-text-title">인증번호 문자를 못 받으셨나요?</p>

      <p class="auth-text-body">•입력하신 인증정보가 일치하지 않을 경우, 인증번호 문자는 발송 되지 않습니다.</p>
        
      <p class="auth-text-body">•인증번호가 문자로 수신되지 않을 경우 정확한 정보로 재시도 해 주시기 바랍니다.</p>
    </div>
    <button type="submit" id="submit-form" class="hidden">제출</button>
     </form>
  </main>
   <!-- Include GSAP and Plugins -->
   <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/gsap.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/ScrollTrigger.min.js"></script>
   <script src="https://unpkg.com/lenis@1.1.14/dist/lenis.min.js"></script>
   
   <%@ include file="/WEB-INF/loadingImage.jsp" %>     
   <!-- 풋터 -->
   <%@ include file="/WEB-INF/footer.jsp" %>
   <script>
   var contextPath = "${pageContext.request.contextPath}";
   console.log("Context Path:", contextPath); // 디버깅용
</script>
   
  <script src="${pageContext.request.contextPath}/resources/js/auth.js"></script>
  
  
 
</body>
</html>
