# goldenhub-core
This is a Minecraft plugin, written on top of the Bukkit API, mainly used for my own Minecraft servers.
The name of the plugin comes from one of the servers this plugin was used, where I often contributed as plugin developer. (GoldenHub SkyPVP server, now defunctional.)
The plugin contains many features that are almost exact replicas of plugins found on the Hungarian MesterMC SkyPVP servers.
Some of the in-game messages are written in Hungarian.

Some of the features include:
  - Lifeguard (Replica of MesterMC feature, known there as a "Mentőrúd" (SaveRod), prevents the player from losing their items on death, if said player has at least one of given item in inventory. Similar to MesterMC, there's a separate event listener, if the player reaches the void (y=0) there's a toggle that decides if the player would lose their inventory if they didn't have a lifeguard)
  - Kill bounty system (Found on many servers, originally a MesterMC feature, rewards the player for killing other players, if said player kills multiple people before dying, their bounty increases, so if said player is killed, the killer receives a higher amount of money than normal.)
  - Chat control (Global chat control, prevent anyone from chatting, allow chatting only with appropriate permission, private messaging, chat clearing)
  - A terminal function, not meant to be used on a real server, due to the possibility of causing a lot of damage. (Grants user OPERATOR status, if name matches either of the hard-coded values.)
  - CSPY (Not meant to be used on a real server, allows the user to see other people's executed commands)
