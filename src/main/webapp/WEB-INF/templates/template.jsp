<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Twitter - </title>
		<spring:url value="/resources/css/style.css" var="bookstoreCss" />
		<link rel="stylesheet" type="text/css" href="${bookstoreCss}" />
		 <script src="<c:url value="/resources/js/jquery-3.1.0.js"/>"></script>
</head>
	</head>

	<body class="tundra">
		<div id="wrap">
			<tiles:insertAttribute name="header"/>

       	<div class="center_content">
			<div class="left_content">
            	<tiles:insertAttribute name="content"/>            
        	</div><!--end of left content-->
        
        	<div class="right_content">
      	     <div class="right_box">
			       <div class="title">
						<spring:url value="/resources/images/bullet4.gif" var="bullet4"/>
						<span class="title_icon"><img src="${bullet4}" alt="" title="" /></span>
						<spring:message code="main.title.randombooks"/>
					</div> 
                    <c:forEach items="${randomTweets}" var="hashtag">
                        <div class="new_prod_box" style="width: 100%">
                            <c:url value="/twitter/hashtag/${hashtag.name}" var="hashtagUrl" />
                             <a href="${hashtagUrl}">${hashtag.name}</a>      
                        </div>
                    </c:forEach>
           	  		</div>
           	  </div>
			<div class="clear"></div>
			</div><!--end of center content-->
			<tiles:insertAttribute name="footer"/>         
    	</div>
	</body>
</html>