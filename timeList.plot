set xdata time
set encoding utf8
set timefmt "%Y-%m-%d"
plot 'timeList.txt' using 1:2 with lines ti "Среднесуточная температура"