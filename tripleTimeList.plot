set xdata time
set timefmt "%Y-%m-%d"
set grid
plot 'tripleTimeList.txt' using 1:2 with lines,  'tripleTimeList.txt' using 1:3 with lines ,'tripleTimeList.txt' using 1:4 with lines