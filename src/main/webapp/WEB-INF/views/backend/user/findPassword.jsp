<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh"
           uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
  <title>智能答疑后台管理系统</title>
  <meta name="viewport"
        content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
  <link rel="icon" href="/icon.png" type="image/x-icon" />
  <link rel="SHORTCUT ICON" href="/icon.png"  type="image/x-icon" />
</head>

<div class="page-content-area">
  <form id="form-school" class="form-signin" method="post">

    <div class="singleContent">
      <div class="left">邮箱：</div>
      <div class="right">
        <input type="text" id="email" name="email" isNull="false" checkType="empty" />
        <span style="color: red">*</span>
        <button id="sendMail" style="width: 80px; height: 30px;"
                class="btn btn-xs btn-success bigger" type="button">发送邮件
        </button>&nbsp;&nbsp;&nbsp;&nbsp;
        <font style="color:blue; font-size: 13px" id="message"></font>
      </div>
    </div>

    <div class="singleContent">
      <div class="left">验证码：</div>
      <div class="right">
        <input type="text" id="valiCode" name="valicode" isNull="false" checkType="empty" />
        <span style="color: red">*</span>
      </div>
    </div>

    <div class="singleContent">
      <div class="left">用户名：</div>
      <div class="right">
        <input type="text" id="loginName" name="loginName" isNull="false" checkType="empty" />
        <span style="color: red">*</span>
      </div>
    </div>

    <div class="singleContent">
      <div class="left">学校编码：</div>
      <div class="right">
        <input type="text" id="schoolCode" name="schoolCode" isNull="false" checkType="empty" />
        <span style="color: red">*(如不记得学校标识，可询问招办)</span>
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
    $("#sendMail").click(function (e) {
      sendMail();
    });


    $("#submitBtn").click(function (e) {
      formValidate();
      if (validat() == false) {
        alert("请先填写数据");
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
        url: "/user/findPassword/updatePassword",
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

  function sendMail(){
    var emailReg = /^[_a-z 0-9]+@([_a-z 0-9]+\.)+[a-z 0-9]{2,3}$/;
    if($("#email").val() == null || $("#email").val() == ''){
      alert("请填写验证码接收邮箱");
      return false;
    }
    if($("#email").val() != '' && !emailReg.test($("#email").val())){
      alert("请输入正确的邮箱格式");
      return false;
    }

    var params = $("#form-school").serialize();
    $("#sendMail").addClass("disabled");

    $.ajax({
      type: "POST",
      url: "/user/findPassword/sendPassWordMail",
      data: params
    }).done(function (data) {
        $("#sendMail").removeClass("disabled");
        $("#message").html(data.message);
    }).always(function () {
      $("#sendMail").removeClass("disabled");
    });

  }

</script>

