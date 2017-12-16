# Conditional Profiles

[![Build Status](https://travis-ci.org/shyamz-22/conditional-profiles.svg?branch=master)](https://travis-ci.org/shyamz-22/conditional-profiles)

### Default Spring profile behavior
```@Profile("A", "B")``` on a bean , Creates that bean when Profile A or B is active.

### Conditional Profiles
```@ConditionalOnProfiles("A","B")``` on a bean , Creates that bean only when Profile A and B is active.
