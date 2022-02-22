width=1
bin(x, s) = s*int(x/s) + width/2
set boxwidth width
set grid
plot 'correlogram.txt' u ($1):($2) \
s f w boxes fs solid 0.5 title 'гистограмма'