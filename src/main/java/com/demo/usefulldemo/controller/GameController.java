package com.demo.usefulldemo.controller;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.usefulldemo.model.Game;
import com.demo.usefulldemo.model.GameSummery;
import com.demo.usefulldemo.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping("Game")
public class GameController implements GameInterface {

	private static final String apiKey = "9761138c44b54698811aa4ad18ab0552";

	@Autowired
	private GameService service;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ModelMapper mapper;

	int count = 0;

	/*
	 * @Scheduled(initialDelay = 1000, fixedRate = 10000) public void run() {
	 * System.out.println("Current time is :: " + Calendar.getInstance().getTime());
	 * }
	 */

	@PostMapping
	public void getGameDetail()
			throws UnirestException, URISyntaxException, JsonMappingException, JsonProcessingException {

		ResponseEntity<Object[]> response = restTemplate.getForEntity(
				"https://api.sportsdata.io/v3/mlb/scores/json/Games/2021" + "?key=" + apiKey, Object[].class);

		Object[] objects = response.getBody();
		List<GameSummery> game = new ArrayList<>();

		for (int i = 0; i < objects.length; i++) {
			GameSummery gameSummery = mapper.map(objects[i], GameSummery.class);
			game.add(gameSummery);
		}

		game.forEach(g -> {

			Game game1 = new Game();
			game1.setGameId(g.getGameID());
			game1.setStartDate(convertToLocalDateTimeViaInstant(g.getDay()));
			game1.setStatus(g.getStatus());
			game1.setTitle("something");
			service.save(game1);
			// count++;
			// System.out.println("\n\n\n\nNo:" +count+"\t"+ g.getGameID() + "\t" +
			// g.getStatus() + "\t" + g.getDay());
		});

	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/*
	 * import java.util.ArrayList; import java.util.Arrays; import
	 * java.util.Calendar; import java.util.HashMap; import java.util.Iterator;
	 * import java.util.List; import java.util.Map;
	 * 
	 * import org.json.JSONArray; import org.json.JSONObject; import
	 * org.modelmapper.ModelMapper; import
	 * org.springframework.beans.factory.annotation.Autowired; import
	 * org.springframework.http.HttpEntity; import
	 * org.springframework.http.HttpHeaders; import
	 * org.springframework.http.HttpMethod; import
	 * org.springframework.http.HttpStatus; import
	 * org.springframework.http.MediaType; import
	 * org.springframework.http.ResponseEntity; import
	 * org.springframework.scheduling.annotation.Scheduled; import
	 * org.springframework.web.bind.annotation.GetMapping; import
	 * org.springframework.web.bind.annotation.RestController; import
	 * org.springframework.web.client.RestTemplate;
	 * 
	 * import com.example.springsocial.model.Game; import
	 * com.example.springsocial.model.GameSummary; import
	 * com.example.springsocial.repository.GameRepository; import
	 * com.fasterxml.jackson.databind.ObjectMapper; import
	 * com.mashape.unirest.http.HttpResponse; import
	 * com.mashape.unirest.http.JsonNode; import com.mashape.unirest.http.Unirest;
	 * import com.mashape.unirest.http.exceptions.UnirestException;
	 * 
	 * @RestController public class GameController implements GameInterface {
	 * 
	 * public static final String apiKey = "9761138c44b54698811aa4ad18ab0552";
	 * 
	 * static int count=1;
	 * 
	 * // @Scheduled(initialDelay = 1000, fixedRate = 10000) // @Scheduled(cron =
	 * "0 0/1 1/1 ?")
	 * 
	 * @Autowired GameRepository gameRepository;
	 * 
	 * Game game;
	 * 
	 * @GetMapping("/game") public ResponseEntity<Game> getGames() throws Exception
	 * {
	 * 
	 * HttpResponse<JsonNode> contactObject;
	 * 
	 * Map<String, String> headers = new HashMap<String, String>();
	 * 
	 * headers.put("Content-Type", "application/json");
	 * 
	 * Map<String, Object> param = new HashMap<String, Object>();
	 * param.put("key","9761138c44b54698811aa4ad18ab0552");
	 * 
	 * contactObject = getMethodCallSync(
	 * "https://api.sportsdata.io/v3/mlb/scores/json/GamesByDate/2021-MAR-4",
	 * headers,param); //System.out.println(contactObject.getBody().toString());
	 * 
	 * JSONArray jsonArray = contactObject.getBody().getArray();
	 * 
	 * jsonArray.forEach(data ->{
	 * 
	 * JSONObject obj = (JSONObject) data; game = new Game();
	 * game.setGameID((Integer) obj.get("GameID"));
	 * System.out.println("Game ID"+count+ " : "+game.getGameID());
	 * game.setStatus((String) obj.get("Status"));
	 * System.out.println("Game Status "+count+" : "+game.getStatus()); count++;
	 * gameRepository.save(game); }); return new
	 * ResponseEntity<>(game,HttpStatus.OK); } }
	 */

}
