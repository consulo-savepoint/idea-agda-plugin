package org.jetbrains.agda.parser

import com.intellij.psi.impl.cache.impl.BaseFilterLexer
import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import com.intellij.ide.highlighter.custom.AbstractCustomLexer

/**
 * @author Evgeny.Kurbatsky
 */
class Lexer(isHighLight : Boolean) : LexerBase() {
    public override fun start(buffer: CharSequence?, startOffset: Int, endOffset: Int, initialState: Int) {
        throw UnsupportedOperationException()
    }
    public override fun getState(): Int {
        throw UnsupportedOperationException()
    }
    public override fun getTokenType(): IElementType? {
        throw UnsupportedOperationException()
    }
    public override fun getTokenStart(): Int {
        throw UnsupportedOperationException()
    }
    public override fun getTokenEnd(): Int {
        throw UnsupportedOperationException()
    }
    public override fun advance() {
        throw UnsupportedOperationException()
    }
    public override fun getBufferSequence(): CharSequence? {
        throw UnsupportedOperationException()
    }
    public override fun getBufferEnd(): Int {
        throw UnsupportedOperationException()
    }


}