package com.matheusklabunde.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusklabunde.dslist.dto.GameListDTO;
import com.matheusklabunde.dslist.dto.GameMinDTO;
import com.matheusklabunde.dslist.dto.ReplacementDTO;
import com.matheusklabunde.dslist.services.GameListService;
import com.matheusklabunde.dslist.services.GameService;

@RestController //para configurar a classe para ser um controlador.
@RequestMapping(value = "/lists") //configurando o caminho que vai ser respondido na minha API.
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping //para configurar como "GET".
	public List<GameListDTO> findAll(){
		List<GameListDTO> result = gameListService.findAll();
		return result;
	}
	
	@GetMapping(value = "/{listId}/games") //para configurar como "GET".
	public List<GameMinDTO> findByList(@PathVariable Long listId){
		List<GameMinDTO> result = gameService.findByList(listId);
		return result;
	}
	
	@PostMapping(value = "/{listId}/replacement") //para configurar como "POST".
	public void move(@PathVariable Long listId,@RequestBody ReplacementDTO body){
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}
}