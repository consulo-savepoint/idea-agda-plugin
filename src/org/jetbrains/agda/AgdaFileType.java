package org.jetbrains.agda;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author max
 */
public class AgdaFileType extends LanguageFileType {
    public static final LanguageFileType INSTANCE = new AgdaFileType();
    @NonNls
    public static final String DEFAULT_EXTENSION = "agda";
    @NonNls
    public static final String DOT_DEFAULT_EXTENSION = "." + DEFAULT_EXTENSION;
    private Icon myIcon;

    private AgdaFileType() {
        super(AgdaLanguage.INSTANCE);
        myIcon = IconLoader.findIcon("/org/jetbrains/agda/agda_16.png");
    }

    @NotNull
    public String getName() {
        return "Agda file";
    }

    @NotNull
    public String getDescription() {
        return "Agda file";
    }

    @NotNull
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return myIcon;
    }

    public String getCharset(@NotNull VirtualFile file, final byte[] content) {
        return "UTF-8";
    }
}