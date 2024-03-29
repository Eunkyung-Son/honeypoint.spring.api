# ------------------------------------------------------------------------------
#               NOTE: THIS DOCKERFILE IS GENERATED VIA "build_latest.sh" or "update_multiarch.sh"
#
#                       PLEASE DO NOT EDIT IT DIRECTLY.
# ------------------------------------------------------------------------------
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

FROM ubuntu:18.04

ENV LANG='ko_KR.UTF-8' LC_ALL='ko_KR.UTF-8'

RUN apt-get update \
    && apt-get install -y --no-install-recommends tzdata curl ca-certificates fontconfig locales \
    && echo "ko_KR.UTF-8 UTF-8" >> /etc/locale.gen \
    && locale-gen ko_KR.UTF-8 \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_VERSION jdk-14.0.2+12_openj9-0.21.0

RUN set -eux; \
    ARCH="$(dpkg --print-architecture)"; \
    case "${ARCH}" in \
    ppc64el|ppc64le) \
    ESUM='ad7a55a3669878c0c7d7c66faafe7c626d4341374719b6fdd81d2986c6e80945'; \
    BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.2%2B12_openj9-0.21.0/OpenJDK14U-jre_ppc64le_linux_openj9_14.0.2_12_openj9-0.21.0.tar.gz'; \
    ;; \
    s390x) \
    ESUM='6a3c0f61873bfcd23da806f5df9267e08cbfd064ab7cf6e8b08648b4cbe2f9e2'; \
    BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.2%2B12_openj9-0.21.0/OpenJDK14U-jre_s390x_linux_openj9_14.0.2_12_openj9-0.21.0.tar.gz'; \
    ;; \
    amd64|x86_64) \
    ESUM='3a137146a7b0bd8b029e72beb37c5fbb09dcfb9e33a10125076fff1555227cfd'; \
    BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.2%2B12_openj9-0.21.0/OpenJDK14U-jre_x64_linux_openj9_14.0.2_12_openj9-0.21.0.tar.gz'; \
    ;; \
    *) \
    echo "Unsupported arch: ${ARCH}"; \
    exit 1; \
    ;; \
    esac; \
    curl -LfsSo /tmp/openjdk.tar.gz ${BINARY_URL}; \
    echo "${ESUM} */tmp/openjdk.tar.gz" | sha256sum -c -; \
    mkdir -p /opt/java/openjdk; \
    cd /opt/java/openjdk; \
    tar -xf /tmp/openjdk.tar.gz --strip-components=1; \
    rm -rf /tmp/openjdk.tar.gz;

ENV JAVA_HOME=/opt/java/openjdk \
    PATH="/opt/java/openjdk/bin:$PATH"
ENV JAVA_TOOL_OPTIONS="-XX:+IgnoreUnrecognizedVMOptions -XX:+UseContainerSupport -XX:+IdleTuningCompactOnIdle -XX:+IdleTuningGcOnIdle"



VOLUME /honeypoint
RUN mkdir -p /honeypoint/data
ADD honeypoint-0.0.1-SNAPSHOT.jar /honeypoint/server.jar

RUN sh -c 'touch /honeypoint/server.jar'
ENV JAVA_OPTS=""

ENV LANG=ko_KR.UTF-8
ENV LANGUAGE=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8


ENTRYPOINT [ "/bin/bash", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom --spring.config.location=classpath:file:/honeypoint/application.properties -jar /honeypoint/server.jar" ]


EXPOSE 8080
