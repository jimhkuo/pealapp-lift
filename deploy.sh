#!/bin/sh
sbt assembly
cd target
NAME=`ls -tr PealApp-lift-* | tail -1`
cd ..
scp $NAME hk2109@markham.doc.ic.ac.uk:
ssh hk2109@markham.doc.ic.ac.uk "./killPeal.sh"
command="java -jar "$NAME" 55555"
echo $command
ssh hk2109@markham.doc.ic.ac.uk $command&