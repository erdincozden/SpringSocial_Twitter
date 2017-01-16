<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

	<jsp:directive.page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" />

	<div class="header">
		<div class="logo">
			<spring:url value="/index.htm" var="home" />
			<a href="${home}">
				<spring:url value="/resources/images/logoNew.png" var="logo" /> 
				<img src="${logo }" alt="" title="" border="0" />
			</a>
		</div>
		<div class="nav">
			<ul>
				<li>
					<a href="${home}"><spring:message code="nav.home"/></a>
				</li>
				<li>
					<c:if test="${authenticatedAccount !=null}">
				    <spring:url value="/friendList" var="createOrder" />
					<a href="friendlist"><spring:message code="nav.userList"/></a>
					</c:if>
				</li>
				<li>
					<c:if test="${authenticatedAccount !=null}">
						<spring:url value="/myprofile" var="${sessionScope.authenticatedAccount}" />
						<a href="myprofile">${sessionScope.authenticatedAccount.firstName}</a>
					</c:if>
				</li>
				<li>
					<c:if test="${authenticatedAccount ==null}">	
					<spring:url value="/login"  var="login" />
					<a href="${login}"><spring:message code="nav.login"/></a>
					</c:if>
				</li>
				<c:if test="${sessionScope.authenticatedAccount ne null}">
					<li>
	                	<spring:url value="/logout" var="logout" />
    	            	<a href="${logout}"><spring:message code="nav.logout"/></a>
        	        </li>
				</c:if>
				
			</ul>
			<ul style="float: right;">
			 	<c:url value="/resources/images/gb.gif" var="gb"/>
			 	<c:url value="/resources/images/tr.png" var="nl"/>
                <li><a href="?lang=en" class="selected"><img src="${gb}" alt="" title="" border="0" /></a></li>
                <li><a href="?lang=nl"><img src="${nl}" alt="" title="" border="0" /></a></li>
            </ul>
		</div>
	</div>
