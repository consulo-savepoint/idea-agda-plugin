package org.jetbrains.agda.settings

import com.intellij.ide.util.PropertiesComponent

/**
 * @author Evgeny.Kurbatsky
 */
class AgdaSettings() {
    val AGDA_EXECUTABLE_PATH = "agda.executable.path"

    fun getAgdaPath() : String {
        return PropertiesComponent.getInstance()?.getValue(AGDA_EXECUTABLE_PATH) ?: "agda";
    }

    fun setAgdaPath(path : String) {
        PropertiesComponent.getInstance()?.setValue(AGDA_EXECUTABLE_PATH, path)
    }
}