FROM amazoncorretto:11-alpine

ARG BASE_PATH=/usr/src/guestbook-api

RUN mkdir -p ${BASE_PATH}

WORKDIR ${BASE_PATH}

ADD build/libs/app.jar .

ADD bin/entrypoint.sh .

ENTRYPOINT [ "/bin/sh", "entrypoint.sh" ]
