<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
${sessionScope.user}, hello! Trainer page
<hr/>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>menu</h2>
        </div>

        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers_users'">show all my users</button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_paid_users_with_personal_trainer'">show all paid users with personal trainer</button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_users_without_exercises'">show all users without exercises</button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_users_without_diet'">show all users without diet</button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_paid_users'">show all paid users</button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=choose_personal_users_mark'">choose personal users mark</button>
                <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=logout'">Logout</button>
            </div>
        </div>
    </div>
</div>

<%--choose mark my users--%>
<c:if test="${personalUsersForMark.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>Mark my users</h2>
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
                <button type="submit" class="w3-btn w3-blue w3-round-large ">mark personal users visit</button>
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
                <h2>Trainer's users</h2>
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
                                <button type="submit" class="w3-btn w3-blue w3-round-large ">show exercises for this user</button>
                            </form>
                            <%--show diet for chosen user--%>
                            <form name="showUserDiet" method="POST" action="controller">
                                <input type="hidden" name="command" value="show_user_diet" />
                                <input type="hidden" name="userLogin" value="${a.login}">
                                <button type="submit" class="w3-btn w3-blue w3-round-large ">show diet for this user</button>
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
                <h2>No exercises fo this user</h2>
            </div>
                <%--choose exercises for current user--%>
            <br/>
            <form name="chooseNewExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_exercises" />
                <c:forEach var="a" items="${userExercises}">
                    <input type="hidden" name="currentExercises" value="${a.exerciseType}"/>
                </c:forEach>
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-blue w3-round-large ">choose new exercises</button>
            </form>
            <br/>
        </div>
    </div>
</c:if>
<c:if test="${userExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>Current user exercises</h2>
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
                    <button type="submit" class="w3-btn w3-blue w3-round-large ">delete chosen exercises</button>
            </form><hr/>
                <%--choose exercises for current user--%>
            <form name="chooseNewExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_exercises" />
                <c:forEach var="a" items="${userExercises}">
                    <input type="hidden" name="currentExercises" value="${a.exerciseType}"/>
                </c:forEach>
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-blue w3-round-large ">choose new exercises</button>
            </form>
            <br/>
        </div>
    </div>
</c:if>

<%--set chosen exercises for current user--%>
<c:if test="${allNotSameExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>Exercises</h2>
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
                    <button type="submit" class="w3-btn w3-blue w3-round-large ">set personal exercises</button>
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
                <h2>There is no diets</h2>
            </div>
                <%--choose diet for current user--%>
            <br/>
            <form name="chooseNewDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_diet" />
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-blue w3-round-large ">choose new diet</button>
            </form>
            <br/>
        </div>
    </div>
</c:if>
<c:if test="${userDiet.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>Current user diet</h2>
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
                    <button type="submit" class="w3-btn w3-blue w3-round-large ">delete chosen exercises</button>
            </form><hr/>
        </div>
    </div>
</c:if>

<%--        set personal diet--%>
<c:if test="${allPersonalDiets.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>Set diet for user</h2>
            </div>
            <form name="selectExercises" method="POST" action="controller">
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <c:forEach var="a" items="${allPersonalDiets}">
                    ${a.dietType}
                    <input type="hidden" name="command" value="set_personal_diet" />
                    <input type="radio" name="diet" value="${a.dietType}" checked />
                    <br/>
                </c:forEach>
                <button type="submit" class="w3-btn w3-blue w3-round-large ">set personal diet</button>
            </form><hr/>
        </div>
    </div>
</c:if>

<%--show all paid users with personal trainer--%>
<c:if test="${usersWithPersonalTrainer.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>User who paid personal trainer</h2>
            </div>
            <ul class=\"w3-ul\">
                <c:forEach var="a" items="${usersWithPersonalTrainer}">
                    ${a.login}
                    <form name="becomePersonalTrainer" method="POST" action="controller">
                        <input type="hidden" name="command" value="become_personal_trainer" />
                        <input type="hidden" name="userLogin" value="${a.login}" />
                        <button type="submit" class="w3-btn w3-blue w3-round-large ">become personal trainer</button>
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
                <h2>Mark users</h2>
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
                <button type="submit" class="w3-btn w3-blue w3-round-large ">mark users visit</button>
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
                <h2>Users without exercises</h2>
            </div>
            <form name="chooseExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_exercises" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${usersWithoutExercises}">
                        ${a.login}
                        <input type="hidden" name="currentUserLogin" value="${a.login}">
                        <button type="submit" class="w3-btn w3-blue w3-round-large ">choose exercises</button>
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
                <h2>Users without diet</h2>
            </div>
            <form name="chooseDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_diet" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${usersWithoutDiet}">
                        ${a.login}
                        <input type="hidden" name="currentUserLogin" value="${a.login}">
                        <button type="submit" class="w3-btn w3-blue w3-round-large ">choose diet</button>
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
                <h2>All Exercises</h2>
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
                <button type="submit" class="w3-btn w3-blue w3-round-large ">set exercises for user</button>
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
                <h2>All diets</h2>
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
                <button type="submit" class="w3-btn w3-blue w3-round-large ">set diet for user</button>
            </form>
            </br>
        </div>
    </div>
</c:if>

</body></html>