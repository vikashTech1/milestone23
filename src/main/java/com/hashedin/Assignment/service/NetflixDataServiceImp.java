package com.hashedin.Assignment.service;

import com.hashedin.Assignment.Utils.Utility;
import com.hashedin.Assignment.pojo.Netflix;
import com.hashedin.Assignment.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflixDataServiceImp implements NetflixDataService {

    String file = "src/netflix_titles.csv";

    private AssignmentRepository assignmentRepository;

    @Autowired
    public NetflixDataServiceImp(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<Netflix> getTVShowRecords(int count) {
        List<Netflix> netflixData = Utility.readDataFromCSV(file);
        return Utility.filterFirstNTVShowsRecords(netflixData, count);
    }

    @Override
    public List<Netflix> getMoviesByType(String movieType) {
        List<Netflix> netflixData = Utility.readDataFromCSV(file);
        return Utility.filterMoviesRecordsByType(netflixData, movieType);
    }

    @Override
    public List<Netflix> getMoviesByCountry(String country) {
        List<Netflix> netflixData = Utility.readDataFromCSV(file);
        return Utility.filterMoviesByCountry(netflixData, country);
    }

    @Override
    public List<Netflix> getTVShowsByStartDateAndEndDate(String startDate, String endDate) {
        List<Netflix> netflixData = Utility.readDataFromCSV(file);
        return Utility.performDateSearch(Utility.filterTVShowsRecords(netflixData),
                startDate, endDate);
    }

    @Override
    public void addNetflixDataToDb(List<Netflix> netflixData) {
        netflixData.stream().forEach(data -> assignmentRepository.save(data));
    }


}
