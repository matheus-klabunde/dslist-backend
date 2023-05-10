package com.matheusklabunde.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusklabunde.dslist.dto.GameListDTO;
import com.matheusklabunde.dslist.services.GameListService;

@RestController //para configurar a classe para ser um controlador.
@RequestMapping(value = "/lists") //configurando o caminho que vai ser respondido na minha API.
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@GetMapping //para configurar como "GET".
	public List<GameListDTO> findAll(){
		List<GameListDTO> result = gameListService.findAll();
		return result;
	}
}