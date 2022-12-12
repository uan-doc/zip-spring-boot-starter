#!/bin/bash
cd /Users/Shared/ON3/git/devops/jenkins/api || exit
./script_clone_job.sh s3-spring-boot-starter zip-spring-boot-starter false
./add_job_to_view.sh 'zip-spring-boot-starter' 'GedocFlex - MicroServices'
./build.sh 'zip-spring-boot-starter'
