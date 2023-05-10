package com.matheusklabunde.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheusklabunde.dslist.dto.GameListDTO;
import com.matheusklabunde.dslist.entities.GameList;
import com.matheusklabunde.dslist.repositories.GameListRepository;

@Service //registra o GameListService como sendo componente do sistema.
		// Pode-se usar o @Component também.
public class GameListService {

	@Autowired  // Para o próprio Spring resolver dependências.
				//Injetando uma instâcia do GameListRepository dentro do GameListService.
	private GameListRepository gameListRepository;
	
	@Transactional(readOnly = true) //Boa pratica: para que a operação com o banco de dados aconteça ACID.
									// readOnly = true -> Assegurando que nao irá fazer nenhuma operação de escrita.
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
}