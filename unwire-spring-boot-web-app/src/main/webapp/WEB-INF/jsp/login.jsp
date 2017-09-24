<%@ include file="common/header.jspf"%>


<div class="container">

	<h1>Unwire - Spring Boot Web App - Login Page</h1>

	<div id="formSubmitForUserLogIn">

		<form method="post">

			<div id="enterUserCredentials">

				<fieldset class="form-group">
					<label> User Name :</label> <input type="text" name="userName"
						class="form-control" required="required" maxlength="50" />
				</fieldset>

				<input type="submit" value="Log In" class="btn btn-success" />
			</div>

			<div id="showErrorMessage">
				<font color="red"> ${errorMessageForTheUserName}</font>
			</div>

			<div id="showWarningMessage">
				<p />
				'Admin' or 'user' are the only valid user name literals. <br />
				Type admin in the textbox to have admin previliges or else <br />
				type user to test the basic functionality of the web application.
			</div>

		</form>

	</div>
</div>

<%@ include file="common/footer.jspf"%>