#!/bin/sh
sbt assembly
ssh hk2109@markham.doc.ic.ac.uk "./killPeal.sh"
cd target
NAME=`ls -tr PealApp-lift-* | tail -1`
scp $NAME hk2109@markham.doc.ic.ac.uk:
cd ..
command="java -jar -Drun.mode=production "$NAME" 55555"
echo $command
ssh hk2109@markham.doc.ic.ac.uk $command&
