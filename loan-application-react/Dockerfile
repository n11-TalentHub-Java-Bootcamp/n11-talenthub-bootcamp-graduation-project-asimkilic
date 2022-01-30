FROM alpine:3.14
ENV NODE_VERSION 17.4.0
WORKDIR /loan-application-react
ENV PATH="/.node_modules/.bin:$PATH"
COPY . .

RUN npm run build
CMD ["npm","start"]