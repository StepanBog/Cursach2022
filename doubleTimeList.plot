set xdata time
set timefmt "%Y-%m-%d"
set grid
plot 'doubleTimeList.txt' using 1:2 with lines,  'doubleTimeList.txt' using 1:3 with lines