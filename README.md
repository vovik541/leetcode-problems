# LeetCode Solutions Repository

This repository contains my personal solutions to problems from **LeetCode**.
It is structured to support:
- multiple programming languages,
- programming and database (SQL) problems,
- local debugging and experimentation,
- future automation and scaling.

The main goal of this repository is **learning, practice, and clean organization** of LeetCode problems, not mirroring LeetCode’s UI structure.

---

## 📂 Repository Structure Overview
```bash
leetcode/
├─ solutions/
│ ├─ programming/
│ └─ databases/
│
├─ java-workspace/
│
├─ scripts/
│
├─ README.md
└─ .gitignore
```
## **solutions/**

This folder contains **final, archived solutions**.

## **solutions/programming/**
- Algorithmic and data structure problems.
- Each problem has its **own folder**, identified by the official LeetCode ID.
- Inside a problem folder, solutions are organized by **programming language**.

Example:
```bash
├─ README.md
├─ java/
├─ ts/
└─ python/
```

## **solutions/databases/**
- SQL / database-related LeetCode problems.
- Each problem contains SQL solutions and optional dialect-specific variants.

Each problem folder may include:
- problem link,
- difficulty,
- tags,
- explanation,
- time/space complexity.

---

## `java-workspace/`

This is a **working Java module**, used for:
- solving problems locally,
- debugging with breakpoints,
- running tests,
- experimenting with approaches.

Key idea:
> **Problems are solved here first**, and once finalized, the solution is moved into `solutions/`.

- Single Maven/Gradle project
- One `src/` directory
- Temporary or in-progress code lives here

This avoids having hundreds of small Java projects and keeps local development fast and clean.

---

## `scripts/`

Contains helper scripts for automation and maintenance of the repository.
See `scripts/README.md` for details.

---

## Design Principles

- **One problem = one folder**
- **LeetCode ID is the primary identifier**
- Difficulty, daily/contest status are metadata, not folder structure
- One workspace per language, not per problem
- Final solutions live in `solutions/`, experiments do not

---

## Future Plans

- Automatic creation of problem folders
- Automatic archiving from workspace to `solutions/`
- Index generation (daily, difficulty, topics)
- CI checks and formatting

---

## ⚠ Disclaimer

These solutions are for **educational purposes only**.
They reflect my own learning process and may evolve over time.