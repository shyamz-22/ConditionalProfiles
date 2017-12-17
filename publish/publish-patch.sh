#!/usr/bin/env bash
set -x

set -e


main() {
  git checkout master

  local CURRENT_BRANCH=$(git branch -a | grep 'release/' | tail -n 1)

  echo "${CURRENT_BRANCH}"
  
  git checkout ${CURRENT_BRANCH}

  ./gradlew clean build

  ./gradlew versionFile

  export $(cat build/version.properties | xargs)

  GIT_TAG="${VERSION_DISPLAY}"

  echo "./gradlew uploadArchives"

  echo "git tag ${GIT_TAG}"

  echo "git push --tag"

}


main $*

