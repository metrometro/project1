
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <h2>Payment form</h2>
    </div>

    <h1>Personal trainer<h1/>
        <form name="choosePayment" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
            <input type="hidden" name="command" value="choose_payment" />
            <input type="hidden" name="user" value="${sessionScope.user}">

            <input type="checkbox" name="trainer" value="true"  />
            <br><br>

            <select name="visits" class="w3-btn w3-green w3-round-large">
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
            <button type="submit" class="w3-btn w3-green w3-round-large ">choose payment</button>
        </form>
        <br/>
        ${errorPayment}
</div>
<c:if test="${priceWithDiscount.size() == 2}">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>visits: </h2>${numberOfVisits}
            <br/>
            <h2>price: </h2>${priceWithDiscount.get(0)}
            <br/>
            <h2>discount: <h2>${priceWithDiscount.get(1)}
                <br/>
                <c:if test="${personalTrainer == true}">
                <h2>personal trainer</h2>
                </c:if>
        </div>


        <form name="choosePayment" method="POST" class="w3-selection w3-light-grey w3-padding" action="controller">
            <input type="hidden" name="command" value="pay" />
            <input type="hidden" name="user" value="${sessionScope.user}">
            <input type="hidden" name="user" value="${priceWithDiscount}">
            <input type="hidden" name="personalTrainer" value="${personalTrainer}">
            <input type="hidden" name="numberOfVisits" value="${numberOfVisits}">
            <input type="hidden" name="price" value="${priceWithDiscount.get(0)}">
            <label>First code: <input type="text" name="firstCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
                    <%--        required pattern="[0-9]{4}"--%>
            </label>
            <label>Second code:<input type="text" name="secondCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label>
            <label>Third code:<input type="text" name="thirdCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label>
            <label>Fourth code:<input type="text" name="fourthCode" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label>
            <label>CVC:<input type="text" name="cvc" class="w3-input w3-border w3-round-large" style="width: 30%" value="" />
            </label><br>
            <button type="submit" class="w3-btn w3-green w3-round-large ">pay</button>
                ${errorCardData}
        </form>
        <br/>
    </div>
</c:if>

</body>
</html>
