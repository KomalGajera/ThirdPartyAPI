package com.demo.usefulldemo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.usefulldemo.model.Game;
import com.demo.usefulldemo.repository.GameRepository;


@Service
@Transactional
public class GameServiceImpl implements GameService{
	
	@Autowired
	private GameRepository repository;

	@Override
	public void save(Game game1) {
		// TODO Auto-generated method stub
		repository.save(game1);
	}

}
