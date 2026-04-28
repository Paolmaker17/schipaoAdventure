# How to create a new item

1. Create a new `val ITEMNAME` into the `item/ModItems.kt`, asign it the `registerItem()`
   
```kotlin
   val RAW_MITHRIL :Item = registerItem("raw_mithril", Item(Item.Settings()))
```

2. Insert into `resources/assets/schipaoadventure/textures/item` a texture file like `ITEMNAME.png`

3. Create a new `ITEMNAME.json` file inside `resources/assets/schipaoadventure/models/item`, and specify the `ITEMNAME.png` texture file

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

> [!IMPORTANT]
> `"raw.mithril"` must be replaced with id you registered the item with

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
                 entries.add(ModItems.RAW_MITHRIL)
                 entries.add(ModItems.MITHRIL_INGOT)
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

> [!IMPORTANT]
> `"schipaoadventure_items"` must be replaced with id you registered the item group with


