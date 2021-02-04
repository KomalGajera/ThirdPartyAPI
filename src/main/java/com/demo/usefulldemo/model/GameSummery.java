package com.demo.usefulldemo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GameSummery {
	
	@JsonProperty("GameID")
	private int GameID;
	@JsonProperty("Status")
	private String Status;
	@JsonProperty("Day")
	private Date Day;

}
