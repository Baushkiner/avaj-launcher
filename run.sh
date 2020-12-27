#!/bin/bash
javac -d . ./src/*.java
java  src.Simulator ./scenario.txt
rm ./src/*.class
rm ./src/aircraft/*.class
rm ./src/file/*.class