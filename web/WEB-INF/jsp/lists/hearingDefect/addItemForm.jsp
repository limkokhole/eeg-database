<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ui:listsTemplate pageTitle="pageTitle.addEditHearingImpairment">
    <c:choose>
        <c:when test="${addHearingDefect.hearingImpairmentId > 0}">
            <!-- editation -->
            <h1><fmt:message key="pageTitle.editHearingImpairment"/></h1>

            <c:url value="edit.html?id=${addHearingDefect.hearingImpairmentId}" var="formUrl"/>
        </c:when>
        <c:otherwise>
            <!-- creating new -->
            <h1><fmt:message key="pageTitle.addHearingImpairment"/></h1>

            <c:url value="add.html" var="formUrl"/>
        </c:otherwise>
    </c:choose>

    <form:form action="${formUrl}" method="post" commandName="addHearingDefect" cssClass="standardInputForm">
        <fieldset>

            <form:hidden path="hearingImpairmentId"/>

            <div class="itemBox">
                <form:label path="description" cssClass="textFieldLabel"
                            cssErrorClass="textFieldLabel errorLabel"><fmt:message
                        key="label.description"/></form:label>

                <form:input path="description" cssClass="textField" cssErrorClass="textField errorField"/>

                <form:errors path="description" cssClass="errorBox"/>
            </div>


            <div class="itemBox">
                <input type="submit" value="<fmt:message key='button.save'/>" class="submitButton lightButtonLink"/>
            </div>

        </fieldset>
    </form:form>
</ui:listsTemplate>
