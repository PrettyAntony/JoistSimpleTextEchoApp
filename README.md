# 🗣️ Simple Text Echo App

A simple Android app built for the **Joist Simple Text Echo App**.  
The app allows users to enter text, submit it for simulated validation, and then display the echoed text if validation succeeds — or show an error otherwise.

---

## 📱 Features

- Enter text input and submit  
- Simulated server validation using coroutines  
- Displays echoed text on success  
- Displays error message on failure or empty input  
- Proper state management via `ViewModel` and `StateFlow`  
- Unit tests for `UseCase` and `ViewModel`

---

## 🧱 Architecture

The project follows a **Clean Architecture + MVVM** approach for clarity and scalability

## 🧩 Tech Stack

| Category | Library / Tool |
|-----------|----------------|
| Language | **Kotlin** |
| UI | **Jetpack Compose** |
| Architecture | **Clean Architecture**, **MVVM** |
| Coroutines | **Kotlinx Coroutines**, **StateFlow** |
| Dependency Injection | Manual (lightweight for simplicity) |
| Testing | **JUnit4**, **kotlinx-coroutines-test** |

---

## 🚀 How It Works

1. The user enters text and clicks **Submit**.
2. The ViewModel calls the `ValidateAndEchoTextUseCase`.
3. The use case checks for empty input, then calls the `EchoRepositoryImpl` which simulates a success/failure response.
4. The ViewModel updates the UI state:
   - `echoedText` on success
   - `errorMessage` on failure
5. The UI reacts automatically via Compose’s state observation.

---

## 🧪 Unit Testing

### ✅ Tested Components:
- `ValidateTextUseCaseTest`
  - Success and error scenarios
  - Blank input handling
- `EchoViewModelTest`
  - UI state updates on success/failure
  - Error clears previous echoed text

### 🧰 Libraries:
- `kotlinx-coroutines-test`
- `JUnit4`
