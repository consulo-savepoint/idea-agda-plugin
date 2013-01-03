package org.jetbrains.agda.scope

import org.jetbrains.agda.psi.FqName
import com.intellij.psi.PsiElement
import java.util.Arrays
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile

/**
 * @author Evgeny.Kurbatsky
 */
public class AgdaGlobalScope() {

        public fun find(element : FqName) : PsiElement? {
            val text : String = element.getText()!!
            val path : Array<String> = text.split("\\.")
            var containingDirectory : PsiDirectory? = element.getContainingFile()?.getContainingDirectory();
            while (containingDirectory!!.getName() != "src") {
                containingDirectory = containingDirectory?.getParent();
            }
            val file : PsiFile? = findPath(containingDirectory, path, 0)
            return file
        }

        private fun findPath(directory : PsiDirectory?, path : Array<String>, index : Int) : PsiFile? {
            var name : String? = path.get(index)
            if (path.size == index + 1)
            {
                for (file : PsiFile? in directory?.getFiles()!!)
                {
                    var fileName : String? = file?.getName()
                    if (fileName?.equals(name + ".agda")!!)
                    {
                        return file
                    }

                }
            }
            else
            {
                for (psiDirectory : PsiDirectory? in directory?.getSubdirectories()!!)
                {
                    var fileName : String? = psiDirectory?.getName()
                    if (fileName?.equals(name)!!)
                    {
                        return findPath(psiDirectory, path, index + 1)
                    }

                }
            }
            return null
        }

}
