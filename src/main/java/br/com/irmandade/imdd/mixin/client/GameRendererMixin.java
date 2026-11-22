package br.com.irmandade.imdd.mixin.client;

import br.com.irmandade.imdd.relic.RelicRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/** (26.3: o método saiu do GameRenderer e foi para o LocalPlayer.) Animação do totem: se o jogador segura a Relíquia da Imortalidade, mostra ela (encantada, com brilho). */
@Mixin(LocalPlayer.class)
public abstract class GameRendererMixin {

    @ModifyVariable(method = "displayItemActivation", at = @At("HEAD"), argsOnly = true, require = 0)
    private ItemStack imdd$totemEncantado(ItemStack shown) {
        if (shown == null || !shown.is(Items.TOTEM_OF_UNDYING)) return shown;
        Player p = Minecraft.getInstance().player;
        if (p == null) return shown;
        if (RelicRegistry.is(p.getMainHandItem(), "imortalidade")) return p.getMainHandItem().copy();
        if (RelicRegistry.is(p.getOffhandItem(), "imortalidade")) return p.getOffhandItem().copy();
        return shown;
    }
}
