package models

import play.api.libs.json.{Json, OWrites}

case class Customer(id: String, name: String, tel: String, email: String)

object Customer {
  implicit val writes: OWrites[Customer] = Json.writes[Customer]
}
