package org.agda.compiler;

import com.intellij.compiler.CompilerConfiguration;
import com.intellij.compiler.impl.CompilerUtil;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.compiler.TranslatingCompiler;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Chunk;
import org.agda.AgdaFileType;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.*;

public final class AgdaCompiler implements TranslatingCompiler {
    private final Project project;

    public AgdaCompiler(Project project) {
        this.project = project;
    }

    public boolean isCompilableFile(VirtualFile file, CompileContext context) {
        return isCompilableFile(file);
    }

    public static boolean isCompilableFile(VirtualFile file) {
        FileType fileType = FileTypeManager.getInstance().getFileTypeByFile(file);
        return AgdaFileType.INSTANCE.equals(fileType);
    }

    public void compile(CompileContext context, Chunk<Module> moduleChunk, VirtualFile[] files, OutputSink sink) {
        for (VirtualFile file: files) {

        }
    }

    @NotNull
    public String getDescription() {
        return "Agda compiler";
    }

    public boolean validateConfiguration(CompileScope compileScope) {
        VirtualFile[] files = compileScope.getFiles(AgdaFileType.INSTANCE, true);
        if (files.length == 0)
            return true;



        return true;
    }
}
