<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div></div>
	<nav>
		<div>
			<ul>
				<li><a href="#">로그인</a></li>
				<li><a href="#">English</a></li>
				<li><a href="#">회원가입</a></li>
				<li><a href="#">사이트맵</a></li>
			</ul>
		</div>
	</nav>

	<div>

		<div>
			<button>
				<span>Toggle navigation</span> <span></span> <span></span> <span></span>
			</button>
			<a> <img>
			</a>
		</div>

		<div>
			<ul>
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
				<li><a href="#">사이트맵</a></li>
				<li><a href="#">ENGLISH</a></li>
				<li><a href="#"><i></i></a></li>
				<li><a href="#"><i></i></a></li>
				<li><a href="#"><i></i></a></li>
			</ul>
		</div>


		<div>
			<ul>
				<li><a href="#">데이터셋</a>
					<ul>
						<li><a href="#">파일데이터</a></li>
						<li><a href="#">오픈 API</a></li>
						<li><a href="#">표쥰 데이터</a></li>
						<li><a href="#">국가중점데이터</a></li>
						<li><a href="#">이슈데이터</a></li>
						<li><a href="#">국가데이터맵</a></li>
					</ul></li>
				<li><a href="#">제공신청</a>
					<ul>
						<li><a href="#">데이터 1 번가</a></li>
						<li><a href="#">공공데이터 제공신청</a></li>
						<li><a href="#">분쟁조정신청</a></li>
					</ul></li>
				<li><a href="#">활용사례</a>
					<ul>
						<li><a href="#">공공데이터 활용사례</a></li>
						<li><a href="#">기업탐방 인터뷰</a></li>
						<li><a href="#">공공데이터 시각화</a></li>
						<li><a href="#">국민참여지도</a></li>
						<li><a href="#">위치정보 시각화</a></li>
						<li><a href="#">공공데이터 분석서비스</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<div class="sett" id="openapiDivArea">
		<div class="head1 clearfix">
			<i class="fa fa-cogs"></i> 오픈API [13건]

			<div class="right visible-desktop">
				<a href="#" class="sort-btn active" data-index="OPENAPI"
					data-sort="SCORE" data-order="DESC"> &nbsp;정확도&nbsp; <i
					class="fa fa-angle-down" aria-hidden="true"></i>



					<ul class="sort-popup">
						<li class="sort-select-btn" data-index="OPENAPI" data-sort="SCORE"
							data-order="ASC">오름차순</li>
						<li class="sort-select-btn" data-index="OPENAPI" data-sort="SCORE"
							data-order="DESC">내림차순</li>
					</ul></a> <a href="#" class="sort-btn " data-index="OPENAPI"
					data-sort="U_DATE" data-order="DESC">
					날&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;짜

					<ul class="sort-popup">
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="U_DATE" data-order="ASC">오름차순</li>
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="U_DATE" data-order="DESC">내림차순</li>
					</ul>
				</a> <a href="#" class="sort-btn " data-index="OPENAPI"
					data-sort="DOC_TITLE" data-order="DESC">
					제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목

					<ul class="sort-popup">
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="DOC_TITLE" data-order="ASC">오름차순</li>
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="DOC_TITLE" data-order="DESC">내림차순</li>
					</ul>
				</a> <a href="#" class="sort-btn " data-index="OPENAPI"
					data-sort="VIEW_COUNT" data-order="DESC"> &nbsp;조회수&nbsp;

					<ul class="sort-popup">
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="VIEW_COUNT" data-order="ASC">오름차순</li>
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="VIEW_COUNT" data-order="DESC">내림차순</li>
					</ul>
				</a> <a href="#" class="sort-btn " data-index="OPENAPI"
					data-sort="REQUEST_COUNT" data-order="DESC"> 활용신청

					<ul class="sort-popup">
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="REQUEST_COUNT" data-order="ASC">오름차순</li>
						<li class="sort-select-btn" data-index="OPENAPI"
							data-sort="REQUEST_COUNT" data-order="DESC">내림차순</li>
					</ul>
				</a>
			</div>
		</div>

		<!-- OPENAPI 결과리스트 -->


		<div id="openapi-list-wrapper">

			<div class="data-item">
				<span class="category-filter visible-desktop"> 재정금융 </span>

				<div class="data-title">
					<a href="/dataset/15001145/openapi.do"> 한국예탁결제원_주식정보서비스 </a> <span>조회수
						: 427</span> <span>활용신청건수 : 857</span> <span
						class="category-filter visible-mobile"> 재정금융 </span>
				</div>

				<div class="data-meta">
					<span>수정일 : 2019.06.03</span><span>기관 : 한국예탁결제원</span> <br
						class="visible-mobile"> <span>서비스유형 : REST</span>

				</div>

				<div class="data-desc">주식정보서비스</div>

				<div class="data-types">


					<span class="data-type XML">XML</span>

				</div>
			</div>

			<div class="data-item">
				<span class="category-filter visible-desktop"> 문화관광 </span>

				<div class="data-title">
					<a href="/dataset/15038496/openapi.do"> 한국체육산업개발주식회사_올림픽공원 및
						미사경정공원 유실물 정보 </a> <span>조회수 : 9</span> <span>바로가기 횟수 : 1</span> <span
						class="category-filter visible-mobile"> 문화관광 </span>
				</div>

				<div class="data-meta">
					<span>수정일 : 2019.09.10</span><span>기관 : 한국체육산업개발주식회사</span> <br
						class="visible-mobile"> <span>서비스유형 : LINK</span>

				</div>

				<div class="data-desc">올림픽공원 및 미사경정공원에서 습득한 유실물 정보로 접수일자, 품명,
					수량, 습득장소 등의 정보를 제공합니다.</div>

				<div class="data-types">


					<span class="data-type XML">XML</span>

				</div>
			</div>

			<div class="data-item">
				<span class="category-filter visible-desktop"> 문화관광 </span>

				<div class="data-title">
					<a href="/dataset/3059774/openapi.do"> 올림픽공원 사진정보 </a> <span>조회수
						: 69</span> <span>바로가기 횟수 : 83</span> <span
						class="category-filter visible-mobile"> 문화관광 </span>
				</div>

				<div class="data-meta">
					<span>수정일 : 2016.07.04</span><span>기관 : 한국체육산업개발주식회사</span> <br
						class="visible-mobile"> <span>서비스유형 : LINK</span>

				</div>

				<div class="data-desc">올림픽공원을 대상으로 촬영한 사진정보 제공</div>

				<div class="data-types">


					<span class="data-type XML">XML</span>

				</div>
			</div>

		</div>


		<p class="the_view more-dataset">
			<a href="javascript:doIndexSearch('OPENAPI');"> 오픈API 결과 더보기 <i
				class="fa fa-chevron-right"></i>
			</a>
		</p>







	</div>
</body>
</html>