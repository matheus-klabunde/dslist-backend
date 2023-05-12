package com.matheusklabunde.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.matheusklabunde.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {
	
	@Modifying // usa quando o value não é SELECT.
	@Query(nativeQuery = true, 
		value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
	
}
