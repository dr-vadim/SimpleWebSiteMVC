<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
    <head>
        <head>
            <title>${Title}</title>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" href="/static/bootstrap/3.3.7/css/bootstrap.min.css" />
            <link rel="stylesheet" type="text/css" href="/static/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
            <link rel="stylesheet/less" type="text/css" href="/static/css/style.less" />
            <script type="text/javascript" src="/static/less/2.5.3/less.min.js"></script>
            <script type="text/javascript" src="/static/jquery/3.1.1/jquery.js"></script>
            <script type="text/javascript" src="/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="/static/js/script.js"></script>
        </head>
    </head>
    <body>
        <div class="container main-container">
            <a href="/" role="button" class="btn btn-primary btn-sm">
                Назад
            </a>
            <f:form method="post" action="/user" modelAttribute="userForm">
                <f:input type="hidden" path="id" />
                <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Имя:</label>
                    <div class="col-sm-10">
                        <f:input path="name" id="inputName" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputAge" class="col-sm-2 control-label">Возраст:</label>
                    <div class="col-sm-10">
                        <f:input path="age" id="inputAge" />
                    </div>
                </div>
                <f:button type="submit" class="btn btn-primary btn-sm">Сохранить</f:button>
            </f:form>
        </div>
    </body>
</html>
