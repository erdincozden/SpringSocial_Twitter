<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1><spring:message code="Tweets" /></h1>
<form:form method="GET" modelAttribute="tweetSearchCriteria"
	id="tweetSearchForm">
	<fieldset>
		<legend>
			<spring:message code="tweet.searchcriteria" />
		</legend>
		<table>
			<tr>
				<td><form:label path="title"><spring:message code="tweet.title" />
					</form:label></td>
				<td><form:input path="title" /></td>
			</tr>
		</table>
	</fieldset>
	<button id="search">
		<spring:message code="button.search" />
	</button>
</form:form>
<script>
$('#tweetSearchForm').submit(function(evt){
	evt.preventDefault();
	var title = $('#title').val();
	var json = {"title" : title};
		$.ajax({
		url: 'index2',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(json),
		success: function(tweets) {
			var content = '';
			for (var i = 0; i<tweets.length; i++) {
				content += '<tr>';
				content += '<td><img src="'+ tweets[i].user.profileImageUrl+'" alt="'+tweets[i].user.name+'"/></td>';
				content += '<td>'+tweets[i].user.name+'</td>';
                content += '<td>'+tweets[i].text+'</td>';
                content += '</tr>';
			}
			$('#tweetSearchResults tbody').html(content);
		}
	});	
});
</script>
<table id="tweetSearchResults">    
    <tr>
    	<th></th>
    	<th><spring:message code="account.userinfo" text="user"/></th>
    	<th>Tweet</th>
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
