package com.hashedin.Assignment.netflixContent;

import com.hashedin.Assignment.netflixDetails.NetflixRecords;

import java.util.List;

public interface NetflixDataService {

    List<NetflixRecords> getDataTypeTVShow(int count);

    List<NetflixRecords> getMoviesDataByType(String movieType);

    List<NetflixRecords> getMoviesDataByCountryName(String country);

    List<NetflixRecords> getTVShowsDataByUserDateInput(String startDate, String endDate);

    void addNetflixDetailsToDataBase(List<NetflixRecords> netflixData);
}
