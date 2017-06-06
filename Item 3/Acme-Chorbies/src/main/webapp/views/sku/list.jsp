<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag"
	name="skus" requestURI="sku/administrator/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="SKUPROPERTYCODE" var="skucode"></spring:message>
	
	<jstl:choose>
		<jstl:when test="${adminSkus.contains(row)}">
			<display:column title="${skucode}">
				<p style="font-weight: bold; font-style: italic"><jstl:out value="${row.SKUPROPERTY}" /></p>
			</display:column>
		</jstl:when>
		
		<jstl:otherwise>
			<acme:column code="SKUPROPERTYCODE" property="${row.SKUPROPERTY}"/>
		</jstl:otherwise>
	
	</jstl:choose>
	
	<!-- REST OF ATTRIBUTES -->
	
	<!-- ACTION LINKS -->
	<jstl:if test="${!row.cancelled}">
		<acme:link href="sku/administrator/cancel.do?skuId=${row.id}" code="misc.cancel"/>
	</jstl:if>
	
</display:table>

<br />