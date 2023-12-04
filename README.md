Parses and outputs radiation samples from a file.

I estimate that a camping trip to a higher elevation / radiation zone was taken
from 5/31/2019 - 6/1/2019. I think this is the case because this date range
has the highest frequency of radiation counts within 5 of the maximum count
(which I found to be 38).

I initially sorted my output by count, but this wasn't too useful for
finding the date range. I then sorted by date, which made it easy to find.

To run the calculation, either pass the  edit the hardcoded file path
`FILE_NAME` variable in `Main.java`, or pass the file path as an argument.

Building with Maven:

* Build: `make`
* Rebuild: `make all`
* Run: `java -jar GeigerCounter-jar-with-dependencies.jar <path/to/file.txt>`

Tested with:
* Maven 3.9.5
* Java 21