> **Language:** [Русский](README.md) · English

# Bountiful (Minecraft 1.21.4 Fabric Port)

![Java 21](https://img.shields.io/badge/Java-21-blue.svg)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.4-blue.svg)
![Fabric](https://img.shields.io/badge/Loader-Fabric-blue.svg)
![ModMenu](https://img.shields.io/badge/ModMenu-Supported-blue.svg)
![License](https://img.shields.io/badge/License-LGPL_3.0-blue.svg)

Port and update of the **Bountiful** mod for **Minecraft 1.21.4 (Fabric)** by **byMr712**.

Original Developer: [Ejektaflex/Bountiful](https://github.com/ejektaflex/Bountiful).

---

## About

**Bountiful** adds Bounty Boards to Minecraft, providing players with exciting tasks, quests, and rewards. By completing bounties for villagers, you can obtain valuable resources, emeralds, rare items, and experience.

---

## Gallery

| Bounty Board | Board Interface | Bounty Item |
|:---:|:---:|:---:|
| ![Bounty Board](/images/bounty_board.png) | ![Board Interface](/images/bounty_board_gui.png) | ![Bounty Item](/images/bounty_item.png) |

---

## Features

- **Bounty Boards**: naturally generate in villages or can be crafted by players on a crafting table.
- **Diverse Bounties**: missions involving resource gathering, monster hunting, exploration, and crafting.
- **Decree System**: special items placed on boards to curate and specialize the types of objectives that appear.
- **Bounty Rarities**: bounties range across rarity tiers (Common, Uncommon, Rare, Epic) with scaling rewards.
- **Datapack Support**: full JSON customization for reward pools, objectives, and conditions.

---

## Changes in 1.21.4 Port (byMr712)

- **Modded Village Generation:** added dynamic Jigsaw template pool injection, guaranteeing that bounty gazebos and boards spawn in modded and datapack villages (Towns and Towers, ChoiceTheorem's Overhauled Village / CTOV, Repurposed Structures, Structory, etc.) with automatic biome-based style selection.
- **Safe Structure Handling:** added resilient pool lookup to prevent crashes when optional third-party village pools are not loaded.
- **Ported to Minecraft 1.21.4:** full adaptation for Fabric Loader, Yarn mappings, and Java 21 LTS.
- **Graphics Pipeline:** updated GUI rendering, textures, and inventory screens to match Minecraft 1.21.4 standards.
- **Network Protocol:** migrated data and bounty synchronization packets to the modern CustomPayload API.
- **Build & Optimization:** configured direct build output of `Bountiful-1.21.4-byMr712.jar` into the root `build/libs/` directory.

---

## Installation

1. Download the latest release from [GitHub Releases](https://github.com/byMr712/Bountiful-1.21.4-MinecraftMod/releases).
2. Requires:
   - [Fabric Loader](https://fabricmc.net/) (Minecraft 1.21.4)
   - [Fabric API](https://modrinth.com/mod/fabric-api)
   - [Cloth Config](https://modrinth.com/mod/cloth-config)
   - [Mod Menu](https://modrinth.com/mod/modmenu) (recommended)
3. Place the `.jar` file into your `mods` folder.
4. Launch the game.

---

## Building

1. Requires Java 21 and Fabric Loader for Minecraft 1.21.4.
2. To build the project, run:
   ```bash
   ./gradlew :fabric:build
   ```
3. The built jar file will be located at `build/libs/Bountiful-1.21.4-byMr712.jar`.

---

## Credits & License

- Original Author: [Ejektaflex](https://github.com/ejektaflex) ([Bountiful](https://github.com/ejektaflex/Bountiful)).
- Ported and adapted for 1.21.4 by: [Mr712](https://github.com/byMr712).
- Distributed under the [LGPL 3.0 License](LICENSE).
