<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh"
           uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="page-content-area">
  <form id="form" class="form-signin" method="post">
    <input id="id" name="id" type="hidden" value="${dictionary.id}"/>
    <div class="singleContent">
      <div class="left">字典类型：</div>
      <div class="right">
        <select id="type" name="type" style="width: 165px">
          <option value="">请选择</option>
          <option <c:if test="${user.dataLevel ne -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 1}"><c:out value="selected"/></c:if> value="1">省份</option>
          <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 2}"><c:out value="selected"/></c:if> value="2">招生类型</option>
          <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 3}"><c:out value="selected"/></c:if> value="3">招生批次</option>
          <option <c:if test="${user.dataLevel ne -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 5}"><c:out value="selected"/></c:if> value="5">版权配置</option>
          <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 6}"><c:out value="selected"/></c:if> value="6">录取查询地址</option>
          <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 7}"><c:out value="selected"/></c:if> value="7">用户建议分类</option>
          <option <c:if test="${user.dataLevel eq -1}">style="display: none" </c:if> <c:if test="${dictionary.type == 8}"><c:out value="selected"/></c:if> value="8">学位</option>
        </select>
        <span style="color: red">*</span>
      </div>
    </div>
    <div class="singleContent">
      <div class="left">字典名称：</div>
      <div class="right">
        <input name="name" id="name" value="${dictionary.name}" class="form-control" length="10" placeholder="请填写字典名称" isNull="false" checkType="empty" />
      </div>
      <span style="color: red">*</span>
    </div>
    <div class="singleContent">
      <div class="left">排序：</div>
      <div class="right">
        <input type="number" name="orderNum" id="orderNum" value="${dictionary.orderNum}" class="form-control" length="10" placeholder="请填写字典名称" isNull="false" checkType="empty" />
      </div>
      <span style="color: red">*</span>
    </div>
    <div class="singleContent">
      <div class="alert alert-danger hidden" role="alert"></div>
    </div>
    <div class="singleContent">
      <div class="left"></div>
      <div class="right">
        <button id="submitBtn" style="width: 80px; height: 30px;"
                class="btn btn-xs btn-success bigger" type="button">提交
        </button>
      </div>
    </div>
  </form>
</div>

<script type="text/javascript">
  $(function () {
    $("#submitBtn").click(function (e) {
      if($("#type").val() == '') {
        parent.$.dialog({title: '提示', content: "请选择字典类型", icon: 'error.gif', ok: '确定'});
        return false;
      }
      if($.trim($("#name").val()) == '') {
        parent.$.dialog({title: '提示', content: "请填写字典名称", icon: 'error.gif', ok: '确定'});
        return false;
      }
      var params = $("#form").serialize();
      $("#submitBtn").addClass("disabled");

      $.ajax({
        type: "POST",
        url: "/dictionary/save",
        data: params
      }).done(function (data) {
        if (data.retCode == 0) {
          $("#submitBtn").removeClass("disabled");
          parent.$.dialog({title: '提示', content: "保存成功", icon: 'success.gif',lock:true ,ok: '确定'});
          dialog.close();
          parent.dictionaryFacade.query();
        } else {
          $("#submitBtn").removeClass("disabled");
          parent.$.dialog({title: '提示', content: data.message, icon: 'error.gif', ok: '确定'});
        }
      }).always(function () {
        $("#submitBtn").removeClass("disabled");
      });
    });
  })

</script>


