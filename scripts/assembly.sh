#!/bin/bash
mkdir -p ../output
kotlinc ../src/*.kt -cp ../lib/kotlinx-cli-jvm-0.3.6.jar -include-runtime -d ../output/app.jar