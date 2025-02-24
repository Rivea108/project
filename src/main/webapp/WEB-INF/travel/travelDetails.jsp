<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>일정 상세 페이지</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/scheduleDetails.css">  
  <!-- 폰트어썸 불러오기 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
  
  <script src="${pageContext.request.contextPath}/resources/js/scheduleDetails.js"></script>
</head>
<body>
  <!-- 헤더 -->
  <%@ include file="/WEB-INF/header1.jsp" %>
  <nav class="breadcrumb">
	<span>홈</span> &gt; <span>여행일정</span> &gt; <span>여행상세</span>
  </nav>
  
  <!-- 메인 콘텐츠 -->
  <main class="main-content">
  <form name="frm">
    <!-- 지도 섹션 -->
    <section class="map-section">
      <h2 class="detailtitle">일본 도쿄</h2>
      <!-- <button class="tag">상비약</button> -->
<!--       <div class="map-container">
        하드코딩된 지도 이미지
        <img src="${pageContext.request.contextPath}/resources/images/image 187.png" alt="지도 이미지">
      </div> -->
      <div class="dropdown">
        <button class="dropdown-btn" type="button">1일차 ∨</button>
        <ul class="dropdown-menu">
          <li>2일차</li>
          <li>3일차</li>
          <li>4일차</li>
        </ul>
      </div>
    </section>

    <!-- 첫 번째 컨텐츠 -->
    <section class="content-section">
      <h3 class="h3title">1. 해리포터 스튜디오</h3>
      <div class="content">
        <div class="images">
          <img src="${pageContext.request.contextPath}/resources/images/harry1.png" alt="해리포터 스튜디오 외관">
          <img src="${pageContext.request.contextPath}/resources/images/harry2.png" alt="해리포터 성 내부">
          <!-- <button class="direction-btn">길찾기</button> -->
        </div>
        <p>
            2023년 6월 오픈한 일본의 실내 테마파크이다. 
            해리 포터 시리즈와 신비한 동물사전 시리즈의 세계관을 체험할 수 있다. 
            소재지는 일본 도쿄 네리마구로,
            세이부 이케부쿠로선 토시마엔역에서 도보 2분 거리이다. 
            대한민국에서는 도쿄 해리포터 스튜디오라 불린다.
            해리포터 스튜디오는 현재 영국 런던과 일본 도쿄 두 곳에서 운영중이며, 
            도쿄의 해리포터 스튜디오는 세계 최대 규모이다.
            해리포터 영화 세트 일부(대연회장, 기숙사계단, 해그리드오두막, 프리뱃가4번지, 죽음의 숲 등)를 재현하였으며, 
            영화에서 사용했던 소품등도 전시되어있다. 
            세트, 소품, 크리처 등의 제작과정도 볼 수 있으며 움직이는 사진, 
            퀴디치 응원석등은 직접 체험하여 영상으로 만들어지고 파일을 다운 받을 수도 있다.
            여기서도 버터맥주를 팔고 있다.
            투어는 시간을 정해서 예약해야 하고 1시간 전부터 대기장으로 입장이 가능하다. 
            투어 최초 입장은 동시간대에 예약한 사람들이 다 같이 모여서 입장하나, 
            특정 시점부터는 본인 속도에 맞게 움직이며 관람이 가능하다.
            대기장에는 푸드코트와 기념품샵이 있고 기념품샵에는 해리포터와 관련된 각종 물품을 
            구매 할 수 있으며 망토와 지팡이도 구매 가능하다. 
            영화에서 나왔던 두꺼비 초콜릿 등의 간식류도 있으며 병에든 버터맥주도 판매 중이다.
            근데 더럽게 비싸다 원래는 토시마엔이라는 유서 깊은 유원지가 있던 곳이었으나, 
            해당 유원지가 2020년 폐업한 후 그 자리에 들어섰다.
          <br><br>
          운영시간: 오전 10시 ~ 오후 8시<br>
          추천 시간: 최소 3시간 이상 소요
        </p>
      </div>
    </section>

    <!-- 두 번째 컨텐츠 -->
    <section class="content-section">
      <h3 class="h3title">2. 교파오 롯폰기</h3>
      <div class="content">
        <div class="images">
          <img src="${pageContext.request.contextPath}/resources/images/kyopao1.png" alt="교파오 외부">
          <img src="${pageContext.request.contextPath}/resources/images/kyopao2.png" alt="교파오 내부">
          <!-- <button class="direction-btn">길찾기</button> -->
        </div>
        <p>
          우리 교파오는 롯폰기 지역에서 유명한 딤섬과 일본식 만두 요리를 즐길 수 있는 레스토랑입니다.
          <br><br>
          추천 메뉴: 딤섬, 새우 튀김, 일본식 술<br>
          운영시간: 오후 4시 ~ 자정<br>
          테이블 서비스 가능
        </p>
      </div>
    </section>

    <!-- 하단 버튼 -->
    <div class="bottom-buttons">    
    <c:choose>
   	<c:when test="${!empty requestScope.destination}">
		<button class="blue-btn" type="button" onClick="goScrapList();">스크랩</button>
		<button class="white-btn" type="button" onClick="history.back();">뒤로</button>
   	</c:when>
   	<c:otherwise>
		<a class="blue-btn" style="display: inline-block; box-sizing: border-box; text-align: center;" href="${pageContext.request.contextPath}/travel/travelModify.do">수정</a>
		<a class="white-btn" style="display: inline-block; box-sizing: border-box; text-align: center; background: transparent;" onClick="alert('삭제되었습니다'); location.href = '${pageContext.request.contextPath}/scrap/scrapList.do';">삭제</a>
		<a class="white-btn" style="background: transparent; display: inline-block; box-sizing: border-box; text-align: center;" href="${pageContext.request.contextPath}/scrap/scrapList.do">목록</a>
   	</c:otherwise>
    </c:choose>
    </div>
    </form>
  </main>

  <%@ include file="/WEB-INF/loadingImage.jsp" %>
  <!-- 풋터 -->
  <%@ include file="/WEB-INF/footer.jsp" %>
  
  <script>
  function goScrapList() {    	

		let fm = document.frm;	
		
		let ans = confirm("스크랩 하시겠습니까?");
		
		if (ans == true) {

 			// sessionStorage에 저장
/*		    sessionStorage.setItem('sights', findCheckedValue("sight"));
		    sessionStorage.setItem('restaurants', findCheckedValue("restaurant"));*/
		    
			// sessionStorage에서 불러오기
/*		    document.querySelector('#peopleCount').value = sessionStorage.getItem('peopleCount');
		    document.querySelector('#departureMonth').value = sessionStorage.getItem('departureMonth');
		    document.querySelector('#groupType').value = sessionStorage.getItem('groupType');
		    document.querySelector('#budgetMin').value = sessionStorage.getItem('budgetMin');
		    document.querySelector('#budgetMax').value = sessionStorage.getItem('budgetMax');
		    document.querySelector('#destination').value = sessionStorage.getItem('destination');
		    document.querySelector('#duration').value = sessionStorage.getItem('duration');
		    document.querySelector('#thema').value = sessionStorage.getItem('thema');
		            	   
		    document.querySelector('#sightsInput').value = findCheckedValue("sight");
		    document.querySelector('#restaurantsInput').value = findCheckedValue("restaurant");*/
		    
			// sessionStorage 초기화
			sessionStorage.removeItem("peopleCount");
			sessionStorage.removeItem("departureMonth");
			sessionStorage.removeItem("duration");
			sessionStorage.removeItem("groupType");
			sessionStorage.removeItem("budgetMin");
			sessionStorage.removeItem("budgetMax");
			sessionStorage.removeItem("thema");
			sessionStorage.removeItem("destination");
			sessionStorage.removeItem("sights");
			sessionStorage.removeItem("restaurants");
			sessionStorage.removeItem("schedule");
			sessionStorage.removeItem("city");		    
	        
			fm.action="${pageContext.request.contextPath}/scrap/scrapWriteAction.do";
			fm.method="post";
			fm.submit();

			document.getElementById('loading').style.display = 'block';
		}
	  
		return;
	}   
  </script>
</body>
</html>