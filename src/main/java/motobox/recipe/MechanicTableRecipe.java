package motobox.recipe;

import motobox.Motobox;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiConsumer;

public class MechanicTableRecipe implements Recipe<SimpleInventory>, Comparable<MechanicTableRecipe> {
    public static final Identifier ID = Motobox.id("mechanic_table");
    public static final RecipeType<MechanicTableRecipe> TYPE = new RecipeType<>() {
    };
    private final Identifier id;

    protected final Identifier category;
    protected final Map<Ingredient, Integer> ingredients;
    protected final ItemStack result;
    protected final int sortNum;

    public MechanicTableRecipe(Identifier id, Identifier category, Map<Ingredient, Integer> ingredients, ItemStack result, int sortNum) {
        this.id = id;
        this.category = category;
        this.ingredients = ingredients;
        this.result = result;
        this.sortNum = sortNum;
    }

    public Identifier getCategory() {
        return this.category;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        boolean[] result = {true};
        this.forMissingIngredients(inventory, true, (ing, i) -> result[0] = false);

        return result[0];
    }

    @Override
    public ItemStack craft(SimpleInventory inv, DynamicRegistryManager registryManager) {
        for (var ing : this.ingredients.keySet()) {
            int count = ingredients.get(ing);
            for (int i = 0; i < inv.size(); i++) {
                var stack = inv.getStack(i);
                if (ing.test(stack) && count <= stack.getCount()) {
                    stack.decrement(count);
                    break;
                }
            }
        }

        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MechanicTableRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    public void forMissingIngredients(Inventory inv, boolean checkCount, BiConsumer<Ingredient, Integer> action) {
        var invCopy = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.size(); i++) {
            invCopy.add(inv.getStack(i));
        }

        for (var ing : this.ingredients.keySet()) {
            if (invCopy.stream().noneMatch(ing.and(stack -> !checkCount || stack.getCount() >= ingredients.get(ing)))) {
                action.accept(ing, ingredients.get(ing));
            } else {
                invCopy.remove(invCopy.stream().filter(ing).toList().get(0));
            }
        }
    }

    @Override
    public int compareTo(@NotNull MechanicTableRecipe o) {
        int diff = this.getCategory().compareTo(o.getCategory());
        if (diff != 0) return diff;

        diff = Integer.compare(this.sortNum, o.sortNum);
        if (diff != 0) return diff;

        return this.getId().compareTo(o.getId());
    }
}
