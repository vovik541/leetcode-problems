# Scripts Module

This folder will contain utility scripts used to **automate and maintain** the LeetCode repository.

At the moment, scripts may be minimal or experimental, but this module is intended to grow over time.

---

## 🎯 Purpose

The scripts in this directory are planned to handle tasks such as:
- creating new problem folder structures,
- moving finalized solutions from `java-workspace/` to `solutions/`,
- generating indexes (by difficulty, topic, daily problems),
- archiving solved problems automatically,
- enforcing naming and structure conventions.

---

## hilosophy

Manual organization does not scale.

This folder exists to gradually:
- reduce repetitive manual work,
- enforce consistency,
- keep the repository clean as the number of solved problems grows.

---

## Future Direction

Planned scripts may include:
- `new_problem` — generate a problem skeleton from a LeetCode URL
- `archive_java_solution` — move completed Java solutions to `solutions/`
- `generate_indexes` — rebuild README indexes automatically
- validation scripts for structure and naming
