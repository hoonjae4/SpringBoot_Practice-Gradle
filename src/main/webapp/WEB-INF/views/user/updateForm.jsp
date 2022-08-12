<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">
    <!-- <form action="/user/join" method="POST"> 을 사용하지 않을것-->
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">수정 완료</button>
</div>
<!-- static 경로는 알아서 찾아감 -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>


