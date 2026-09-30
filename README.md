> **Language:** Русский · [English](README.en.md)

# Bountiful (Minecraft 1.21.4 Fabric Port)

![Java 21](https://img.shields.io/badge/Java-21-blue.svg)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.4-blue.svg)
![Fabric](https://img.shields.io/badge/Loader-Fabric-blue.svg)
![ModMenu](https://img.shields.io/badge/ModMenu-Supported-blue.svg)
![License](https://img.shields.io/badge/License-LGPL_3.0-blue.svg)

Порт и обновление мода **Bountiful** для **Minecraft 1.21.4 (Fabric)** от **byMr712**.

Оригинальный разработчик: [Ejektaflex/Bountiful](https://github.com/ejektaflex/Bountiful).

---

## О моде

**Bountiful** добавляет в мир Minecraft доски объявлений (Bounty Boards) с разнообразными контрактами и наградами. Выполняя задания жителей, вы можете получать ценные ресурсы, изумруды, редкие предметы и опыт.

---

## Галерея

| Доска объявлений | Интерфейс контрактов | Предмет контракта |
|:---:|:---:|:---:|
| ![Доска объявлений](/images/bounty_board.png) | ![Интерфейс доски](/images/bounty_board_gui.png) | ![Предмет контракта](/images/bounty_item.png) |

---

## Возможности

- **Доски объявлений (Bounty Boards)**: естественно генерируются в деревнях или создаются игроком на верстаке.
- **Разнообразные контракты (Bounties)**: задания на добычу ресурсов, охоту на монстров, исследование и крафт.
- **Система указов (Decrees)**: специальные предметы, позволяющие настраивать и специализировать типы заданий, появляющихся на доске.
- **Редкость контрактов**: задания делятся по уровням редкости (Common, Uncommon, Rare, Epic) с прогрессивными наградами.
- **Поддержка датапаков**: полная кастомизация пулов наград, условий и целей заданий через JSON.

---

## Что изменено в порте для 1.21.4 (byMr712)

- **Генерация в модифицированных деревнях:** добавлен динамический механизм инъекции в Jigsaw-пулы структур, благодаря которому беседки с досками объявлений гарантированно спавнятся не только в ванильных поселениях, но и в любых модифицированных деревнях и датапаках (Towns and Towers, ChoiceTheorem's Overhauled Village / CTOV, Repurposed Structures, Structory и др.) с автоматической адаптацией стиля под биом.
- **Безопасная обработка структур:** предотвращены сбои при генерации в случае отсутствия опциональных пулов деревень сторонних модификаций.
- **Портирование на Minecraft 1.21.4:** полная адаптация под Fabric Loader, Yarn mappings и Java 21 LTS.
- **Графический интерфейс:** обновление и адаптация рендеринга GUI, текстур и экранов инвентаря под графический пайплайн Minecraft 1.21.4.
- **Сетевой протокол:** миграция пакетов синхронизации данных и контрактов на современный CustomPayload API.
- **Сборка и оптимизация:** настроен вывод артефакта `Bountiful-1.21.4-byMr712.jar` в корневую директорию `build/libs/`.

---

## Установка

1. Скачайте последнюю версию мода со страницы [GitHub Releases](https://github.com/byMr712/Bountiful-1.21.4-MinecraftMod/releases).
2. Требуются:
   - [Fabric API](https://modrinth.com/mod/fabric-api)
   - [Cloth Config](https://modrinth.com/mod/cloth-config)
   - [Mod Menu](https://modrinth.com/mod/modmenu) (по желанию)
3. Поместите `.jar` файл в папку `mods`.
4. Запустите игру.

---

## Сборка

1. Требуется Java 21 и Fabric Loader для Minecraft 1.21.4.
2. Для сборки выполните:
   ```bash
   ./gradlew :fabric:build
   ```
3. Собранный файл находится в `build/libs/Bountiful-1.21.4-byMr712.jar`.

---

## Авторы и лицензия

- Оригинальный автор: [Ejektaflex](https://github.com/ejektaflex) ([Bountiful](https://github.com/ejektaflex/Bountiful)).
- Порт и адаптация для 1.21.4: [Mr712](https://github.com/byMr712).
- Распространяется под лицензией [LGPL 3.0](LICENSE).
