<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tags.tld"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />

<html>
<head>
    <title>admin</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Final project</h1>
</div>

<h3>Welcome</h3>
<hr/>
${sessionScope.user}, <fmt:message key="label.welcome" />
<hr/>


<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2><fmt:message key="label.siteMenu"/></h2>
        </div>
        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_exercises'"><fmt:message key="label.showAllExercises" /></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_diets'"><fmt:message key="label.showAllDiets" /></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_users'"><fmt:message key="label.showAllUsers" /></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers'"><fmt:message key="label.showAllTrainers" /></button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_not_active_users'"><fmt:message key="label.showAllNotActiveUsers" /></button>
                <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=logout'"><fmt:message key="label.logout"/></button>
            </div>
        </div>
    </div>
</div>

<%--show all exercises--%>
<c:if test="${AllExercises.size() == 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.thereIsNoExercises"/></h2>
            </div>
            <form name="createNewExercise" method="POST" action="controller">
                <input type="hidden" name="command" value="create_new_exercise" />
                </br>
                <textarea type="text" name="exercise" class="w3-btn w3-white w3-round-large" rows="2" cols="40" ></textarea>
                </br>
                </br>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.createExercise"/></button>
                </br>
                </br>
            </form>
        </div>
    </div>
</c:if>
<c:if test="${AllExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <form name="createNewExercise" method="POST" action="controller">
                <input type="hidden" name="command" value="create_new_exercise" />
                </br>
                <textarea type="text" name="exercise" class="w3-btn w3-white w3-round-large" rows="2" cols="40" ></textarea>
                </br>
                </br>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.createExercise"/></button>
                </br>
                </br>
            </form>
        </div>
    </div>
</c:if>
<c:if test="${AllExercises.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allExercises"/></h2>
            </div>
            <form name="deleteExercises" method="POST" action="controller">
                <input type="hidden" name="command" value="delete_exercises" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${AllExercises}">
                        ${a.exerciseType}
                        <input type="checkbox" name="exercises" value="${a.exerciseType}" />
                        <br/>
                    </c:forEach>
                </ul>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.deleteExercises"/></button>
            </form>
            </br>

        </div>
    </div>
</c:if>

<%--show all diets-----------------------------------------------------------------------------------%>
<c:if test="${AllDiets.size() == 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.thereIsNoDiets"/></h2>
            </div>
            <form name="createNewDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="create_new_diet" />
                </br>
                <textarea type="text" name="diet" class="w3-btn w3-white w3-round-large" rows="2" cols="40" ></textarea>
                </br>
                </br>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.createDiet"/></button>
                </br>
                </br>
            </form><hr/>
        </div>
    </div>
</c:if>
<c:if test="${AllDiets.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <form name="createNewDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="create_new_diet" />
                </br>
                <textarea type="text" name="diet" class="w3-btn w3-white w3-round-large" rows="2" cols="40" ></textarea>
                </br>
                </br>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.createDiet"/></button>
                </br>
                </br>
            </form><hr/>
        </div>
    </div>
</c:if>
<c:if test="${AllDiets.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allDiets"/></h2>
            </div>
            <form name="deleteDiets" method="POST" action="controller">
                <input type="hidden" name="command" value="delete_diets" />
                <ul class=\"w3-ul\">
                    <c:forEach var="a" items="${AllDiets}">
                        <td>${a.dietType}</td>
                        <input type="checkbox" name="diets" value="${a.dietType}" />
                        <br/>
                    </c:forEach>
                </ul>
                <button type="submit" class="w3-btn w3-blue w3-round-large "><fmt:message key="label.deleteDiets"/></button>
            </form>
            </br>
        </div>
    </div>
</c:if>

<%--show all active users-----------------------------------------------------------------------------%>
<c:if test="${users.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allUsers"/></h2>
            </div>
            <ul class=\"w3-ul\">
                <div class="w3-container w3-center">
                    <div class="w3-bar w3-padding-large w3-padding-24">
                        <c:forEach var="a" items="${users}">
                            <div class="w3-bar w3-padding-large w3-padding-24">
                                    ${a.login}
                                <form name="deleteCurrentUser" method="POST" action="controller">
                                    <input type="hidden" name="command" value="delete_user" />
                                    <input type="hidden" name="userLogin" value="${a.login}"/>
                                    <button type="submit" class="w3-btn w3-blue w3-round-large"><fmt:message key="label.deleteUser"/></button>
                                </form>
                                <td><form name="makeTrainer" method="POST" action="controller">
                                    <input type="hidden" name="command" value="make_trainer" />
                                    <input type="hidden" name="userLogin" value="${a.login}"/>
                                    <button type="submit" class="w3-btn w3-blue w3-round-large"><fmt:message key="label.makeTrainer"/></button>
                                </form>
                            </div>
                            </br>
                        </c:forEach>
                    </div>
                </div>
            </ul>
        </div>
    </div>
</c:if>


<%--show all deleted users------------------------------------------------------------------------------%>
<c:if test="${allDeletedUsers.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allDeletedUsers"/></h2>
            </div>
            <ul class=\"w3-ul\">
                <div class="w3-container w3-center">
                    <div class="w3-bar w3-padding-large w3-padding-24">
                        <c:forEach var="a" items="${allDeletedUsers}">
                            ${a.login}
                            <form name="restoreUser" method="POST" action="controller">
                                <input type="hidden" name="command" value="restore_user" />
                                <input type="hidden" name="userLogin" value="${a.login}"/>
                                <button type="submit" class="w3-btn w3-blue w3-round-large"><fmt:message key="label.restoreUser"/></button>
                            </form>
                            </br>
                        </c:forEach>
                    </div>
                </div>
            </ul>
        </div>
    </div>
</c:if>

<%--show all trainers----------------------------------------------------------------------------%>
<c:if test="${trainers.size() > 0}">
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2><fmt:message key="label.allTrainers"/></h2>
            </div>
            <ul class=\"w3-ul\">
                <div class="w3-container w3-center">
                    <div class="w3-bar w3-padding-large w3-padding-24">
                        <c:forEach var="a" items="${trainers}">
                            ${a.login}
                            <form name="restoreUser" method="POST" action="controller">
                                <input type="hidden" name="command" value="make_user" />
                                <input type="hidden" name="userLogin" value="${a.login}"/>
                                <button type="submit" class="w3-btn w3-blue w3-round-large"><fmt:message key="label.makeUser"/></button>
                            </form>
                            </br>
                        </c:forEach>
                    </div>
                </div>
            </ul>
        </div>
    </div>
</c:if>
<tag:date text="${local}"/><br>
</body></html>
