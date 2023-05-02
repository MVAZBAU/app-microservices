package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;
import com.usuario.service.entities.Usuario;
import com.usuario.service.feignclients.CarroFeignClient;
import com.usuario.service.feignclients.MotoFeignClient;

@Service
public class UsuarioService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarroFeignClient carroFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;
	
	private static final String carrosURL = "http://localhost:8002/carro/usuario/";
	private static final String motosURL = "http://localhost:8003/moto/usuario/";

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getById(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario saveUsuario(Usuario usuario) {
		Usuario newUsuario = usuarioRepository.save(usuario);
		return newUsuario;
	}

	public List<Carro> getCarrosByUsuarioId(Integer usuarioId) {
		List<Carro> listCarros = restTemplate.getForObject(carrosURL + usuarioId, List.class);

		return listCarros;
	}

	public List<Moto> getMotosByUsuarioId(Integer usuarioId) {
		List<Moto> listMotos = restTemplate.getForObject(motosURL + usuarioId, List.class);

		return listMotos;
	}

	public Carro saveCarroByUsuarioId(Integer usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro newCarro = carroFeignClient.saveCarro(carro);
		return newCarro;
	}
	
	public Moto saveMotoByUsuarioId(Integer usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto newMoto = motoFeignClient.saveMoto(moto);
		return newMoto;
	}

	public Map<String, Object> getUsuarioAndVehiculos(Integer usuarioId) {
		Map<String, Object> usuarioVehiculos = new HashMap<>();
		Usuario usuario = getById(usuarioId);

		if(usuario == null) {
			usuarioVehiculos.put("Usuario", "Usuario no existente.");
			return usuarioVehiculos;
		}
		usuarioVehiculos.put("Usuario", usuario);
		
		List<Carro> listCarros = carroFeignClient.getCarros(usuarioId);		
		if(listCarros == null || listCarros.isEmpty()) {
			usuarioVehiculos.put("Carros", "Usuario sin carros.");
		}
		else {
			usuarioVehiculos.put("Carros", listCarros);			
		}
		
		List<Moto> listMotos = motoFeignClient.getMotos(usuarioId);
		if(listMotos.isEmpty()) {
			usuarioVehiculos.put("Motos", "Usuario sin motos.");
		}
		else {
			usuarioVehiculos.put("Motos", listMotos);			
		}
		
		return usuarioVehiculos;
	}

}
