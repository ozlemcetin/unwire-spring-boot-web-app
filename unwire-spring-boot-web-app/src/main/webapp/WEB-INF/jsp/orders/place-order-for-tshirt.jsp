<%@ include file="../common/header.jspf"%>

<div class="container">

	<h1>Place Order For T-Shirt Page</h1>


	<form:form method="post" commandName="orderAtt">

		<fieldset class="form-group">
			<form:label path="tshirt.name"> T-shirt Name : </form:label>
			<form:input path="tshirt.name" type="text" name="tshirt.name"
				class="form-control" disabled="true" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="tshirt.stockLevelForTheTshirt"> Stock Level of T-Shirt : </form:label>
			<form:input path="tshirt.stockLevelForTheTshirt" type="text"
				name="tshirt.stockLevelForTheTshirt" class="form-control"
				disabled="true" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="orderLevelForTheTshirt"> Quantity of T-shirt To Be Ordered : </form:label>
			<form:input path="orderLevelForTheTshirt" type="text"
				name="orderLevelForTheTshirt" class="form-control" />
			<form:errors path="orderLevelForTheTshirt" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="orderDate"> Order Date : </form:label>
			<form:input path="orderDate" type="text" name="orderDate"
				class="form-control" />
			<form:errors path="orderDate" cssClass="text-warning" />
		</fieldset>


		<input type="submit" value="Place Order" class="btn btn-success" />
		<a type="button" class="btn btn-danger" href="list-place-orders">Cancel</a>


	</form:form>


</div>

<%@ include file="../common/footer.jspf"%>
