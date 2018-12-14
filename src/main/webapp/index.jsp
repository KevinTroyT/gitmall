<%@ page language="java"  contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h2>SpringMVC 上传到ftp测试</h2>

springmvc 上传文件
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="go">
</form>

富文本图片上传文件
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="go">
</form>
</body>
</html>
