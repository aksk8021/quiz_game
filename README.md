# 🧠 Quiz App (Android)

A sleek and modern Android Quiz App built using Kotlin and MVVM architecture.  
This app presents multiple-choice questions with instant feedback, visual progress tracking,  
and a final result summary screen — all wrapped in a smooth dark-themed UI.

## 🚀 Features
- 🎯 Multiple-choice quiz with real-time evaluation  
- 📊 Live score and streak tracking  
- 🌿 Progress indicator with leaf-based UI metaphor  
- 💡 Skip questions anytime  
- 🌘 Always-on Dark Theme for better accessibility  
- 📈 End-of-quiz result summary with restart option  
- ⚙️ Splash screen with animated transition  

---

| Splash Screen                           | Quiz UI                             | Result Screen                           |
|-----------------------------------------|-------------------------------------|-----------------------------------------|
| ![splash](screenshot/splashScreen.png) | ![quiz](screenshot/quizScreen.png) | ![result](screenshot/resultScreen.png) |

---

## 🛠 Tech Stack

- **Kotlin**
- **MVVM Architecture**
- **ViewModel + LiveData**
- **Retrofit** (for network requests)
- **Navigation Component**
- **Material Components**
- **Vector Drawables**
- **Kotlin Coroutines**

## 📦 API
The app uses a hosted JSON for questions:

json :
{
  "id": 1,
  "question": "What hidden feature ...?",
  "options": ["Flappy Bird–style game", "Virtual pet", "Hidden performance menu", "System UI tuner"],
  "correctOptionIndex": 0
}

## Architecture Overview
MainActivity
└── SplashFragment
└── QuizFragment
└── ResultFragment

- QuizViewModel holds quiz logic & state
- QuizRepository handles API access
- UI observes ViewModel via LiveData

##  ⚙️ Setup Instructions
git clone https://github.com/aksk8021/quiz_game.git 

## 🔁 Restart Logic
On quiz completion, users see their:
- Correct answers
- Longest answer streak
- “Restart Quiz” button resets all ViewModel state

## 📂 Project Structure
  📁 ui/fragments/
  ├── SplashFragment.kt
  ├── QuizFragment.kt
  └── ResultFragment.kt

📁 ui/viewmodel/
└── QuizViewModel.kt

📁 network/
├── RetrofitInstance.kt
└── QuizApiService.kt

📁 data/
└── Question.kt




