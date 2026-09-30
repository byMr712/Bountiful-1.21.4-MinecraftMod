package io.ejekta.kambrik.structure

import com.mojang.datafixers.util.Pair
import io.ejekta.kambrik.ext.Identifier
import io.ejekta.kambrik.internal.mixins.StructurePoolAccessor
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import kotlin.jvm.optionals.getOrNull

/**
 * Accessed via [Kambrik.Structure][io.ejekta.kambrik.Kambrik.Structure]
 */
class KambrikStructureApi internal constructor() {

    private val EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(Registries.PROCESSOR_LIST, Identifier("minecraft", "empty"))

    // Meant to be called from inside a ServerLifecycleEvents.SERVER_STARTING event
    fun addToStructurePool(server: MinecraftServer, nbtLocation: ResourceLocation, poolLocation: ResourceLocation, processorLocation: ResourceLocation, weight: Int = 10_000) {
        if (weight == 0) {
            return
        }

        val procRegistry = server.registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST)
        val emptyProcessorList: Holder.Reference<StructureProcessorList> =
            procRegistry.getOrThrow(EMPTY_PROCESSOR_LIST_KEY)

        val OUR_PROCESSOR_LIST_KEY = ResourceKey.create(Registries.PROCESSOR_LIST, processorLocation)

        val ourProcessorList: Holder.Reference<StructureProcessorList> =
            procRegistry.get(OUR_PROCESSOR_LIST_KEY).getOrNull() ?: emptyProcessorList

        val poolRegistry = server.registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL)
        val poolHolder = poolRegistry.get(ResourceKey.create(Registries.TEMPLATE_POOL, poolLocation)).getOrNull()
        if (poolHolder == null) {
            return
        }
        val pool = poolHolder.value()

        val pieceList = (pool as StructurePoolAccessor).elements
        val piece = StructurePoolElement.single(nbtLocation.toString(), ourProcessorList).apply(
            StructureTemplatePool.Projection.RIGID)

        val list = (pool as StructurePoolAccessor).elementCounts.toMutableList()
        list.add(Pair(piece, weight))
        (pool as StructurePoolAccessor).elementCounts = list

        repeat(weight) {
            pieceList.add(piece)
        }
    }

}