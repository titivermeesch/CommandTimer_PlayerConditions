#!/bin/bash

cat build.gradle | grep version | head -n 1 | cut -d"'" -f2
