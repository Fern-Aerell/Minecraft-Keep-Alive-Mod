# 1.2.4-1.20.1

Revision 01

## Features
- Added toggle ```<use_totem>``` to enable the use of totems.
- Keepalive status will not reset when the player dies.

## Changes
- Changed the mod name from ```Minecraft but health is half-hearted``` to ```Keep-Alive Mod```.
- Changed the command prefix from ```/mbtlhihh``` to ```/keepalive```.

## Commands
View the mod status for a specific player:
```
/keepalive status <player>
```
Activate and deactivate the mod for a specific player:
```
/keepalive <player> <status> <use_totem>
```

## Information
- Players will remain dead when using ```/kill``` if the ```use_totem``` value is true.

------------------------------------------------------

# 1.0-1.20.1

Initial Version

## Required
- Minecraft Version : **1.20.1**
- Fabric Loader Version : **0.15.3**
- Fabric API Version : **0.91.0+1.20.1**

## Features
- Minecraft with the twist that the lowest health is half-hearted.
- Command to set the player with the lowest health to half a heart.

## Command
View the mod status for a specific player:
```
/mbtlhihh status <player>
```
Activate the mod for a specific player:
```
/mbtlhihh activate <player>
```
Deactivate the mod for a specific player:
```
/mbtlhihh deactivate <player>
```