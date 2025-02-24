<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>처방받은약</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <style>
    
/* 노말라이즈 */
body,
ul,
li,
h1 {
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
	display : flex;
	flex-direction: column;	
}
button {
	cursor: pointer;
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

.img-box > img {
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

.line-height-0 > * {
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

.table-row > .cell {
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
* > .flex {
  display: flex;
}

* > .flex-grow-1 {
  flex-grow: 1;
}

* > .flex-jc-c {
  justify-content: center;
}

* > .flex-jc-s {
  justify-content: flex-start;
}

* > .flex-jc-e {
  justify-content: flex-end;
}

* > .flex-jc-sb {
  justify-content: space-between;
}

* > .flex-jc-sa {
  justify-content: space-around;
}

* > .flex-ai-c {
  align-items: center;
}

* > .flex-ai-s {
  align-items: flex-start;
}

* > .flex-ai-e {
  align-items: flex-end;
}

* > .flex-as-s {
  align-self: stretch;
}

* > .flex-as-c {
  align-self: center;
}

* > .flex-as-s {
  align-self: flex-start;
}

* > .flex-as-e {
  align-self: flex-end;
}

* > .flex-column-nowrap {
  flex-flow: column nowrap;
}

* > .flex-column-wrap {
  flex-flow: column wrap;
}

* > .flex-row-wrap {
  flex-flow: row wrap;
}

.flex-ch-basis-0 > * {
  flex-basis: 0;
}

* > .flex-1-0-0 {
  flex: 1 0 0;
}

* > .bg-red {
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
  width: 1200px;
  text-align: center;
  font-size: 32px;
  margin: 50px auto 50px;
  color: #333333;
}

table {
  width: 1200px;
  border-collapse: collapse;
  margin: 0 auto;
  text-align: center;
  font-size: 20px;
}

.table-header,
.table-body {
  table-layout: fixed;
  width: 1200px;
}

.table-body th,
.table-body td {
  height: 50px; /* 기본 높이를 50px로 설정 */
  padding: 0;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
}

.table-header th,
.table-body th,
.table-body td {
  height: 70px;
  padding: 0;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
}

.table-header th,
.table-body th {
  font-weight: normal; /* 글씨를 일반 두께로 변경 */
}


.table-header .ht1 {
  background-color: #3b8eef;
  color: white;
  width: 134px;
}

.ht2 {
  width: 150px;
}

.header-row {
  color: #333333;
  font-size: 20px;
}

.header-row th {
  border: 1px solid #333333;
}

.header-row th:first-child {
  border-left: none;
}

.header-row th:last-child {
  border-right: none;
}

.table-body {
  margin-top: 20px;
}

.sub-header-row {
  background-color: #3b8eef;
  color: white;
}

.sub-header-row th {
  height: 51px;
  border-top: 2px solid #333333;
  border-bottom: 1px solid #333333;
}

.table-body td {
  border-top: 1px solid #d9d9d9;
  height: 50px;
  color:#333333;
}

.table-body tr:last-child td {
  height: 50px;
  border-bottom: 2px solid #333333;
}

/* 테이블을 감싸는 컨테이너 */
.table-container {
        position: relative; /* 버튼 위치 기준점 설정 */
        width: 1200px; /* 테이블과 동일한 너비 */
        margin: 0 auto 90px; /* 중앙 정렬 */
        flex: 1 1 auto;
      }
      
.btn-box {
	text-align: center;
	margin-top: 50px;
}

.custom-btn {
  background-color: transparent;
  color: #3b8eef;
  border: 2px solid #3b8eef; /* 테두리를 #3b8eef 색상으로 설정 */
  border-radius: 20px; 
  width: 105px; 
  height: 40px;
  font-size: 24px;
  /* position: absolute; */ /* 정중앙 배치를 유지하려면 기존 중앙 정렬 코드도 함께 사용 */
  left: 50%;
  top: calc(50% + 365px);
  /* transform: translate(-50%, -50%); */
}

  </style>
</head>
<body>
   <%@ include file="/WEB-INF/header1.jsp" %>
   <nav class="breadcrumb">
     <span>홈</span> &gt; <span>처방받은약</span> &gt; <span>처방전 상세</span>
   </nav>
   
<article class="title">처방전 상세 조회</article>

   <!-- 테이블 컨테이너 -->
   <div class="table-container">
      <table class="table-header">
        <colgroup>
          <col style="width:134px">
          <col style="width:164px">
          <col style="width:134px">
          <col style="width:320px">
          <col style="width:134px">
          <col style="width:314px">
        </colgroup>
        <thead>
          <tr class="header-row">
            <th class="ht1">조제일자</th>
            <th class="ht2">${prescription.resMenufactureDate}</th>
            <th class="ht1">처방기관</th>
            <th>
              병원 이름: ${prescription.resPrescribeOrg}<br>
              병원 전화번호: ${prescription.resTelNo}
            </th>
            <th class="ht1">조제기관</th>
            <th>
              약국 이름: ${prescription.commBrandName}<br>
              약국 전화번호: ${prescription.commTelNo}
            </th>
          </tr>
        </thead>
      </table>

      <!-- 약 목록 테이블 -->
      <table class="table-body">
        <colgroup>
          <col style="width: 66px;">
          <col style="width: 136px;">
          <col style="width: 165px;">
          <col style="width: 152px;">
          <col style="width: 171px;">
          <col style="width: 91px;">
          <col style="width: 128px;">
          <col style="width: 139px;">
          <col style="width: 149px;">
        </colgroup>
        <thead>
          <tr class="sub-header-row">
            <th>번호</th>
            <th>제품명</th>
            <th>약효분류</th>
            <th>성분명</th>
            <th>약품코드</th>
            <th>단위</th>
            <th>1회 투약량</th>
            <th>1회 투여횟수</th>
            <th>총 투약일수</th>
          </tr>
        </thead>
        <c:choose>
            <c:when test="${not empty prescription.drugs}">
                <c:forEach var="drug" items="${prescription.drugs}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${drug.resDrugName}</td>
                        <td>${drug.resPrescribeDrugEffect}</td>
                        <td>${drug.resIngredients}</td>
                        <td>${drug.resDrugCode}</td>
                        <td>${drug.resContent}</td>
                        <td>${drug.resOneDose}</td>
                        <td>${drug.resDailyDosesNumber}</td>
                        <td>${drug.resTotalDosingdays}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="9">처방된 약이 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
       </table>

  <!-- 목록 버튼을 테이블 아래에 위치 -->
  <div class="btn-box">
 	<button class="custom-btn" onclick = "location.href = '${pageContext.request.contextPath}/prescription/prescriptionList.do'">목록</button>
  </div>
</div>


<%@ include file="/WEB-INF/footer.jsp" %>
</body>
</html>