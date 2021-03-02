#!/bin/bash
set -x

javac -Xlint -d bin src/cs1302/genlist/Node.java
javac -Xlint -cp bin:lib/genlistadt.jar -d bin src/cs1302/genlist/LinkedGenList.java
javac -Xlint -d bin -cp bin:lib/genlistadt.jar src/cs1302/genlist/ListIterator.java
javac -Xlint -d bin -cp bin:lib/genlistadt.jar src/cs1302/genlist/LinkedGenListTest.java
java -cp bin:lib/genlistadt.jar cs1302.genlist.LinkedGenListTest
