package com.openmmo.analysers

import com.openmmo.mapper.*

class ItemInventory : IdentityMapper.Class() {
    override val predicate = predicateOf<ClassWrapper> { it.constructors.any { it.arguments.size == 1  && it.arguments.first().baseType == type<ItemInfo>() && it.arguments.first().arrayDimensions == 1} }
}