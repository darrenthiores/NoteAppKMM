# NoteAppKMM

This app is a note app, a CRUD app, where user can create note, read note, update note, and delete note, also search a note. This app use KMM (Kotlin Multiplatform Mobile) for the data layer and supports Android and IOS.

# Running the app

On IOS, to enable local database using SqlDelight:

1. Go to iosApp Target and Build Settings
2. Search for Other Linker Flags
3. add "-lsqlite3"

Only on IOS if you use an arm64 architecture macbook, then you need to exclude arm64 in Pods to makes it work, the step:

1. Go to Pods Project and Build Settings
2. Find Excluded Architectures and add "arm64"

# Demo

Android

![Portfolio - Darren (4)](https://github.com/darrenthiores/NoteAppKMM/assets/69592810/95e288ca-f185-44cd-99ab-97bbb621114e)

Ios

![Portfolio - Darren (5)](https://github.com/darrenthiores/NoteAppKMM/assets/69592810/772fbc72-df0f-4655-8e9b-2211886fcd7f)

# Technologies

- KMM (Kotlin Multiplatform Mobile)
- Kotlin
- Swift
- Jetpack Compose
- SwiftUI
- SqlDelight (Local Database)
