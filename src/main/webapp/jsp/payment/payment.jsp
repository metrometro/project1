<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Final project</h1>
</div>
<div class="w3-container w3-padding">
<div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
        <h2><fmt:message key="label.paymentForm"/></h2>
    </div>
    <form name="loginForm" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
        <input type="hidden" name="command" value="choose_payment" />

        <fmt:message key="label.trainerForm"/><select name="trainer" class="w3-btn w3-green w3-round-large">
        <option><fmt:message key="label.withPersonalTrainer"/></option>
        <option><fmt:message key="label.withoutPersonalTrainer"/></option>

    </select>
        <fmt:message key="label.numberOfWorkOuts"/><select name="visits" class="w3-btn w3-green w3-round-large">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
        </select>
        <button type="submit" class="w3-btn w3-green w3-round-large "><fmt:message key="label.toPay"/></button>
    </form><hr/>

    <c:if test="${errorCardData == 'CVC code must contain 3 digits and card numbers must contain 4 digits only.'}">
        <div class="w3-container w3-padding">
            <div class="w3-container w3-center">
                <div class="w3-bar w3-padding-large w3-padding-24">
                    <fmt:message key="label.errorCard"/>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${errorPayment == 'Incorrect card details or not enough funds'}">
        <div class="w3-container w3-padding">
            <div class="w3-container w3-center">
                <div class="w3-bar w3-padding-large w3-padding-24">
                    <fmt:message key="label.errorPayment"/>
                </div>
            </div>
        </div>
    </c:if>

</div>
<c:if test="${priceWithDiscount.size() == 2}">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h4><fmt:message key="label.visits"/></h4>${numberOfVisits}
            <br/>
            <h4><fmt:message key="label.price"/></h4>${priceWithDiscount.get(0)}
            <br/>
            <h4><fmt:message key="label.discount"/></h4>${priceWithDiscount.get(1)}
                <br/>
                <c:if test="${personalTrainer == true}">
                <h4><fmt:message key="label.personalTrainerFrom"/></h4>
                </c:if>
        </div>


        <form name="choosePayment" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
            <input type="hidden" name="command" value="pay" />
            <input type="hidden" name="personalTrainer" value="${personalTrainer}">
            <input type="hidden" name="numberOfVisits" value="${numberOfVisits}">
            <input type="hidden" name="price" value="${priceWithDiscount.get(0)}">
            <label>First code: <input type="text" name="firstCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
                    <%--        required pattern="[0-9]{4}"--%>
            </label>
            <label><fmt:message key="label.fourNumbers"/><input type="text" name="secondCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label>
            <label><fmt:message key="label.fourNumbers"/><input type="text" name="thirdCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label>
            <label><fmt:message key="label.fourNumbers"/><input type="text" name="fourthCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label>
            <label><fmt:message key="label.cvc"/>:<input type="text" name="cvc" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label><br>
            <button type="submit" class="w3-btn w3-green w3-round-large "><fmt:message key="label.toPay"/></button>

        </form>
        <br/>
    </div>
</c:if>

    <form name="mainButton" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
        <input type="hidden" name="command" value="user_main_button" />
        <button type="submit" class="w3-btn w3-green w3-round-large "><fmt:message key="label.goBack"/></button>
    </form><hr/>

</body>
</html>
