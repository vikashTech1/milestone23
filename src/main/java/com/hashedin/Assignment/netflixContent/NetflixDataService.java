package com.hashedin.Assignment.netflixContent;

import java.util.List;

import com.hashedin.Assignment.netflixDetails.NetflixRecords;

public interface NetflixDataService {

        List<NetflixRecords> getDataTypeTVShow(int count);

        List<NetflixRecords> getMoviesDataByType(String movieType);

        List<NetflixRecords> getMoviesDataByCountryName(String country);

        List<NetflixRecords> getTVShowsDataByUserDateInput(String startDate, String endDate);

        void addNetflixDetailsToDataBase(List<NetflixRecords> netflixData);
}
