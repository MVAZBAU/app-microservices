package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entities.Moto;
import com.moto.service.service.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {
	@Autowired
	private MotoService motoService;

	@GetMapping
	public ResponseEntity<List<Moto>> getMotos() {
		List<Moto> listMotos = motoService.getAll();
		if (listMotos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listMotos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> getMoto(@PathVariable("id") Integer id) {
		Moto moto = motoService.getMotoById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto) {
		Moto newMoto = motoService.saveMoto(moto);
		return ResponseEntity.ok(newMoto);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> getMotosByUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
		List<Moto> listMotos = motoService.findByUsuarioId(usuarioId);
		if (listMotos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listMotos);
	}
}
