package com.santosh.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeagueInfo {
	private String name;
    private String country;
    private int year;
    private String winner;
    private String runnerup;
}
