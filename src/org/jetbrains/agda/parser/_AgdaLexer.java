/* The following code was generated by JFlex 1.4.3 on 29/12/12 3:00 PM */

package org.jetbrains.agda.parser;

import java.util.*;
import com.intellij.lexer.*;
import com.intellij.psi.*;
import org.jetbrains.agda.gen.parser.AgdaTokenTypes;
import com.intellij.psi.tree.IElementType;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 29/12/12 3:00 PM from the specification file
 * <tt>Agda.flex</tt>
 */
class _AgdaLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int BLOCK_COMMENT = 4;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\2\1\3\1\0\1\2\23\0\1\2\2\0\1\43\4\0"+
    "\1\11\1\12\3\0\1\4\1\5\1\16\12\1\1\13\1\14\1\0"+
    "\1\15\1\17\35\0\1\6\4\0\1\27\1\0\1\37\1\26\1\22"+
    "\1\41\1\0\1\31\1\24\2\0\1\21\1\33\1\25\1\34\1\36"+
    "\1\0\1\32\1\40\1\23\1\35\1\0\1\30\1\42\2\0\1\10"+
    "\1\0\1\7\u05e2\0\12\0\206\0\12\0\u026c\0\12\0\166\0\12\0"+
    "\166\0\12\0\166\0\12\0\166\0\12\0\167\0\11\0\166\0\12\0"+
    "\166\0\12\0\166\0\12\0\340\0\12\0\166\0\12\0\106\0\12\0"+
    "\u0116\0\12\0\u031f\0\11\0\u046e\0\12\0\46\0\12\0\u012c\0\12\0"+
    "\u0842\0\1\20\155\0\1\44\udd0f\0\12\0\346\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\4\1\1\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\13\1\1\17\1\20\1\0\1\21\1\0\1\22\1\1"+
    "\1\0\1\1\1\23\13\1\1\24\1\25\1\26\1\27"+
    "\13\1\1\30\2\1\1\31\3\1\1\32\4\1\1\33"+
    "\1\1\1\34\4\1\1\35\1\1\1\36\1\37\1\40"+
    "\1\41\1\42\6\1\1\43\2\1\1\44";

  private static int [] zzUnpackAction() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\45\0\112\0\157\0\224\0\271\0\336\0\u0103"+
    "\0\45\0\u0128\0\45\0\u014d\0\45\0\45\0\157\0\45"+
    "\0\157\0\157\0\u0172\0\u0197\0\u01bc\0\u01e1\0\u0206\0\u022b"+
    "\0\u0250\0\u0275\0\u029a\0\u02bf\0\u02e4\0\157\0\45\0\u0309"+
    "\0\45\0\336\0\u032e\0\45\0\u0353\0\u0378\0\u039d\0\u03c2"+
    "\0\u03e7\0\u040c\0\u0431\0\u0456\0\u047b\0\u04a0\0\u04c5\0\u04ea"+
    "\0\u050f\0\u0534\0\45\0\45\0\45\0\157\0\u0559\0\u057e"+
    "\0\u05a3\0\u05c8\0\u05ed\0\u0612\0\u0637\0\u065c\0\u0681\0\u06a6"+
    "\0\u06cb\0\45\0\u06f0\0\u0715\0\157\0\u073a\0\u075f\0\u0784"+
    "\0\157\0\u07a9\0\u07ce\0\u07f3\0\u0818\0\u083d\0\u0862\0\157"+
    "\0\u0887\0\u08ac\0\u08d1\0\u08f6\0\157\0\u091b\0\157\0\157"+
    "\0\157\0\157\0\157\0\u0940\0\u0965\0\u098a\0\u09af\0\u09d4"+
    "\0\u09f9\0\157\0\u0a1e\0\u0a43\0\157";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\2\4\1\22"+
    "\1\23\2\4\1\24\1\4\1\25\1\4\1\26\1\4"+
    "\1\27\1\30\1\31\1\4\1\32\1\33\1\4\1\34"+
    "\1\4\1\35\1\36\45\0\4\37\1\40\40\37\2\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\30\4"+
    "\1\0\1\5\45\0\1\6\42\0\2\41\1\42\42\41"+
    "\2\4\2\0\1\43\1\0\1\4\4\0\1\4\1\0"+
    "\2\4\1\22\25\4\16\0\1\44\32\0\1\45\40\0"+
    "\2\4\2\0\1\4\1\0\1\4\4\0\1\4\1\0"+
    "\5\4\1\46\24\4\2\0\1\4\1\0\1\4\4\0"+
    "\1\4\1\0\10\4\1\47\5\4\1\50\13\4\2\0"+
    "\1\4\1\0\1\4\4\0\1\4\1\0\12\4\1\51"+
    "\17\4\2\0\1\4\1\0\1\4\4\0\1\4\1\0"+
    "\14\4\1\52\15\4\2\0\1\4\1\0\1\4\4\0"+
    "\1\4\1\0\5\4\1\53\24\4\2\0\1\4\1\0"+
    "\1\4\4\0\1\4\1\0\17\4\1\54\12\4\2\0"+
    "\1\4\1\0\1\4\4\0\1\4\1\0\21\4\1\55"+
    "\10\4\2\0\1\4\1\0\1\4\4\0\1\4\1\0"+
    "\17\4\1\56\12\4\2\0\1\4\1\0\1\4\4\0"+
    "\1\4\1\0\17\4\1\57\12\4\2\0\1\4\1\0"+
    "\1\4\4\0\1\4\1\0\7\4\1\60\7\4\1\61"+
    "\12\4\2\0\1\62\1\0\1\4\4\0\1\4\1\0"+
    "\30\4\7\37\1\63\35\37\3\43\1\0\41\43\43\64"+
    "\1\65\1\64\2\4\2\0\1\4\1\0\1\4\4\0"+
    "\1\4\1\0\6\4\1\66\23\4\2\0\1\4\1\0"+
    "\1\4\4\0\1\4\1\0\24\4\1\67\5\4\2\0"+
    "\1\4\1\0\1\4\4\0\1\4\1\0\21\4\1\70"+
    "\10\4\2\0\1\4\1\0\1\4\4\0\1\4\1\0"+
    "\6\4\1\71\23\4\2\0\1\4\1\0\1\4\4\0"+
    "\1\4\1\0\5\4\1\72\24\4\2\0\1\4\1\0"+
    "\1\4\4\0\1\4\1\0\22\4\1\73\7\4\2\0"+
    "\1\4\1\0\1\4\4\0\1\4\1\0\11\4\1\74"+
    "\20\4\2\0\1\4\1\0\1\4\4\0\1\4\1\0"+
    "\5\4\1\75\24\4\2\0\1\4\1\0\1\4\4\0"+
    "\1\4\1\0\23\4\1\76\6\4\2\0\1\4\1\0"+
    "\1\4\4\0\1\4\1\0\10\4\1\77\21\4\2\0"+
    "\1\4\1\0\1\4\4\0\1\4\1\0\5\4\1\100"+
    "\24\4\2\0\1\4\1\0\1\4\4\0\1\4\1\0"+
    "\15\4\1\101\14\4\2\0\1\4\1\0\1\4\1\102"+
    "\3\0\1\4\1\0\32\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\7\4\1\103\22\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\17\4\1\104\12\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\12\4"+
    "\1\105\17\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\15\4\1\106\14\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\17\4\1\107\12\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\20\4\1\110\11\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\10\4"+
    "\1\111\21\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\6\4\1\112\23\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\23\4\1\113\6\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\4\4\1\114\25\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\12\4"+
    "\1\115\17\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\25\4\1\116\4\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\15\4\1\117\14\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\5\4\1\120\24\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\15\4"+
    "\1\121\14\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\4\4\1\122\25\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\20\4\1\123\11\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\6\4\1\124\23\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\11\4"+
    "\1\125\20\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\4\4\1\126\25\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\4\4\1\127\10\4\1\130\14\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\6\4"+
    "\1\131\23\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\11\4\1\132\20\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\5\4\1\133\24\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\4\4\1\134\25\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\15\4"+
    "\1\135\14\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\4\4\1\36\25\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\12\4\1\136\17\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\20\4\1\137\11\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\6\4"+
    "\1\140\23\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\22\4\1\141\7\4\2\0\1\4\1\0\1\4"+
    "\4\0\1\4\1\0\5\4\1\142\24\4\2\0\1\4"+
    "\1\0\1\4\4\0\1\4\1\0\6\4\1\143\23\4"+
    "\2\0\1\4\1\0\1\4\4\0\1\4\1\0\17\4"+
    "\1\144\12\4\2\0\1\4\1\0\1\4\4\0\1\4"+
    "\1\0\15\4\1\145\12\4";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2664];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\10\1\0\5\1\1\11\1\1\1\11\1\1"+
    "\2\11\1\1\1\11\16\1\1\11\1\0\1\11\1\0"+
    "\1\1\1\11\1\0\15\1\3\11\14\1\1\11\43\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private int prevIndentSize;


  _AgdaLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  _AgdaLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 194) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
      return;

    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL.charAt(zzCurrentPosL++);
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL.charAt(zzCurrentPosL++);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 18: 
          { return AgdaTokenTypes.END_OF_LINE_COMMENT;
          }
        case 37: break;
        case 34: 
          { return AgdaTokenTypes.MODULE_KEYWORD;
          }
        case 38: break;
        case 6: 
          { return AgdaTokenTypes.LAMBDA;
          }
        case 39: break;
        case 32: 
          { return AgdaTokenTypes.IMPORT_KEYWORD;
          }
        case 40: break;
        case 19: 
          { return AgdaTokenTypes.IN_KEYWORD;
          }
        case 41: break;
        case 3: 
          { return TokenType.WHITE_SPACE;
          }
        case 42: break;
        case 11: 
          { return AgdaTokenTypes.COLON;
          }
        case 43: break;
        case 12: 
          { return AgdaTokenTypes.SEMICOLON;
          }
        case 44: break;
        case 22: 
          { return AgdaTokenTypes.PRAGMA_OPEN;
          }
        case 45: break;
        case 29: 
          { return AgdaTokenTypes.FIELD_KEYWORD;
          }
        case 46: break;
        case 36: 
          { return AgdaTokenTypes.CONSTRUCTOR_KEYWORD;
          }
        case 47: break;
        case 33: 
          { return AgdaTokenTypes.RECORD_KEYWORD;
          }
        case 48: break;
        case 27: 
          { return AgdaTokenTypes.INFIX_KEYWORD;
          }
        case 49: break;
        case 26: 
          { return AgdaTokenTypes.OPEN_KEYWORD;
          }
        case 50: break;
        case 35: 
          { return AgdaTokenTypes.POSTULATE_KEYWORD;
          }
        case 51: break;
        case 25: 
          { return AgdaTokenTypes.DATA_KEYWORD;
          }
        case 52: break;
        case 9: 
          { return AgdaTokenTypes.LEFT_PAREN;
          }
        case 53: break;
        case 1: 
          { return AgdaTokenTypes.ID;
          }
        case 54: break;
        case 23: 
          { return AgdaTokenTypes.LET_KEYWORD;
          }
        case 55: break;
        case 30: 
          { return AgdaTokenTypes.INFIXL_KEYWORD;
          }
        case 56: break;
        case 13: 
          { return AgdaTokenTypes.ASSIGNMENT;
          }
        case 57: break;
        case 10: 
          { return AgdaTokenTypes.RIGHT_PAREN;
          }
        case 58: break;
        case 2: 
          { return AgdaTokenTypes.NUMBER;
          }
        case 59: break;
        case 4: 
          { return AgdaTokenTypes.VIRTUAL_RIGHT_PAREN;
          }
        case 60: break;
        case 31: 
          { return AgdaTokenTypes.INFIXR_KEYWORD;
          }
        case 61: break;
        case 28: 
          { return AgdaTokenTypes.WHERE_KEYWORD;
          }
        case 62: break;
        case 8: 
          { return AgdaTokenTypes.LEFT_BRACE;
          }
        case 63: break;
        case 24: 
          { return AgdaTokenTypes.PRAGMA_CLOSE;
          }
        case 64: break;
        case 21: 
          { yybegin(BLOCK_COMMENT); return AgdaTokenTypes.COMMENT;
          }
        case 65: break;
        case 16: 
          { return AgdaTokenTypes.COMMENT;
          }
        case 66: break;
        case 5: 
          { return AgdaTokenTypes.DOT;
          }
        case 67: break;
        case 14: 
          { return AgdaTokenTypes.ARROW;
          }
        case 68: break;
        case 17: 
          { yybegin(YYINITIAL);
        int indentSize = yytext().length() - 2;
        yypushback(1);
        IElementType result;
        if (indentSize > prevIndentSize) {
          result = AgdaTokenTypes.VIRTUAL_LEFT_PAREN;
        } else if (indentSize < prevIndentSize) {
          result = AgdaTokenTypes.VIRTUAL_RIGHT_PAREN;
        } else {
          result = AgdaTokenTypes.VIRTUAL_SEMICOLON;
        }
        prevIndentSize = indentSize;
        return result;
          }
        case 69: break;
        case 7: 
          { return AgdaTokenTypes.RIGHT_BRACE;
          }
        case 70: break;
        case 15: 
          { return AgdaTokenTypes.FORALL;
          }
        case 71: break;
        case 20: 
          { yybegin(YYINITIAL); return AgdaTokenTypes.COMMENT;
          }
        case 72: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
