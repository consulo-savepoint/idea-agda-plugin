// This is a generated file. Not intended for manual editing.
package org.jetbrains.agda.gen.parser;

import org.jetbrains.annotations.*;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static org.jetbrains.agda.gen.parser.AgdaTokenTypes.*;
import static org.jetbrains.agda.parser.AgdaParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class AgdaParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("org.jetbrains.agda.gen.parser.AgdaParser");

  @NotNull
  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this);
    if (root_ == APPLICATION) {
      result_ = application(builder_, level_ + 1);
    }
    else if (root_ == CONSTRUCTOR) {
      result_ = constructor(builder_, level_ + 1);
    }
    else if (root_ == CONSTRUCTORS) {
      result_ = constructors(builder_, level_ + 1);
    }
    else if (root_ == DATA_DECLARATION) {
      result_ = data_declaration(builder_, level_ + 1);
    }
    else if (root_ == EXPRESSION) {
      result_ = expression(builder_, level_ + 1);
    }
    else if (root_ == FQ_NAME) {
      result_ = fqName(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_DECLARATION) {
      result_ = function_declaration(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_TYPE) {
      result_ = function_type(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_TYPE_DECLARATION) {
      result_ = function_type_declaration(builder_, level_ + 1);
    }
    else if (root_ == IMPORT_NAMES) {
      result_ = import_names(builder_, level_ + 1);
    }
    else if (root_ == MODULE_DECLARATION) {
      result_ = module_declaration(builder_, level_ + 1);
    }
    else if (root_ == MODULE_IMPORT) {
      result_ = module_import(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      result_ = parse_root_(root_, builder_, level_);
      while (builder_.getTokenType() != null) {
        builder_.advanceLexer();
      }
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return root(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // (id_or_pareses application) | id_or_pareses
  public static boolean application(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "application")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<application>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<application>");
    result_ = application_0(builder_, level_ + 1);
    if (!result_) result_ = id_or_pareses(builder_, level_ + 1);
    if (result_) {
      marker_.done(APPLICATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (id_or_pareses application)
  private static boolean application_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "application_0")) return false;
    return application_0_0(builder_, level_ + 1);
  }

  // id_or_pareses application
  private static boolean application_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "application_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = id_or_pareses(builder_, level_ + 1);
    result_ = result_ && application(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // expression ":" expression
  public static boolean constructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructor")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<constructor>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<constructor>");
    result_ = expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // constructor (VIRTUAL_SEMICOLON constructor) *
  public static boolean constructors(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructors")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<constructors>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<constructors>");
    result_ = constructor(builder_, level_ + 1);
    result_ = result_ && constructors_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONSTRUCTORS);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // (VIRTUAL_SEMICOLON constructor) *
  private static boolean constructors_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructors_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!constructors_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "constructors_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (VIRTUAL_SEMICOLON constructor)
  private static boolean constructors_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructors_1_0")) return false;
    return constructors_1_0_0(builder_, level_ + 1);
  }

  // VIRTUAL_SEMICOLON constructor
  private static boolean constructors_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructors_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_SEMICOLON);
    result_ = result_ && constructor(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "data" id ":" expression "where" (VIRTUAL_LEFT_PAREN constructors VIRTUAL_RIGHT_PAREN) ?
  public static boolean data_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration")) return false;
    if (!nextTokenIs(builder_, DATA_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, DATA_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, ID));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COLON)) && result_;
    result_ = pinned_ && report_error_(builder_, expression(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, WHERE_KEYWORD)) && result_;
    result_ = pinned_ && data_declaration_5(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(DATA_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // (VIRTUAL_LEFT_PAREN constructors VIRTUAL_RIGHT_PAREN) ?
  private static boolean data_declaration_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration_5")) return false;
    data_declaration_5_0(builder_, level_ + 1);
    return true;
  }

  // (VIRTUAL_LEFT_PAREN constructors VIRTUAL_RIGHT_PAREN)
  private static boolean data_declaration_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration_5_0")) return false;
    return data_declaration_5_0_0(builder_, level_ + 1);
  }

  // VIRTUAL_LEFT_PAREN constructors VIRTUAL_RIGHT_PAREN
  private static boolean data_declaration_5_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration_5_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_LEFT_PAREN);
    result_ = result_ && constructors(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, VIRTUAL_RIGHT_PAREN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // module_declaration | module_import | data_declaration |
  //       function_type_declaration | function_declaration | VIRTUAL_SEMICOLON
  static boolean declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "declaration")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = module_declaration(builder_, level_ + 1);
    if (!result_) result_ = module_import(builder_, level_ + 1);
    if (!result_) result_ = data_declaration(builder_, level_ + 1);
    if (!result_) result_ = function_type_declaration(builder_, level_ + 1);
    if (!result_) result_ = function_declaration(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, VIRTUAL_SEMICOLON);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // function_type | application
  public static boolean expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<expression>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<expression>");
    result_ = function_type(builder_, level_ + 1);
    if (!result_) result_ = application(builder_, level_ + 1);
    if (result_) {
      marker_.done(EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // id ('.' id) *
  public static boolean fqName(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fqName")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ID);
    result_ = result_ && fqName_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(FQ_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // ('.' id) *
  private static boolean fqName_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fqName_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!fqName_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "fqName_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ('.' id)
  private static boolean fqName_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fqName_1_0")) return false;
    return fqName_1_0_0(builder_, level_ + 1);
  }

  // '.' id
  private static boolean fqName_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fqName_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && consumeToken(builder_, ID);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // expression "=" expression
  public static boolean function_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<function declaration>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function declaration>");
    result_ = expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ASSIGNMENT);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(FUNCTION_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // application "->" application
  public static boolean function_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<function type>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function type>");
    result_ = application(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "->");
    result_ = result_ && application(builder_, level_ + 1);
    if (result_) {
      marker_.done(FUNCTION_TYPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // expression ":" expression
  public static boolean function_type_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_declaration")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<function type declaration>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function type declaration>");
    result_ = expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(FUNCTION_TYPE_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ("(" expression ")") | id
  static boolean id_or_pareses(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "id_or_pareses")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = id_or_pareses_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, ID);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("(" expression ")")
  private static boolean id_or_pareses_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "id_or_pareses_0")) return false;
    return id_or_pareses_0_0(builder_, level_ + 1);
  }

  // "(" expression ")"
  private static boolean id_or_pareses_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "id_or_pareses_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // fqName (';' fqName) *
  public static boolean import_names(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_names")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = fqName(builder_, level_ + 1);
    result_ = result_ && import_names_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(IMPORT_NAMES);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (';' fqName) *
  private static boolean import_names_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_names_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!import_names_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "import_names_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (';' fqName)
  private static boolean import_names_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_names_1_0")) return false;
    return import_names_1_0_0(builder_, level_ + 1);
  }

  // ';' fqName
  private static boolean import_names_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_names_1_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SEMICOLON);
    result_ = result_ && fqName(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // 'module' fqName "where"
  public static boolean module_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_declaration")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<module declaration>");
    result_ = consumeToken(builder_, "module");
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, fqName(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, WHERE_KEYWORD) && result_;
    if (result_ || pinned_) {
      marker_.done(MODULE_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // 'open' ? 'import' fqName ('using' '(' import_names ')') ?
  public static boolean module_import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<module import>");
    result_ = module_import_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "import");
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, fqName(builder_, level_ + 1));
    result_ = pinned_ && module_import_3(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(MODULE_IMPORT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // 'open' ?
  private static boolean module_import_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_0")) return false;
    consumeToken(builder_, "open");
    return true;
  }

  // ('using' '(' import_names ')') ?
  private static boolean module_import_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_3")) return false;
    module_import_3_0(builder_, level_ + 1);
    return true;
  }

  // ('using' '(' import_names ')')
  private static boolean module_import_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_3_0")) return false;
    return module_import_3_0_0(builder_, level_ + 1);
  }

  // 'using' '(' import_names ')'
  private static boolean module_import_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_3_0_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, "using");
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && import_names(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // declaration *
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "root");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

}
