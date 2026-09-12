package io.ejekta.bountiful.content.board

import io.ejekta.bountiful.config.JsonFormats
import io.ejekta.bountiful.util.readOnlyCopy
import io.ejekta.kambrik.ext.ksx.decodeFromStringTag
import io.ejekta.kambrik.ext.ksx.encodeToStringTag
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.StringTag
import net.minecraft.server.MinecraftServer
import net.minecraft.util.datafix.DataFixTypes
import net.minecraft.world.ContainerHelper
import net.minecraft.world.SimpleContainer
import net.minecraft.world.level.saveddata.SavedData

class GlobalBoardData : SavedData() {

    val decrees = SimpleContainer(3)
    val bounties = BountyInventory()
    var bountyTimestamps: MutableMap<Int, Long> = mutableMapOf()
    var lastUpdatedTime: Long = 0L
    var playerData: MutableMap<String, PlayerBoardData> = mutableMapOf()

    @Serializable
    data class PlayerBoardData(var done: Int, var totalTime: Long, val taken: MutableSet<Int>) {
        companion object {
            fun empty() = PlayerBoardData(0, 0L, mutableSetOf())
        }
    }

    fun loadFrom(compoundTag: CompoundTag, levelRegistry: HolderLookup.Provider) {
        val decreeList = compoundTag.getCompound("decree_inv")
        val bountyList = compoundTag.getCompound("bounty_inv")

        lastUpdatedTime = compoundTag.getLong("lastUpdated")

        ContainerHelper.loadAllItems(decreeList, decrees.items, levelRegistry)
        ContainerHelper.loadAllItems(bountyList, bounties.items, levelRegistry)

        val playerDataMap = compoundTag.get("completed")
        if (playerDataMap != null) {
            playerData = JsonFormats.BlockEntity.decodeFromStringTag(playerDataSerializer, playerDataMap as StringTag).toMutableMap()
        }

        val timeStampMap = compoundTag.get("timestamps")
        if (timeStampMap != null) {
            bountyTimestamps = JsonFormats.BlockEntity.decodeFromStringTag(bountyStampSerializer, timeStampMap as StringTag).toMutableMap()
        }
    }

    override fun save(compoundTag: CompoundTag, levelRegistry: HolderLookup.Provider): CompoundTag {
        compoundTag.putLong("lastUpdated", lastUpdatedTime)

        val doneMap = JsonFormats.BlockEntity.encodeToStringTag(playerDataSerializer, playerData)
        compoundTag.put("completed", doneMap)

        val timeStampMap = JsonFormats.BlockEntity.encodeToStringTag(bountyStampSerializer, bountyTimestamps)
        compoundTag.put("timestamps", timeStampMap)

        val decreeList = CompoundTag()
        ContainerHelper.saveAllItems(decreeList, decrees.readOnlyCopy, levelRegistry)
        compoundTag.put("decree_inv", decreeList)

        val bountyList = CompoundTag()
        ContainerHelper.saveAllItems(bountyList, bounties.readOnlyCopy, levelRegistry)
        compoundTag.put("bounty_inv", bountyList)

        return compoundTag
    }

    companion object {
        internal val playerDataSerializer = MapSerializer(String.serializer(), PlayerBoardData.serializer())
        private val bountyStampSerializer = MapSerializer(Int.serializer(), Long.serializer())

        private val TYPE: SavedData.Factory<GlobalBoardData> = SavedData.Factory(
            ::GlobalBoardData,
            { compoundTag, levelRegistry -> GlobalBoardData().apply { loadFrom(compoundTag, levelRegistry) } },
            DataFixTypes.LEVEL
        )

        fun getOrCreate(server: MinecraftServer): GlobalBoardData {
            return server.overworld().getDataStorage().computeIfAbsent(TYPE, "global_board")
        }
    }
}