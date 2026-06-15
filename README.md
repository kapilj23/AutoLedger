# 🚗 AutoLedger

A smart vehicle expense tracker built with **Jetpack Compose**, **Room DB** and **Clean Architecture**.

![AutoLedger](https://github.com/user-attachments/assets/eec21733-8617-4e15-9751-a70af70f72c6)

---

## 📱 Screenshots

<p float="left">
  <img src="https://github.com/user-attachments/assets/eec21733-8617-4e15-9751-a70af70f72c6" width="200"/>
  <img src="https://github.com/user-attachments/assets/6f3e0f57-252e-4dab-9ffd-cefcc7464a4c" width="200"/>
  <img src="https://github.com/user-attachments/assets/aab2dbf6-5f35-4901-9b60-09f89d5e059b" width="200"/>
  <img src="https://github.com/user-attachments/assets/4bb8a1cf-a20b-486d-ac1f-07ee40dbd307" width="200"/>
</p>

---

## ✨ Features

- 🚗 Add and manage multiple cars
- ⛽ Track fuel logs — liters, price, mileage
- 💰 Auto calculate total fuel expense
- 🚀 Average mileage tracking per car
- 📊 Statistics — mileage trend and expense charts
- 🗑️ Delete cars and fuel logs
- 💾 Fully offline — data stored locally with Room DB

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | Clean Architecture, MVVM |
| Database | Room DB |
| DI | Hilt |
| Async | Coroutines, StateFlow, Flow |
| Navigation | Jetpack Navigation Compose |
| Charts | Vico Charts |

---

## 🏗️ Architecture

This app follows **Clean Architecture** with 3 layers:

presentation/

├── ui/

│   ├── CarListScreen.kt

│   ├── CarDetailScreen.kt

│   ├── AddFuelLogScreen.kt

│   └── StatsScreen.kt

└── viewmodel/

├── CarViewModel.kt

└── FuelLogViewModel.kt
domain/

├── model/

│   ├── Car.kt

│   └── FuelLog.kt

├── repository/

│   ├── CarRepository.kt

│   └── FuelLogRepository.kt

└── usecase/

├── GetAllCarsUseCase.kt

├── AddCarUseCase.kt

├── DeleteCarUseCase.kt

├── GetFuelLogsUseCase.kt

├── AddFuelLogUseCase.kt

├── DeleteFuelLogUseCase.kt

└── GetTotalExpenseUseCase.kt
data/

├── local/

│   ├── entity/

│   │   ├── CarEntity.kt

│   │   └── FuelLogEntity.kt

│   ├── CarDao.kt

│   ├── FuelLogDao.kt

│   └── AutoLedgerDatabase.kt

└── repository/

├── CarRepositoryImpl.kt

└── FuelLogRepositoryImpl.kt

---

## 🗄️ Database Schema

cars

├── id (PrimaryKey, autoGenerate)

├── name

├── model

├── year

├── fuelType

└── createdAt
fuel_logs

├── id (PrimaryKey, autoGenerate)

├── carId (ForeignKey → cars.id)

├── date

├── liters

├── pricePerLiter

├── totalCost

├── mileage

└── notes

---

## 🚀 Setup

1. Clone the repo
```bash
git clone https://github.com/kapilj23/AutoLedger.git
```

2. Open in Android Studio

3. Build and run!

> No API key needed — fully offline app

---

## 👨‍💻 Author

**Kapil Joshi** — Android Developer

[![GitHub](https://img.shields.io/badge/GitHub-kapilj23-black?logo=github)](https://github.com/kapilj23)
