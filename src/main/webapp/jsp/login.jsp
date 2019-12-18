<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Final project</h1>
</div>
<div class="w3-container w3-padding">
<div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
        <h2>Log in</h2>
    </div>
<form name="loginForm" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
    <input type="hidden" name="command" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" value="login" />
    <label>Login:<input type="text" name="login" class="w3-input w3-border w3-round-large" style="width: 30%" value=""/>
    </label>
    <label>Password:<input type="password" name="password" class="w3-input w3-border w3-round-large" style="width: 30%" value=""/>
    </label>
        <br/>
            ${errorLoginPassMessage}
        <br/>
            ${wrongAction}
        <br/>
            ${nullPage}
        <br/>
            ${notActiveAccount}
        <br/>
    local: <select name="local" class="w3-btn w3-green w3-round-large">
    <option>EN</option>
    <option>RU</option>
</select>
    <button type="submit" class="w3-btn w3-green w3-round-large ">log in</button>
</form><hr/>
</div>

<form name="registrationButton" method="POST" action="controller">
    <input type="hidden" name="command" value="registration_button" />
    <button type="submit" class="w3-btn w3-green w3-round-large ">registration</button>
</form><hr/>
</div>


</body>
</html>
