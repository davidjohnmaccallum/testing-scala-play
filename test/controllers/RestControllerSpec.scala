package controllers

import models.Customer
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class RestControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "RestController" should {

    val controller = new RestController(stubControllerComponents())

    "return a list of customers" in {
      val customers = controller.listCustomers().apply(FakeRequest())

      status(customers) mustBe OK
      contentType(customers) mustBe Some("application/json")
      // TODO Validate body
    }

    "return a single customer" in {
      val customers = controller.getCustomer("1").apply(FakeRequest())

      status(customers) mustBe OK
      contentType(customers) mustBe Some("application/json")
      contentAsString(customers) must include ("""{"id":"1","name":"Bob","tel":"1234","email":"bob@bob.com"}""")
    }

    "return a 404 if customer not found" in {
      val customers = controller.getCustomer("x").apply(FakeRequest())

      status(customers) mustBe NOT_FOUND
    }
  }
}
