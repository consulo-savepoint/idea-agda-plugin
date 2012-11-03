package org.agda;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class AgdaFileImpl extends PsiFileBase {

    public AgdaFileImpl(@NotNull FileViewProvider provider) {
        super(provider, AgdaLanguage.INSTANCE);
    }

    @NotNull
    public FileType getFileType() {
        return AgdaFileType.INSTANCE;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        visitor.visitFile(this);
    }
}