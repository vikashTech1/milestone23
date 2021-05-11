package com.hashedin.Assignment.controller;


import com.hashedin.Assignment.exception.ListEmptyException;
import com.hashedin.Assignment.pojo.Netflix;
import com.hashedin.Assignment.service.NetflixDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class NetflixDataController {


    private NetflixDataService netflixDataService;

    @Autowired
    public NetflixDataController(NetflixDataService netflixDataService) {
        this.netflixDataService = netflixDataService;
    }

    @GetMapping(path = "/tvshows")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Netflix>> getNetflixDataRecords(
            @RequestParam(value = "count", required = false) String count,
            @RequestParam(value = "movieType", required = false) String movieType,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            HttpServletRequest request) {

        String tag = request.getHeader("X-Auth-Token");
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {

            List<Netflix> filteredList = null;
            long startTime = System.currentTimeMillis();
            long endTime = 0;

            if (count != null) {
                filteredList = netflixDataService.getTVShowRecords(Integer.parseInt(count));
                endTime = System.currentTimeMillis();
            } else if (movieType != null) {
                filteredList = netflixDataService.getMoviesByType(movieType);
                endTime = System.currentTimeMillis();

            } else if (country != null) {
                filteredList = netflixDataService.getMoviesByCountry(country);
                endTime = System.currentTimeMillis();

            } else if (startDate != null && endDate != null) {
                filteredList = netflixDataService.getTVShowsByStartDateAndEndDate(startDate, endDate);
                if (filteredList.size() == 0) throw new ListEmptyException();
                endTime = System.currentTimeMillis();
            }
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time:" + timeElapsed);

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-TIME-TO-EXECUTE", timeElapsed + "ms");

            //return ResponseEntity.ok(filteredList);
            return new ResponseEntity<>(filteredList, headers, HttpStatus.OK);
        }

    }

    @PostMapping(path = "/addtodb")
    public void addNetflixDataToDB(@RequestBody List<Netflix> netflixData) {
        netflixDataService.addNetflixDataToDb(netflixData);
    }

    @PostMapping(path = "/addtodborcsv")
    public void addNetflixDataToDbOrCSV(@RequestParam(value = "option") String option,
                                        @RequestBody List<Netflix> netflixData) {

    }


}
