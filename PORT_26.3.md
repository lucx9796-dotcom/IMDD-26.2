# Port para Minecraft 26.3

Feito sobre o port 26.2. NÃO foi compilado (sem rede/jar do jogo no ambiente do port): o primeiro `./gradlew build` pode apontar nomes a ajustar.

## Build
- JDK 25. Gradle 9.6.0 (wrapper), Loom `net.fabricmc.fabric-loom` 1.17-SNAPSHOT.
- minecraft 26.3, Fabric Loader 0.19.5, Fabric API 0.165.0+26.3.
- fabric.mod.json: minecraft `~26.3`.

## Mudanças 26.2 -> 26.3 (primer do NeoForge)
- `GameRenderer#displayItemActivation` -> `LocalPlayer#displayItemActivation`: o mixin do totem (GameRendererMixin) agora mira `LocalPlayer`.
  Se a assinatura nova não receber mais um `ItemStack`, o mixin é ignorado em silêncio (require = 0) e o totem só não mostra a relíquia.
- O projeto não usa teclas, `KeyEvent`, `InputConstants`, loot tables nem `PoseStack`: a troca GLFW -> SDL, as loot tables reloadable e o `mulPose` não afetam.
- Receita (`data/imdd/recipe/bizonho_axe.json`): receitas agora são registro reloadable, mas o JSON continua igual.

## ATENÇÃO — conferir no primeiro teste
1. **Lua do Expurgo** (`SkyRenderingMixin`): `SkyRenderer#renderSkyDisc` virou `render` e vários métodos ficaram privados; o nome `renderMoon` continua assumido.
2. **Armadura invisível**: `AvatarRenderer.extractRenderState` (o `EntityRenderDispatcher#getPlayerRenderer` virou `getRenderer`).
3. Marca d'água do F3 (`mc.gui.hud.getDebugOverlay()`) e `DebugScreenEntryList` continuam como no 26.2.
4. Magma Congelado: translúcido pelo `force_translucent` do modelo; o 26.3 tem transparência nova (OIT) opcional.
5. Só li o início do primer 26.2 -> 26.3 (as listas de mudanças de `world`/`server` ficaram de fora).
