package com.pdms.model

/**
 * Created with IntelliJ IDEA.
 * User: sting
 * Date: 9/25/14
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
case class Patient(var id: Option[Long], var name: String, var age: Int) {
  def this() = this(None, "", 0)
}
