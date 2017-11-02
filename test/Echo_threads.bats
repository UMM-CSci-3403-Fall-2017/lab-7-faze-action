#!/usr/bin.env bats

setup() {
	BATS_TMPDIR='mktemp --directory'
	cd src
	rm -f echoserver/EchoServer.class
	javac echoserver/*.java
	java echoserver.EchoServer &
	cd ..
}

teardown() {
	rm -rf "$BATS_TMPDIR"
	kill %1
	sleep 1
}
