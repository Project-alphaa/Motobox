package motobox.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import motobox.Motobox;
import motobox.recipe.MechanicTableRecipe;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

import java.util.*;

public class MechanicTableScreen extends HandledScreen<MechanicTableScreenHandler> {
    private static final Identifier TEXTURE = Motobox.id("textures/gui/container/mechanic_table.png");

    private static final int RECIPE_BUTTON_SIZE = 17;
    private static final int RECIPE_PANEL_WIDTH = 85;
    private static final int RECIPE_PANEL_HEIGHT = 51;

    private static final int CATEGORY_BUTTON_WIDTH = 12;
    private static final int CATEGORY_BUTTON_HEIGHT = 15;
    private static final int CATEGORY_BUTTON_AREA_WIDTH = 91;

    private static final int SCROLL_BAR_WIDTH = 3;
    private static final int SCROLL_BAR_HEIGHT = 10;
    private static final int SCROLL_BAR_AREA_HEIGHT = 51;

    private long time = 0;

    private int recipePanelX;
    private int recipePanelY;

    private int categoryButtonsX;
    private int categoryButtonsY;

    private int currentCategory = 0;
    private int recipeScroll = 0;
    private final List<Identifier> orderedCategories = createDefaultCategories();
    private final Map<Identifier, List<RecipeEntry>> recipes = new HashMap<>();

    private OrderedText categoryTitle;

    public MechanicTableScreen(MechanicTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 209;

        this.titleY = 8;

        for (int id = 0; id < handler.recipes.size(); id++) {
            var recipe = handler.recipes.get(id);
            var category = recipe.getCategory();

            this.recipes.computeIfAbsent(category, cat -> new ArrayList<>());
            if (!this.orderedCategories.contains(category)) {
                this.orderedCategories.add(category);
            }

            this.recipes.get(category).add(new RecipeEntry(id, recipe));
        }

        this.playerInventoryTitleY = this.y + 115;
    }

    private static List<Identifier> createDefaultCategories() {
        var list = new ArrayList<Identifier>();
        list.add(Motobox.id("frames"));
        list.add(Motobox.id("engines"));
        list.add(Motobox.id("wheels"));

        return list;
    }

    @Override
    protected void init() {
        super.init();

        this.recipePanelX = this.x + 76;
        this.recipePanelY = this.y + 21;

        this.categoryButtonsX = this.x + 75;
        this.categoryButtonsY = this.y + 4;

        this.categoryTitle = this.createCategoryTitle(this.orderedCategories.get(0));
    }

    @Override
    protected void handledScreenTick() {
        super.handledScreenTick();

        this.time++;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void preDraw() {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.renderBackground(context);

        this.preDraw();
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.drawCategoryBar(context, mouseX, mouseY);
        this.drawRecipes(context, mouseX, mouseY);

        this.drawMissingIngredients(context);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(this.textRenderer, this.title, this.titleX, this.titleY, 0xffffffff, false);
        context.drawText(this.textRenderer, this.playerInventoryTitle, this.playerInventoryTitleX, this.playerInventoryTitleY, 0x404040, false);

        int hoveredRecipe = this.getHoveredRecipe(mouseX, mouseY);
        if (hoveredRecipe >= 0) {
            assert this.client != null;
            context.drawItemTooltip(this.textRenderer, this.handler.recipes.get(hoveredRecipe).getOutput(this.client.world.getRegistryManager()), mouseX - this.x, mouseY - this.y);
        }
    }

    private void changeCategory(int by) {
        this.currentCategory = Math.floorMod((this.currentCategory + by), this.orderedCategories.size());
        this.categoryTitle = createCategoryTitle(this.orderedCategories.get(this.currentCategory));
        this.recipeScroll = 0;
    }

    private OrderedText createCategoryTitle(Identifier category) {
        var translated = I18n.translate("part_category."+category.getNamespace()+"."+category.getPath());
        if (this.textRenderer.getWidth(translated) > 64) {
            return Text.literal(this.textRenderer.trimToWidth(translated, 57) + "...").asOrderedText();
        }
        return Text.literal(this.textRenderer.trimToWidth(translated, 64)).asOrderedText();
    }

    private void buttonClicked() {
        if (this.client != null) {
            this.client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            int selectedCatButton = getHoveredCategoryButton((int) mouseX, (int) mouseY);
            if (selectedCatButton != 0) {
                this.changeCategory(selectedCatButton);
                this.buttonClicked();

                return true;
            }

            int recipe = this.getHoveredRecipe((int) mouseX, (int) mouseY);
            if (recipe >= 0) {
                this.selectRecipe(recipe);
                this.buttonClicked();

                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void selectRecipe(int id) {
        assert this.client != null;
        this.handler.onButtonClick(this.client.player, id);
        assert this.client.interactionManager != null;
        this.client.interactionManager.clickButton(this.handler.syncId, id);
    }

    private int getMaxRecipeScroll() {
        return Math.max(0, MathHelper.ceil((float)this.getRecipeList().size() / 5) - 3);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (amount != 0 && this.getHoveredRecipe((int) mouseX, (int) mouseY) >= -1) {
            this.recipeScroll += amount > 0 ? -1 : 1;
            this.recipeScroll = MathHelper.clamp(this.recipeScroll, 0, this.getMaxRecipeScroll());

            return true;
        }

        return false;
    }

    protected final void drawMissingIngredient(DrawContext context, Ingredient ing, int x, int y) {
        context.fill(x, y, x + 16, y + 16, 0x45FF0000);

        var stacks = ing.getMatchingStacks();
        ItemStack stack = stacks[MathHelper.floor((float)this.time / 30) % stacks.length];
        stack.setCount(handler.missingIngredients.get(ing));
        context.drawItemInSlot(this.textRenderer, stack, x, y);

        RenderSystem.depthFunc(516);
        context.fill(x, y, x + 16, y + 16, 0x30FFFFFF);
        RenderSystem.depthFunc(515);
    }

    protected void drawMissingIngredients(DrawContext context) {
        var inputInv = this.handler.inputInv;
        var missingIngs = new ArrayDeque<>(this.handler.missingIngredients.keySet());

        for (int i = 0; i < inputInv.size(); i++) if (missingIngs.size() > 0) {
            int x = this.x + 8 + (i * 18);
            int y = this.y + 88;

            if (inputInv.getStack(i).isEmpty()) {
                this.drawMissingIngredient(context, missingIngs.removeFirst(), x, y);
            }
        }
    }

    protected List<RecipeEntry> getRecipeList() {
        if (this.currentCategory < this.orderedCategories.size() && this.currentCategory >= 0) {
            return this.recipes.get(this.orderedCategories.get(this.currentCategory));
        }

        return Collections.emptyList();
    }

    protected int getHoveredCategoryButton(int mouseX, int mouseY) {
        if (mouseY > this.categoryButtonsY && mouseY < this.categoryButtonsY + CATEGORY_BUTTON_HEIGHT) {
            int relX = mouseX - this.categoryButtonsX;
            if (relX < 0 || relX > CATEGORY_BUTTON_AREA_WIDTH) {
                return 0;
            }

            if (relX < CATEGORY_BUTTON_WIDTH) {
                return -1;
            }
            if (relX > (CATEGORY_BUTTON_AREA_WIDTH - CATEGORY_BUTTON_WIDTH)) {
                return 1;
            }
        }

        return 0;
    }

    protected int getHoveredRecipe(int mouseX, int mouseY) {
        mouseX -= this.recipePanelX;
        mouseY -= this.recipePanelY;

        if (this.currentCategory < this.orderedCategories.size() && this.currentCategory >= 0 &&
                (mouseX >= 0 && mouseX < RECIPE_PANEL_WIDTH) && (mouseY >= 0 && mouseY < RECIPE_PANEL_HEIGHT)) {
            int row = MathHelper.floor((float)mouseY / RECIPE_BUTTON_SIZE);
            int col = MathHelper.floor((float)mouseX / RECIPE_BUTTON_SIZE);

            if (row >= 0 && col >= 0) {
                int idx = (5 * (row + this.recipeScroll)) + col;
                var recipes = this.recipes.get(this.orderedCategories.get(this.currentCategory));
                if (idx < recipes.size()) {
                    return recipes.get(idx).id();
                }

                return -1; // Still within the recipe box bounds, but no recipe
            }
        }

        return -2;
    }

    protected void drawCategoryBar(DrawContext context, int mouseX, int mouseY) {
        int hoveredCatButton = this.getHoveredCategoryButton(mouseX, mouseY);

        this.preDraw();
        context.drawTexture(TEXTURE, this.categoryButtonsX, this.categoryButtonsY,
                176, 17 + (hoveredCatButton < 0 ? CATEGORY_BUTTON_HEIGHT : 0), CATEGORY_BUTTON_WIDTH, CATEGORY_BUTTON_HEIGHT);
        context.drawTexture(TEXTURE, this.categoryButtonsX + (CATEGORY_BUTTON_AREA_WIDTH - CATEGORY_BUTTON_WIDTH), this.categoryButtonsY,
                188, 17 + (hoveredCatButton > 0 ? CATEGORY_BUTTON_HEIGHT : 0), CATEGORY_BUTTON_WIDTH, CATEGORY_BUTTON_HEIGHT);

        if (this.categoryTitle != null) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.categoryTitle, this.x + 120, this.y + 8, 0xFFFFFF);
        }
    }

    protected void drawRecipes(DrawContext context, int mouseX, int mouseY) {
        if (this.orderedCategories.size() > 0) {
            var recipes = this.recipes.get(this.orderedCategories.get(this.currentCategory));

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 5; col++) {
                    int idx = (5 * this.recipeScroll) + (5 * row) + col;

                    if (idx < recipes.size()) {
                        int x = (col * RECIPE_BUTTON_SIZE) + this.recipePanelX;
                        int y = (row * RECIPE_BUTTON_SIZE) + this.recipePanelY;

                        var entry = recipes.get(idx);

                        var state = RecipeButtonState.DEFAULT;
                        if (this.handler.getSelectedRecipe().isPresent() &&
                                this.handler.getSelectedRecipeId() == entry.id()) {
                            state = RecipeButtonState.SELECTED;
                        } else if (this.getHoveredRecipe(mouseX, mouseY) == entry.id()) {
                            state = RecipeButtonState.HOVERED;
                        }

                        this.drawRecipeEntry(entry, context, x, y, state);
                    } else {
                        break;
                    }
                }
            }
        }

        this.preDraw();
        int maxScroll = this.getMaxRecipeScroll();

        int scrollBarX = this.x + 162;
        int scrollBarY = this.y + 21;
        if (maxScroll > 0) {
            scrollBarY += (int)((SCROLL_BAR_AREA_HEIGHT - SCROLL_BAR_HEIGHT) * ((float)this.recipeScroll / maxScroll));
        }

        context.drawTexture(TEXTURE, scrollBarX, scrollBarY, 227, 0, SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT);
    }

    protected void drawRecipeEntry(RecipeEntry entry, DrawContext context, int x, int y, RecipeButtonState state) {
        this.preDraw();
        context.drawTexture(TEXTURE, x, y, 176 + (state.ordinal() * RECIPE_BUTTON_SIZE), 0, RECIPE_BUTTON_SIZE, RECIPE_BUTTON_SIZE);

        assert this.client != null;
        var stack = entry.recipe.getOutput(this.client.world.getRegistryManager());
        context.drawItemInSlot(this.textRenderer, stack, x, y);
    }

    public record RecipeEntry(int id, MechanicTableRecipe recipe) {}

    public enum RecipeButtonState {
        DEFAULT, HOVERED, SELECTED
    }
}
