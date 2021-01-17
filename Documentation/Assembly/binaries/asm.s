.section .data

	.equ EFFICIENCY, 7 # 70/10
	.equ MAX_CHARGE, 1000 # 1*100

.section .text

	.global est_battery	# int est_battery(int capacity, int charge, int currentCapacity)
	
	est_battery:
		# prologue
		pushl %ebp	# save previous stack frame pointer
		movl %esp, %ebp	# the stack frame pointer for sum function
		pushl %ebx
		
		movl 16(%ebp), %edx  # int currentCapacity
		movl $MAX_CHARGE, %ebx
		subl %edx, %ebx # (Total charge - currentCharge) Stored in %ebx

		movl 8(%ebp), %eax  # int capacity
		imul %ebx, %eax  # (Capacity(%eax) * missing charge(%ebx)) Stored in %eax 

		movl $EFFICIENCY, %ebx  # ebx no longer in use, so using it
		cdq
		idiv %ebx # (%eax / Efficiency) result in %eax

		movl 12(%ebp), %ebx  # int currentCapacity; ebx no longer in use, so using it
		cdq
		idiv %ebx # (%eax / currentCapacity) result in %eax

	est_battery_end:
		# epilogue
		popl %ebx
		movl %ebp, %esp	# restore the previous stack pointer ("clear" the stack)
		popl %ebp	# restore the previous stack frame pointer
		ret
