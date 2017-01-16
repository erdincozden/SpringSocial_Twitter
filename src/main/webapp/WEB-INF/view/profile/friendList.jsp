<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1><spring:message code="Friend List" /></h1>
<table id="friendList">    
    <tr>
    	<th></th>
    	<th><spring:message code="account.userinfo" text="user"/></th>
    </tr>
    <tbody>
    <c:forEach items="${friendLists}" var="friendList">
        <tr>
        <td>
       <a href="<c:url value="${friendList.profileUrl}" />" target="_blank">
        <img src="${friendList.profileImageUrl}" alt="${friendList.name}" title="${friendList.name}" class="thumb" border="0" width="30px"/>
         <c:url value="${friendList.profileUrl}" var="profileImage"/>
         </a>
      	</td>
        <td>${friendList.name}</td>
        </tr>            
    </c:forEach>
    </tbody>
</table>