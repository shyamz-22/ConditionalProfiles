# Conditional Profiles

### Default Spring profile behavior
```@Profile("A", "B")``` on a bean , Creates that bean when Profile A or B is active.

### Conditional Profiles 
```@ConditionalOnProfiles("A","B")``` on a bean , Creates that bean only when Profile A and B is active.
