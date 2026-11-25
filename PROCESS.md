# How I built it

The brief came in parts, and every part changed code from the one before. My OneDrive ended up with a folder per stage ("P1", "P2", "P3", "P4.2" and a couple of early copies). I've turned those into commits here so you can see the changes with `git log -p`.

## Part 1: one Tool class reading from a file (19 Feb to 9 Mar)

I started with a single `Tool` class and a `Shop` holding an `ArrayList<Tool>`. `readToolData()` opens a `FileDialog`, reads each line, and splits it on commas with a second `Scanner`.

Reading from a file means making an empty object and filling it in, so `Tool` has a no-arg constructor and a `readData(Scanner)` method. The parsing lives in the class that owns the fields, not in `Shop`. Between the 19 February copy and the 9 March one, the no-arg constructor went from empty to setting every field to a safe default.

The data file has spaces around the commas, so the line scanner uses `\s*,\s*` as its delimiter and each value is also `.trim()`med. Without that, `Boolean.parseBoolean(" true")` returns `false`.

Then the brief asked for hand tools and electric tools, so I split `Tool` into `HandTool` and `ElectricTool`. The data file has flag lines in square brackets that say which type the following lines are. `Shop` remembers the last flag and builds an `ElectricTool` or `HandTool` depending on whether it contains "electric" or "hand". With no flag, it falls back to a basic tool so the older `tool_data_1.txt` still loads.

## Part 2: ShopItem and more item types (9 Mar)

The shop also hires accessories, workwear and perishables, which aren't tools. I pulled the shared fields (name, code, cost) up into an abstract `ShopItem` and made `Tool` extend it.

The fiddly bit is the order fields are read in. Each `readData` calls `super.readData(sc)` first, so the file's fields have to follow the inheritance chain from top to bottom. Every `readData` has a comment listing the exact field order, because getting it wrong shifts every value along by one.

## Part 3: customers and unique IDs (9 Mar)

I added `Customer` with read and write methods, and saved customers back to a file with `PrintWriter`.

Customers arrive with ID `unknown`, so the shop generates one like `CU-482915`. A random number on its own can repeat, so a `HashSet<String>` of used IDs makes the generator keep trying until it gets a fresh one. That's the same bug my Programming 1 Wine Direct project still has.

I also changed fields from `ArrayList<...>` to the `List<...>` interface and added `HashMap`s keyed by code and ID, so looking up an item or customer doesn't mean looping through the whole list.

## Part 4: reservations and the diary (24 Mar)

This was the biggest part. `makeItemReservation` checks that:

1. the customer exists
2. the item exists
3. the start date is a valid date
4. the number of days is at least 1
5. the item isn't already booked on any of those days (checked against the diary, day by day)

It only creates the reservation if all five pass. `Test` deliberately tries a bad customer, a bad item and a clash, so I can see each check fail properly.

`Diary` and `DateUtil` were given to us by the tutor. `Diary` stores a reservation against every day it covers, which is why my clash check can call `diary.getReservations(date)` once for each day of the new booking.

## What I'd change

- `cost` is a whole number of pounds. It should be pence, or `BigDecimal`.
- Errors are printed with `System.out` and the method returns `false`. A real app should throw exceptions or return a result the UI can show.
- `Test` is just a `main` method I read through by eye. JUnit would actually tell me when something breaks.
- **Bug:** reservation numbers come from a counter that starts at 0 every run, and `readItemReservationData()` doesn't move it on. If you load saved reservations and then make a new one, it gets `000001` again and replaces the loaded reservation with that number in the map. The fix is to set the counter to the highest number loaded.
