## Tech Stack & Architecture

This Android application is built using modern Android development practices and libraries:

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material3 Design System
- **Architecture**: Clean Architecture with multi-module structure
  - Feature modules: list, details
  - Core modules: data, domain, network, database, designsystem
- **Dependency Injection**: Hilt/Dagger
- **Navigation**: Jetpack Navigation Compose
- **Image Loading**: Coil
- **Database**: Room
- **Networking**: Retrofit with Kotlin Serialization
- **Testing**: JUnit, Compose UI Testing, Robolectric
- **Build System**: Gradle with Version Catalogs and Convention Plugins
- **Minimum SDK**: API 23

## Development Time

This project was completed in approximately 2.5 - 3 hours, focusing on implementing the core functionality while maintaining clean architecture principles and following modern Android development best practices.

The time was primarily spent on:

- Setting up the multi-module architecture
- Implementing the UI using Jetpack Compose
- Handling data synchronization with retry logic
- Setting up the local storage system
- Implementing the navigation flow between screens

## Design mockup:

![design](https://i.ibb.co/5WzcrWR/Screenshot-2020-03-28-at-15-25-52.png")

## Code Style & Formatting

This project uses Spotless for automated code formatting and style enforcement. Spotless is configured with ktlint to ensure consistent Kotlin code style across the project.

To apply Spotless formatting to all files, run:

```shell
./gradlew --init-script gradle/init.gradle.kts spotlessApply
```

This will:

- Format all Kotlin files according to ktlint rules
- Format Kotlin script files (.kts)
- Format XML files
- Ensure consistent code style across the project


Here is the endpoint for fetching data
https://mpa68396c1d2046f93e0.free.beeceptor.com/
