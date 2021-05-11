package com.hashedin.Assignment.JavaControllerSpring;


import com.hashedin.Assignment.JavaException.ExceptionClass;
import com.hashedin.Assignment.netflixContent.NetflixDataService;
import com.hashedin.Assignment.netflixDetails.NetflixRecords;

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
    public ResponseEntity<List<NetflixRecords>> getNetflixDataRecords(
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

            List<NetflixRecords> filteredList = null;
            long startTime = System.currentTimeMillis();
            long endTime = 0;

            if (count != null) {
                filteredList = netflixDataService.getDataTypeTVShow(Integer.parseInt(count));
                endTime = System.currentTimeMillis();
            } else if (movieType != null) {
                filteredList = netflixDataService.getMoviesDataByType(movieType);
                endTime = System.currentTimeMillis();

            } else if (country != null) {
                filteredList = netflixDataService.getMoviesDataByCountryName(country);
                endTime = System.currentTimeMillis();

            } else if (startDate != null && endDate != null) {
                filteredList = netflixDataService.getTVShowsDataByUserDateInput(startDate, endDate);
                if (filteredList.size() == 0) throw new ExceptionClass();
                endTime = System.currentTimeMillis();
            }
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time:" + timeElapsed);

            HttpHeaders headers = new HttpHeaders();
            headers.add("TIME-TO-EXECUTE", timeElapsed + "ms");


            return new ResponseEntity<>(filteredList, headers, HttpStatus.OK);
        }

    }

    @PostMapping(path = "/addtodb")
    public void addNetflixDetailsToDataBase(@RequestBody List<NetflixRecords> netflixData) {
        netflixDataService.addNetflixDetailsToDataBase(netflixData);
    }

    @PostMapping(path = "/addtodborcsv")
    public void addNetflixDataToDbOrCSV(@RequestParam(value = "option") String option,
                                        @RequestBody List<NetflixRecords> netflixData) {

    }


}
