<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<div class="container">

	<h1>Welcome Page</h1>

	<%
		//Use the expression lange to read the model data sent by LoginController > showWelcomePageForTheLoggedInUser().
	%>
	<div id="welcomeUser">Welcome ${userObj.userName}!</div>


</div>


<%@ include file="common/footer.jspf"%>