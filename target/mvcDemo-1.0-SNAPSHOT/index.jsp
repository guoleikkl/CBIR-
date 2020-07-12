<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
</head>
<body class="container">
<h2>基于内容的图像检索系统</h2>

<form class="btn-default.focus" action="search" method="post" enctype="multipart/form-data">
    <div>
        <h4>选择检索方式</h4>
    </div>
    <div>
        <label class="radio-inline">
            <input type="radio" name="choice" value="a" checked="checked">图像颜色直方图
        </label>
        <label class="radio-inline">
            <input type="radio" name="choice" value="b">感知哈希算法
        </label>
        <label class="radio-inline">
            <input type="radio" name="choice" value="c">HSV颜色空间
        </label>
        <label class="radio-inline">
            <input type="radio" name="choice" value="d">图像纹理
        </label>

    </div>
    <div> </div>
    <h4>文件上传：</h4>
    <input class="btn btn-success" type="file" name="image" ><br/>
    <input  class="btn btn-success" style="display:block;margin:0 auto"
           type="submit" ><br/>
</form>

</body>
</html>
