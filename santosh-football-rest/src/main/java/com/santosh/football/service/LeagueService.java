package com.santosh.football.service;

import java.util.concurrent.CompletableFuture;

import com.santosh.football.model.LeagueInfo;

public interface LeagueService {
	CompletableFuture<LeagueInfo> getLeagueInfo(int year);
}
