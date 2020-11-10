<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Charts - SB Admin</title>
<link href="assets/dist/css/styles.css" rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
									crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand" href="index">COVID19 알림e</a>
		<button class="btn btn-link btn-sm order-1 order-lg-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<div class="input-group">
				 <input class="form-control" id='param' type="text" placeholder="네이버 검색.." aria-label="Search" aria-describedby="basic-addon2" />
                    <div class="input-group-append">
                   		
                        <button class="btn btn-primary" type="button" id='search_btn' onclick='search()'><i class="fas fa-search"></i></button>
				</div>
			</div>
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="userDropdown" href="#"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<!--     <a class="dropdown-item" href="#">Settings</a>
                        <a class="dropdown-item" href="#">Activity Log</a>
                        <div class="dropdown-divider"></div> -->
					<a class="dropdown-item" href="memberLogout.do">Logout</a>
				</div></li>
		</ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">종합 현황</div>
						<a class="nav-link" href="index">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Dashboard
						</a> <a class="nav-link" href="charts">
							<div class="sb-nav-link-icon">
								<i class="fas fa-chart-area"></i>
							</div> 지역별 통계
						</a>
						<div class="sb-sidenav-menu-heading">회원 서비스</div>
						<!--  <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Layouts
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="layout-static.html">Static Navigation</a>
                                    <a class="nav-link" href="layout-sidenav-light.html">Light Sidenav</a>
                                </nav>
                            </div> -->

						<a class="nav-link collapsed" href="#" data-toggle="collapse"
							data-target="#collapsePages" aria-expanded="false"
							aria-controls="collapsePages">
							<div class="sb-nav-link-icon">
								<i class="fas fa-book-open"></i>
							</div> 회원정보 관리
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapsePages"
							aria-labelledby="headingTwo" data-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav accordion"
								id="sidenavAccordionPages">
								<a class="nav-link collapsed" href=crystal>
                                      	회원정보 수정
									<div class="sb-sidenav-collapse-arrow"></div>
									
								</a>
								<div class="collapse" id="pagesCollapseAuth"
									aria-labelledby="headingOne"
									data-parent="#sidenavAccordionPages">
									<nav class="sb-sidenav-menu-nested nav">
										<!--     <a class="nav-link" href="login.html">Login</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                            <a class="nav-link" href="password.html">Forgot Password</a> -->
									</nav>
								</div>
								<!--    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div> -->
							</nav>
						</div>
						<a class="nav-link" href="tables">
							<div class="sb-nav-link-icon">
								<i class="fas fa-table"></i>
							</div> 공유 게시판
						</a>



					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Logged in as:</div>
					${member.nickName}
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid">
					<h1  class="mt-4">각 지역별 세부 현황</h1>
		<div class='row'>
			<div class="dropdown col-sm-5">
  				<button id='si' type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
   					특별시, 광역시 단위 선택
  				</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" onclick="graphChanger('Total')">전체</a> 
						<a class="dropdown-item" onclick="graphChanger('Seoul')">서울</a> 
						<a class="dropdown-item" onclick="graphChanger('Busan')">부산</a>
						<a class="dropdown-item" onclick="graphChanger('Daegu')">대구</a>
						<a class="dropdown-item" onclick="graphChanger('Incheon')">인천</a>
						<a class="dropdown-item" onclick="graphChanger('Gwangju')">광주</a>
						<a class="dropdown-item" onclick="graphChanger('Daejeon'')">대전</a>
						<a class="dropdown-item" onclick="graphChanger('Ulsan')">울산</a>
						<a class="dropdown-item" onclick="graphChanger('Sejong')">세종</a>
						<a class="dropdown-item" onclick="graphChanger('Lazaretto')">해외유입</a>
					</div>
			</div>
			<div class="dropdown col-sm-1">
  				<button id='do' type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
   					도 단위 선택
  				</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" onclick="graphChanger('Total')">전체</a> 
						<a class="dropdown-item" onclick="graphChanger('Gyeonggi-do')">경기</a>
						<a class="dropdown-item" onclick="graphChanger('Gangwon-do')">강원</a>
						<a class="dropdown-item" onclick="graphChanger('Chungcheongbuk-do')">충북</a>
						<a class="dropdown-item" onclick="graphChanger('Chungcheongnam-do')">충남</a>
						<a class="dropdown-item" onclick="graphChanger('Jeollabuk-do')">전북</a>
						<a class="dropdown-item" onclick="graphChanger('Jeollanam-do')">전남</a>
						<a class="dropdown-item" onclick="graphChanger('Gyeongsangbuk-do')">경북</a>
						<a class="dropdown-item" onclick="graphChanger('Gyeongsangnam-do')">경남</a>
						<a class="dropdown-item" onclick="graphChanger('Jeju')">제주</a>
						<a class="dropdown-item" onclick="graphChanger('Lazaretto')">해외유입</a>
					</div>
			</div>
		</div>
		
					<div class="card mb-4">
						<div class="card-header" id='area'>
							<i class="fas fa-chart-area mr-1"></i> 일일 신규 확진자 수 추이
						</div>
						<div class="card-body">
							<canvas id="myAreaChart" width="100%" height="30"></canvas>
						</div>
						<div class="card-footer small text-muted">Updated yesterday
							at 11:59 PM</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-bar mr-1"></i> 지역별 누적 확진자 수
										<div class="dropdown col-sm-5">
  											<button id='si' type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
   												시/도 단위 선택
  											</button>
											<div class="dropdown-menu">
												<a class="dropdown-item" onclick="barChanger(0)">도 별</a> 
												<a class="dropdown-item" onclick="barChanger(1)">특별/광역시 별</a> 
											</div>
										</div>
								</div>
								<div class="card-body">
									<canvas id="myBarChart" width="100%" height="30"></canvas>
								</div>
								<div class="card-footer small text-muted">Updated
									yesterday at 11:59 PM</div>
							</div>
						</div>
						<div class="col-lg-6">
							
						</div>
					</div>
				</div>
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2020</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="assets/dist/js/scripts.js"></script>
	<script src="assets/dist/js/search.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script src="assets/dist/assets/demo/chart-area-demo2.js"></script>
	
	<!-- 자바의 SimpleDateFormat처럼 간단하게 날짜 변환해주는 라이브러리 -->
	

</body>
</html>