<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册信息</title>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="./bower_components/bootstrap/dist/css/bootstrap-theme.css">
    <script src="./bower_components/jquery/dist/jquery.js"></script>
    <script src="./bower_components/bootstrap/dist/js/bootstrap.js"></script>
</head>

<body>

    <div class="container">

        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">注册信息</h3>
            </div>
            <div class="panel-body">
            	<!-- 前缀写requestScope，指明只从requestScope找userName，还有sessionScope,applicationScope -->
   				${requestScope.userName }注册成功。
                    <br>
                    <button type="button" class="btn btn-success pull-right" onclick="location.href='/'">返回首页</button>               
            </div>

        </div>

    </div>

</body>

</html>