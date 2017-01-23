<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${Title}</title>
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
    <link rel="stylesheet/less" type="text/css" href="/static/css/style.less" />
    <script type="text/javascript" src="/static/less/2.5.3/less.min.js"></script>
    <script type="text/javascript" src="/static/jquery/3.1.1/jquery.js"></script>
    <script type="text/javascript" src="/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/js/script.js"></script>
</head>
<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Добавить машину</h4>
            </div>
            <f:form class="form-horizontal" method="post" action="/auto">
                <input type="hidden" name="_method" value="PUT" />
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputModel" class="col-sm-2 control-label">Модель:</label>
                        <div class="col-sm-10">
                            <f:input path="model" type="text" name="model" class="form-control" id="inputModel" placeholder="Model" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputColor" class="col-sm-2 control-label">Цвет:</label>
                        <div class="col-sm-10">
                            <f:input path="color" type="text" name="color" class="form-control" id="inputColor" placeholder="Color" />
                        </div>
                    </div>

                </div>
                <f:input path="user_id" type="hidden" name="user_id" value="${user.getId()}" />
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </f:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="container main-container">
    <div class="row">
        <div class="col-md-3">
            <p>
                <button onclick="addModal()" type="button" class="btn btn-primary btn-lg">
                    Добавить Машину
                </button>
            </p>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Машины пользователя <b>${user.getName()}</b>
                    <a href="/" role="button" class="btn btn-primary btn-sm">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Вернутся
                    </a>
                </div>
                <!-- Table -->
                <table class="table table-bordered" id="autoTable">
                    <tr>
                        <th>#</th>
                        <th>Модель</th>
                        <th>Цвет</th>
                        <th>Действия</th>
                    </tr>
                    <c:forEach var="auto" items="${listAuto}">
                        <tr class="tr-${auto.getId()}">
                            <td>${auto.getId()}</td>
                            <td>${auto.getModel()}</td>
                            <td>${auto.getColor()}</td>
                            <td>
                                <form action="/auto/${auto.getId()}" method="post">
                                    <input id="form-action-method" type="hidden" name="_method" value="DELETE" />
                                    <button type="button" class="btn btn-primary btn-sm remove-btn">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Удалить
                                    </button>
                                </form>
                                <a href="/auto/${auto.getId()}" role="button" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Редактировать
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>