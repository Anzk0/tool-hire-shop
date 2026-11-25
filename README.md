# Tool hire shop

Java coursework for Programming 2 in Year 1 at Salford (February to March 2026). It's a tool and equipment hire shop that grew over four parts of the brief: reading items from a file, an inheritance hierarchy, customers with unique IDs, and finally reservations backed by a diary.

The commit history follows those parts. Each commit is the code as it stood at the end of that stage.

## Class structure

```
ShopItem (abstract)        name, code, cost
├── Tool (abstract)        times hired, on loan, weight
│   ├── HandTool           sharpenable
│   └── ElectricTool
├── Accessory
├── Perishable
└── Workwear

Customer                   ID, name, title
ShopItemReservation        reservation no, item, customer, start date, days
Shop                       holds everything, reads and writes the data files
Diary, DateUtil            provided by the module tutor (D E Newton)
Test                       runs through every feature, including edge cases
```

Every class has `readData(Scanner)` and `printDetails()`. Subclasses call `super` first and then handle their own fields, so the file-reading code in `Shop` doesn't need to know which type it's building beyond the first token.

## Running it

```bash
javac *.java
java Test
```

`Test` opens file dialogs for the item and customer data files. Those came with the brief and aren't in this repo.

See [PROCESS.md](PROCESS.md) for how each part went.
