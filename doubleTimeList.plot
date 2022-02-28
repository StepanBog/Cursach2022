set xdata time
set encoding utf8
set timefmt "%Y-%m-%d"
plot 'doubleTimeList.txt' using 1:2 with lines ti "Ряд с sin",'doubleTimeList.txt' using 1:3 with lines ti "Ряд с cos"