# How to create a new item

1. Create the a new `val ITEMNAME` into the `item/ModItems.kt`, asign it the `registerItem()`
   
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

