<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <script>
	// 메세지
	const msg = "${requestScope.msg}";
	if (msg != null && msg != "") {
	 alert(msg);
	}
	</script>
	
	
    <header class="header">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png" class="logo" alt="LOGOTRIP Logo">
        </a>
        <nav id="side-menu" class="side-menu <c:if test='${not empty sessionScope.midx}'>logged-in</c:if>">
           <ul>
           <c:choose>
            <c:when test="${not empty sessionScope.midx}">
                <!-- 로그인 상태 -->
                <li><a href="${pageContext.request.contextPath}/member/Logout.do">로그아웃</a></li>
                <li><span>|</span></li> 
                <li><a href="${pageContext.request.contextPath}/member/memberMypage.do">회원정보수정</a></li>
                <li><span>|</span></li>
                <li><a href="${pageContext.request.contextPath}/travel/travelConditions.do">여행 일정 짜기</a></li>
                <li><span>|</span></li>
                <li><a href="${pageContext.request.contextPath}/prescription/prescriptionList.do">처방전</a></li>
                <li><span>|</span></li>
                <li><a href="${pageContext.request.contextPath}/scrap/scrapList.do">스크랩</a></li>
             </c:when>
	           <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/member/memberLogin.do">로그인</a></li>
                <li><span>|</span></li> <!-- 구분자 추가 -->
                <li><a href="${pageContext.request.contextPath}/member/memberSignUp.do">회원가입</a></li>
                <li><span>|</span></li> <!-- 구분자 추가 -->
                <li><a href="${pageContext.request.contextPath}/travel/travelConditions.do">여행 일정 짜기</a></li>
                <li class="invisible-item"></li> <!-- 안 보이는 li 추가 -->
               </c:otherwise>
        </c:choose>
            </ul>
        </nav>
        <div class="menu-container">
            <button id="menu-toggle" class="menu-button">
                <span class="bar"></span>
                <span class="bar"></span>
                <span class="bar"></span>
            </button>
            
        </div>
    </header>

  <script src="${pageContext.request.contextPath}/resources/js/common.js"></script>