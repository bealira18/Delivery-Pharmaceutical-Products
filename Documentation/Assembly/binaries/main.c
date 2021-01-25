#include <dirent.h>
#include <regex.h>
#include <unistd.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "asm.h"

regex_t regex;
pthread_t tID;

int stopProgram = 0;
int vehicleCount = 0;
int validVehicleCount = 0;
int maxPower = 0;

typedef struct {
	int wellParked;
	char name[48];
	int timestamp;
	int vehicleID;
	int maxCapacity;
	int currentCapacity;
} t_vehicle;

t_vehicle *vehicleArray;

void resizeArray()
{
	t_vehicle *placeHolder = (t_vehicle *) realloc(vehicleArray, (vehicleCount+1)*sizeof(t_vehicle));
	if (placeHolder == NULL)
	{
		printf("ERROR: Ran out of memory, terminating.\n");
		exit(1);
	}
	vehicleArray = placeHolder;
}

void deleteFlag(char *fileName)
{
	char deletePath[64] = "../lock_";
	strcat(deletePath, fileName);
	strcat(deletePath, ".data");
	remove(deletePath);
	strcat(deletePath, ".flag");
	remove(deletePath);
}

void writeFinal(int vehicleIndex, float estimate)
{
	char pathToEstimate[64] = "../estimate_";
	strcat(pathToEstimate, vehicleArray[vehicleIndex].name);
	strcat(pathToEstimate, ".data");

	FILE* stream = fopen(pathToEstimate, "w");
	if (stream == NULL)
	{
		printf("Something went wrong while creating the file %s. File not created.\n", pathToEstimate);
		return;
	}
	fprintf(stream, "%d;%d;%.2f", vehicleArray[vehicleIndex].timestamp, vehicleArray[vehicleIndex].vehicleID, estimate);
	fclose(stream);

	printf("Successfully wrote %s\n", pathToEstimate);
	strcat(pathToEstimate, ".flag");
	stream = fopen(pathToEstimate, "w");
	if (stream == NULL)
	{
		printf("Something went wrong while creating the file %s. File not created.\n", pathToEstimate);
		return;
	}
	fclose(stream);
}

void dealWithIt(char *fileName)
{
	char format[20];
	strncpy(format, fileName+5, 19);
	*(format+19) = '\0';

	char fullPath[64] = "../lock_";
	strcat(fullPath, format);
	strcat(fullPath, ".data");

	printf("Opening %s\n", fullPath);

	FILE* stream = fopen(fullPath, "r");
	if (stream == NULL)
	{
		printf("File does not exist. Flag was deleted.\n");
		deleteFlag(format);
		return;
	}

	vehicleCount++;
	resizeArray();

	/*Store the original name, idk, just do it*/

	strcpy(vehicleArray[vehicleCount - 1].name, format);
	
	char fullLine[1024], splitter[1024];

	fgets(fullLine, 1024, stream);
	fclose(stream);
	strcpy(splitter, fullLine);
	/*line 1: epoch time. Idk, its just useful*/
	char *tok = strtok(splitter, ";");
	if (tok == NULL)
	{
		printf("Failed on 1st Argument. Files deleted. Not enough info to return a error output.\n");
		vehicleArray[vehicleCount - 1].wellParked = 0;
		deleteFlag(format);
		return;
	}
	vehicleArray[vehicleCount - 1].timestamp = atoi(tok);

	/*line 2: vehicleID*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 2nd Argument. Files deleted. Not enough info to return a error output.\n");
		vehicleArray[vehicleCount - 1].wellParked = 0;
		deleteFlag(format);
		return;
	}
	vehicleArray[vehicleCount - 1].vehicleID = atoi(tok);

	/*line 3: maxCharge*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 3rd Argument. Files deleted. Returned error info.\n");
		/*this is to simulate a not connected bike*/
		vehicleArray[vehicleCount - 1].wellParked = 0;
		deleteFlag(format);
		writeFinal(vehicleCount - 1, -1.0f);
		return;
	}
	vehicleArray[vehicleCount - 1].maxCapacity = atoi(tok);

	/*line 4: currentCapacity*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 4th Argument. Files deleted. Returned error info.\n");
		/*this is to simulate a not connected bike*/
		vehicleArray[vehicleCount - 1].wellParked = 0;
		deleteFlag(format);
		writeFinal(vehicleCount - 1, -1.0f);
		return;
	}
	vehicleArray[vehicleCount - 1].currentCapacity = atoi(tok);	

	printf("All info detected, calculating.\n");
	validVehicleCount++;
	vehicleArray[vehicleCount - 1].wellParked = 1;

	int i = 0;

	/*printf("VehicleCount: %d; WellParkedCount: %d\n", vehicleCount, validVehicleCount);*/

	for (i = 0; i < vehicleCount; i++)
	{
		if (vehicleArray[i].wellParked == 1)
		{
			int result = est_battery((vehicleArray[i].maxCapacity - vehicleArray[i].currentCapacity) * 100000, maxPower*10/validVehicleCount);
			float realRes = (float) result / 100.0;

			/*printf("Power: %.2f; Vehicle Index: %d; To Charge: %d; Result: %d; Real Result %.2f\n", (float) maxPower/validVehicleCount, i, (vehicleArray[i].maxCapacity - vehicleArray[i].currentCapacity), result, realRes);*/
			writeFinal(i, realRes);
		}
		
	}

	deleteFlag(format);

}

void scanDir(void)
{
	DIR *d;
	struct dirent *dir;
	d = opendir("..");
	int regFailed;

	if (d)
	{
		while ((dir = readdir(d)) != NULL)
		{
			regFailed = regexec(&regex, dir->d_name, 0, NULL, 0);
			if (!regFailed && strlen(dir->d_name) == 34) /*regex was not complying with me, so I have to assure the string length*/
			{
				printf("Found %s, processing...\n", dir->d_name);
				dealWithIt(dir->d_name);
			}
		}
		closedir(d);
	}
}

void *timerThread(void *arg)
{
	while(!stopProgram)
	{
		scanDir();
		sleep(10);
	}
	return 0;
}

int main(void)
{
	int regFailed = regcomp(&regex, "^lock.*[.]data[.]flag$", 0);
	if (regFailed) {
		printf("Cant compile regex. Go fix it.\n");
		return 0;
	}

	vehicleArray = (t_vehicle *) malloc(sizeof(t_vehicle));

	printf("Type this parks maximum allowed power.\n");
	scanf("%d", &maxPower);

	if (maxPower <= 0)
	{
		printf("Invalid power value, exiting.\n");
		return 0;
	}
	
	pthread_create(&tID, NULL, &timerThread, NULL);
	
	int testInteger;
	printf("Max Power set to: %d\nType anything to stop the daemon.\n", maxPower);
	scanf("%d", &testInteger);
	stopProgram = 1;
	return 0;
}