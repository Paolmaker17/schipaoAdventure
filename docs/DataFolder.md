# Data Folder Breakdown

## Structure

```
data/
├── minecraft/
│   └── tags/
│       └── blocks/
│           └── mineable/
│               ├── pickaxe.json
│               └── //.json
│           ├── needs_iron_tool.json
│           ├── needs_diamond_tool.json
│           └── //.json
│
└── schipaoadventure/
    ├── loot_tables/
    │   └── blocks/
    │       ├── mithril_ore.json
    │       └── //.json
    │
    └── recipes/
        ├── raw_mithril.json
        └── //.json
```

---

## Tags (`minecraft/tags/blocks/`)

Defines how blocks interact with tools.

* `mineable/pickaxe.json`
  → Blocks that can be mined with a pickaxe
  
> Replace pickaxe with others tool if needed

Specify the tool type

* `needs_iron_tool.json`
  → Requires iron pickaxe or better

* `needs_diamond_tool.json`
  → Requires diamond pickaxe

> Can be done with any type of tool material

---

## Loot Tables (`schipaoadventure/loot_tables/blocks/`)

Controls what blocks drop when broken.

* `mithril_block.json` → drops the mithril block
* `mithril_ore.json` → defines ore drops
* `deepslate_mithril_ore.json` → variant for deepslate
* `raw_mithril_block.json` → raw storage block drops

---

## Recipes (`schipaoadventure/recipes/`)

Defines crafting and smelting recipes.

### Crafting

* `item/block_id.json`

### Smelting / Blasting

* `_from_smelting_*`
* `_from_blasting_*`

---

> [!NOTE]
> Folder names are **case-sensitive**
> <br>Must use:
>
>  * `blocks` (NOT `block`)
>  * `loot_tables` (NOT `loot_table`)
>  * `recipes` (NOT `recipe`)
>  * File names must match registry IDs exactly
