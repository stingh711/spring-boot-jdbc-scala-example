package com.pdms.service

import org.springframework.stereotype.Component
import org.springframework.jdbc.core.{PreparedStatementCreator, RowMapper, JdbcTemplate}
import org.springframework.beans.factory.annotation.Autowired
import com.pdms.model.Patient
import java.sql.{PreparedStatement, Connection, ResultSet}
import org.springframework.jdbc.support.GeneratedKeyHolder

/**
 * Created with IntelliJ IDEA.
 * User: sting
 * Date: 9/25/14
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
@Component
class PatientService @Autowired() (val jdbcTemplate: JdbcTemplate) {
  def getAll = jdbcTemplate.query("select * from patient", PatientMapper)

  def get(id: Long): Patient = jdbcTemplate.queryForObject("select * from patient where id = ?", PatientMapper , java.lang.Long.valueOf(id))

  def getByName(name: String): Patient = jdbcTemplate.queryForObject("select * from patient where name = ?", PatientMapper , name)

  def insert(patient: Patient) = {
    val sql = "insert into patient (name, age) values (?, ?)"
    val keyHolder = new GeneratedKeyHolder()
    jdbcTemplate.update(new PreparedStatementCreator {
      def createPreparedStatement(con: Connection): PreparedStatement = {
        val ps = con.prepareStatement(sql, Array("id"))
        ps.setString(1, patient.name)
        ps.setInt(2, patient.age)
        ps
      }
    }, keyHolder)
    keyHolder.getKey
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

