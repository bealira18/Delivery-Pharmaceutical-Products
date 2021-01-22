.section .data

	.equ EFFICIENCY, 70 # 0.7*100

.section .text

	.global est_battery	# int est_battery(int neeededCapacity, int charge);
	
	est_battery:
		# prologue
		pushl %ebp	# save previous stack frame pointer
		movl %esp, %ebp	# the stack frame pointer for sum function

		movl 8(%ebp), %eax  # int neeededCapacity

		movl $EFFICIENCY, %ecx  # ecx
		cdq
		idiv %ecx # (%eax neeededCapacity / Efficiency) result in %eax

		movl 12(%ebp), %ecx  # int currentCapacity; ecx no longer in use, so using it
		cdq
		idiv %ecx # (%eax / currentCapacity) result in %eax

	est_battery_end:
		# epilogue
		movl %ebp, %esp	# restore the previous stack pointer ("clear" the stack)
		popl %ebp	# restore the previous stack frame pointer
		ret
