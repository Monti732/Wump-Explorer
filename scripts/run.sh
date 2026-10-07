#!/bin/bash
java -cp "../output/app.jar;../lib/kotlinx-cli-jvm-0.3.6.jar" MainKt "$@"

# java -cp "../output/app.jar:../lib/kotlinx-cli-jvm-0.3.6.jar" MainKt -l aboba -p 1234 -a read -r A.B.C -v 20
# java -cp "../output/app.jar:../lib/kotlinx-cli-jvm-0.3.6.jar" MainKt -h