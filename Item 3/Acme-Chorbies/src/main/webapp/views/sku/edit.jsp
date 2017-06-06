<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="sku/administrator/edit.do" modelAttribute="sku" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="event" />
	
	<jstl:if test="${!isCancel}">
		<!-- OTHER ATTRIBUTES -->
		<acme:textbox code="" path=""/>
		
		<acme:submit name="save" code="misc.save"/>
	</jstl:if>
	
	<jstl:if test="${isCancel}">
		<!-- ONLY JUSTIFICATION, ADMIN IS CANCELLING -->
		<acme:textbox code="sku.justification" path="justification"/>
		<acme:submit name="cancellation" code="misc.save"/>
	</jstl:if>
	
	<input type="button" name="cancel"
		value="<spring:message code="misc.cancel" />"
		onclick="window.location='sku/administrator/list.do'" />
		
</form:form>