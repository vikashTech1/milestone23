package com.hashedin.Assignment.service;

import com.hashedin.Assignment.pojo.Netflix;

import java.util.List;

public interface NetflixDataService {

    List<Netflix> getTVShowRecords(int count);

    List<Netflix> getMoviesByType(String movieType);

    List<Netflix> getMoviesByCountry(String country);

    List<Netflix> getTVShowsByStartDateAndEndDate(String startDate, String endDate);

    void addNetflixDataToDb(List<Netflix> netflixData);
}
