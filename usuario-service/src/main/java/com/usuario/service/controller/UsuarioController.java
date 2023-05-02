package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.service.UsuarioService;
import com.usuario.service.entities.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> getUsuarios() {
		List<Usuario> listUsuarios = usuarioService.getAll();
		if (listUsuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(listUsuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Integer id) {
		Usuario usuario = usuarioService.getById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
		Usuario newUsuario = usuarioService.saveUsuario(usuario);
		return ResponseEntity.ok(newUsuario);
	}

	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> getCarrosByUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
		Usuario usuario = usuarioService.getById(usuarioId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		List<Carro> listCarros = usuarioService.getCarrosByUsuarioId(usuarioId);

		return ResponseEntity.ok(listCarros);
	}

	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> getMotosByUsuarioId(@PathVariable("usuarioId") Integer usuarioId) {
		Usuario usuario = usuarioService.getById(usuarioId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		List<Moto> listMotos = usuarioService.getMotosByUsuarioId(usuarioId);

		return ResponseEntity.ok(listMotos);
	}

	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> saveCarro(@PathVariable("usuarioId") Integer usuarioId, @RequestBody Carro carro) {
		Carro newCarro = usuarioService.saveCarroByUsuarioId(usuarioId, carro);
		return ResponseEntity.ok(newCarro);
	}
	
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> saveMoto(@PathVariable("usuarioId") Integer usuarioId, @RequestBody Moto moto) {
		Moto newMoto = usuarioService.saveMotoByUsuarioId(usuarioId, moto);
		return ResponseEntity.ok(newMoto);
	}
	
	@GetMapping("/vehiculos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> getVehiculosByUsuarioId(@PathVariable("usuarioId") Integer usuarioId){
		Map<String, Object> usuarioVehiculos = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(usuarioVehiculos);		
	}

}
