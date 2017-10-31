#!/bin/bash

numCalls=$1
file=$2

for (( i=1; i<=$numCalls; i++ ))
do
	echo "Doing run $i"
	java echoserver.EchoClient < $file > /tmp/$i &
done
echo "Now waiting"
date
wait
echo "Done"
date
