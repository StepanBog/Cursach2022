set xdata time
set encoding utf8
set timefmt "%Y-%m-%d"
plot 'tripleTimeList.txt' using 1:2 with lines ti "Шум",'tripleTimeList.txt' using 1:3 with lines ti "Тренд",'tripleTimeList.txt' using 1:4 with lines ti "Сезон",