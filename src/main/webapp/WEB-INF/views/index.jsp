<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
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
    <body>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Добавить пользователя</h4>
                    </div>
                    <f:form modelAttribute="userForm" class="form-horizontal" method="post" action="/user">
                        <input id="form-action-method" type="hidden" name="_method" value="PUT" />
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="inputUsername" class="col-sm-2 control-label">Имя:</label>
                                <div class="col-sm-10">
                                    <f:input path="name" type="text" name="name" class="form-control" id="inputUsername" placeholder="Username" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputAge" class="col-sm-2 control-label">Возраст:</label>
                                <div class="col-sm-10">
                                    <f:input path="age"  type="text" name="age" class="form-control" id="inputAge" placeholder="Age" />
                                </div>
                            </div>
                        </div>
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
                            Добавить Пользователя
                        </button>
                    </p>
                </div>
                <div class="col-md-9">
                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading">Пользователи</div>
                        <!-- Table -->
                        <table class="table table-bordered">
                            <tr>
                                <th>#</th>
                                <th>Имя</th>
                                <th>Вазраст</th>
                                <th>Действия</th>
                            </tr>
                            <c:forEach var="user" items="${userList}">
                            <tr class="tr-${user.getId()}">
                                <td><a href="/user/${user.getId()}">${user.getId()}</a></td>
                                <td><a href="/user/${user.getId()}">${user.getName()}</a></td>
                                <td><a href="/user/${user.getId()}">${user.getAge()}</a></td>
                                <td>
                                    <a href="/user/${user.getId()}/autos" role="button" class="btn btn-primary btn-sm">
                                        <span class="glyphicon glyphicon-list" aria-hidden="true"></span> Авто
                                    </a>
                                    <form action="/user/${user.getId()}" method="post">
                                        <input type="hidden" name="_method" value="DELETE" />
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Удалить
                                        </button>
                                    </form>
                                    <a href="/user/${user.getId()}" role="button" class="btn btn-primary btn-sm">
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
