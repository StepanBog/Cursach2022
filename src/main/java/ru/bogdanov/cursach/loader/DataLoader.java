package ru.bogdanov.cursach.loader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import ru.bogdanov.cursach.graph.TimeLine;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DataLoader {

    @SneakyThrows
        public TimeLine read(String path){
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        TimeLine timeLine = new TimeLine();

        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(path))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()){
            List<String[]> r = reader.readAll();
            r.forEach(x -> {
                timeLine.getDate().add(Parser.parseDate(x[0]));
                timeLine.getInd().add(Double.parseDouble(x[2]));
                    }
            );


        }
        return timeLine;
    }
}
