package com.hashedin.Assignment.Utilities;

import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hashedin.Assignment.netflixDetails.NetflixRecords;

public class Utility {

//    String file="src/netflix_titles.csv";

    @Cacheable(value = "readDataFromCSV")
    public static List<NetflixRecords> readDataFromCSV(String filename) {

        try {

            Stream<String> rows = Files.lines(Paths.get(filename));

            return rows
                    .skip(0)
                    .map(row -> row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))
                    .map(data -> new NetflixRecords(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
                            data[7], data[8], data[9], data[10], data[11]))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<NetflixRecords> filterFirstNTVShows(List<NetflixRecords> data, int count) {

        return data.stream().filter(row -> row.getType().equals("TV Show")).limit(count).collect(Collectors.toList());

    }

    public static List<NetflixRecords> filterTVShows(List<NetflixRecords> data) {

        return data.stream().filter(row -> row.getType().equals("TV Show")).collect(Collectors.toList());

    }

    public static List<NetflixRecords> filterByType(List<NetflixRecords> data, String movieType) {
        //example type: Horror Movies
        return data.stream().filter(row -> row.getListedAs().contains(movieType)).collect(Collectors.toList());
    }

    public static List<NetflixRecords> filterByCountry(List<NetflixRecords> data, String country) {

        return data.stream().filter(row -> row.getType().equals("Movie")
                && row.getCountry().equals(country)).collect(Collectors.toList());
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
        sdf.setLenient(false);

        try {
            Date d = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Date dateFormate(String userDate) {
        String pattern = "dd-MMM-yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(userDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<NetflixRecords> DateSearch(List<NetflixRecords> filteredList, String startDate, String endDate) {

        String date = startDate.trim() + "," + endDate.trim();
        List<NetflixRecords> finalList = filteredList.stream()
                .filter(row -> row.getDateAdded().length() > 0 &&
                        !(row.getDateAdded().charAt(0) >= 'a' && row.getDateAdded().charAt(0) <= 'z') &&
                        moviesBetweenUserDateInput(date, row.getDateAdded().trim()))
                .collect(Collectors.toList());

        return finalList;
    }


    public static boolean moviesBetweenUserDateInput(String userDate, String dateAdded) {
        String[] tempDate = userDate.split(",");
        Date startDate = Utility.dateFormate(tempDate[0]);
        Date endDate = Utility.dateFormate(tempDate[1]);

        Date newDateAdded = null;
        if (Utility.isValidDate(dateAdded)) {
            newDateAdded = Utility.dateFormate(dateAdded);
            return startDate.equals(newDateAdded) || endDate.equals(newDateAdded);
        }

        return false;
    }
}
