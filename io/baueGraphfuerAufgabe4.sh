#!/bin/bash
echo "#gerichtet"
for i in $(seq 1 $1) ; do

  for j in $(seq 1 $1) ; do
    if [ $i == $j ]] ; then continue ; fi
    RAND=$(($RANDOM / 100 ))
    echo "a${i},a${j},${RAND}"
  done

done
