package com.br.myteam.repositories;

import com.br.myteam.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayersRepository extends JpaRepository<PlayerModel, UUID> {

}
