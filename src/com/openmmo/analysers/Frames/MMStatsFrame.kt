package com.openmmo.analysers.Frames

//@DependsOn(BaseResizableFrame::class, BaseDialogLayout::class)
//class MMStatsFrame : IdentityMapper.Class() {
//    override val predicate = predicateOf<ClassWrapper> { it.methods.any { it.instructions.filter { it.opcode == Opcodes.LDC }.any { it.ldcCst == "mm-stats-window" } } }
//        .and { it.superType == type<BaseResizableFrame>() }
//        .and { it.instanceFields.any { it.type == type<BaseDialogLayout>() } }
//}