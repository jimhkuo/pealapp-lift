#!/bin/sh
ps -ef | grep PealApp | awk '{print $2}' | xargs kill
