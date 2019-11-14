<%--
  Created by IntelliJ IDEA.
  User: Nero.Blade
  Date: 2019/11/4
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/root/updateInfo" method="post" enctype="multipart/form-data">
    上传头像：<input type="file" name="headImg"/><br/>
    <input type="submit" value="提交"/>
</form>


<%--<form action="${pageContext.request.contextPath}/root/updateInfo" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"/>
    密码：  <input type="text" name="password"/>
    头像：  <input type="file" name="headImg"/>
    <input type="submit" value="注册">
</form>--%>


</body>
</html>
