package com.carro.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carro.service.entities.Carro;
import com.carro.service.repository.CarroRepository;

@Service
public class CarroService {
	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> getAll() {
		return carroRepository.findAll();
	}

	public Carro getCarroById(Integer id) {
		return carroRepository.findById(id).orElse(null);
	}

	public Carro saveCarro(Carro Carro) {
		Carro newCarro = carroRepository.save(Carro);
		return newCarro;
	}

	public List<Carro> findByUsuarioId(Integer usuarioId) {
		return carroRepository.findByUsuarioId(usuarioId);
	}
}
