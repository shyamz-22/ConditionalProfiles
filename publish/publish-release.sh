#!/usr/bin/env bash
set -x

set -e


main() {

  echo "Current Branch Version with travis : ${TRAVIS_BRANCH}"

  if [ ${TRAVIS_BRANCH} != "master" ]; then
  
          ./gradlew clean build -PVERSIONING_RELEASE_BUILD=true -PVERSIONING_RELEASE_MODE=tag -PVERSIONING_DISPLAY_MODE=base  -PSONATYPE_USERNAME=${SONATYPE_USERNAME} -PSONATYPE_PASSWORD=${SONATYPE_PASSWORD}

          ./gradlew versionFile -PVERSIONING_RELEASE_BUILD=true -PVERSIONING_RELEASE_MODE=tag -PVERSIONING_DISPLAY_MODE=base  -PSONATYPE_USERNAME=${SONATYPE_USERNAME} -PSONATYPE_PASSWORD=${SONATYPE_PASSWORD}

          export $(cat build/version.properties | xargs)

          GIT_TAG="${VERSION_DISPLAY}"

          ./gradlew uploadArchives -PVERSIONING_RELEASE_BUILD=true -PVERSIONING_RELEASE_MODE=tag -PVERSIONING_DISPLAY_MODE=base  -PSONATYPE_USERNAME=${SONATYPE_USERNAME} -PSONATYPE_PASSWORD=${SONATYPE_PASSWORD}

          git tag ${GIT_TAG}

          git push --tag

  fi

}


main $*

