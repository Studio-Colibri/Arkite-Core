#!/bin/bash

# Arkite-Core 단일 빌드 & 복사 스크립트
# (결과물은 D:/Arkite/Build/Arkite-Core.jar 로 복사)

set -e

echo "== Arkite-Core 빌드 시작 =="
./gradlew clean build

# 산출물 복사 (이미 gradle에서 경로 지정했다면 불필요, 그래도 보장용으로 추가)
cp build/libs/Arkite-Core.jar /mnt/d/Arkite/Build/Arkite-Core.jar

echo "== 빌드 및 복사 완료! =="
