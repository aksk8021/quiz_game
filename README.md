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

## 📸 Screenshots

| Splash Screen                           | Quiz UI                             | Result Screen                           |
|-----------------------------------------|-------------------------------------|-----------------------------------------|
| ![splash](screenshots/splashScreen.png) | ![quiz](screenshots/quizScreen.png) | ![result](screenshots/resultScreen.png) |

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
git clone https://github.com/yourusername/quiz-app.git

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


MIT License

Copyright (c) 2025

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.




