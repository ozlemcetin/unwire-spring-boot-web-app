<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">


	<h1>List/Place Orders</h1>


	<div id="placeOrders">

		<p />
		<div id="showErrorMessage">
			<font color="red"> ${errorMessageForTshirtTransaction}</font>
		</div>

		<div id="showSuccessMessage">
			<font color="green"> ${successMessageForTshirtTransaction}</font>
		</div>


		<table class="table table-striped">
			<caption>Place Order for Available T-shirts</caption>
			<thead>
				<tr>
					<th>T-shirt Name</th>
					<th>Stock Level of T-Shirt</th>
					<th>Place Order</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${ordersForTshirtsList}" var="order">
					<tr>
						<td>${order.tshirt.name}</td>
						<td>${order.tshirt.stockLevelForTheTshirt}</td>

						<td><a type="button" class="btn btn-success"
							href="place-order-for-tshirt?userId=${order.userId}&tshirtId=${order.tshirtId}">
								Place on Order </a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>

	<div id="listOrders">

		<table class="table table-striped">
			<caption>Previously Made Orders</caption>
			<thead>
				<tr>
					<th>T-shirt Name</th>
					<th>Quantity of T-shirt To Be Ordered</th>
					<th>Order Date</th>
					<th>Order Placed</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${previouslyMadeOrdersList}" var="order">
					<tr>
						<td>${order.tshirt.name}</td>
						<td>${order.orderLevelForTheTshirt}</td>
						<td><fmt:formatDate value="${order.orderDate}"
								pattern="dd/MM/yyyy" /></td>
						<td>${order.orderForThsirtSuccesfullyPlaced}</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>


</div>


<%@ include file="../common/footer.jspf"%>