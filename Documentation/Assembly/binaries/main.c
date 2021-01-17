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

void deleteFlag(char *fileName)
{
	char deletePath[64] = "../";
	strcat(deletePath, fileName);
	remove(deletePath);
}

void writeFinal(char *fileName, char *lineToWrite)
{
	char pathToEstimate[64] = "../estimate";
	strcat(pathToEstimate, fileName);

	FILE* stream = fopen(pathToEstimate, "w");
	fprintf(stream, lineToWrite);
	fclose(stream);
	printf("Successfully wrote %s\n", pathToEstimate);
	strcat(pathToEstimate, ".flag");
	stream = fopen(pathToEstimate, "w");
	fclose(stream);
}

void dealWithIt(char *fileName)
{
	char format[26];
	strncpy(format, fileName+4, 25);
	*(format+25) = '\0';

	char fullPath[64] = "../lock";
	strcat(fullPath, format);
	printf("Opening %s\n", fullPath);

	FILE* stream = fopen(fullPath, "r");
	if (stream == NULL)
	{
		printf("File does not exist. Flag was deleted.\n");
		deleteFlag(fileName);
		return;
	}
	char fullLine[1024], splitter[1024], finalLine[1024];

	fgets(fullLine, 1024, stream);
	fclose(stream);
	strcpy(splitter, fullLine);
	/*line 1: email*/
	char *tok = strtok(splitter, ";");
	if (tok == NULL)
	{
		printf("Failed on 1st Argument. Flag was deleted. Not enough info to return a error output.\n");
		deleteFlag(fileName);
		return;
	}
	strcpy(finalLine, tok);
	strcat(finalLine, ";");

	/*line 2: scooterid*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 2nd Argument. Flag was deleted. Not enough info to return a error output.\n");
		deleteFlag(fileName);
		return;
	}
	strcat(finalLine, tok);
	strcat(finalLine, ";");

	/*line 3: maxVehicleCharge*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 3rd Argument. Flag was deleted. Returned error info.\n");
		/*this is to simulate a not connected bike*/
		deleteFlag(fileName);
		strcat(finalLine, "-1");
		writeFinal(format, finalLine);
		return;
	}
	int maxVehicleCharge = atoi(tok);

	/*line 4: chargingPointCapacity*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 4th Argument. Flag was deleted. Returned error info.\n");
		/*this is to simulate a not connected bike*/
		deleteFlag(fileName);
		strcat(finalLine, "-1");
		writeFinal(format, finalLine);
		return;
	}
	int chargingPointCapacity = atoi(tok);

	/*line 5: currentPercentage*/
	tok = strtok(NULL, ";");
	if (tok == NULL)
	{
		printf("Failed on 5th Argument. Flag was deleted. Returned error info.\n");
		/*this is to simulate a not connected bike*/
		deleteFlag(fileName);
		strcat(finalLine, "-1");
		writeFinal(format, finalLine);
		return;
	}
	int currentPercentage = atoi(tok);

	printf("All info detected, calculating.\n");

	int result = est_battery(maxVehicleCharge, chargingPointCapacity, currentPercentage*10);

	float realRes = (float) result / 100.0;

	char sResult[32];
	sprintf(sResult, "%.2f", realRes);

	deleteFlag(fileName);
	strcat(finalLine, sResult);
	writeFinal(format, finalLine);

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

	pthread_create(&tID, NULL, &timerThread, NULL);
	
	int testInteger;
	printf("Type anything to stop the daemon.\n");
	scanf("%d", &testInteger);
	stopProgram = 1;
	return 0;
}