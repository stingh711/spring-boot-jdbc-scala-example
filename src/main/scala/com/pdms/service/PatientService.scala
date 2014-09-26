package com.pdms.service

import org.springframework.stereotype.Component
import org.springframework.jdbc.core.{RowMapper, JdbcTemplate}
import org.springframework.beans.factory.annotation.Autowired
import com.pdms.model.Patient
import java.sql.ResultSet
import javax.sql.DataSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import collection.JavaConversions._

/**
 * Created with IntelliJ IDEA.
 * User: sting
 * Date: 9/25/14
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
@Component
class PatientService @Autowired() (val dataSource: DataSource) {
  private val jdbcTemplate = new JdbcTemplate(dataSource)
  private val insertPatient = new SimpleJdbcInsert(dataSource).withTableName("patient").usingGeneratedKeyColumns("id")

  def getAll = jdbcTemplate.query("select * from patient", PatientMapper)

  def get(id: Long): Patient = jdbcTemplate.queryForObject("select * from patient where id = ?", PatientMapper , java.lang.Long.valueOf(id))

  def getByName(name: String): Patient = jdbcTemplate.queryForObject("select * from patient where name = ?", PatientMapper , name)

  def insert(patient: Patient): Long = {
    val parameters: Map[String, AnyRef] = Map("name" -> patient.name, "age" -> java.lang.Integer.valueOf(patient.age))

    //Need to import collection.JavaConversions._ to make this line work
    val id = insertPatient.executeAndReturnKey(parameters)
    id.longValue()
  }
}

object PatientMapper extends RowMapper[Patient] {
  def mapRow(rs: ResultSet, rowNum: Int): Patient = {
    val name = rs.getString("name")
    val age = rs.getInt("age")
    val id = rs.getLong("id")
    Patient(Some(id), name, age)
  }
}

