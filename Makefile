.PHONY: build clean
BUILD_MARKER=.build_complete

build: $(BUILD_MARKER)
$(BUILD_MARKER):
	mvn clean compile assembly:single
	@touch $(BUILD_MARKER)

clean:
	rm $(BUILD_MARKER)

all: clean build