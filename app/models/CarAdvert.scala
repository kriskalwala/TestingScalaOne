package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


case class CarAdvert(id: Pk[Long] = NotAssigned, title: String, fuel: String, price: Option[Long], issnew: String, mileage: String, carregister: String)

/**
 * for pagination
 */
case class PageCarAdvert[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

object CarAdvert {


/* "", Car adverts should have the following fields:, 
* **id** (_required_): **int** or **guid**, choose whatever is more convenient for you;, 
* **title** (_required_): **string**, e.g. _"Audi A4 Avant"_;, 
* **fuel** (_required_): gasoline or diesel, 
use some type which could be extended in the future by adding additional fuel types;, 
* **price** (_required_): **integer**;, 
* **new** (_required_): **boolean**, indicates if car is new or used;, 
* **mileage** (_only for used cars_): **integer**;, 
* **first registration** ...
*/

 // -- Parsers
  
  /**
   * Parse a CarAdvert from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("caradvert.id") ~
    get[String]("caradvert.title") ~
    get[String]("caradvert.fuel") ~
    get[Option[Long]]("caradvert.price") ~
    get[String]("caradvert.issnew") ~
    get[String]("caradvert.mileage") ~
    get[String]("caradvert.carregister") map {
      case id~title~fuel~price~issnew~mileage~carregister => CarAdvert(id, title, fuel, price, issnew, mileage, carregister)
    }
  }
  
  /**  ALBO CAR ALBO FUEL wprowadzic
   * Parse a (Computer,Company) from a ResultSet
   */
  val withCompany = CarAdvert.simple map {
    case caradvert => (caradvert)
  }

// -- Queries
  
  /**
   * Retrieve a caradvert from the id.
   */
  def findById(id: Long): Option[CarAdvert] = {
    DB.withConnection { implicit connection =>
      SQL("select * from caradvert where id = {id}").on('id -> id).as(CarAdvert.simple.singleOpt)
    }
  }


   /* KKALWALA: pageSize is FIXED here and not passed by the call so must be changed here if necessary*/
  def show(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): PageCarAdvert[(CarAdvert)] = {
    
    val offest = pageSize * page

    /* KKALWALA TESTS 23 SEPTEMBER 2015 */
    println("offest CARADVERT: " + offest + ", pageSize " + pageSize + ", page  - ktora page " + page )
    
    DB.withConnection { implicit connection =>
      
      val caradverts = SQL(
        """
          select * from caradvert 
          where caradvert.title like {filter}
          order by {orderBy} nulls last
          limit {pageSize} offset {offset}
        """
      ).on(
        'pageSize -> pageSize, 
        'offset -> offest,
        'filter -> filter,
        'orderBy -> orderBy
      ).as(CarAdvert.withCompany *)   //MOZE BYC DO CAR ALBO FUEL  .withCompany *

      val totalRows = SQL(
        """
          select count(*) from caradvert 
          where caradvert.title like {filter}
        """
      ).on(
        'filter -> filter
      ).as(scalar[Long].single)

      PageCarAdvert(caradverts, page, offest, totalRows)
      
    }
    
  }


  def update(id: Long, caradvert: CarAdvert) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          update caradvert
          set title = {title}, fuel = {fuel}, price = {price}, issnew = {issnew}, mileage = {mileage}, carregister = {carregister}
          where id = {id}
        """
      ).on(
        'id -> id,
        'title -> caradvert.title,
        'fuel -> caradvert.fuel,
        'price -> caradvert.price,
        'issnew -> caradvert.issnew,
        'mileage -> caradvert.mileage,
        'carregister -> caradvert.carregister
      ).executeUpdate()
    }
  }


  def insert(caradvert: CarAdvert) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into caradvert values (
            (select next value for caradvert_seq), 
            {title}, {fuel}, {price}, {issnew}, {mileage}, {carregister}
          )
        """
      ).on(
        'title -> caradvert.title,
        'fuel -> caradvert.fuel,
        'price -> caradvert.price,
        'issnew -> caradvert.issnew,
        'mileage -> caradvert.mileage,
        'carregister -> caradvert.carregister
      ).executeUpdate()
    }
  }




  def delete(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from caradvert where id = {id}").on('id -> id).executeUpdate()
    }
  }



}