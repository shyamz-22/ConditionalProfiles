# Conditional Profiles

[![Build Status](https://travis-ci.org/shyamz-22/conditional-profiles.svg?branch=master)](https://travis-ci.org/shyamz-22/conditional-profiles)
[![codecov](https://codecov.io/gh/shyamz-22/conditional-profiles/branch/master/graph/badge.svg)](https://codecov.io/gh/shyamz-22/conditional-profiles)
[![MavenCentral](https://maven-badges.herokuapp.com/maven-central/io.github.shyamz-22/conditional/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.shyamz-22/conditional)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

### Default Spring profile behavior

Creates the bean when Profile A **OR** B is active.

```java
@Bean
@Profile("A", "B")
public ExampleBean exampleBean() {
  return new ExampleBean();
}
```

### Conditional On Profiles

Creates the bean only when Profile A **AND** B are active.

```java
@Bean
@ConditionalOnProfiles("A","B")
public ExampleBean exampleBean() {
  return new ExampleBean();
}
```

## Installation

### Gradle

```gradle
compile 'io.github.shyamz-22:conditional:0.0.2'
```

### Maven

```xml
<dependency>
    <groupId>io.github.shyamz-22</groupId>
    <artifactId>conditional</artifactId>
    <version>0.0.2</version>
</dependency>
```
