package ru.bogdanov.cursach.loader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.bogdanov.cursach.TimeSeriesAnalyse.TimeSeries;

import java.io.FileReader;
import java.util.List;

@UtilityClass
public class DataLoader {

    @SneakyThrows
        public TimeSeries read(String path){
        CSVParser csvParser = new CSVParserBuilder().withSeparator('\t').build();
        TimeSeries timeSeries = new TimeSeries();

        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(path))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()){
            List<String[]> r = reader.readAll();
            r.forEach(x -> {
                timeSeries.getDate().add(Parser.parseDate(x[0]));
                timeSeries.getInd().add(Double.parseDouble(x[3]));
                    }
            );


        }
        return timeSeries;
    }
}
