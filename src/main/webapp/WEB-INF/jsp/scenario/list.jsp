<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/" %>

<ui:scenariosTemplate pageTitle="pageTitle.listOfScenarios">
    <h1><fmt:message key="pageTitle.listOfScenarios"/></h1>

    <table class="dataTable">
        <thead>
            <tr>
                <th style="width: 60px;"><fmt:message key="dataTable.heading.number"/></th>
                <th style="width: 300px;"><fmt:message key="dataTable.heading.title"/></th>
                <th><fmt:message key="dataTable.heading.scenarioLength"/></th>
                <th style="width: 150px;"><fmt:message key="dataTable.heading.file"/></th>
            </tr>
        </thead>
        <c:forEach items="${scenarioList}" var="scenario">
          <c:if test="${scenario.userMemberOfGroup || !scenario.privateScenario}" >
            <tr>
                <td><a href="<c:url value='detail.html?scenarioId=${scenario.scenarioId}' />"><c:out value="${scenario.scenarioId}" /></a></td>
                <td><c:out value="${scenario.title}" /></td>
                <td><c:out value="${scenario.scenarioLength}" /></td>
                <td>
                    <c:choose>
                        <c:when test="${not empty scenario.scenarioType.scenarioXml}">
                            <a href="<c:url value='download-xml.html?scenarioId=${scenario.scenarioId}' />">
                               <fmt:message key="link.download"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="label.notAvailable"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
          </c:if>
        </c:forEach>
    </table>
</ui:scenariosTemplate>
