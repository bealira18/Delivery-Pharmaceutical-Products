park_sys: main.o asm.o
	gcc -m32 -std=c90 -pthread -g -Wall main.o asm.o -o park_sys
	
main.o: main.c asm.h
	gcc -m32 -std=c90 -pthread -g -Wall -c main.c -o main.o
	
asm.o: asm.s
	gcc -m32 -std=c90 -g -Wall -c asm.s -o asm.o

clean:
	rm -f *.o park_sys
	
run: park_sys
	./park_sys

debug: park_sys
	gdb -tui park_sys
	