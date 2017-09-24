<%@ include file="../common/header.jspf"%>


<div class="container">


	<h1>T-Shirt Transactions Page</h1>


	<form:form method="post" commandName="tshirtAtt">


		<form:input type="hidden" name="id" path="id" />

		<fieldset class="form-group">
			<form:label path="name"> Name : </form:label>
			<form:input path="name" type="text" name="name" class="form-control"
				required="required" maxlength="100"
				disabled="${isUpdateStockLevelForTshirt}" />
			<%
				//Show validation errors related to the name filed wth a bootstrap warning css class. 
					//The validation errors will come from Tshirt java class.
			%>
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="size"> Size : </form:label>
			<form:input path="size" type="text" name="size" class="form-control"
				maxlength="50" disabled="${isUpdateStockLevelForTshirt}" />
			<form:errors path="size" cssClass="text-warning" />

		</fieldset>

		<fieldset class="form-group">
			<form:label path="colour"> Colour : </form:label>
			<form:input path="colour" type="text" name="colour"
				class="form-control" maxlength="50"
				disabled="${isUpdateStockLevelForTshirt}" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="price"> Price : </form:label>
			<form:input path="price" type="text" name="price"
				class="form-control" maxlength="50"
				disabled="${isUpdateStockLevelForTshirt}" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="image">Image : </form:label>
			<form:input path="image" type="text" name="image"
				class="form-control" maxlength="50"
				disabled="${isUpdateStockLevelForTshirt}" />
		</fieldset>


		<fieldset class="form-group">
			<form:label path="stockLevelForTheTshirt">Stock Level : </form:label>
			<form:input path="stockLevelForTheTshirt" type="text"
				name="stockLevelForTheTshirt" class="form-control" maxlength="50"
				disabled="${!isUpdateStockLevelForTshirt}" />
		</fieldset>


		<input type="submit" value="Submit" class="btn btn-success" />
		<a type="button" class="btn btn-danger" href="list-manage-tshirts">Cancel</a>


	</form:form>

</div>


<%@ include file="../common/footer.jspf"%>