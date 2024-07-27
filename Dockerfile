FROM ubuntu:latest
LABEL authors="savio"

ENTRYPOINT ["top", "-b"]