<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<title>智能答疑后台管理系统<sitemesh:title /></title>
<link rel="icon" href="/icon.png" type="image/x-icon" />
<link rel="SHORTCUT ICON" href="/icon.png"  type="image/x-icon" />

<style type="text/css">
.no-skin .nav-list > li.active::after {right: 0px;}
#sidebar{border: 0px;}
.main-content{border-style: solid; border-width: 0px 0px 0px 1px; border-color: #fff #fff #fff #cccccc;}
</style>
<sitemesh:head />
</head>

<body class="no-skin"  style="overflow:auto; ">
    <%@ include file="/WEB-INF/layouts/includeResource.jsp"%>
    <%@ include file="/WEB-INF/layouts/header.jsp"%>
    <div class="main-container" id="main-container" style="overflow:hidden; ">
        <%@ include file="/WEB-INF/layouts/sidebar.jsp"%>
        <!--#内容显示 -->
        <div class="main-content" style="overflow-y:hidden;padding:0px;">
	            <sitemesh:body />
            	<iframe id="contentFrame" scrolling="auto" style="width:100%;padding-left: 3px;">
            	</iframe>
        </div>
    </div>
    <!-- 设置sidebar的高亮显示 -->

    <script type="text/javascript">
        $(function() {
         	// 当文档窗口发生改变时 触发
            updateFrameHeightAndWidth();
            // 左侧菜单滚动
             $('.sidebar-collapse>i').click(function(){
                setTimeout(function(){
                    var _sidebar = $('#sidebar-list');
                    _sidebar.perfectScrollbar('destroy');
                    if(!$('#sidebar').hasClass('menu-min')) {
                        //sidebarAotoScroll();
                	} else {
                	    _sidebar.height('auto');
                    	updateFrameHeightAndWidth();
                	}
                },10);
            }); 
            
         	// 当文档窗口发生改变时 触发
            $(window).resize(function() {
            	updateFrameHeightAndWidth();
	        });
         	
        });
		
		function updateFrameHeightAndWidth() {
           	if($("#sidebar").height() == 0) {
       		}else {
       		}
            $('#contentFrame').height($(window).height() - $("#navbar").height() - 10);
            $($('#contentFrame').prop('contentWindow').document).width($('#contentFrame').width());
		}
        
      	//replace icons with FontAwesome icons like above
		function updatePagerIcons(table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
		}
    </script>
</body>
</html>
