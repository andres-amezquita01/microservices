FROM openjdk:17-alpine

ARG SCALA_VERSION=3.3.0
ARG SBT_VERSION=1.8.2
ENV SCALA_HOME=/usr/share/scala

EXPOSE 8090

RUN apk add --no-cache --virtual=.build-dependencies wget ca-certificates && \
  apk add --no-cache bash && \
  cd "/tmp" && \
  wget "https://github.com/lampepfl/dotty/releases/download/${SCALA_VERSION}/scala3-${SCALA_VERSION}.tar.gz" && \
  tar xzf "scala3-${SCALA_VERSION}.tar.gz" && \
  mkdir "${SCALA_HOME}" && \
  rm "/tmp/scala3-${SCALA_VERSION}/bin/"*.bat && \
  mv "/tmp/scala3-${SCALA_VERSION}/bin" "/tmp/scala3-${SCALA_VERSION}/lib" "${SCALA_HOME}" && \
  ln -s "${SCALA_HOME}/bin/"* "/usr/bin/" && \
  apk del .build-dependencies && \
  rm -rf "/tmp/"*

RUN apk add --no-cache --virtual=.build-dependencies bash curl bc ca-certificates && \
  cd "/tmp" && \
  update-ca-certificates && \
  scala -version && \
  scalac -version && \
  curl -fsL https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz | tar xfz - -C /usr/local && \
  $(mv /usr/local/sbt-launcher-packaging-$SBT_VERSION /usr/local/sbt || true) && \
  ln -s /usr/local/sbt/bin/* /usr/local/bin/ && \
  apk del .build-dependencies && \
  rm -rf "/tmp/"*

RUN \
  sbt -Dsbt.rootdir=true -batch sbtVersion && \
  rm -rf project target

WORKDIR /root

COPY ./ ./

RUN sbt update

CMD ["sbt", "run"]
