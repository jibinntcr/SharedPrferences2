This **README** is designed for your BITS Pilani students to follow along with the GitHub repository. It explains the project in layman's terms while highlighting the core Android persistence concepts.


# 📱 Student Enrollment App - SharedPreferences Demo

Welcome to the **CS09: Software Development for Portable Devices** lab session! This project demonstrates how to make an Android app "remember" information using **SharedPreferences**. 

## 🎓 Overview

In modern Android, screens are often recreated during rotation or app switching. To prevent data loss, we must move beyond temporary UI state and use **persistent storage**. 

This demo app, styled in **BITS Pilani colors**, allows you to:

1. **Input** three different types of student data (Name, ID, and Residency status).
2. **Save** that data to the device's local storage. 
3. **Retrieve** and display the data, even after closing and reopening the app. 


## 🛠️ The "Notebook" Analogy

Think of this app as a student with a **private notebook** (the SharedPreferences file). 

**The Locker (Storage):** A private XML file that only this app can access. 


* **The Labels (Keys):** Unique names like `key_name` or `key_id` used to find data later. 


* **The Pen (Editor):** A special tool required to write or change any notes in the notebook. 


## 📝 Core Concepts

### 1. Saving Multiple Values

We store three distinct data types to show the versatility of the storage system: 

* **String:** The student's name.
* **Int:** The student's ID (converted from text).
* **Boolean:** A true/false toggle for hostel residency.

### 2. The Persistence Workflow

1. **Get Reference:** Access the file via `getSharedPreferences`. 

2. **Edit:** Open the `Editor`. 

3. **Put:** Place data into the "lockers" using specific keys. 

4. **Apply:** Seal the notebook to save changes in the background. 



## ⚠️ Known Drawbacks

While simple, **SharedPreferences** has limitations your we should know:

* **Manual Refresh:** The UI does not update automatically when data changes; it must be re-read manually. 

* **Key Errors:** Typos in keys (labels) are a common source of bugs. 

* **Not for Large Data:** It is not intended for images, PDFs, or long lists. 

* **Main Thread:** Reading/writing large files can occasionally slow down the UI. 


> **Note:** For modern, production-grade apps, Google recommends moving to **Jetpack DataStore**. 
> 
> 
## 🚀 How to Run the Demo

1. **Clone** the repository.
2. **Open** in Android Studio.
3. **Run** on an emulator or physical device.
4. **Test Persistence:** Enter data → Click **Save** → **Kill the app** (swipe it away) → Reopen → Click **Fetch**!


**Course:** CS09 - Software Development for Portable Devices

**Institution:** BITS Pilani (WILP)

**Instructor:** Jibin N 

Would you like me to add a section on how to inspect the physical `.xml` file on a device using the **Device File Explorer**?

<img width="1257" height="830" alt="Screenshot 2026-03-06 at 10 17 57 AM" src="https://github.com/user-attachments/assets/ee4fad0b-88f3-466e-af2a-28a8b1457d7c" />

