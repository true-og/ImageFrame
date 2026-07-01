[![Build Status](https://ci.loohpjames.com/job/ImageFrame/badge/icon)](https://ci.loohpjames.com/job/ImageFrame/) [![Crowdin](https://badges.crowdin.net/imageframe/localized.svg)](https://crowdin.com/project/imageframe)
# ImageFrame

https://www.spigotmc.org/resources/106031/<br>
https://modrinth.com/plugin/imageframe<br>
https://hangar.papermc.io/LOOHP/ImageFrame

Put images on maps and walls!

More information (screenshots, commands, permissions) about the plugin can be found on the Spigot page linked above.

## Creating Images In-Game

The main command is `/imageframe` (aliases: `/iframe`, `/if`, `/frame`). All creation commands are op-only by default.
Sizes are measured in **maps** — one map fills one item frame block, so a `4 x 3` image needs a 4-wide by 3-tall grid of item frames.

### Quick way — create straight onto a wall
1. Place a grid of item frames on the wall (e.g. 4 wide × 3 tall).
2. Run `/imageframe select`, then left-click one corner frame and right-click the opposite corner frame to select the whole grid.
3. Run `/imageframe create <name> <url> selection`.

The image is downloaded, sized to your selection, and placed into the frames automatically.

### Manual way — create, then place yourself
1. `/imageframe create <name> <url> <width> <height>` — creates the image map (`width`/`height` in maps).
2. Put it on frames one of two ways:
   - `/imageframe get <name>` — gives you the map item(s); place each on the item frames in order (left→right, top→bottom).
   - or select a frame grid (as above) and run `/imageframe get <name> selection` to auto-fill it.

### Using preloaded maps
Preloaded maps (defined in the config) are owned by **Console**. Anyone with access can see them with `/imageframe list` and use them by name:
- `/imageframe get <name> selection`, or reference them explicitly as `Console:<name>`.

### Useful extras
- `/imageframe info <name>` — shows a map's size (handy to build a matching frame grid) and its URL.
- `/imageframe refresh <name>` — re-downloads and re-renders an existing map.
- Optional flags on `create`/`get`: a dithering type, and `combined`/`separated`.

### Behaviour of this fork
- Images are **stretched to fill** the whole frame grid (edge to edge) instead of being letterboxed with a transparent border. Match your frame grid's width:height to the image's aspect ratio to avoid distortion.
- Item frames filled by the plugin are automatically made **invisible**, so neighbouring tiles join seamlessly with no thin border between them.

## Built against Spigot
Built against [Spigot's API](https://www.spigotmc.org/wiki/buildtools/) (required mc versions are listed on the spigot page above).
Plugins built against Spigot usually also work with [Paper](https://papermc.io/).

## Development Builds

- [Jenkins](https://ci.loohpjames.com/job/ImageFrame/)

## Maven
```html
<repository>
  <id>loohp-repo</id>
  <url>https://repo.loohpjames.com/repository</url>
</repository>
```
```html
<dependency>
  <groupId>com.loohp</groupId>
  <artifactId>ImageFrame</artifactId>
  <version>VERSION</version>
  <scope>provided</scope>
</dependency>
```
Replace `VERSION` with the version number.

## Official Addons

- [ImageFrameClient](https://github.com/LOOHP/ImageFrameClient) (Complementary client mod for servers with ImageFrame to display HD and full color images)

## Partnerships

### Server Hosting
**Use the link or click the banner** below to **get a 25% discount off** your first month when buying any of their gaming servers!<br>
It also **supports my development**, take it as an alternative way to donate while getting your very own Minecraft server as well!

*P.S. Using the link or clicking the banner rather than the code supports me more! (Costs you no extra!)*

**https://www.bisecthosting.com/loohp**

[![](https://www.bisecthosting.com/partners/custom-banners/96e11ee5-50e4-494f-854d-8c1708813abd.png)](https://www.bisecthosting.com/loohp)
