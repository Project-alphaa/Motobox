package motobox.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import motobox.Motobox;
import motobox.item.VehicleComponentItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class MechanicTableRecipeSerializer implements RecipeSerializer<MechanicTableRecipe> {
    public static final MechanicTableRecipeSerializer INSTANCE = new MechanicTableRecipeSerializer();

    private static ItemStack vehicleComponentStackFromJson(JsonObject obj) throws JsonSyntaxException, IllegalStateException {
        var id = Identifier.tryParse(obj.get("item").getAsString());
        int count = obj.has("count") ? obj.get("count").getAsInt() : 1;
        return vehicleComponentStackFromJson(obj, id, count);
    }

    private static ItemStack vehicleComponentStackFromJson(JsonObject obj, Identifier id, int count) {
        ItemStack stack = Registries.ITEM.getOrEmpty(id).map(i -> new ItemStack(i, count)).orElse(ItemStack.EMPTY);

        if (obj.has("component") && stack.getItem() instanceof VehicleComponentItem<?> item) {
            var component = Identifier.tryParse(obj.get("component").getAsString());
            if (component != null) {
                item.setComponent(stack, component);
            }
        }
        return stack;
    }

    @Override
    public MechanicTableRecipe read(Identifier id, JsonObject json) {
        try {
            var category = Identifier.tryParse(json.get("category").getAsString());
            var ingredients = new HashMap<Ingredient, Integer>();
            for (JsonElement ingredientEle : json.get("ingredients").getAsJsonArray()) {
                JsonObject ingredientObj = ingredientEle.getAsJsonObject();
                int count = ingredientObj.has("count") ? ingredientObj.get("count").getAsInt() : 1;
                JsonElement itemsEle = ingredientObj.get("items");
                if (itemsEle != null) {
                    JsonObject itemsObj = itemsEle.getAsJsonObject();
                    boolean isWheel = false;
                    if (itemsObj.has("item")) {
                        String item = itemsObj.get("item").getAsString();
                        if (item.equals(Motobox.id("wheel").toString())) {
                            vehicleComponentStackFromJson(itemsEle.getAsJsonObject(), Motobox.id("wheel"), count);
                            isWheel = true;
                        }
                    }

                    if (!isWheel) {
                        ingredients.put(Ingredient.fromJson(itemsEle), count);
                    }
                }
                else {
                    System.err.println("Failed to load recipe: " + id.toString());
                }
            }
            var result = vehicleComponentStackFromJson(json.get("result").getAsJsonObject());
            int sortNum = 0;
            if (json.has("sortnum")) {
                sortNum = json.get("sortnum").getAsInt();
            }

            return new MechanicTableRecipe(id, category, ingredients, result, sortNum);
        }
        catch (IllegalStateException ex) {
            throw new JsonSyntaxException("Error parsing Mechanic Table recipe - " + ex.getMessage());
        }
    }

    @Override
    public MechanicTableRecipe read(Identifier id, PacketByteBuf buf) {
        var category = Identifier.tryParse(buf.readString());

        int size = buf.readByte();
        var ingredients = new HashMap<Ingredient, Integer>();
        for (int i = 0; i < size; i++) {
            ingredients.put(Ingredient.fromPacket(buf), buf.readInt());
        }

        var result = buf.readItemStack();
        int sortNum = buf.readInt();

        return new MechanicTableRecipe(id, category, ingredients, result, sortNum);
    }

    @Override
    public void write(PacketByteBuf buf, MechanicTableRecipe recipe) {
        buf.writeString(recipe.category.toString());
        buf.writeByte(recipe.ingredients.size());
        recipe.ingredients.forEach((ingredient, count) -> {
            ingredient.write(buf);
            buf.writeInt(count);
        });
        buf.writeItemStack(recipe.result);
        buf.writeInt(recipe.sortNum);
    }
}
