
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>
<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Payment OK</h2>
        </div>
<form name="mainButton" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
    <input type="hidden" name="command" value="user_main_button" />
    <button type="submit" class="w3-btn w3-green w3-round-large ">go back</button>
</form><hr/>
</body>
</html>
