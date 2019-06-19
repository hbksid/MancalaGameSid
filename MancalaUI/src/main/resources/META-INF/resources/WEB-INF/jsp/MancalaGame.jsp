<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mancala.css">

    <meta charset="utf-8">
    <title>Mancala Game</title>
</head>

<body>
<h2 class="title">PLAY</h2>
<div class="container">
    <div class="message">
        ${message}
    </div>
    <table class="gameBoard" border="1">
        <thead>
        <td>
            PLAYER 2
        </td>
        <td>PIT</td>
        <td>PIT</td>
        <td>PIT</td>
        <td>PIT</td>
        <td>PIT</td>
        <td>PIT</td>
        <td>
            PLAYER 1
        </td>
        </thead>
        <tr>
            <c:choose>
                <c:when test="${MancalaGame.player2.active}">
                    <td rowspan="2">
                            ${MancalaGame.board.pits[totalPits - 1].numberOfSeeds}
                    </td>
                    <c:forEach var="i" begin="1" end="${rowLength - 1}" step="1" varStatus="status">
                        <td>
                            <a href="${pageContext.request.contextPath}/makeMove.do?pitId=${MancalaGame.board.pits[totalPits - 1 - i].id}">${MancalaGame.board.pits[totalPits - 1 - i].numberOfSeeds}</a>
                        </td>
                    </c:forEach>
                    <td rowspan="2">
                            ${MancalaGame.board.pits[totalPits - 1 - rowLength].numberOfSeeds}
                    </td>
                </c:when>
                <c:otherwise>
                    <td rowspan="2">
                            ${MancalaGame.board.pits[totalPits - 1].numberOfSeeds}
                    </td>
                    <c:forEach var="i" begin="1" end="${rowLength - 1}" step="1" varStatus="status">
                        <td>
                                ${MancalaGame.board.pits[totalPits - 1 - i].numberOfSeeds}
                        </td>
                    </c:forEach>
                    <td rowspan="2">
                            ${MancalaGame.board.pits[totalPits - 1 - rowLength].numberOfSeeds}
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${MancalaGame.player1.active}">
                    <c:forEach var="i" begin="0" end="${rowLength - 2}" step="1" varStatus="status">
                        <td>
                            <a href="${pageContext.request.contextPath}/makeMove.do?pitId=${MancalaGame.board.pits[i].id}">${MancalaGame.board.pits[i].numberOfSeeds}</a>
                        </td>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="i" begin="0" end="${rowLength - 2}" step="1" varStatus="status">
                        <td>
                                ${MancalaGame.board.pits[i].numberOfSeeds}
                        </td>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>

    <br>
    <br>
    <br>

    <h2>
        <a href="${pageContext.request.contextPath}/">Go back to start</a>
    </h2>

</div>
</body>
</html>