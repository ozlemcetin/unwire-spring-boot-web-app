
package interview.assignment.selling.tshirts.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import interview.assignment.selling.tshirts.model.Tshirt;
import interview.assignment.selling.tshirts.model.Order;
import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.service.OrderService;
import interview.assignment.selling.tshirts.service.StockService;
import interview.assignment.selling.tshirts.service.TshirtService;
import interview.assignment.selling.tshirts.service.UserService;

/*
 * Use @SessionAttributes("userObj") to use the User session attribute which was put into the session by the LoginController.
 */

@Controller
@SessionAttributes("userObj")
public class LoginController {
	/*
	 * For dependency injection framework to work, (step number 1 to be in the
	 * UserService), step number 2 tell that UserService to be injected
	 * automatically, auto wire it.
	 * 
	 */

	/*
	 * Use @Autowired to tell that when the UserService bean is found, take it and
	 * auto wire it in here. UserService will be created by the spring framework.
	 */
	@Autowired
	UserService userService;

	/*
	 * Use this method only for the GET request (aka when
	 * http://localhost:8080/login is typed in the browser.)
	 */
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String showLogInPage(ModelMap model) {

		/*
		 * If you want to print a string message on the web page, use @ResponseBody
		 * above the method signature.
		 */
		// return "Hello World!";

		/*
		 * Search for a view named login (aka login.jsp), make the neccassary changes in
		 * the application.properties for that.
		 */
		return "login";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.POST)
	public String showWelcomePageForTheLoggedInUser(ModelMap model, @RequestParam String userName) {

		/*
		 * Use (@RequestParam String userName) in the method signature if you want to
		 * read parameter values sent by the login.jsp form.
		 */

		// Validate the logged in user
		// Find the user in the repository
		User loggedInUser = null;

		if (userName != null)
			loggedInUser = userService.findUserByUserName(userName.toLowerCase());

		// User is not found, redirect to the login.jsp
		if (loggedInUser == null) {

			String errorMessageForTheUserName = userName + "is not a valid user name.";
			model.put("errorMessageForTheUserName", errorMessageForTheUserName);

			// User is not valid, to try again forward the user to the login.jsp
			return "login";

		}

		/*
		 * Model is used to pass data from controller to view (jsp) or (for session
		 * attributes only) other subsequent controller classes.
		 */
		model.put("userObj", loggedInUser);

		/*
		 * Search for a view named welcome (aka welcome.jsp)
		 */
		return "welcome";

	}

}
