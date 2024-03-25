package com.santosh.football.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santosh.football.model.FootballMatch;
import com.santosh.football.model.LeagueInfo;

public class HttpClientUtil {

	private static final HttpClient httpClient = HttpClient.newHttpClient();
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static CompletableFuture<HttpResponse<String>> sendAsyncRequest(String url) {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
	}

	public static FootballMatch parseFootballMatchResponse(String responseBody) throws IOException {
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		int totalMatches = jsonNode.get("total").asInt();
		FootballMatch match = new FootballMatch();
		match.setTotal(totalMatches);
		return match;
	}

	public static LeagueInfo parseLeagueInfoResponse(String responseBody) throws IOException {
		if (responseBody == null) {
			throw new IOException("Response body is null");
		}

		JsonNode rootNode = objectMapper.readTree(responseBody);
		JsonNode dataNode = rootNode.path("data").get(0);
		if (dataNode != null) {
			String name = Optional.ofNullable(dataNode.path("name").asText(null)).orElse("");
			String country = Optional.ofNullable(dataNode.path("country").asText(null)).orElse("");
			int year = Optional.ofNullable(dataNode.path("year").asInt(0)).orElse(0);
			String winner = Optional.ofNullable(dataNode.path("winner").asText(null)).orElse("");
			String runnerup = Optional.ofNullable(dataNode.path("runnerup").asText(null)).orElse("");

			return new LeagueInfo(name, country, year, winner, runnerup);
		}else {
			return new LeagueInfo("Not Found", "Not Found", 0, "Not Found", "Not Found");
		}
	}
}
