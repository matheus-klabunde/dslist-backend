package com.matheusklabunde.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheusklabunde.dslist.dto.GameListDTO;
import com.matheusklabunde.dslist.dto.GameMinDTO;
import com.matheusklabunde.dslist.entities.GameList;
import com.matheusklabunde.dslist.projections.GameMinProjection;
import com.matheusklabunde.dslist.repositories.GameListRepository;
import com.matheusklabunde.dslist.repositories.GameRepository;

@Service //registra o GameListService como sendo componente do sistema.
		// Pode-se usar o @Component também.
public class GameListService {

	@Autowired  // Para o próprio Spring resolver dependências.
				//Injetando uma instâcia do GameListRepository dentro do GameListService.
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true) //Boa pratica: para que a operação com o banco de dados aconteça ACID.
									// readOnly = true -> Assegurando que nao irá fazer nenhuma operação de escrita.
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj =  list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}