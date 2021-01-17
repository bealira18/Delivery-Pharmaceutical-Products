est_calc: main.o asm.o
	gcc -m32 -std=c90 -pthread -g -Wall main.o asm.o -o est_calc
	
main.o: main.c asm.h
	gcc -m32 -std=c90 -pthread -g -Wall -c main.c -o main.o
	
asm.o: asm.s
	gcc -m32 -std=c90 -g -Wall -c asm.s -o asm.o

clean:
	rm -f *.o est_calc
	
run: est_calc
	./est_calc

debug: est_calc
	gdb -tui est_calc
	
