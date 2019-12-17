<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>
<div class="w3-container w3-padding">
<div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
        <h2>Registration</h2>
    </div>
<form name="registrationForm" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
    <input type="hidden" name="command" value="registration" />
    <label>Login:<input type="text" name="login" class="w3-input w3-border w3-round-large" style="width: 30%" value="${login}" />
        ${errorLoginAlreadyExists}
        ${errorLogin}
    </label>
        <label>Password:<input type="password" name="password" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            ${errorPassword}
        </label>
            <label>Repeat password:<input type="password" name="repeat password" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
                ${errorRepeatPassword}
            </label>
                <label>First name:<input type="text" name="first name" class="w3-input w3-border w3-round-large" style="width: 30%" value="${firstName}" required />
    ${errorFirstName}
                </label>
    <label>Second name:<input type="text" name="last name" class="w3-input w3-border w3-round-large" style="width: 30%" value="${lastName}" />
    ${errorLastName}
    </label>
    <label>e-mail:<input type="text" name="mail" class="w3-input w3-border w3-round-large" style="width: 30%" value="${mail}" />
    ${errorEmail}
    </label>
    <br/>
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <button type="submit" class="w3-btn w3-green w3-round-large ">registration</button>
</form><hr/>
</div>
<br/>
<form name="registrationButton" method="POST" action="controller">
    <input type="hidden" name="command" value="login_button" />
    <button type="submit" class="w3-btn w3-green w3-round-large ">go to login</button>
</form><hr/>
</div>
</body>
</html>
