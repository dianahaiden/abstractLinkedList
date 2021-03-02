#!/bin/bash
set -x

javac -Xlint -d bin src/genlist/Node.java
javac -Xlint -cp bin:lib/genlistadt.jar -d bin src/genlist/LinkedGenList.java
javac -Xlint -d bin -cp bin:lib/genlistadt.jar src/genlist/ListIterator.java
javac -Xlint -d bin -cp bin:lib/genlistadt.jar src/genlist/LinkedGenListTest.java
java -cp bin:lib/genlistadt.jar genlist.LinkedGenListTest
