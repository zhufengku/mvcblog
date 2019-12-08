<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册</title>
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="./bower_components/bootstrap/dist/css/bootstrap-theme.css">
    <script src="./bower_components/jquery/dist/jquery.js"></script>
    <script src="./bower_components/bootstrap/dist/js/bootstrap.js"></script>
</head>

<body>

    <div class="container">

        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">注册</h3>
            </div>
            <div class="panel-body">
                <form action="${applicationScope.contextPath }./regist.do" method="post">

                    <div class="input-group">
                        <div class="input-group-addon">用户名</div>
                        <input type="text" class="form-control" name="userName">
                    </div><br>
                    <div class="input-group">
                        <div class="input-group-addon">密码</div>
                        <input type="text" class="form-control" name="pwd">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success pull-right">注册</button>
                    
                </form>
            </div>

        </div>

    </div>

</body>

</html>