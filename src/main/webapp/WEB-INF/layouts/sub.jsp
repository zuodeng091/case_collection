<%@ page import="com.casecollection.front.model.FrontUser" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="frontUser" value="${sessionScope.frontUser}" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="Description" content="" />
	<link rel="icon" href="<c:if test="${school.yrScholar != null and  school.yrScholar!=''}">${ctx}${school.yrScholar}</c:if><c:if test="${school.yrScholar == null ||  school.yrScholar==''}">/icon.png</c:if>" type="image/x-icon" />
	<title>
		<c:if test="${school.professor != null and  school.professor!=''}">
			${school.professor}
		</c:if>
		<c:if test="${school.professor == null ||  school.professor==''}">
			高考招录智能咨询平台
		</c:if>
	</title>
	<link rel="SHORTCUT ICON" href="<c:if test="${school.yrScholar != null and  school.yrScholar!=''}">${ctx}${school.yrScholar}</c:if><c:if test="${school.yrScholar == null ||  school.yrScholar==''}">/icon.png</c:if>" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/styles/front/common/base.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/styles/front/common/global.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/styles/front/common/top.css" />
<link rel="stylesheet" type="text/css"
    href="${ctx}/static/styles/front/common/foot.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/styles/front/common/popup.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/styles/front/page/subpage.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/front/page/subbar.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/front/pagination/page.css" />
    <script src="${ctx}/static/jquery/jquery.min.js"></script>
<style type="text/css">
.txt {
    cursor: pointer;
}
</style>
</head>

<body>
	<input type="hidden" value="${ctx}" id="ctx" />
    <input type="hidden" value="${school.code}" id="sCode" />
    <input type="hidden" value="${school.identify}" id="identify" />
    <input type="hidden" value="${infoId}" id="contentId" />
    <c:if test="${frontUser != null}">
        <input type="hidden" value="${frontUser.id}" id="userId" />
        <input type="hidden" value="${frontUser.nickName}" id="nickName" />
    </c:if>
	<div class="container subpage">
        <div id="header" class="header">
            <jsp:include page="../views/front/header.jsp" />
        </div>
		<div class="container g-mlr-a w1024 pb-100">
			<div class="g-cf g-ptb-20">
				<div class="g-f-l menu">
					<p class="m-head">信息分类</p>
					<ul class="m-con">
						<c:if test="${infoTree != null}">
							<c:forEach items="${infoTree}" var="menu">
								<c:if test="${menu.id == 4}">
                                    <li class="m-level1">
										<p class="g-mb-15" onclick="gotoPage('${ctx}/front/info/scenery/${school.code}')">
											${menu.name}
										</p>
									</li>
								</c:if>
								<c:if test="${menu.id != 4}">
                                    <li class="m-level1">
										<p class="g-mb-15 
                                        <c:if test="${menu.subMenu != null}">
                                          txt
                                        </c:if>
                                        ">${menu.name}</p>
										<c:if test="${menu.subMenu != null}">
											<ul class="g-bt-e6 g-bb-e6">
												<c:forEach items="${menu.subMenu}" var="sub">
													<li class="m-level2">
														<p class="txt">
															<span class="g-ellipsis sub-name">${sub.name}</span>
															<c:if test="${sub.subMenu != null}">
															  <i class="g-ml-20 g-downArrow arrow"></i>
																<ul>
																	<c:forEach items="${sub.subMenu}" var="sub2">
																		<li class="g-ellipsis m-level3"><i class="dot-list"></i> 
																		    <a href="${ctx}/front/info/route/3/${sub2.id}" id="${sub2.id}">${sub2.name}</a>
																		</li>
																	</c:forEach>
																</ul>
															</c:if>
														</p> 
													</li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:if>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div class="g-f-l content">
					<div class="g-cf filter-bar">
						<div class="g-f-l navigation">
							<a href="javascript:void(0);">${crumb.level1}</a>
							<c:if test="${crumb.level2 != null}">
								<i class="dot-list dot"></i> <a href="javascript:void(0);" class="cur">${crumb.level2}</a>
							</c:if>
						</div>
					</div>
					<!-- content start -->
					<div class="filter-con clearfix" id="info_container">
						<sitemesh:body />
					</div>
					<!-- content end -->
				</div>
			</div>
		</div>

		<jsp:include page="../views/front/footer.jsp" />

        <a href="javascript:void(0);" class="answer-enter ans_entry"></a>
        <a href="javascript:void(0)" class="back-totop" id="backToTop"></a>
        
	<script type="text/javascript" src="${ctx}/static/scripts/front/require.js" data-main="${ctx}/static/scripts/front/page/subSpecial.js"></script>
		
	<script type="text/javascript" src="${ctx}/static/scripts/com/utils.js"></script>
	<script type="text/javascript">
          function gotoIndex(sCode) {
             if(sCode != null && sCode != '') {
                 window.location.href = $("#ctx").val() + '/front/index/' + sCode;
             }
          }
          function gotoPage(url) {
             window.location.href = $("#ctx").val() + url;
          }
		  $(function(){
			  var contentId = $('#contentId').val();
			  var href = '/front/info/3/'+contentId;
			  var curMenu = $();
			  //分数预测样式切换
			  var showClass = "${showClass}";
			  if(showClass == "yes"){
				  $("#class_score").attr('class','cur');
				  $("#class_question").attr('class','');
			  }else{
				  $("#class_question").attr('class','cur');
				  $("#class_score").attr('class','');
			  }
		  });
    </script>
    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"6","bdPos":"right","bdTop":"335"},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>