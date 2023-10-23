FROM sbtscala/scala-sbt:graalvm-ce-22.3.3-b1-java17_1.9.7_3.3.1

WORKDIR /root

COPY ./ ./

RUN sbt update

ENTRYPOINT ["sbt", "run"]
