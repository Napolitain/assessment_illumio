# Assessment Illumio

The code is written using Java. The version don't really matters as I don't use many specific features.

### How to run the code

To simplify the execution of the code I've created a Dockerfile.
You just need to build :

```bash
docker build -t assessment_maxime_boucher .
```

You can a distroless image with the JAR file.

And then run with default files :

```bash
docker run --rm assessment_maxime_boucher
```

or with your files :

```bash
docker run --rm -v $(pwd):/app/data assessment_maxime_boucher data/src/main/resources/lookup.csv data/src/main/resources/flow_logs.csv
```

The first run command will use resources files packaged in the jar file (in the docker image) as examples.
The second run command will use the files you provide as arguments.

### How to develop

I used IntelliJ IDEA to develop the code, so running through IntelliJ must be easy as well (or gradle-wrapper).
jar generation is done using gradle, so you can run `./gradlew jar` to generate the jar file.
There are some tests cases made with TestNG.

LogAnalyser and LogParser are interfaces.
FlowLogAnalyser and FlowLogParser are the implementations of the interfaces for Flow logs.
FlowLog is a record (dataclass) for the flow logs.
ProtocolNumbers is an enum to map protocols (tcp, udp...) to their numbers (6, 17...).
Main is the entry point of the program.
LookupTable is a class to store the mappings of tags to count or other mappings.

