# 🏦 Java Bank Management System

A beginner-friendly **OOP project** in Java demonstrating core Object-Oriented Programming concepts through a real-world Bank Management System.

---

## 📁 Project Structure

```
bank-system/
└── src/com/bank/
    ├── Main.java                    # Entry point & demo
    ├── model/
    │   ├── Account.java             # Abstract base class (Encapsulation + Abstraction)
    │   ├── SavingsAccount.java      # Subclass with interest (Inheritance)
    │   ├── CurrentAccount.java      # Subclass with overdraft (Polymorphism)
    │   └── Transaction.java         # Immutable transaction record
    └── service/
        └── BankService.java         # Business logic (Single Responsibility)
```

---

## 🧠 OOP Concepts Used

| Concept | Where |
|---|---|
| **Encapsulation** | `Account` — private fields, public getters/setters |
| **Abstraction** | `Account` is abstract; `getAccountType()` must be implemented |
| **Inheritance** | `SavingsAccount` and `CurrentAccount` extend `Account` |
| **Polymorphism** | `CurrentAccount` overrides `withdraw()` for overdraft logic |

---

## ✨ Features

- Create **Savings** and **Current** accounts
- **Deposit**, **Withdraw**, and **Transfer** funds
- **Overdraft** support for current accounts
- **Interest** application for savings accounts
- Full **transaction history** per account
- Clean **error handling** with meaningful messages

---

## 🚀 How to Run

### Option 1 — Command Line (no Maven needed)

```bash
# 1. Compile all Java files
javac -d out src/com/bank/model/*.java src/com/bank/service/*.java src/com/bank/Main.java

# 2. Run
java -cp out com.bank.Main
```

### Option 2 — IntelliJ IDEA

1. Open IntelliJ → **File → Open** → select `bank-system/`
2. Mark `src` as **Sources Root** (right-click → Mark Directory As)
3. Right-click `Main.java` → **Run 'Main'**

---

## 📊 Sample Output

```
╔══════════════════════════════════════╗
║   Java Bank Management System 🏦     ║
╚══════════════════════════════════════╝

✅ Savings account created: SAV1001
✅ Current account created: CUR1002
✅ Deposited ₹10000.00 | New Balance: ₹60000.00
✅ Withdrawn ₹23000.00 | New Balance: ₹2000.00
⚠️  Account is in overdraft by ₹1000.00
🔄 Transferred ₹7000.00 from CUR1002 to SAV1001
💰 Interest applied at 4% → ₹2280.00 added
```

---

## 📖 License

MIT — free to use and modify.
