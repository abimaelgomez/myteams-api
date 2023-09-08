package com.br.myteam.controllers;

import com.br.myteam.dtos.PlayerRecordDto;
import com.br.myteam.models.PlayerModel;
import com.br.myteam.repositories.PlayersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PlayersController {

    @Autowired
    PlayersRepository playersRepository;

    /*Metodo - postando jogadores*/
    @PostMapping("/players")
    public ResponseEntity<PlayerModel> savePlayer(@RequestBody @Valid PlayerRecordDto playertRecordDto) {
        var playerModel = new PlayerModel();
        BeanUtils.copyProperties(playertRecordDto, playerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(playersRepository.save(playerModel));
    }
    /*Metodo - buscar todos jogadores*/
    @GetMapping("/players")
    public ResponseEntity<List<PlayerModel>> getAllPlayers() {
        List<PlayerModel> playerList = playersRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(playersRepository.findAll());
    }
    /*Metodo - buscar 1 jogador especifico*/
    @GetMapping("/players/{id}")
    public ResponseEntity<Object> getOnePlayer(@PathVariable(value = "id") UUID id) {
        Optional<PlayerModel> playerO = playersRepository.findById(id);
        if (playerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(playerO.get());
    }
    /*Metodo - Atualizar dados jogador*/
    @PutMapping("/players/{id}")
    public ResponseEntity<Object> updatePlayer(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid PlayerRecordDto playerRecordDto) {
        Optional<PlayerModel> playerO = playersRepository.findById(id);
        if (playerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found.");
        }
        var playerModel = playerO.get();
        BeanUtils.copyProperties(playerRecordDto, playerModel);
        return ResponseEntity.status(HttpStatus.OK).body(playersRepository.save(playerModel));
    }
    /*Metodo - Deletar dados jogador*/
    @DeleteMapping("/players/{id}")
    public ResponseEntity<Object> deletePlayer(@PathVariable(value = "id") UUID id) {
        Optional<PlayerModel> playerO = playersRepository.findById(id);
        if (playerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found.");
        }
        playersRepository.delete(playerO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Player deleted successfully.");
    }
}
