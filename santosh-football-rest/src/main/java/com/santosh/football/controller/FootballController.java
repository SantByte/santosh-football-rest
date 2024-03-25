package com.santosh.football.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.football.dto.YearRequest;
import com.santosh.football.model.LeagueInfo;
import com.santosh.football.service.FootballService;
import com.santosh.football.service.LeagueService;

@RestController
@RequestMapping("/api/football")
public class FootballController {

	@Autowired
	FootballService footballService;

	@Autowired
	LeagueService leagueService;

	@GetMapping("/drawn-matches")
	public CompletableFuture<Integer> getDrawnMatches(@RequestBody YearRequest request) {
		return footballService.getDrawnMatches(request.getYear());
	}

	@GetMapping("/league-info")
	public CompletableFuture<LeagueInfo> getLeagueInfo(@RequestBody YearRequest request) {
		return leagueService.getLeagueInfo(request.getYear());
	}
}
