package org.rust.cargo.runconfig

import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.util.Function
import org.rust.cargo.runconfig.producers.CargoExecutableRunConfigurationProducer
import org.rust.lang.core.psi.RustFnElement
import org.rust.lang.core.psi.RustTokenElementTypes
import org.rust.lang.core.psi.util.elementType

class RustRunLineMarkerContributor : RunLineMarkerContributor() {

    override fun getInfo(element: PsiElement?): Info? {
        if (element == null || element.elementType != RustTokenElementTypes.IDENTIFIER) return null
        val fn = element.parent as? RustFnElement ?: return null
        return when {
            CargoExecutableRunConfigurationProducer.isMainFunction(fn) -> Info(
                AllIcons.RunConfigurations.TestState.Run,
                Function<PsiElement, String> { "Run Application" },
                *ExecutorAction.getActions(0)
            )

            fn.isTest -> Info(
                AllIcons.RunConfigurations.TestState.Green2,
                Function<PsiElement, String> { "Run Test" },
                *ExecutorAction.getActions(0)
            )

            else -> null
        }
    }
}
