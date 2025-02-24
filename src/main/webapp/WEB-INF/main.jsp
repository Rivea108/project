<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>RECOTRIP</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
  <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body>
    <!-- 해더 -->
    <%@ include file="/WEB-INF/header.jsp" %>
  <!-- Parallax Container -->
  <div class="parallax">
    <!-- Header Section -->
    <section class="parallax__header">
      <div class="parallax__visuals">
        <!-- Black line at the bottom -->
        <div class="parallax__black-line-overflow"></div>
        <!-- Parallax Layers -->
        <div data-parallax-layers class="parallax__layers">
          <!-- Background Layer 1 -->
          <img 
            src="${pageContext.request.contextPath}/resources/images/mainbackmoutinimg2.png" 
            alt="Layer 1" 
            data-parallax-layer="1" 
            class="parallax__layer-img">
            <img 
            src="${pageContext.request.contextPath}/resources/images/maincloud2.png" 
            alt="Layer 3" 
            data-parallax-layer="3" 
            class="parallax__layer-img">
          </div>
          <!-- Background Layer 2 -->
          <img 
            src="${pageContext.request.contextPath}/resources/images/maincloud.png" 
            alt="Layer 2" 
            data-parallax-layer="2" 
            class="parallax__layer-img">
          <!-- Title Layer -->
          <div data-parallax-layer="3" class="parallax__layer-title">
            <h2 class="parallax__title">어떤 여행을 하고싶으세요?</h2>
          </div>
          <!-- Foreground Layer 3 -->
          <!-- <img 
            src="your-image3.jpg" 
            alt="Layer 3" 
            data-parallax-layer="4" 
            class="parallax__layer-img">
        </div> -->

        <!-- Fade Effect -->
        <div class="parallax__fade"></div>
      </div>
    </section>

    <!-- Content Section -->
   <!-- Parallax Content Section -->
<section class="parallax__content">

    <div class="card-container">
        <!-- 카드 1 -->
        <div class="card" tabindex="0">
            <img src="${pageContext.request.contextPath}/resources/images/mainimg1.png" alt="여행지 1">
            <div class="text">
                <h2 class="card-title">The Bali</h2>
                <p class="card-description">발리섬(인도네시아어: Bali)은 인도네시아의 섬으로 자와섬 동쪽에 자리하고 있다. 관광지로 널리 알려져 있다. 인구는 약 310만 명이다.
                  소순다 열도에 속한 섬으로 자와섬에서 북쪽으로 3.2 km 떨어져 있다. 길이 153 km, 폭 112 km 크기로 면적은 5,700 km2이다. 가장 높은 산은 섬 북동부에 위치한 3,148 m의 아궁산으로 1963년에도 분화한 적이 있는 활화산이다.</p>
            </div>
        </div>

        <!-- 카드 2 -->
        <div class="card" tabindex="0">
            <img src="${pageContext.request.contextPath}/resources/images/mainimg2.png" alt="여행지 2">
            <div class="text">
                <h2 class="card-title">해변 휴양</h2>
                <p class="card-description">푸른 바다가 보이는 해변에서 힐링하세요.</p>
            </div>
        </div>

        <!-- 카드 3 -->
        <div class="card" tabindex="0">
            <img src="${pageContext.request.contextPath}/resources/images/mainimg3.png" alt="여행지 3">
            <div class="text">
                <h2 class="card-title">산악 트래킹</h2>
                <p class="card-description">아름다운 자연 속에서 트래킹을 즐겨보세요.</p>
            </div>
        </div>

        <!-- 카드 4 -->
        <div class="card" tabindex="0">
            <img src="${pageContext.request.contextPath}/resources/images/mainimg4.png" alt="여행지 4">
            <div class="text">
                <h2 class="card-title">사막 탐험</h2>
                <p class="card-description">끝없는 모래 언덕을 탐험하는 색다른 경험.</p>
            </div>
        </div>

        <!-- 카드 5 -->
        <div class="card" tabindex="0">
            <img src="${pageContext.request.contextPath}/resources/images/mainimg5.png" alt="여행지 5">
            <div class="text">
                <h2 class="card-title">북극 탐험</h2>
                <p class="card-description">눈 덮인 극지방에서 특별한 모험을 경험하세요.</p>
            </div>
        </div>

        <!-- 카드 6 -->
        <div class="card" tabindex="0">
            <img src="${pageContext.request.contextPath}/resources/images/mainimg6.png" alt="여행지 6">
            <div class="text">
                <h2 class="card-title">전원 생활</h2>
                <p class="card-description">자연 속에서 여유로운 시간을 보내보세요.</p>
            </div>
        </div>
    </div>
</section>
<section class="button-section">
    <a href="${pageContext.request.contextPath}/travel/travelConditions.do" class="button-transparent">여행조건 입력</a>
    <a href="${pageContext.request.contextPath}/travel/travelInput.do" class="button-solid">여행지 입력</a>
  </section>
  </div>

  <!-- Include GSAP and Plugins -->
  <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/gsap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/ScrollTrigger.min.js"></script>
  <script src="https://unpkg.com/lenis@1.1.14/dist/lenis.min.js"></script>
  <!-- 풋터 -->
  <%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>
