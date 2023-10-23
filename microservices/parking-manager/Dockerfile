FROM node:18-alpine
WORKDIR /usr/src/app
COPY . .
RUN npm install
COPY . .
EXPOSE 3042

RUN npx platformatic db migrations apply
CMD [ "npm", "start" ]
