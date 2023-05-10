package com.matheusklabunde.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusklabunde.dslist.dto.GameDTO;
import com.matheusklabunde.dslist.dto.GameMinDTO;
import com.matheusklabunde.dslist.services.GameService;

@RestController //para configurar a classe para ser um controlador.
@RequestMapping(value = "/games") //configurando o caminho que vai ser respondido na minha API.
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping(value = "/{id}") //para configurar como "GET" e a rota.
	public GameDTO findById(@PathVariable long id){ // para que o id seja corresponde ao da requisição.
		GameDTO result = gameService.findById(id);
		return result;
	}
	
	@GetMapping //para configurar como "GET".
	public List<GameMinDTO> findAll(){
		List<GameMinDTO> result = gameService.findAll();
		return result;
	}
}
