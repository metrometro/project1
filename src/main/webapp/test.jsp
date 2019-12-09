<%--
  Created by IntelliJ IDEA.
  User: Metro
  Date: 08.12.2019
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--show exercises for current user--%>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <c:if test="${userDiet.size() == 0}">
            <div class="w3-container w3-light-blue">
                <h2>No вшуеы</h2>
            </div>
            <%--choose exercises for current user--%>
            <br/>
            <form name="chooseNewDiet" method="POST" action="controller">
                <input type="hidden" name="command" value="choose_personal_diet" />
                <input type="hidden" name="userLogin" value="${currentUserLogin}"/>
                <button type="submit" class="w3-btn w3-green w3-round-large ">choose new diet</button>
            </form>
            <br/>
        </c:if>

        <c:if test="${userDiet.size() > 0}">

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
                    <button type="submit" class="w3-btn w3-green w3-round-large ">delete chosen exercises</button>
            </form><hr/>
        </c:if>
        <c:if test="${userDiet.size() > 0}">

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
                <button type="submit" class="w3-btn w3-green w3-round-large ">set personal diet</button>
            </form><hr/>
        </c:if>
    </div>
</div>

<%--set chosen exercises for current user--%>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <c:if test="${allNotSameExercises.size() > 0}">
            <div class="w3-container w3-light-blue">
                <h2>Another exercises</h2>
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
                    <button type="submit" class="w3-btn w3-green w3-round-large ">set personal exercises</button>
            </form>
            <br/>
            <br/>
        </c:if>
    </div>
</div>





</body>
</html>
