<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar responsive compact" style="border-right:1px;">
    <script type="text/javascript">
         try {
            ace.settings.check('sidebar', 'fixed');
        } catch (e) {
        } 
    </script>
    <ul class="nav nav-list" id="sidebar-list">
    </ul>
    <div id="sidebar-collapse" class="sidebar-toggle sidebar-collapse">
        <i data-icon2="ace-icon fa fa-angle-double-right" data-icon1="ace-icon fa fa-angle-double-left" class="ace-icon fa fa-angle-double-left"></i>
    </div>

   <script type="text/javascript">
        $(function()  {
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
            $("#sidebar-collapse").click(function(e) {
            	if($("#sidebar").hasClass("menu-min")) {
                    $('#contentFrame').width($(window).width()*0.99 - 105);
            	}else {
	            	$('#contentFrame').width($(window).width()*0.99 - 43);
            	}
            });
            $.ajax({
                type:"POST",
                url: '/loadMenu',
                dataType: "json",
                success: function(data){
                    var html='<li class="hover">' ;
                    if($("#index_dataLevel").val() == -1){
                        html+= '<a id="indexPage" href="javascript:void(0)" onclick="toPage(\'/school/toList\', null)" >';
                    }else{
                        html+= '<a id="indexPage" href="javascript:void(0)" onclick="toPage(\'/backend/index/toWorkbench\', null)" >';
                    }
                    html+= '<i class="menu-icon fa fa-home"></i>' ;
                    html+= '<span class="menu-text">首页</span></a></li>';
                    $.each(data, function(k, v) {
                        var linkAction = v.url ? 'onclick="toPage(\'' + v.url + '\',\''+v.menuName+'\')"': '';
                        var expandClass = v.childMenu && v.childMenu.length > 0 ? 'class="dropdown-toggle"' : '';
                        var iconClass = v.iconClass || "fa fa-list";
                        html += '<li class="hover"> ' +
                                '<a id="menu_'+v.menuName+'" href="javascript:void(0)" ' + linkAction + ' ' + expandClass + '>' +
                                '<i class="menu-icon ' + iconClass +'"></i>' +
                                '<span class="menu-text">'+v.menuName+'</span>' +
                                '<b class="arrow fa fa-angle-down"></b>' +
                                '</a>' +
                                '<b class="arrow"></b>';
                    $.each(v.childMenu, function(k1, v1) {
                        var size = v.childMenu.length;
                        var linkAction = v1.url ? 'onclick="toPage(\'' + v1.url + '\',\''+v1.menuName+'\')"': '';
                        var iconClass = v1.iconClass || "fa fa-caret-right";
                        if(k1==0){
                            html+='<ul class="submenu nav-show">'
                        }
                        html+='<li class="hover">' +
                                '<a  id="menu_'+v1.menuName+'" href="javascript:void(0)" ' + linkAction + '>' +
                                '<i class="menu-icon ' + iconClass + '"></i>' +
                                v1.menuName
                                + '</a><b class="arrow"></b></li>'
                        if(k1==size-1){
                            html+='</ul>'
                        }
                        });
                        html+'</li>'
                    });
                    $("#sidebar-list").html(html);
                    $("#indexPage").click();
                },
                error: function   (){
                    alert("服务器发生错误");
                }
            });
        });

        function toPage(url, menu) {
        	$("#sidebar-list").children("li.hover").removeClass("active");
        	if(url == '/index') {
                $("#indexPage").parent().addClass("active");
        	}else {
	        	$("div.scroll-hover").parent().addClass("active");
        	}
        	if(menu) {
	            $("#showHelpBtn").attr('menu', menu);
        		$("#showHelpBtn").show();
        	}else {
                $("#showHelpBtn").removeAttr('menu');
                $("#showHelpBtn").hide();
        	}
            $("div.scroll-hover").prev('ul.submenu').css("cssText","display:none!important");
            $('#contentFrame').attr('src', url);
            $('#contentFrame').height($(window).height() - $("#navbar").height());
            updateFrameHeightAndWidth();
        }
    </script>
</div>
