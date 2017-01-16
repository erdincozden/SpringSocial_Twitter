<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="${userprofile.profileImageUrl}" var="userImage" />
<img src="${userprofile.profileImageUrl}" align="left"
	alt="${userprofile.name}" width="80" />

<table style="background-color:gray; color: white;">
	<tr>
		<td style="text-decoration: underline;">Name</td>
		<td>${userprofile.name}</td>
		<td style="text-decoration: underline;">Username</td>
		<td>${userprofile.screenName}</td>
	</tr>
	<tr>
		<td style="text-decoration: underline;">Friends Count</td>
		<td>${userprofile.friendsCount}</td>
		<td style="text-decoration: underline;">Followers Count</td>
		<td>${userprofile.followersCount}</td>
	</tr>
	<tr>
		<td style="text-decoration: underline;">Created Date</td>
		<td>${userprofile.createdDate}</td>
		<td style="text-decoration: underline;">Favorites Count</td>
		<td>${userprofile.favoritesCount}</td>
	</tr>
</table>
<div class="container">
	<h1>Send new Tweet</h1>

	<c:if test="${not empty message}">
			${message}<br />
		<a href="${pageContext.request.contextPath}/blogposts">Blog Posts</a>
	</c:if>
	<form action="${pageContext.request.contextPath}/sendTweet"
		method="post">
		<table style="width: 100%" class="table table-striped">
			<tr>
				<td>Tweet</td>
				<td><textarea style="width:100%" name="content" required maxlength="169" rows="2"></textarea></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="submit" value="Send"></td>
			</tr>
		</table>
	</form>
</div>
<table id="tweetSearchResults">    
    <tr>
    	<th></th>
    	<th><spring:message code="account.userinfo" text="user"/></th>
    	<th>My Last Tweets</th>
    </tr>
    <tbody>
    <c:forEach items="${tweets}" var="tweet">
        <tr>
        <td>
        <a href="<c:url value="${tweet.user.profileUrl}" />" target="_blank">
        <img src="${tweet.user.profileImageUrl}" alt="${tweet.user.name}" title="${tweet.user.name}" class="thumb" border="0" width="30px"/>
         <c:url value="${tweet.user.profileImageUrl}" var="bookImage"/>
         </a>
      	</td>
        <td>${tweet.user.name}</td>
        <td>${tweet.text}</td>
        </tr>        
    </c:forEach>
    </tbody>
</table>



