package com.santosh.football.service;

import java.util.concurrent.CompletableFuture;

public interface FootballService {
	CompletableFuture<Integer> getDrawnMatches(int year);
}
