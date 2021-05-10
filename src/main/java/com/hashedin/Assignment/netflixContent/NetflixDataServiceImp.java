package com.hashedin.Assignment.netflixContent;

import com.hashedin.Assignment.Utilities.Utility;
import com.hashedin.Assignment.netflixDetails.NetflixRecords;
import com.hashedin.Assignment.repo.AssignmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class NetflixDataServiceImp implements NetflixDataService{

    String file="src/netflix_titles.csv";

    private AssignmentRepository assignmentRepository;

    
    public NetflixDataServiceImp(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<NetflixRecords> getDataTypeTVShow(int count) {
        List<NetflixRecords> netflixData = Utility.readDataFromCSV(file);
        return Utility.filterFirstNTVShows(netflixData, count);
    }

    @Override
    public List<NetflixRecords> getMoviesDataByType(String movieType) {
        List<NetflixRecords> netflixData = Utility.readDataFromCSV(file);
        return Utility.filterByType(netflixData, movieType);
    }

    @Override
    public List<NetflixRecords> getMoviesDataByCountryName(String country) {
        List<NetflixRecords> netflixData = Utility.readDataFromCSV(file);
        return Utility.filterByCountry(netflixData, country);
    }

    @Override
    public List<NetflixRecords> getTVShowsDataByUserDateInput(String startDate, String endDate) {
        List<NetflixRecords> netflixData = Utility.readDataFromCSV(file);
        return Utility.DateSearch(Utility.filterTVShows(netflixData),
                startDate, endDate);
    }
    

    @Override
    public void addNetflixDetailsToDataBase(List<NetflixRecords> netflixData) {
        netflixData.stream().forEach(data -> assignmentRepository.save(data));
    }


}
