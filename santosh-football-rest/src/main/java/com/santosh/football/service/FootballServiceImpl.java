package com.santosh.football.service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.santosh.football.model.FootballMatch;
import com.santosh.football.util.HttpClientUtil;

@Service
public class FootballServiceImpl implements FootballService {

	@Override
	public CompletableFuture<Integer> getDrawnMatches(int year) {
		int totalDrawnMatches = 0;
		for (int goal = 0; goal <= 10; goal++) {
			String url = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year + "&team1goals=" + goal
					+ "&team2goals=" + goal;
			CompletableFuture<HttpResponse<String>> responseFuture = HttpClientUtil.sendAsyncRequest(url);
			try {
				String responseBody = responseFuture.thenApplyAsync(HttpResponse::body).get();
				FootballMatch match = HttpClientUtil.parseFootballMatchResponse(responseBody);
				totalDrawnMatches += match.getTotal();
			} catch (InterruptedException | ExecutionException | IOException e) {
				e.printStackTrace();
			}
		}
		return CompletableFuture.completedFuture(totalDrawnMatches);
	}
}
