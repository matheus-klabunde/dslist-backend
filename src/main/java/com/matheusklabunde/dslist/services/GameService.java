package com.matheusklabunde.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusklabunde.dslist.dto.GameMinDTO;
import com.matheusklabunde.dslist.entities.Game;
import com.matheusklabunde.dslist.repositories.GameRepository;

@Service //registra o GameService como sendo componente do sistema.
		// Pode-se usar o @Component também.
public class GameService {

	@Autowired  // Para o próprio Spring resolver dependências.
				//Injetando uma instâcia do GameRepository dentro do GameService.
	private GameRepository gameRepository;
	
	public List<GameMinDTO> findAll(){
		List<Game> result = gameRepository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
	}
}