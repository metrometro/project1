<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html>
<head>
    <title>trainer</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Final project</h1>
</div>
<hr/>
${sessionScope.user}, hello! Trainer page
<hr/>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2><fmt:message key="label.siteMenu"/></h2>
        </div>

        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers_users'"><fmt:message key="label.showAllMyUsers"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_paid_users_with_personal_trainer'"><fmt:message key="label.showAllPaidUsersWithPersonalTrainer"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_users_without_exercises'"><fmt:message key="label.showAllUsersWithoutExercises"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_users_without_diet'"><fmt:message key="label.showAllUsersWithoutDiet"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_paid_users'"><fmt:message key="label.showAllPaidUsers"/></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=choose_personal_users_mark'"><fmt:message key="label.choosePersonalUsersMark"/></button>
                <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=logout'"><fmt:message key="label.logout"/></button>
            </div>
        </div>
    </div>
</div>

<%--choose mark my users--%>
<c:if test="${personalUsersForMark.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.markMyUsers"/></h2>
            </div>
            <form name="markPersonalUsersVisit" method="POST" action="controller">
                <input type="hidden" name="command" value="mark_personal_users_visit" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${personalUsersForMark}">
                        ${a.login}
                        <input type="checkbox" name="users" value="${a.login}" />
                        <br/>
                    </c:forEach>
                </ul>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.markMyUsersVisit"/></button>
            </form>
            </br>
        </div>
    </div>
</c:if>

<%--show all my users--%>
<c:if test="${currentTrainerUsers.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.myUsers"/></h2>
            </div>
            <ul class=\"w3-ul\">
                <div class="w3-container w3-center">
                    <div class="w3-bar w3-padding-large w3-padding-24">

                        <c:forEach var="a" items="${currentTrainerUsers}">
                            ${a.login}
                            <%--show exercises for chosen user--%>
                            <form name="showUserExercises" method="POST" action="controller">
                                <input type="hidden" name="command" value="show_user_exercises" />
                                <input type="hidden" name="userLogin" value="${a.login}">
                                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.showExercisesForThisUser"/></button>
                            </form>
                            <%--show diet for chosen user--%>
                            <form name="showUserDiet" method="POST" action="controller">
                                <input type="hidden" name="command" value="show_user_diet" />
                                <input type="hidden" name="userLogin" value="${a.login}">
                                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.showDietForThisUser"/></button>
                            </form>
                            </br>
                        </c:forEach>
                    </div>
                </div>
            </ul>
        </div>
    </div>
</c:if>

<%--show exercises for current user--%>
<c:if test="${userExercises.size() == 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.noExercisesForThisUser"/></h2>
            </div>
                <%--choose exercises for current user--%>
            <br/>
            <form name="chooseNewExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_exercises" />
                <c:forEach var="a" items="${userExercises}">
                    <input type="hidden" name="currentExercises" value="${a.exerciseType}"/>
                </c:forEach>
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.chooseNewExercises"/></button>
            </form>
            <br/>
        </div>
    </div>
</c:if>
<c:if test="${userExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.userExercises"/>: ${currentUserLogin}</h2>
            </div>
            <form name="deleteExercises" method="POST" action="controller">
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${userExercises}">
                        ${a.exerciseType}
                    <input type="hidden" name="command" value="delete_chosen_personal_exercises" />
                    <input type="checkbox" name="exercises" value="${a.exerciseType}" />
                    <br/>
                    </c:forEach>
                    <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.deleteChosenExercises"/></button>
            </form><hr/>
                <%--choose exercises for current user--%>
            <form name="chooseNewExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_exercises" />
                <c:forEach var="a" items="${userExercises}">
                    <input type="hidden" name="currentExercises" value="${a.exerciseType}"/>
                </c:forEach>
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.chooseNewExercises"/></button>
            </form>
            <br/>
        </div>
    </div>
</c:if>

<%--set chosen exercises for current user--%>
<c:if test="${allNotSameExercises.size() >= 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.exercisess"/>: ${currentUserLogin}</h2>
            </div>
            <form name="setChosenExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="set_personal_exercises" />
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${allNotSameExercises}">
                        ${a.exerciseType}
                    <input type="checkbox" name="exercises" value="${a.exerciseType}" />
                    <br/>
                    </c:forEach>
                    <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.setPersonalExercises"/></button>
            </form>
            <br/>
            <br/>
        </div>
    </div>
</c:if>

<%--show diet for current user--%>
<c:if test="${userDiet.size() == 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">

            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.thereIsNoDiets"/></h2>
            </div>
                <%--choose diet for current user--%>
            <br/>
            <form name="chooseNewDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_diet" />
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.chooseNewDiet"/></button>
            </form>
            <br/>
        </div>
    </div>
</c:if>
<c:if test="${userDiet.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.userDiet"/>: ${currentUserLogin}</h2>
            </div>
                <%--    delete diet for current user--%>
            <form name="deleteExercises" method="POST" action="controller">
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${userDiet}">
                        ${a.dietType}
                    <input type="hidden" name="command" value="delete_chosen_personal_diet" />
                    <input type="hidden" name="userLogin" value="${currentUserLogin}" />
                    <input type="hidden" name="diet" value="${a.dietType}" />
                    <br/>
                    </c:forEach>
                    <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.deleteChosenExercises"/></button>
            </form><hr/>
        </div>
    </div>
</c:if>

<%--        set personal diet--%>
<c:if test="${allPersonalDiets.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.setDietForUser"/>: ${currentUserLogin}</h2>
            </div>
            <form name="selectExercises" method="POST" action="controller">
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <c:forEach var="a" items="${allPersonalDiets}">
                    ${a.dietType}
                    <input type="hidden" name="command" value="set_personal_diet" />
                    <input type="radio" name="diet" value="${a.dietType}" checked />
                    <br/>
                </c:forEach>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.setPersonalDiet"/></button>
            </form><hr/>
        </div>
    </div>
</c:if>

<%--show all paid users with personal trainer--%>
<c:if test="${usersWithPersonalTrainer.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.userWhoPaidPersonalTrainer"/></h2>
            </div>
            <ul class=\"w3-ul\">
                <c:forEach var="a" items="${usersWithPersonalTrainer}">
                    ${a.login}
                    <form name="becomePersonalTrainer" method="POST" action="controller">
                        <input type="hidden" name="command" value="become_personal_trainer" />
                        <input type="hidden" name="userLogin" value="${a.login}" />
                        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.becomePersonalTrainer"/></button>
                    </form><hr/>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>

<%--show all paid users--%>
<c:if test="${users.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.markUsers"/></h2>
            </div>
            <form name="markVisit" method="POST" action="controller">
                <input type="hidden" name="command" value="mark_users_visit" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${users}">
                        ${a.login}
                        <input type="checkbox" name="users" value="${a.login}" />
                        <br/>
                    </c:forEach>
                </ul>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.markUsersVisit"/></button>
            </form>
            </br>
        </div>
    </div>
</c:if>

<%--show all users withou exercises--%>
<c:if test="${usersWithoutExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.usersWithoutExercises"/></h2>
            </div>
            <form name="chooseExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_exercises" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${usersWithoutExercises}">
                        ${a.login}
                        <input type="hidden" name="currentUserLogin" value="${a.login}">
                        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.chooseExercises"/></button>
                        <br/>
                    </c:forEach>
                </ul>
            </form>
            </br>
        </div>
    </div>
</c:if>

<%--show all users withou diet--%>
<c:if test="${usersWithoutDiet.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.usersWithoutDiet"/></h2>
            </div>
            <form name="chooseDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_diet" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${usersWithoutDiet}">
                        ${a.login}
                        <input type="hidden" name="currentUserLogin" value="${a.login}">
                        <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.chooseDiet"/></button>
                        <br/>
                    </c:forEach>
                </ul>
            </form>
            </br>
        </div>
    </div>
</c:if>

<%--exercises for one user--%>
<c:if test="${AllExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allExercises"/></h2>
            </div>
            <form name="setExercisesForUser" method="POST" action="controller">
                <input type="hidden" name="command" value="set_exercises_for_user" />
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${AllExercises}">
                        ${a.exerciseType}
                        <input type="checkbox" name="exercises" value="${a.exerciseType}" />
                        <br/>
                    </c:forEach>
                </ul>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.setExercisesForUser"/></button>
            </form>
            </br>
        </div>
    </div>
</c:if>

<%--diet for one user--%>
<c:if test="${AllDiets.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allDiets"/></h2>
            </div>
            <form name="setExercisesForUser" method="POST" action="controller">
                <input type="hidden" name="command" value="set_diet_for_user" />
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${AllDiets}">
                        ${a.dietType}
                        <input type="radio" name="diet" value="${a.dietType} checked"/>
                        <br/>
                    </c:forEach>
                </ul>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.setDietForUser"/></button>
            </form>
            </br>
        </div>
    </div>
</c:if>

</body></html>