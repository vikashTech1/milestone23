package com.hashedin.Assignment.Utils;

import com.hashedin.Assignment.pojo.Netflix;
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

public class Utility {

//    String file="src/netflix_titles.csv";

    @Cacheable(value = "readDataFromCSV")
    public static List<Netflix> readDataFromCSV(String filename) {

        try {

            Stream<String> lines = Files.lines(Paths.get(filename));

            return lines
                    .skip(0)
                    .map(line -> line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))
                    .map(data -> new Netflix(data[0], data[1], data[2], data[3], data[4], data[5], data[6],
                            data[7], data[8], data[9], data[10], data[11]))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Netflix> filterFirstNTVShowsRecords(List<Netflix> data, int count) {

        return data.stream().filter(row -> row.getType().equals("TV Show")).limit(count).collect(Collectors.toList());

    }

    public static List<Netflix> filterTVShowsRecords(List<Netflix> data) {

        return data.stream().filter(row -> row.getType().equals("TV Show")).collect(Collectors.toList());

    }

    public static List<Netflix> filterMoviesRecordsByType(List<Netflix> data, String movieType) {
        //example type: Horror Movies
        return data.stream().filter(row -> row.getListedAs().contains(movieType)).collect(Collectors.toList());
    }

    public static List<Netflix> filterMoviesByCountry(List<Netflix> data, String country) {

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

    public static Date dateParser(String userDate) {
        String pattern = "dd-MMM-yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(userDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Netflix> performDateSearch(List<Netflix> filteredList, String startDate, String endDate) {

        String date = startDate.trim() + "," + endDate.trim();
        List<Netflix> finalList = filteredList.stream()
                .filter(row -> row.getDateAdded().length() > 0 &&
                        !(row.getDateAdded().charAt(0) >= 'a' && row.getDateAdded().charAt(0) <= 'z') &&
                        isWithinRange(date, row.getDateAdded().trim()))
                .collect(Collectors.toList());

        return finalList;
    }


    public static boolean isWithinRange(String userDate, String dateAdded) {
        String[] tempDate = userDate.split(",");
        Date startDate = Utility.dateParser(tempDate[0]);
        Date endDate = Utility.dateParser(tempDate[1]);

        Date newDateAdded = null;
        if (Utility.isValidDate(dateAdded)) {
            newDateAdded = Utility.dateParser(dateAdded);
            //return startDate.compareTo(newDateAdded) * newDateAdded.compareTo(endDate) >= 0;
            return startDate.equals(newDateAdded) || endDate.equals(newDateAdded);
        }

        return false;
    }
}
