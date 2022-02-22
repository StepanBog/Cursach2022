set xdata time
set timefmt "%Y-%m-%d"
set grid
plot 'fourTimeList.txt' using 1:2 with lines,  'fourTimeList.txt' using 1:3 with lines ,'fourTimeList.txt' using 1:4 with lines ,'fourTimeList.txt' using 1:5 with lines