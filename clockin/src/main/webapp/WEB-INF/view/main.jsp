<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/taglibs.jsp"%>

<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<title>${systemName}</title>
	<%@ include file="/jsp/common/meta.jsp"%>
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="${base }/resources/common/script/pace/pace.min.js"></script>
	<script src="${base}/js/common.js"></script>
	<!-- ================== END BASE JS ================== -->
	<style type="text/css">
		body {
			overflow-y: hidden;
		}
		.content {
			padding: 60px 0px;
		}
	</style>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-default navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<a href="${base}/login" class="navbar-brand" style="width: 300px;font-size: 30px;font-weight:  bold;">${systemName }</a>
					<button type="button" class="navbar-toggle" data-click="sidebar-toggled">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<!-- end mobile sidebar expand / collapse button -->
				
				<!-- begin header navigation right -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown navbar-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${base}/resources/assets/images/manager.png" alt="">
							<span class="hidden-xs">${sessionScope.role.name }：${sessionScope.manager.name }</span> 
						</a>
					</li>
					<li><a href="${base }/logout">退出</a></li>
				</ul>
				<!-- end header navigation right -->
			</div>
			<!-- end container-fluid -->
		</div>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<%--<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="${sessionScope.manager.logoUrl}" alt=""></a>
						</div>
						<div class="info">
							${sessionScope.role.name }：${sessionScope.manager.name }
						</div>
					</li>
				</ul>--%>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<li class="nav-header">Navigation</li>
					<!-- 菜单 -->
					<c:forEach items="${menuList}" var="menu" varStatus="status">
						<li class="has-sub">
							<a href="javascript:;">
								<c:choose>
									<c:when test="${status.index eq 0}">
										<i class="fa fa-inbox"></i> 
									</c:when>
									<c:when test="${status.index eq 1}">
											<i class="fa fa-cogs"></i> 
									</c:when>
									<c:when test="${status.index eq 2}">
										<i class="fa fa-laptop"></i> 
									</c:when>
									<c:when test="${status.index eq 3}">
										<i class="fa fa-inbox"></i> 
									</c:when>
									<c:when test="${status.index eq 4}">
										<i class="fa fa-suitcase"></i> 
									</c:when>
									<c:when test="${status.index eq 5}">
										<i class="fa fa-file-o"></i> 
									</c:when>
									<c:otherwise>
										<i class="fa fa-align-left"></i> 
									</c:otherwise>
									
								</c:choose>
								<b class="caret pull-right"></b>
								<span>${menu.menuname }</span>
							</a>
							<!-- 目录 -->
							<ul class="sub-menu">
								<c:forEach items="${catalogList}" var="catalog">
									<c:if test="${catalog.menuId==menu.id && menu.id == 2}">
										<c:forEach items="${moduleList}" var="module" varStatus="i">
											<c:if test="${module.catalogId==catalog.id}">
												<li class="has-sub">
													<a href="${base}${module.moduleurl}${module.moduleact}" target="iframe-content">
													    ${module.modulename}
													</a>
												</li>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${catalog.menuId==menu.id && menu.id != 2}">
										<li class="has-sub">
											<a href="javascript:;">
									            <b class="caret pull-right"></b>
									            ${catalog.catalogname }
									        </a>
											<!-- 模块 -->
											<c:if test="${catalog.id > 65}">
												<ul class="sub-menu">
													<c:forEach items="${moduleList}" var="module" varStatus="i">
														<c:if test="${module.modulename.indexOf('列表')>=0 && module.catalogId==catalog.id}">
															<li class="nav-url">
																<a style="cursor:pointer" onclick="openNewPage('${base}${module.moduleurl}${module.moduleact}')" target="iframe-content">
																		${module.modulename}
																</a>
															</li>
														</c:if>
													</c:forEach>
												</ul>
											</c:if>

											<c:if test="${catalog.id < 65}">
												<ul class="sub-menu">
													<c:forEach items="${moduleList}" var="module" varStatus="i">
														<c:if test="${module.catalogId==catalog.id}">
															<li class="nav-url">
																<a style="cursor:pointer" onclick="openNewPage('${base}${module.moduleurl}${module.moduleact}')" target="iframe-content">
																		${module.modulename}
																</a>
																<%--<a href="${base}${module.moduleurl}${module.moduleact}" target="iframe-content">--%>
																		<%--${module.modulename}--%>
																<%--</a>--%>
															</li>
														</c:if>
													</c:forEach>
												</ul>
											</c:if>

										</li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
			        <!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
			        <!-- end sidebar minify button -->
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<!-- end #sidebar -->
		<!-- begin #content -->
		<div id="content" class="content">
			<div>
		        <iframe name="iframe-content" id="iframe-content" src="${base}/jsp/common/welcome.jsp" frameborder="0" width="100%" style="height:100% !important"></iframe>
		    </div>
		</div>
		<!-- end #content -->
		
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	<script>
		$(document).ready(function() {
			App.init();
			var height = $(window).height();
			var width = $(window).width();
		});
		// 自适应大小
		window.onresize=function(){
			var height = $(window).height();
			var width = $(window).width();
		}
	</script>

</body>
</html>
