package ru.bogdanov.cursach.loader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import ru.bogdanov.cursach.TimeLineAnalyse.TimeLine;

import java.io.FileReader;
import java.util.List;

public class DataLoader {

    @SneakyThrows
        public TimeLine read(String path){
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
        TimeLine timeLine = new TimeLine();

        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(path))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()){
            List<String[]> r = reader.readAll();
            r.forEach(x -> {
                timeLine.getDate().add(Parser.parseDate(x[0]));
                timeLine.getInd().add(Double.parseDouble(x[5]));
                    }
            );


        }
        return timeLine;
    }
}
