package org.jetbrains.agda.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent
import javax.swing.Icon
import java.awt.GridBagLayout
import java.awt.GridBagConstraints
import javax.swing.*
import com.intellij.application.options.codeStyle.CodeStyleMainPanel
import com.intellij.ide.util.PropertiesComponent


/**
 * @author Evgeny.Kurbatsky
 */
class AgdaSettingsConfigurable : Configurable {
    val myFiled = JTextField(60)

    public override fun createComponent(): JComponent? {
        val agdaPath = AgdaSettings().getAgdaPath();
        val contentPanel = JPanel(GridBagLayout())

        contentPanel.add(JLabel("Agda excutable : "), gridBagConstraints {
            gridx = 0;
            gridx = 0;
        })

        myFiled.setText(agdaPath);
        contentPanel.add(myFiled, gridBagConstraints {
            gridx = 1;
            gridy = 0;
        })

        contentPanel.add(JPanel(), gridBagConstraints {
            gridx = 2
            gridy = 2
            fill = GridBagConstraints.BOTH
            weightx = 10.0
            weighty = 10.0
        })

        return contentPanel
    }

    public override fun isModified(): Boolean {
        return true
    }

    public override fun apply() {
        AgdaSettings().setAgdaPath(myFiled.getText()!!)
    }

    public override fun reset() {
        myFiled.setText(AgdaSettings().getAgdaPath());
    }

    public override fun disposeUIResources() {

    }

    public override fun getDisplayName(): String? {
        return "Agda"
    }

    public fun getIcon(): Icon? {
        return null;
    }

    public override fun getHelpTopic(): String? {
        return null
    }

}

fun gridBagConstraints(init: GridBagConstraints.() -> Unit): GridBagConstraints {
    val constraints = GridBagConstraints()
    constraints.init()
    return constraints
}