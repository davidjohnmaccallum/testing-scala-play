package controllers

import javax.inject._
import models.Customer
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class RestController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  val customers = List(
    Customer(id = "1", name = "Bob", tel = "1234", email = "bob@bob.com"),
    Customer(id = "2", name = "Sue", tel = "1234", email = "sue@bob.com"),
    Customer(id = "3", name = "Joe", tel = "1234", email = "joe@bob.com"),
  )

  def listCustomers(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(customers))
  }

  def getCustomer(id: String): Action[AnyContent] = Action {
    val customer = customers.find(_.id == id)
    if (customer.isDefined) {
      Ok(Json.toJson(customer))
    } else {
      NotFound("")
    }
  }

}
