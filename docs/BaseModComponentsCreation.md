
> [!IMPORTANT]
> `"schipaoadventure_items"` and `"raw.mithril"`  must be replaced with the id you registered the item group and item/block


# How to create a new item

1. Create a new `val ITEMNAME` into the `item/ModItems.kt`, asign it the `registerItem()`
   
```kotlin
   val RAW_MITHRIL :Item = registerItem("raw_mithril", Item(Item.Settings()))
```

2. Insert into `resources/assets/schipaoadventure/textures/item` a texture file like `item_name.png`

3. Create a new `item_name.json` file inside `resources/assets/schipaoadventure/models/item`, and specify the `item_name.png` texture file

```json
   {
     "parent": "minecraft:item/generated",
     "textures": {
       "layer0": "schipaoadventure:item/mithril_ingot"
     },
     ...
   }
```

4. Update the `resources/assets/schipaoadventure/lang/en_us.json` localization file

```json
   {
     "item.schipaoadventure.raw_mithril": "Raw Mithril",
     ...
   }
```

---

# How to create a new block

1. Create a new `val BLOCKNAME` into the `block/ModBlock.kt`, asign it the `registerBlock()` and related parameters
   
```kotlin
   val MITHRIL_ORE :Block = registerBlock("mithril_ore",
        // Normal block
        Block(AbstractBlock.Settings.create()
            // additional parameters
        )

        // Experience dropping block
        ExperienceDroppingBlock(
            UniformIntProvider.create(3, 7),
            AbstractBlock.Settings.create()
                // additional parameters
        )
   )
```

2. Insert into `resources/assets/schipaoadventure/textures/block` a texture file like `block_name.png`

4. Create a new `block_name.json` file inside `resources/assets/schipaoadventure/models/block` and `models/item`, and specify the `block_name.png` texture file

- `models/block` json file
 
```json
   {
     "parent": "minecraft:block/cube_all",
     "textures": {
       "all": "schipaoadventure:block/mithril_ore"
     }
     ...
   }
```

> `cube_all` makes the texture af all the block faces the same
  
- `models/item` json file

```json
   {
     "parent": "schipaoadventure:block/mithril_ore"
   }
```

4. Update the `resources/assets/schipaoadventure/lang/en_us.json` localization file

```json
   {
     "block.schipaoadventure.mithril_ore": "Mithril Ore",
     ...
   }
```

5. Create a new `block_name.json` file inside `resources/assets/schipaoadventure/blockstates` folder. If the block has some changing properties this is the file to update, if not just copy the example below changing the names

```json
{
  "variants": {
    "": {
      "model": "schipaoadventure:block/mithril_ore"
    }
  }
}
```

---

# How to create a new item group

1. Create a new `val ITEMGROUPNAME` into the `item/ModItemGroups.kt`, asign it the `register()` with related parameters

```kotlin
   val SCHIPAOADVENTURE_ITEMS :ItemGroup = Registry.register(Registries.ITEM_GROUP,
         Identifier.of(SchipaoAdventure.MOD_ID, "schipaoadventure_items"),
         FabricItemGroup.builder()
             .displayName(Text.translatable("itemgroup.schipaoadventure_items"))
             .icon { ItemStack(ModItems.MITHRIL_INGOT) }
             .entries { _, entries ->
                 // Adding items to item group
                 // entries.add(<class.item>)
             }
             .build()
     )
```

2. Update the `resources/assets/schipaoadventure/lang/en_us.json` localization file

```json
   {
     "itemgroup.schipaoadventure_items": "SchiPao Adventure Items",
     ...
   }
```

