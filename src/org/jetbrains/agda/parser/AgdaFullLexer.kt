package org.jetbrains.agda.parser

import com.intellij.lexer.Lexer
import com.intellij.psi.tree.IElementType
import com.intellij.lexer.LexerPosition
import java.util.ArrayList
import com.intellij.lexer.LexerBase
import java.util.LinkedList
import java.util.Collections
import java.util.HashSet
import java.util.Arrays
import org.jetbrains.agda.gen.parser.AgdaTokenTypes
import com.intellij.psi.TokenType

/**
 * @author Evgeny.Kurbatsky
 */
class AgdaFullLexer() : LexerBase() {
    val indentTokens = HashSet<IElementType>(Arrays.asList(
            AgdaTokenTypes.WHERE_KEYWORD,
            AgdaTokenTypes.FIELD_KEYWORD,
            AgdaTokenTypes.POSTULATE_KEYWORD))
    val lexer = AgdaHighlightLexer();
    val tokenStarts = ArrayList<Int>();
    val tokenEnds = ArrayList<Int>();
    val lexerStates = ArrayList<Int>();
    val tokenTypes = ArrayList<IElementType>();
    var currentPosition = 0;

    public override fun start(buffer: CharSequence?, startOffset: Int, endOffset: Int, initialState: Int) {
        lexer.start(buffer, startOffset, endOffset, initialState)
        val indentStack = LinkedList<Int>(Collections.singleton(0))
        var recordIndent : Boolean = false
        var tokenType = lexer.getTokenType()
        while (tokenType != null) {

            addToken(lexer.getTokenStart(), lexer.getTokenEnd(), lexer.getState(), tokenType!!)
            val tokenSize : Int = lexer.getTokenEnd() - lexer.getTokenStart() - 1

            lexer.advance();

            if (tokenType == TokenType.NEW_LINE_INDENT) {
                val nextToken = lexer.getTokenType()
                if (nextToken != null && !AgdaTokenSets.WHITESPACES.contains(nextToken) &&
                    !AgdaTokenSets.COMMENTS.contains(nextToken)) {

                    if (tokenSize == indentStack.last!!) {
                        addToken(lexer.getTokenStart(), lexer.getTokenStart(), 0, AgdaTokenTypes.VIRTUAL_SEMICOLON)
                    } else {
                        if (recordIndent && (tokenSize > indentStack.last!!)) {
                            indentStack.addLast(tokenSize);
                            addToken(lexer.getTokenStart(), lexer.getTokenStart(), 0, AgdaTokenTypes.VIRTUAL_LEFT_PAREN)
                        }
                        if (tokenSize < indentStack.last!!) {
                            while (tokenSize < indentStack.last!!) {
                                indentStack.removeLast();
                                addToken(lexer.getTokenStart(), lexer.getTokenStart(), 0, AgdaTokenTypes.VIRTUAL_RIGHT_PAREN)
                            }
                            addToken(lexer.getTokenStart(), lexer.getTokenStart(), 0, AgdaTokenTypes.VIRTUAL_SEMICOLON)
                        }
                    }
                }
            }

            if (!AgdaTokenSets.COMMENTS.contains(tokenType) && !AgdaTokenSets.WHITESPACES.contains(tokenType)) {
                recordIndent = false;
            }

            if (indentTokens.contains(tokenType)) {
                recordIndent = true;
            }

            tokenType = lexer.getTokenType()
        }
        while (indentStack.size > 1) {
            addToken(tokenEnds[tokenEnds.size - 1], tokenEnds[tokenEnds.size - 1], 0, AgdaTokenTypes.VIRTUAL_RIGHT_PAREN)
            indentStack.removeLast()
        }
    }

    fun addToken(start : Int, end : Int, state : Int, aType : IElementType) {
        tokenStarts.add(start)
        tokenEnds.add(end)
        lexerStates.add(state)
        tokenTypes.add(aType)
    }

    public override fun getState(): Int {
        return lexerStates[currentPosition];
    }

    public override fun getTokenType(): IElementType? {
        return if (tokenTypes.size > currentPosition) tokenTypes[currentPosition] else null
    }

    public override fun getTokenStart(): Int {
        return tokenStarts[currentPosition];
    }

    public override fun getTokenEnd(): Int {
        return tokenEnds[currentPosition]
    }
    public override fun advance() {
        currentPosition++;
    }

    public override fun restore(position: LexerPosition?) {
        throw UnsupportedOperationException()
    }
    public override fun getBufferSequence(): CharSequence? {
        return lexer.getBufferSequence()
    }

    public override fun getBufferEnd(): Int {
        return lexer.getBufferEnd()
    }

}