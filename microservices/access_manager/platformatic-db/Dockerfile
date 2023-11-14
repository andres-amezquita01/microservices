FROM platformatic/platformatic:latest 
USER root 
COPY . . 
EXPOSE 3045 
RUN platformatic db migrations apply 
CMD [ "platformatic","db", "start" ]
