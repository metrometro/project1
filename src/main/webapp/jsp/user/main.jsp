<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>
<hr/>
${user}, hello! User page

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>menu</h2>
        </div>
        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_current_user_state'"><fmt:message key="label.showMyStatus"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=write_comment'"><fmt:message key="label.writeAReview"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_current_user_comments'"><fmt:message key="label.showMyReviews"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_exercises_for_current_user'"><fmt:message key="label.showMyExercises"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_diet_for_current_user'"><fmt:message key="label.showMyDiet"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=payment_form'"><fmt:message key="label.proceedToPayment"/></button>
                ${userAlreadyPaid}
                <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=logout'"><fmt:message key="label.logout"/></button>
            </div>
        </div>
    </div>
</div>

<%--show my status--%>

<c:choose>
    <c:when test="${userState.size() == 0}">
        <div class="w3-container w3-center w3-margin-bottom w3-padding">
            <div class="w3-card-4">
                <div class="w3-container w3-light-blue">
                    <h2><fmt:message key="label.youDidntPay"/></h2>
                </div>
            </div>
        </div>
    </c:when>
    <c:when test="${userState.size() > 0}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.yourStatus"/></h2>
        </div>
        <h1><fmt:message key="label.login"/></h1> ${userState.get(0)}
        <br/>
        <h1><fmt:message key="label.WorkOuts"/></h1> ${userState.get(1)}
        <br/>
        <h1><fmt:message key="label.personalTrainer"/></h1> ${userState.get(2)}
    </div>
</div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<%--make comment--%>

<c:choose>
    <c:when test="${userComment == false}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.youCantWriteReviews"/></h2>
        </div>
    </div>
</div>
    </c:when>
    <c:when test="${userComment == true}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.writeAReview"/></h2>
        </div>
        <form name="CreateComment" method="POST" action="controller">
            <input type="hidden" name="command" value="create_comment" />
            </br>
            <textarea type="text" name="comment" class="w3-btn w3-white w3-round-large" rows="5" cols="45" ></textarea>
            <br/><br/>
            <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.writeAReview"/></button>
        </form>
        <br/>
    </div>
</div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>


<%--show current user comments--%>
<c:choose>
    <c:when test="${comments.size() == 0}">
        <div class="w3-container w3-center w3-margin-bottom w3-padding">
            <div class="w3-card-4">
                <div class="w3-container w3-light-blue">
                    <h2><fmt:message key="label.youDontHaveReviews"/></h2>
                </div>
            </div>
        </div>
    </c:when>
    <c:when test="${comments.size() > 0}">
        <c:forEach var="a" items="${comments}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
            ${a}
    </div>
</div>
        </c:forEach>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<%--show exercises for current user--%>

<c:if test="${exercises.size() == 0}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.thereIsNoExercises"/></h2>
        </div>
    </div>
</div>
</c:if>

<c:if test="${exercises.size() > 0}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.yourExercises"/></h2>
        </div>
        <ul class=\"w3-ul\">
        <c:forEach var="a" items="${exercises}">
            ${a.exerciseType}
            <br/>
        </c:forEach>
        </ul>
    <form name="deleteCurrentUserExercises" method="POST" action="controller">
        <input type="hidden" name="command" value="delete_current_user_exercises" />
        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.deleteCurrentUserExercises"/></button>
    </form>
    <form name="chooseCurrentUserExercises" method="POST" action="controller">
        <input type="hidden" name="command" value="choose_current_user_exercises" />
        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.chooseAnotherExercises"/></button>
    </form>
        </br>
    </div>
</div>
</c:if>
<br/>
<c:if test="${AllExercises.size() > 0}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.exerciseList"/></h2>
        </div>
    <form name="setCurrentUserExercises" method="POST" action="controller">
        <input type="hidden" name="command" value="set_current_user_exercises" />
        <ul class=\"w3-ul\">
        <c:forEach var="a" items="${AllExercises}">
            <td>${a.exerciseType}</td>
            <input type="checkbox" name="exercises" value="${a.exerciseType}" />
            <br/>
        </c:forEach>
        </ul>
        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.setCurrentUserExercises"/></button>
    </form><hr/>
    </div>
</div>
</c:if>

<%--------------------------------------------------------------------------%>
<%--show diet for current user--%>

<c:if test="${diet.size() == 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.thereIsNoDiet"/></h2>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${diet.size() > 0}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.allExercises"/></h2>
        </div>
    <form name="deleteCurrentUserDiet" method="POST" action="controller">
        <input type="hidden" name="command" value="delete_current_user_diet" />
        <ul class=\"w3-ul\">
        <c:forEach var="a" items="${diet}">
            ${a.dietType}
        <input type="hidden" name="diet" value="${a.dietType}" />
        </c:forEach>
        </ul>
        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.deleteCurrentUserDiet"/></button>
    </form>
    <form name="chooseCurrentUserDiet" method="POST" action="controller">
        <input type="hidden" name="command" value="choose_current_user_diet" />
        <button type="submit" class="w3-btn w3-blue w3-round-large ">choose another diet</button>
    </form>
        </br>
    </div>
</div>
</c:if>

<br/>
<c:if test="${AllDiets.size() > 0}">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2><fmt:message key="label.dietsList"/></h2>
        </div>
    <form name="setCurrentUserDiet" method="POST" action="controller">
        <input type="hidden" name="command" value="set_current_user_diet" />
        <ul class=\"w3-ul\">
        <c:forEach var="a" items="${AllDiets}">

            ${a.dietType}
            <input type="radio" name="diet" value="${a.dietType}" checked />
            <br/>
        </c:forEach>
        </ul>
        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.setCurrentUserDiet"/></button>
    </form><hr/>
    </div>
</div>
</c:if>

</body></html>
