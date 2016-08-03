<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh"
           uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="page-content-area">
  <form id="form-school" class="form-signin" method="post">
    <input type="hidden" name="id" value="${user.id}">

    <div class="singleContent">
      <div class="left">旧密码：</div>
      <div class="right">
        <input type="text" id="password" name="password" isNull="false" checkType="empty" />
        <span style="color: red">*</span>
      </div>
    </div>

    <div class="singleContent">
      <div class="left">新密码：</div>
      <div class="right">
        <input type="text" id="newPassword" name="newPassword" isNull="false" checkType="empty" />
        <span style="color: red">(密码为包含数字字母特殊字符的6-15位长度)*</span>
      </div>
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
  //初始化
  var dialog, parent;
  try {
    dialog = frameElement.api, parent = dialog.opener;
  } catch (e) {
  }

  function extValidate(){
    var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,15}');
    if(!regex.test($("#newPassword").val())){
      return false;
    }
    return true;
  }

  $(function () {
    $("#submitBtn").click(function (e) {
      formValidate();
      if (validat() == false) {
        alert("请先填写参数");
        return false;
      }
      if(!extValidate()){
        alert("密码为包含数字字母特殊字符的6-15位长度");
        return false;
      }

      var params = $("#form-school").serialize();

      $("#submitBtn").addClass("disabled");

      $.ajax({
        type: "POST",
        url: "/user/resetPassword",
        data: params
      }).done(function (data) {
        if (data.success) {
          $("#submitBtn").removeClass("disabled");
          $.dialog({title: '提示', content: "操作成功", icon: 'success.gif',lock:true ,ok: '确定'});
        } else {
          $("#submitBtn").removeClass("disabled");
          $.dialog({title: '提示', content: data.error, icon: 'error.gif',lock:true , ok: '确定'});
        }
      }).always(function () {
        $("#submitBtn").removeClass("disabled");
      });
    });

  })

</script>

