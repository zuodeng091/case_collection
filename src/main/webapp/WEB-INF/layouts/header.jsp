<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.header_li {
	margin-left: 20px;
}

.header_li>a {
	padding: 5px;
}

.header_btn {
	cursor: pointer;
}

.selectStore {
	width: 99%;
	height: 35px;
	margin-top: 3px;
}
tabbable {
    height:100%;
}
.tab-content {
    height:100%;
    padding:15px 0px;
}
.tab-pane {
    height:99%;
}

#inputStore {
	line-height: 17px;
	margin-top: -5px;
}

.selectStoreIcon {
	margin-top: 5px;
}

.msg {
    border : 1px;
}

#logout {
	text-decoration: none;
	color: #393939;
}
.trans_to_pro {
    margin-left:20px;
    font-size:1px;
}

</style>
<script src="${ctx}/static/scripts/talk/socket.io.js"></script>
<script src="${ctx}/static/scripts/talk/client.js"></script>

<!-- #section:basics/navbar.layout -->
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
		function showHelp() {
			if($("#showHelpBtn").attr('menu')) {
				$.showCommonEditDialog('/helpDoc/showHelp?menu='+$("#showHelpBtn").attr('menu'),'操作说明',980,550);
			}
		};
	</script>
    <input type="hidden" id="id" value="${user.id}">
    <input type="hidden" id="name" value="${user.name}">
    <input type="hidden" id="sCode" value="${user.sCode}">
    <input type="hidden" id="handNum" value="${user.handNum}">
    <input type="hidden" id="cusLevel" value="${user.cusLevel}">
    <input type="hidden" id="index_dataLevel" value="${user.dataLevel}">
    <input type="hidden" id="isCs" value="${isCs}">
	<div id="navbar-container" class="navbar-container">
		<div class="navbar-header pull-left">
			<!-- #section:basics/navbar.layout.brand -->
			<a class="navbar-brand" href="#"> <small> <i
					class="fa fa-leaf"></i>智能答疑后台管理系统
			</small>
			</a>
		</div>
		<!-- #section:basics/navbar.dropdown -->
		<input type="hidden" id="needSelect" value="${needSelect}" />
		<div role="navigation" class="navbar-buttons navbar-header pull-right">
			<ul class="nav ace-nav" style="margin-top: 20px;">
                <li class="header_li" id="showHelpBtn" onclick="showHelp()" style="border-left: 0px;cursor: pointer;""><i class="glyphicon glyphicon-question-sign"></i>帮助文档</li>
                <li class="header_li" style="border-left: 0px;">${currentDate}</li>
				<li class="header_li" style="border-left: 0px;">${day}</li>
				<li class="header_li" style="border-left: 0px;">
					<div class="" onclick="">
						<i class="glyphicon glyphicon-user"></i>&nbsp; ${user.name}
					</div>
				</li>

				<li class="header_li" style="border-left: 0px;">
					<div class="header_btn" id="logout">
						<i class="fa fa-sign-out"></i> <a href="${ctx}/logout"
							style="color: #393939">退出登录</a>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<c:if test="${isCs == true}">
		<div tabindex="-1" data-backdrop="false" data-fixed="true"
			data-placement="right" data-offset="true" data-body-scroll="false"
			class="modal aside aside-vc aside-fixed navbar-offset no-backdrop aside-right aside-hidden"
			id="right-menu" style="display: none;">
			<div class="modal-dialog" style="width: 400px;height:500px;">
				<div class="modal-content ace-scroll">
					<div
						class="scroll-track scroll-dark no-track idle-hide scroll-active"
						style="display: block; height: 500px;">
						<div class="scroll-bar" style="height: 500px; top: 0px;"></div>
					</div>
					<div class="scroll-content" style="max-height: 500px;">
						<div class="modal-header no-padding">
							<div class="table-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">
									<span class="white">×</span>
								</button>
								在线答疑
							</div>
						</div>
						<div class="modal-body no-padding row" style="height:426px;">
							<div class="col-sm-12 no-padding" style="height:100%;">
								<!-- #section:elements.tab.position -->
								<div class="tabbable tabs-right" style="height:100%;">
									<ul id="talkTab" class="nav nav-tabs">
									</ul>
									<div class="tab-content" id="talkContent">
									</div>
								</div>
							</div>
						</div>
						<div class="row no-padding form-group">
						   <input size="30" id="msg" class="col-sm-8 col-xs-8 form-control" style="width:330px;" placeholder="输入回答内容，按Enter键发送"><div id="sendMsg" class="col-sm-2 col-xs-2 btn btn-sm btn-primary">发送</div>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
		
				<button type="button" data-toggle="modal" data-target="#right-menu"
					class="aside-trigger btn btn-info btn-app btn-xs ace-settings-btn">
					<i class="ace-icon fa bigger-110 icon-only fa-headphones"
						data-icon2="fa-minus" data-icon1="fa-headphones"></i>
				</button>
			</div>
			<!-- /.modal-dialog -->
		</div>
    </c:if>
</div>
<a class="btn-scroll-up btn btn-sm btn-inverse display"
	id="btn-scroll-up" href="#"> <i
	class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>