<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">


	<h1>List/Manage T-shirts</h1>


	<div id="listManageTshirts">

		<div id="showWarningMessage">
			<c:choose>
				<c:when test="${userObj.isAdmin()}">
				An admin user can ,
    				<ol>
						<li>Add/remove/modify T-shirts</li>
						<li>Set stock levels</li>
						<li>See all t-shirts (available or not)</li>
					</ol>
				</c:when>
				<c:otherwise>
				A user can, 
					<ol>
						<li>See only available t-shirts</li>
					</ol>
				</c:otherwise>
			</c:choose>
		</div>

	
		<%
			//  Only admin users can add t-shirts
		%>
		<c:if test="${userObj.isAdmin()}">

			<p />
			<div id="addTshirt">
				<a href="add-tshirt" class="button">ADD T-SHIRT </a>
			</div>

		</c:if>

		<table class="table table-striped">
			<caption>T-shirts List</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Size</th>
					<th>Colour</th>
					<th>Price</th>
					<th>Image</th>
					<th>Stock Level</th>

					<%
						//  Only admin users
					%>
					<c:if test="${userObj.isAdmin()}">

						<th>Update Stock Level</th>
						<th>Update T-shirt</th>
						<th>Delete T-shirt</th>

					</c:if>
				</tr>
			</thead>
			<tbody>

				<%
					//Use the expression lange to read the model data sent by WelcomeController > showAvailableTshirts().
					// For every item in tshirtsList create a variable tshirt and opulate the date for that
				%>
				<c:forEach items="${tshirtsList}" var="tshirt">
					<tr>
						<td>${tshirt.name}</td>
						<td>${tshirt.size}</td>
						<td>${tshirt.colour}</td>
						<td>${tshirt.price}</td>
						<td>${tshirt.image}</td>
						<td>${tshirt.stockLevelForTheTshirt}</td>

						<%
							//  Only admin users see the update and delete buttons
						%>
						<c:if test="${userObj.isAdmin()}">

							<td><a type="button" class="btn btn-success"
								href="update-stock-level-for-tshirt?tshirtId=${tshirt.id}&newStockLevel=${tshirt.stockLevelForTheTshirt}">
									Stock Level Update </a></td>
							<td><a type="button" class="btn btn-warning"
								href="update-tshirt?tshirtId=${tshirt.id}"> UPDATE</a></td>
							<td><a type="button" class="btn btn-warning"
								href="delete-tshirt?tshirtId=${tshirt.id}"> DELETE</a></td>

						</c:if>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>


</div>

<%@ include file="../common/footer.jspf"%>