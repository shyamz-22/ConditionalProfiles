#!/usr/bin/env bash
set -x

set -e


main() {

  CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)

  echo "Current Branch Version : ${CURRENT_BRANCH}"

  echo "Current Branch Version with travis : ${TRAVIS_BRANCH}"

  if [ ${TRAVIS_BRANCH} != "master" ]; then
      git checkout ${CURRENT_BRANCH}

      ./gradlew clean build  -PSONATYPE_USERNAME=${SONATYPE_USERNAME} -PSONATYPE_PASSWORD=${SONATYPE_PASSWORD}

      ./gradlew versionFile  -PSONATYPE_USERNAME=${SONATYPE_USERNAME} -PSONATYPE_PASSWORD=${SONATYPE_PASSWORD}

      export $(cat build/version.properties | xargs)

      GIT_TAG=${VERSION_DISPLAY}

      echo "Tag Version is : ${GIT_TAG}"

      ./gradlew uploadArchives  -PSONATYPE_USERNAME=${SONATYPE_USERNAME} -PSONATYPE_PASSWORD=${SONATYPE_PASSWORD}

      git tag ${GIT_TAG}

      git push --tag

  fi
  

}


main $*

