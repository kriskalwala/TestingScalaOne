package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {


 /* scala> val lines = Source.fromFile("/Users/roseair/Downloads/Test Backend Scala .md").getLines.toArray

lines: Array[String] = Array(### Requirements, "", 
- Create a git repository (either local or public one on GitHub) 
  that contains a RESTful web-service written in Scala. 
- The service should allow users to place new car adverts and view, 
   modify and delete existing car adverts., "", 
- Car adverts should have the following fields:, 
 1. * **id** (_required_): **int** or **guid**, choose whatever is more convenient for you;,
 2. * **title** (_required_): **string**, e.g. _"Audi A4 Avant"_;, * 
 3. **fuel** (_required_): gasoline or diesel, use some type which could be extended in the future by adding additional fuel types;,
 4. * **price** (_required_): **integer**;, * 
 5. **new** (_required_): **boolean**, indicates if car is new or used;, 
 6. * **mileage** (_only for used cars_): **integer**;, 
 7. * **first registration** ... */






  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}