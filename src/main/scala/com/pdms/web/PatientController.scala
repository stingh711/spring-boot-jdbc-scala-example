package com.pdms.web

import org.springframework.web.bind.annotation._
import org.springframework.beans.factory.annotation.Autowired
import com.pdms.service.PatientService
import com.pdms.model.Patient


@RestController
@RequestMapping(Array("/patients"))
class PatientController @Autowired() (private val patientService: PatientService) {
  @RequestMapping(Array("/hello"))
  def hello: String = "hello"

  @RequestMapping(Array("/list"))
  def list = patientService.getAll

  @RequestMapping(Array("/{id}"))
  def get(@PathVariable("id") id: Long) = {
    patientService.get(id)
  }

  @RequestMapping(value = Array("/new"), method = Array(RequestMethod.POST))
  def create(@RequestBody patient: Patient) = {
    patientService.insert(patient)
  }
}
