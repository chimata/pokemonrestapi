package com.pokemon.pokemonrestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonApiClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonApiClientService.class);

    @Autowired
    private RestTemplateClient restTemplate;

    @Value("${pokemon.get.url}")
    private String pokemonServiceUrl;

    //private String pokemonImageDynamicUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +imageCount+".png";


    public List<PokemonApiImageNameResponse> getFirstHundredPokemonsWithImages() {
        LOGGER.info("Entering into the getFirstHundredPokemonsWithImages() Method to Retrieve First 100 Pokemons along with Images.");
        PokemonApiFirstResponse pokemonFirstResponse;
        List<PokemonApiImageNameResponse> nameImageList = new ArrayList<>();
        try {
            pokemonFirstResponse = restTemplate.restTemplate().getForObject(pokemonServiceUrl, PokemonApiFirstResponse.class);

            for (Results results : pokemonFirstResponse.getResults()) {
                PokemonApiSecondResponse pokemonApiSecondResponse = restTemplate.restTemplate().getForObject(results.getUrl(), PokemonApiSecondResponse.class);
                PokemonApiImageNameResponse pokemonApiImageNameResponse = new PokemonApiImageNameResponse();
                pokemonApiImageNameResponse.setImageName(pokemonApiSecondResponse.getName());
                pokemonApiImageNameResponse.setImagePath(pokemonApiSecondResponse.getSprites().getBack_default());
                nameImageList.add(pokemonApiImageNameResponse);
            }
        } catch (Exception ex) {
            LOGGER.error(String.format("Error calling service", pokemonServiceUrl), ex.getMessage());
        }
        return nameImageList;
    }

   /* public PokemonApiFirstResponse getFirstHundredPokemons() {
        PokemonApiFirstResponse pokemonApiFirstResponse = null;
        try {
            pokemonApiFirstResponse = restTemplate.restTemplate().getForObject(pokemonServiceUrl, PokemonApiFirstResponse.class);
            LOGGER.info("API call to " + pokemonServiceUrl + " has recieved a response.");

        } catch (Exception e) {
            LOGGER.error(String.format("Error calling service[%s]", pokemonServiceUrl), e.getMessage());
        }
        return pokemonApiFirstResponse;
    }*/

}