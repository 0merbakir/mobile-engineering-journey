# 14-Day Native Kotlin (Android) Roadmap

This repository documents my journey through a 14-day intensive roadmap to become a proficient Android developer with Kotlin.

---

## File Walkthrough

*   `app/src/main/java/com/example/roadmaps/dayX_excs.kt`: Contains the primary code and interview-focused explanations for each day'''s topics.
*   `app/src/test/java/com/example/roadmaps/DayXExercisesTest.kt`: A test runner file. Use the play button (▶) here to execute the code in the corresponding `dayX_excs.kt` file.
*   `app/src/test/java/com/example/roadmaps/DayXPracticeTest.kt`: Your personal scratchpad. A blank test file created each day for you to practice the concepts yourself.

---

## The Roadmap

### WEEK 1 – FUNDAMENTALS

- [x] **DAY 1 – Kotlin Basics**
    *   **Goal:** Master the language basics.
    *   **Topics:** `val` vs `var`, Null safety, Functions, `when`/`if`, Data classes, Collections.
    *   **Output:** Small console exercises in Kotlin.

- [ ] **DAY 2 – OOP & Kotlin Power**
    *   **Goal:** Understand object-oriented principles and powerful Kotlin-specific features.
    *   **Topics:** Classes/Objects, Inheritance, Interfaces, Abstract & Sealed classes, Extension functions.
    *   **Output:** Simple User–Auth model.

- [ ] **DAY 3 – Android Fundamentals**
    *   **Goal:** Grasp the core components of the Android framework.
    *   **Topics:** Activity & its lifecycle, Fragment & its lifecycle, `Context`, `ViewBinding`.
    *   **Output:** Navigate between 2 Activities.

- [ ] **DAY 4 – UI & Layout**
    *   **Goal:** Build basic user interfaces using Android'''s traditional UI toolkit.
    *   **Topics:** XML Layouts, `ConstraintLayout`, `RecyclerView` & Adapters, Material Design basics.
    *   **Output:** A list screen and a detail screen.

- [ ] **DAY 5 – Navigation & Fragments**
    *   **Goal:** Implement modern, single-activity navigation.
    *   **Topics:** `Fragment` lifecycle, Navigation Component, `SafeArgs`, Backstack management.
    *   **Output:** Navigation between multiple fragments in one activity.

- [ ] **DAY 6 – MVVM Architecture**
    *   **Goal:** Separate UI from business logic using a standard architectural pattern.
    *   **Topics:** MVVM pattern, `ViewModel`, `LiveData`, UI-Logic separation.
    *   **Output:** Refactor an existing app with MVVM.

- [ ] **DAY 7 – Coroutines & Async**
    *   **Goal:** Handle background tasks and asynchronous operations without blocking the UI.
    *   **Topics:** Coroutine scopes, `Dispatchers`, `suspend` functions, Error handling.
    *   **Output:** A fake API call using coroutines.

### WEEK 2 – REAL-WORLD SKILLS

- [ ] **DAY 8 – REST API & Retrofit**
    *   **Goal:** Fetch live data from the internet.
    *   **Topics:** REST basics, `Retrofit` setup, `Gson`/`Moshi` parsing, API error handling.
    *   **Output:** Fetch & display data from a public API.

- [ ] **DAY 9 – Room Database**
    *   **Goal:** Persist data for offline access.
    *   **Topics:** `Entity`, `DAO`, `Database` setup, Offline-first mindset.
    *   **Output:** Implement an API → Database → UI data flow.

- [ ] **DAY 10 – Repository Pattern & Clean Code**
    *   **Goal:** Structure the app'''s data layer for scalability and maintainability.
    *   **Topics:** Repository pattern, Single Source of Truth, `UseCase` concept.
    *   **Output:** Implement a clean architecture structure.

- [ ] **DAY 11 – State & UI Handling**
    *   **Goal:** Build a robust and user-friendly UI that responds to data states.
    *   **Topics:** Loading/Error/Success states, `Sealed class` for UIState.
    *   **Output:** Show proper loading spinners and error messages in the UI.

- [ ] **DAY 12 – Security & Permissions**
    *   **Goal:** Handle sensitive data and system permissions correctly.
    *   **Topics:** Runtime permissions, `SharedPreferences`, `EncryptedSharedPreferences`.
    *   **Output:** Securely store an authentication token.

- [ ] **DAY 13 – Testing & Debugging**
    *   **Goal:** Ensure code quality and learn to effectively find bugs.
    *   **Topics:** Unit testing, `ViewModel` testing, `Logcat` mastery.
    *   **Output:** Write 2–3 meaningful unit tests for the app'''s logic.

- [ ] **DAY 14 – FINAL PROJECT**
    *   **Goal:** Combine all learned skills to build a small but complete application.
    *   **Output:** A mini Android App featuring MVVM, Retrofit, Room, Coroutines, and Navigation.
