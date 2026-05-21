# Project notes

## JAVA_HOME

Project uses Java 25 (see `build.gradle.kts` toolchain). Set before running `gradlew`:

- Path: `C:\Users\jangk\.jdks\openjdk-25.0.2`
- PowerShell (current session): `$env:JAVA_HOME = "C:\Users\jangk\.jdks\openjdk-25.0.2"`
- Run tests: `$env:JAVA_HOME = "C:\Users\jangk\.jdks\openjdk-25.0.2"; .\gradlew.bat test`