FROM platformatic/platformatic:latest

USER root

COPY . .

EXPOSE 3042

RUN platformatic db migrations apply
CMD [ "platformatic","db", "start" ]
