<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tags.tld"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Final project</h1>
</div>
<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2><fmt:message key="label.paymentWasSuccessful"/></h2>
        </div>
<form name="mainButton" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
    <input type="hidden" name="command" value="user_main_button" />
    <button type="submit" class="w3-btn w3-green w3-round-large "><fmt:message key="label.goBack"/></button>
</form>
</body>
</html>
