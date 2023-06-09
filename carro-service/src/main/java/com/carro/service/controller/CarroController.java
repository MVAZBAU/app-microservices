package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entities.Carro;
import com.carro.service.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<List<Carro>> getCarros() {
		List<Carro> listCarros = carroService.getAll();
		if (listCarros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listCarros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> getCarro(@PathVariable("id") Integer id) {
		Carro carro = carroService.getCarroById(id);
		if (carro == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(carro);
	}

	@PostMapping
	public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro) {
		Carro newCarro = carroService.saveCarro(carro);
		return ResponseEntity.ok(newCarro);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Carro>> getCarrosByUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
		List<Carro> listCarros = carroService.findByUsuarioId(usuarioId);
		if (listCarros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listCarros);
	}

}
