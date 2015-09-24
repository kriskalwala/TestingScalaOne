package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import anorm._

import views._
import models._
//import model.CarAdvert

object CarAdverts extends Controller { 

	/**
   * redirection HOME
   */
  val Home = Redirect(routes.CarAdverts.showList(0, 2, ""))
  
  /**
   * 
   */ 
  /* val caradvertForm = Form(
    mapping(
      "id" -> ignored(NotAssigned:Pk[Long]),
      "title" -> nonEmptyText,
      "fuel" -> nonEmptyText,
      "price" -> optional(longNumber),
      "isnew" -> optional(boolean)
    )(CarAdvert.applied)(CarAdvert.unapplied)
  ) */


 val caradvertForm = Form(
    mapping(
      "id" -> ignored(NotAssigned:Pk[Long]),
      "title" -> nonEmptyText,
      "fuel" -> nonEmptyText,
      "price" -> optional(longNumber),
      "issnew" -> nonEmptyText,
      "mileage" -> nonEmptyText,
      "carregister" -> nonEmptyText
    )(CarAdvert.apply)(CarAdvert.unapply)
  )

  
  /* val caradvertForm : Form[CarAdvert] = Form(
      mapping(
      	"id" -> ignored(NotAssigned:Pk[Long]),
        "title" -> nonEmptyText,
        "fuel" -> nonEmptyText,
        "price" -> optional(longNumber)
        "isnew" -> boolean      
   	   )
  	) */




  // -- Actions

  /**
   * 
   */  
  def index = Action { Home }
  
  /**
   */
  def showList(page: Int, orderBy: Int, filter: String) = Action { implicit request =>
    Ok(html.showList(
      CarAdvert.show(page = page, orderBy = orderBy, filter = ("%"+filter+"%")),
      orderBy, filter
    ))
  }


 /**
   * 
   */
  def create = Action {

    Ok(html.createFormCarAdvert(caradvertForm))
  }


  def edit(id: Long) = Action {

    CarAdvert.findById(id).map { caradvert =>
      Ok(html.editFormCarAdvert(id, caradvertForm.fill(caradvert)))
    }.getOrElse(NotFound)
  }

  def save = Action { implicit request =>
    caradvertForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.createFormCarAdvert(formWithErrors)),
      caradvert => {
        CarAdvert.insert(caradvert)
        Home.flashing("success" -> "Car Advert %s  created".format(caradvert.title))
      }
    )
  }
    
  
  def update(id: Long) = Action { implicit request =>
    caradvertForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.editFormCarAdvert(id, formWithErrors)),
      caradvert => {
        CarAdvert.update(id, caradvert)
        Home.flashing("success" -> "Car Advert %s updated".format(caradvert.title))
      }
    )
  }  
    

def delete(id: Long) = Action {
    CarAdvert.delete(id)
    Home.flashing("success" -> "CarAdvert has been deleted")
  }



}