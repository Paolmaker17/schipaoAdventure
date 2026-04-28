# How to create a new item group

1. Create the a new `val ITEMGROUPNAME` into the `item/ModItemGroups.kt`, asign it the `register()` with related parameters
   
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

