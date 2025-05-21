# NIT3213 Android Application

## Project Overview

This Android application demonstrates API integration, user interface design, and Android development best practices. The app features three main screens:

1. **Login Screen**: Authenticates users against the vu-nit3213-api
2. **Dashboard Screen**: Displays a list of entities retrieved from the API
3. **Details Screen**: Shows detailed information about a selected entity

## Setup Instructions

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17 or newer
- Android SDK 34 (minimum SDK 24)
- Gradle 8.0+

### API Configuration

The application connects to the following API:
- Base URL: `https://nit3213-api-h2b3-latest.onrender.com`
- Authentication endpoints:
  - Footscray: `/footscray/auth`
  - Sydney: `/sydney/auth`
  - ORT: `/ort/auth`

## Building and Running the Application



### Open in Android Studio

1. Launch Android Studio
2. Select "New Project" from version control
3. Copy this link and paste it into "https://github.com/Vansh25080/FinalAssessment" and click "Clone"
4. Wait for the Gradle sync to complete


### Run the Application

1. Connect an Android device or start an emulator
2. Click the "Run" button (green triangle) in Android Studio
3. Select your device/emulator and click "OK"

### Login Credentials

- Username: Vansh
- Password:s8094526


## Features

- User authentication with error handling
- Display of entity list in a RecyclerView
- Detailed view of selected entities
- Clean architecture with separation of concerns
- Dependency injection for better testability
- Error handling and loading states


## Git Workflow

The project follows a structured Git workflow with:
- Feature branches for new functionality
- Descriptive commit messages
- Regular commits to track progress

## License

This project is created for educational purposes as part of the NIT3213 course.
