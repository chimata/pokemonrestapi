package com.pokemon.pokemonrestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
public class PokemonApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonApiController.class);

    @Autowired
    private PokemonApiClientService clientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/images")
    public List<PokemonApiImageNameResponse> processRemoteMessage() {
        LOGGER.info("Entering into the processRemoteMessage() Method.");
        try {
            List<PokemonApiImageNameResponse> pokemonApiImageNameResponses = clientService.getFirstHundredPokemonsWithImages();
            return pokemonApiImageNameResponses;
        } catch (Exception ex) {
            LOGGER.error(String.format("Failed to retrieve First Hundred Pokemons and Images", ex.getMessage()));
            return null;
        }
    }

    //This method displaying First 100 pokemon only from PokemonApi
   /* @ResponseStatus(HttpStatus.OK)
    @GetMapping(value ="/images")
    public PokemonApiFirstResponse processRemoteMessage100Pokemon() {
        try {
            PokemonApiFirstResponse apiFirstResponse = clientService.getFirstHundredPokemons();
            return apiFirstResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }*/
}
