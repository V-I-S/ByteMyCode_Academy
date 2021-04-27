# UBS ByteMyCode: Academy
Examples  &amp; materials related with my _UBS ByteMyCode: Academy_ discourses

## How to Write Your Future: Hands-on Log Aggregation & Monitoring (with ELK Demo)

Lecture consisted in theoretical & practical part. In this directory you will find
- [elk] Simple Docker-Composer file that setups an ELK stack on Docker on your machine. Setup is simple, without auxiliary nodes: Filebeat -> Logstash -> Elasticsearch -> Kibana. To work correctly - `docker compose` command shall be called from its location. Obviously you need to have Docker installed first: https://www.docker.com/get-started
- [bmc_2021_demo] Basic SpringBoot application that employs sample slf4j+log4j2 logging & configures logs to be stored in `logs` directory in application workspace.
