width=1
set encoding utf8
bin(x, s) = s*int(x/s) + width/2
set boxwidth width
set grid
plot 'barChart.txt' u ($1):($2) \
s f w boxes fs solid 0.5 title 'Автокорреляция без тренда'