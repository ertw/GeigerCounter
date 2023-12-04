APPLICATION=GeigerCounter-jar-with-dependencies.jar

build: $(APPLICATION)
$(APPLICATION):
	mvn clean compile assembly:single

clean:
	rm -f $(APPLICATION)

all: clean build