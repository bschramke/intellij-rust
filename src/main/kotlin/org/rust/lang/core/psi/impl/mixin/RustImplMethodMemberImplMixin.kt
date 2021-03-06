package org.rust.lang.core.psi.impl.mixin

import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.IStubElementType
import org.rust.ide.icons.RustIcons
import org.rust.ide.icons.addStaticMark
import org.rust.lang.core.psi.RustImplMethodMemberElement
import org.rust.lang.core.psi.iconWithVisibility
import org.rust.lang.core.psi.impl.RustPsiImplUtil
import org.rust.lang.core.stubs.elements.RustImplMethodMemberElementStub
import javax.swing.Icon

abstract class RustImplMethodMemberImplMixin : RustFnImplMixin<RustImplMethodMemberElementStub>
                                             , RustImplMethodMemberElement {

    constructor(node: ASTNode) : super(node)

    constructor(stub: RustImplMethodMemberElementStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getIcon(flags: Int): Icon? {
        var icon = RustIcons.METHOD
        if (isStatic) {
            icon = icon.addStaticMark()
        }
        return iconWithVisibility(flags, icon)
    }

    override val isPublic: Boolean get() = RustPsiImplUtil.isPublic(this)
}

