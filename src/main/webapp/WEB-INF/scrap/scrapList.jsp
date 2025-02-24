<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>스크랩</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css">
<style>
/* 노말라이즈 */
body, ul, li, h1 {
	margin: 0;
	padding: 0;
	list-style: none;
}

a {
	color: inherit;
	text-decoration: none;
}

label[for] {
	cursor: pointer;
	user-select: none;
}

/* 라이브러리 */
html, body {
	height: 100%;
}

body {
	display: flex;
	flex-direction: column;
}

.block {
	display: block;
}

.inline-block {
	display: inline-block;
}

.absolute-left {
	position: absolute;
	left: 0;
}

.absolute-right {
	position: absolute;
	right: 0;
}

.absolute-bottom {
	position: absolute;
	bottom: 0;
}

.absolute-top {
	position: absolute;
	top: 0;
}

.absolute-center {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
}

.absolute-middle {
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
}

.absolute-middle.absolute-center {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
}

.relative {
	position: relative;
}

.cell {
	float: left;
	box-sizing: border-box;
}

.cell-right {
	float: right;
	box-sizing: border-box;
}

.row::after {
	content: "";
	display: block;
	clear: both;
}

.clear-both {
	clear: both;
}

.img-box>img {
	width: 100%;
	display: block;
}

.con {
	margin-left: auto;
	margin-right: auto;
}

.margin-0-auto {
	margin-left: auto;
	margin-right: auto;
}

.text-align-center {
	text-align: center;
}

.text-align-right {
	text-align: right;
}

.line-height-0 {
	line-height: 0;
}

.line-height-0>* {
	line-height: normal;
}

.width-30px {
	width: 30px;
}

.width-35px {
	width: 35px;
}

.width-40px {
	width: 35px;
}

.width-45px {
	width: 35px;
}

.width-50px {
	width: 50px;
}

.width-80px {
	width: 80px;
}

.width-100px {
	width: 100px;
}

.width-100p {
	width: 100%;
}

.width-60p {
	width: 60%;
}

.width-55p {
	width: 55%;
}

.width-50p {
	width: 50%;
}

.width-45p {
	width: 45%;
}

.width-40p {
	width: 40%;
}

.width-30p {
	width: 30%;
}

.width-35p {
	width: 35%;
}

.width-100p-except-30px {
	width: calc(100% - 30px);
}

.width-100p-except-35px {
	width: calc(100% - 35px);
}

.width-100p-except-40px {
	width: calc(100% - 40px);
}

.width-100p-except-50px {
	width: calc(100% - 50px);
}

.width-100p-except-80px {
	width: calc(100% - 80px);
}

.width-100p-except-100px {
	width: calc(100% - 100px);
}

.height-100p {
	height: 100%;
}

.height-50p {
	height: 50%;
}

.border-box {
	box-sizing: border-box;
}

.font-size-dot-8rem {
	font-size: 0.8rem;
}

.table-row {
	display: table;
}

.table-row.row::after {
	display: inline;
	clear: non;
}

.table-row>.cell {
	float: none;
	display: table-cell;
}

.border-red {
	border: 10px solid red;
}

.border-green {
	border: 10px solid green;
}

.border-blue {
	border: 10px solid blue;
}

.border-gold {
	border: 10px solid gold;
}

.border-pink {
	border: 10px solid pink;
}

/* 라이브러리 - flex */
*>.flex {
	display: flex;
}

*>.flex-grow-1 {
	flex-grow: 1;
}

*>.flex-jc-c {
	justify-content: center;
}

*>.flex-jc-s {
	justify-content: flex-start;
}

*>.flex-jc-e {
	justify-content: flex-end;
}

*>.flex-jc-sb {
	justify-content: space-between;
}

*>.flex-jc-sa {
	justify-content: space-around;
}

*>.flex-ai-c {
	align-items: center;
}

*>.flex-ai-s {
	align-items: flex-start;
}

*>.flex-ai-e {
	align-items: flex-end;
}

*>.flex-as-s {
	align-self: stretch;
}

*>.flex-as-c {
	align-self: center;
}

*>.flex-as-s {
	align-self: flex-start;
}

*>.flex-as-e {
	align-self: flex-end;
}

*>.flex-column-nowrap {
	flex-flow: column nowrap;
}

*>.flex-column-wrap {
	flex-flow: column wrap;
}

*>.flex-row-wrap {
	flex-flow: row wrap;
}

.flex-ch-basis-0>* {
	flex-basis: 0;
}

*>.flex-1-0-0 {
	flex: 1 0 0;
}

*>.bg-red {
	background-color: red;
}

/* 커스텀 */

/* 고정형 .con 시작 */
.con-min-width {
	min-width: 1370px;
}

.con {
	width: 1370px;
}
/* 고정형 .con 끝 */

/* 반응형 타입 1 .con 시작 */
/*
.con {
  max-width:1370px;
}
*/
/* 반응형 타입 1 .con 끝 */

/* 반응형 타입 2(넓을때만 반응형) .con 시작 */
/*
.con-min-width {
  min-width:1370px;
}

.con {
  margin-left:50px;
  margin-right:50px;
}
*/
/* 반응형 타입 2(넓을때만 반응형) .con 끝 */
.title {
	width: 1200px; /* 너비를 강제로 1920px로 설정 */
	text-align: center;
	font-size: 32px;
	padding: 0px;
	margin: 0 auto; /* 수평 중앙 정렬 */
	margin-top: 50px;
	margin-bottom: 50px;
	color: #333333;
}

.table-container {
	width: 1200px; /* 테이블과 동일한 너비 */
	margin: 0 auto; /* 중앙 정렬 */
	flex: 1 1 auto;
}

table {
	margin: 0 auto;
	border-collapse: collapse;
	width: 1200px;
}

thead>tr {
	height: 47px; /* 헤더 높이는 그대로 유지 */
	background-color: #3b8eef;
	color: white;
}

tbody>tr {
	height: 46px; /* 헤더를 제외한 모든 행을 46px로 설정 */
}

tr:first-child th {
	border-top: 2px solid #333333;
	border-bottom: 1px solid #333333;
	color: white;
}

tbody tr td {
	border-top: 1px solid #d9d9d9;
	border-bottom: 1px solid #d9d9d9;
}

tr:last-child td {
	border-bottom: 2px solid #333333;
}

th, td {
	padding: 0;
	text-align: center;
	vertical-align: middle;
	color: #333333;
	font-size: 16px;
}

section:not(.allheaders) {
	width: 1200px;
	margin: 0 auto;
	font-size: 14px;
	color: #757575;
	text-align: left;
	margin-top: 15px;
}

.pagination {
	display: flex;
	justify-content: center; /* 버튼을 중앙 정렬 */
	align-items: center;
	gap: 2px; /* 버튼 간 간격을 2px로 고정 */
	width: 1200px;
	height: 25px;
	margin: 60px auto 0; /* 중앙 정렬 */
	padding: 0px;
	font-size: 20px; /* 공백 문자 문제 해결 */
	flex-wrap: nowrap; /* 줄바꿈 방지 */
}

.pagination a {
	display: inline-block;
	width: 25px;
	height: 25px;
	line-height: 25px;
	text-align: center;
	font-size: 20px;
	text-decoration: none;
	border: none;
	border-radius: 5px;
	color: #333333;
}

/* 클릭한 페이지 스타일 */
.pagination a.active {
	background-color: #3b8eef;
	color: white;
}
</style>
</head>

<%@ include file="/WEB-INF/header1.jsp"%>

<c:if test="${empty sessionScope.midx}">
    <c:redirect url="/login.do" />
</c:if>

<nav class="breadcrumb">
    <span>홈</span> &gt; <span>스크랩</span> &gt; <span>스크랩 목록</span>
</nav>

<article class="title">스크랩</article>

<div class="table-container">
  <table>
    <thead>
      <tr>
        <th>번호</th>
        <th>여행지</th>
        <th>기간</th>
        <th>인원</th>
        <th>예산</th>
        <th>구성원</th>
        <th>테마</th>
        <th>등록일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="scrap" items="${scrapList}">
        <tr>
          <td>${scrap.sidx}</td>
          <td>
            <a href="${pageContext.request.contextPath}/travel/travelDetails.do?tcidx=${scrap.tcidx}">
              ${scrap.destination}
            </a>
          </td>
          <td>${scrap.duration}</td>
          <td>${scrap.peopleCount}</td>
          <td>${scrap.budget}</td>
          <td>${scrap.groupType}</td>
          <td>${scrap.thema}</td>
          <td>${scrap.date}</td>
        </tr>
      </c:forEach>

      <!-- scrapList가 비어있는 경우 -->
      <c:if test="${empty scrapList}">
        <tr>
          <td colspan="8">스크랩 목록이 없습니다.</td>
        </tr>
      </c:if>
    </tbody>
  </table>
</div>

<section>※ 여행지를 클릭하시면 스크랩한 여행 정보에 대한 상세정보를 확인하실 수 있습니다.</section>

<div class="pagination">
    <!-- 이전 페이지 버튼 -->
    <c:if test="${pageMaker.prev}">
        <a href="${pageContext.request.contextPath}/scrap/scrapList.do?page=${pageMaker.startPage - 1}">◀</a>
    </c:if>

    <!-- 페이지 번호 목록 -->
    <c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
        <c:set var="currentPage" value="${not empty param.page ? param.page : 1}" />
        <a href="${pageContext.request.contextPath}/scrap/scrapList.do?page=${i}"
           class="${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <!-- 다음 페이지 버튼 -->
    <c:if test="${pageMaker.next}">
        <a href="${pageContext.request.contextPath}/scrap/scrapList.do?page=${pageMaker.endPage + 1}">▶</a>
    </c:if>
</div>

<%@ include file="/WEB-INF/footer.jsp"%>

</body>
</html>
