#!/bin/sh
sbt assembly
NAME`ls -tr PealApp-lift-* | tail -1`
scp $NAME hk2109@markham.doc.ic.ac.uk:
ssh hk2109@markham.doc.ic.ac.uk "ps -ef | grep PealApp | awk '{print $2}' | xargs kill"
ssh hk2109@markham.doc.ic.ac.uk "nohup java -jar "$NAME" 55555&"