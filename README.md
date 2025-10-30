# Notes Manager

A simple and elegant Android Notes Manager application built with Kotlin that allows users to create, view, edit, and manage their notes efficiently.

## Features
## Core Features
  1. Create Notes - Add new notes with title and description with date

  2. View Notes - Browse all notes in an organized list

  3. Edit Notes - Modify existing notes with ease

  4. Delete Notes - Remove individual notes with confirmation

  5. Search Functionality - Find notes quickly using search in toolbar

## Technical Features
  1. Splash Screen
  2. Room Database - Local data persistence
  3. MVVM Architecture
  4. Navigation Component
  5. View Binding - Type-safe view references
  6. Coroutines - Background task management
  7. RecyclerView

## Tech Stack
  1. Kotlin, XML with ConstraintLayout + Material Design
  2. MVVM (Model-View-ViewModel) Architecture
  3. Room DB Persistence Library
  4. Kotlin Coroutines Async Operations	
  5. Android Navigation Component
  6. Gradle Build Tool, Android Studio IDE

## Screenshots

<div align="center">
Application Screenshots

<img src="https://github.com/Chandanaachandu/KotlinProject/blob/136530efe8ecf08b89ecce40ede0d0c7bdcd68b9/Splash_Screen.jpg" width="200" height="400" alt="Splash Screen" />	<img src="https://github.com/Chandanaachandu/KotlinProject/blob/94578c8558b7dbdefa538c5187b09c2cc6b9ecff/Home_Screen.jpg" width="200" height="400" alt="Home Screen" />	<img src="https://github.com/Chandanaachandu/KotlinProject/blob/94578c8558b7dbdefa538c5187b09c2cc6b9ecff/AddNote_Screen.jpg" width="200" height="400" alt="Add Note Screen" />
<img src="https://github.com/Chandanaachandu/KotlinProject/blob/94578c8558b7dbdefa538c5187b09c2cc6b9ecff/NotesList_Screen.jpg" width="200" height="400" alt="Notes List Screen" />	<img src="https://github.com/Chandanaachandu/KotlinProject/blob/94578c8558b7dbdefa538c5187b09c2cc6b9ecff/UpdateNote_Screen.jpg" width="200" height="400" alt="Update Note Screen" />	<img src="https://github.com/Chandanaachandu/KotlinProject/blob/94578c8558b7dbdefa538c5187b09c2cc6b9ecff/DeleteNote_Screen.jpg" width="200" height="400" alt="Delete Note Screen" />
<img src="https://github.com/Chandanaachandu/KotlinProject/blob/94578c8558b7dbdefa538c5187b09c2cc6b9ecff/SearchNote_Functionality.jpg" width="300" height="600" alt="Search Functionality" /></div>


## How to Run the Project
### Prerequisites
- Android Studio, Android SDK 26 (API 26) or higher, Java 11

### Installation Steps
## Option 1: Download ZIP File
  1. Download the project
     - Go to the GitHub repository page
     - Click the green "Code" button
     - Select "Download ZIP"
     - Extract the ZIP file to your desired location

  2. Open in Android Studio
     - Launch Android Studio
     - Select "Open" or "Open an existing project"
     - Navigate to the extracted project folder
     - Click "OK"

  3. Build the project
     - Wait for Gradle sync to complete 
     - Build the project by going to Build > Make Project
     
  4. Run on device/emulator
     - Connect an Android device with USB debugging enabled OR
     - Create and start an Android Virtual Device (AVD) from Android Studio
     - Click the "Run" button or press Shift + F10
  
## Option 2: Clone Repository 
  1. Clone the repository
  
  2. Open in Android Studio
    - Launch Android Studio
    - Select "Open an existing project"
    - Navigate to the cloned repository folder
    - Click "OK"

  3. Build the project
    - Wait for Gradle sync to complete
    - Build the project (Build > Make Project)

  4. Run on device/emulator
     - Connect an Android device or start an emulator
     - Click "Run" (Shift + F10) or the play button

## Usage Guide
  1. Adding a Note: Tap the floating action button (+), fill in title and description, then save
  2. Viewing Notes: All notes are displayed in the main list with title, description, and date
  3. Editing a Note: Tap any note in the list to edit its contents
  4. Deleting a Note: Use the delete icon in the edit screen (with confirmation)
  5. Searching Notes: Use the search icon in the toolbar to filter notes
