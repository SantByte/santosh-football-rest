package com.santosh.football.service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.santosh.football.model.LeagueInfo;
import com.santosh.football.util.HttpClientUtil;

@Service
public class LeagueServiceImpl implements LeagueService{

	 @Override
	    public CompletableFuture<LeagueInfo> getLeagueInfo(int year) {
	        String url = "https://jsonmock.hackerrank.com/api/football_competitions?year=" + year;
	        return HttpClientUtil.sendAsyncRequest(url)
	                .thenApplyAsync(HttpResponse::body)
	                .thenApplyAsync(responseBody -> {
	                    try {
							return HttpClientUtil.parseLeagueInfoResponse(responseBody);
						} catch (IOException e) {
							e.printStackTrace();
							return null;
						}
	                });
	    }

}
