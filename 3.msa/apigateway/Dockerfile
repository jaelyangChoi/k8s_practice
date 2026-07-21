# 필요프로그램 설치
# FROM: 이 이미지를 어떤 기존 이미지 위에서부터 쌓기 시작할지. JDK는 무겁다. 이 base 위에 레이어를 쌓는 구조다.
FROM eclipse-temurin:17-jdk as stage1
# 파일 복사
WORKDIR /app
COPY gradle gradle
COPY src src
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
# 빌드
RUN chmod +x gradlew
RUN ./gradlew bootJar

# 두번째 스테이지. 새로운 from 환경에서 다시 시작. 마지막 스테이지가 이미지가 된다. -> 스테이지를 나누면 딱 실행에 필요한 환경만 갖는다.
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=stage1 /app/build/libs/*.jar app.jar

# 실행 : CMD 또는 ENTRYPOINT를 통해 컨테이너를 배열 형태의 명령어로 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
