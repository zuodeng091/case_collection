<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/static/amazeUi/amazeui.custom.min.css" />
<link rel="stylesheet" href="${ctx}/static/styles/jquery/jquery.gritter.css" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/ui.jqgrid.min.css" />
<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/ace-part2.min.css" class="ace-main-stylesheet" />
<![endif]-->

<!--[if lte IE 9]>
  <link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/ace-ie.min.css" />
<![endif]-->

<link rel="stylesheet" href="${ctx}/static/styles/jquery/perfect-scrollbar.min.css" />

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${ctx}/static/jquery/jquery.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/bootstrap.min.js"></script>

<!-- jqGrid -->
<script src="${ctx}/static/ace-1.3.4/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/jqGrid/i18n/grid.locale-cn.js"></script>

<script src="${ctx}/static/ace-1.3.4/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="${ctx}/static/ace-1.3.4/js/html5shiv.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/respond.min.js"></script>
<![endif]-->

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${ctx}/static/ace-1.3.4/js/excanvas.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/jquery-ui.min.css" />
<script src="${ctx}/static/ace-1.3.4/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/jquery.sparkline.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/fuelux/fuelux.wizard.min.js"></script>

<!--  -->
<script src="${ctx}/static/ace-1.3.4/js/fuelux/fuelux.wizard.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/jquery.validate.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/additional-methods.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/bootbox.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/jquery.maskedinput.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/select2.min.js"></script>

<!-- ace scripts -->
<script src="${ctx}/static/ace-1.3.4/js/ace-elements.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/ace.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/ace.settings.js"></script>

<!-- plugins -->
<script src="${ctx}/static/jq-plugins/perfect-scrollbar.jquery.min.js"></script>

<!-- datepicker -->
<script src="${ctx}/static/ace-1.3.4/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/date-time/moment.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/date-time/daterangepicker.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/ace-1.3.4/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/static/ace-1.3.4/css/daterangepicker.min.css" />

<!-- bi -->
<script src="${ctx}/static/jquery/jquery.gritter.min.js"></script>

<!-- amazeui -->
<script src="${ctx}/static/amazeUi/amazeui.custom.min.js"></script>

<link rel="stylesheet" href="${ctx}/static/lhgdialog/skins/idialog.css" />
<script src="${ctx}/static/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script src="${ctx}/static/scripts/dialog.js"></script>
<script src="${ctx}/static/scripts/common.js"></script>

<link rel="stylesheet" href="${ctx}/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<script src="${ctx}/static/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/static/zTree_v3/js/jquery.ztree.exedit-3.5.min.js"></script>

<!-- 文件上传 -->
<script type="text/javascript" src="${ctx}/static/ajaxfileupload/ajaxfileupload.js"></script>

<!-- 表单校验控件 -->
<script src="${ctx}/static/scripts/superValidator.js"></script>
<!-- 可输入的下拉框控件 -->
<script src="${ctx}/static/scripts/utils/InputSelectUtil.js"></script>
<!-- 可输入的多选下拉框控件 -->
<script src="${ctx}/static/scripts/utils/MultiSelectUtil.js"></script>
<link rel="stylesheet" href="${ctx}/static/styles/common/default.css" />
<link rel="stylesheet" href="${ctx}/static/styles/common/inputSelect.css" />
<link rel="stylesheet" href="${ctx}/static/styles/tooltip.css" />
<link rel="stylesheet" href="${ctx}/static/styles/icon.css" />
<script type="text/javascript">
//dialog对象
var dialog, parent;
try{
	dialog= frameElement.api, parent = dialog.opener;
}catch(e){};
</script>