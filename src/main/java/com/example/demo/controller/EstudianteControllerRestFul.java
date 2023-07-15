package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteControllerRestFul {

	@Autowired
	private IEstudianteService estudianteService;

	// GET
	@GetMapping(path = "/{cedula}")
	public Estudiante consultarPorCedula(@PathVariable String cedula) {
		return this.estudianteService.consultarPorCedula(cedula);
	}

	@PostMapping
	// estudiante debe venir en el cuerpo del request
	public void guardar(@RequestBody Estudiante estudiante) {

		this.estudianteService.guardar(estudiante);
	}

	@PutMapping(path = "/{identificador}")
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		estudiante.setId(identificador);
		this.estudianteService.actualizar(estudiante);
	}

	@PatchMapping(path = "/{identificador}")
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		Estudiante estu1 = this.estudianteService.consultarPorId(identificador);
		estu1.setCedula(estudiante.getCedula());
		
		this.estudianteService.actualizar(estu1);

	}

	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.eliminar(id);
	}
	
	@GetMapping
	public List<Estudiante> consultarTodosPorProvincia(@RequestParam String provincia) {
		return this.estudianteService.consultarTodosPorProvincia(provincia);
	}
}
