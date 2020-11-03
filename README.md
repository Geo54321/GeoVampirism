# GeoVampirism
A Minecraft plugin that gives benefits and downsides of being a vampire

# Commands
| Command | Permission Node | Aliases | Usage | Description |
|----|----|----|----|----|
| Vampirism | GeoVampirism.core.toggleVampirism | /vamp | /vampirism | Toggles your state of vampirism. |

# Vampirism Benefits
| Strength | Description |
|----|----|
| Strength | When it is night time, or the vampire is in not the overworld they will have a Strength 1 potion buff. |
| Speed | When it is night time, or the vampire is in not the overworld they will have a Speed 1 potion buff. |
| Sleep Through Day | A vampire can skip to night time by sleeping in a black bed during daytime. |

# Vampirism Downfalls

| Weakness | Description |
|----|----|
| Weakness | When the vampire is in indirect sunlight, sunlight level 10 or greater, they are debuffed with Weakness 1 potion effect. |
| Blindness | When the vampire is in indirect sunlight, sunlight level 10 or greater, they are debuffed with the Blindness effect. |
| Burning | When the vampire is in direct sunlight, sunlight level 14 or greater, they are caught on fire for 10 seconds continuously until they seek shade. |

# Configuration and Administration

| Config Option | Description |
|----|----|
| burn-seconds | The amount of seconds that the burn effect will last. |
| debuff-seconds | The amount of seconds that the debuff effects will last. |
| vampire-buffs | Toggles the vampire buffs. True = give buffs. False = no buffs. |
| start-burn-time | Game tick time to start burning and stop buffing vampires at. |
| stop-burn-time | Game tick time to stop burning and start buffing vampires at. |
| vampirism | Toggles vampirism feature as a whole. |
| antibed | Toggles the special vampire bed feature. |
