package com.demo.usefulldemo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GameList {
	
	 private List<GameSummery> gameSummery;
	 
	 public GameList() {
		 gameSummery=new ArrayList<>();
	 }

	    

}
