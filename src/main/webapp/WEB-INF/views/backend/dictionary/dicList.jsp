<%--
  Created by IntelliJ IDEA.
  User: luqq
  Date: 16/4/7
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/ace-1.3.4/js/bootstrap-multiselect.min.js"></script>
<link rel="stylesheet"
      href="${ctx}/static/ace-1.3.4/css/bootstrap-multiselect.min.css" type="text/css" />


<div class="widget-box">
  <div class="widget-header widget-header-blue widget-header-flat" style="height:20px;">
    <ul class="breadcrumb">
      <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
      <li><i></i> <a href="#">系统管理</a></li>
      <li class="active">字典信息</li>
    </ul>
  </div>

  <div class="widget-body">
    <div class="widget-main">
      <div>
        <div class="row">
          <div id="bi_params_content">
            <form class="form-inline form-query" method="post">
              <div class="param_field_short">
                <label class="form-label">字典类型：</label>
                <select id="type" name="type" style="width: 155px">
                  <option value="">请选择</option>
                  <option <c:if test="${user.dataLevel ne -1}">style="display: none" </c:if> value="1">省份</option>
                  <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> value="2">招生类型</option>
                  <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> value="3">招生批次</option>
                  <option <c:if test="${user.dataLevel ne -1}">style="display: none" </c:if> value="5">版权配置</option>
                  <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> value="6">录取查询地址</option>
                  <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> value="7">用户建议分类</option>
                  <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> value="8">学位</option>
                </select>
              </div>
              <div class="param_field_short">
                <label class="form-label">字典名称：</label>
                <input id="name" name="name" class="form-control inputSelect ipt" style="width: 170px;" autocomplete="off" isNull="false" checkType="empty"
                       placeholder="输入字典名称" />
              </div>
            </form>
          </div>
          <div class="col-sm-3 param_field_btn">
            <a id="searchBtn" class="btn btn-primary btn-sm">查询</a>&nbsp;&nbsp;
            <a id="addBtn" class="btn btn-primary btn-sm">新增</a>
          </div>
        </div>
        <div class="row data-table">
          <div class="col-xs-12">
            <table id="table-data-list"></table>
            <div id="table-data-list-pager"></div>
          </div>
        </div>
      </div>
    </div>
    <!-- /.widget-main -->
  </div>
  <!-- /.widget-body -->
</div>
<script src="${ctx}/static/scripts/backend/dictionary/dicList.js"></script>
