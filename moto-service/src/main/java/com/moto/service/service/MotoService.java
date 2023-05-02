package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entities.Moto;
import com.moto.service.repository.MotoRepository;

@Service
public class MotoService {
	@Autowired
	private MotoRepository motoRepository;

	public List<Moto> getAll() {
		return motoRepository.findAll();
	}

	public Moto getMotoById(Integer id) {
		return motoRepository.findById(id).orElse(null);
	}

	public Moto saveMoto(Moto moto) {
		Moto newMoto = motoRepository.save(moto);
		return newMoto;
	}

	public List<Moto> findByUsuarioId(Integer usuarioId) {
		return motoRepository.findByUsuarioId(usuarioId);
	}
}
