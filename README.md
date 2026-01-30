# Java Banking System

A console-based banking system built in Java that models real-world banking operations using object-oriented design. The project supports checking and savings accounts, secure transactions, and accurate monetary handling.

## Features
- Create checking and savings accounts
- Deposit and withdraw funds with validation
- Transfer money between accounts
- Maintain transaction history for each account
- Accurate financial calculations using `BigDecimal`
- Modular, extensible object-oriented architecture

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- `BigDecimal` for monetary precision
- Java Collections Framework

## How It Works
- `Account` is an abstract base class defining shared behavior.
- `CheckingAccount` and `SavingsAccount` extend `Account` with specific rules.
- `Bank` manages all accounts and acts as a central registry.
- Each transaction is recorded with type, amount, and timestamp.
- The console menu allows users to interact with the system.

## How to Run
From the project root directory:
```bash
javac bank/*.java
java bank.Main
