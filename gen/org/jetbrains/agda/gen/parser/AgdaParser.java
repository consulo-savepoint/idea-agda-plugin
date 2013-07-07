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
    if (root_ == ABSURD_EXPRESSION) {
      result_ = absurd_expression(builder_, level_ + 1);
    }
    else if (root_ == ABSURD_FUNCTION) {
      result_ = absurd_function(builder_, level_ + 1);
    }
    else if (root_ == APPLICATION) {
      result_ = application(builder_, level_ + 1);
    }
    else if (root_ == ARROW_EXPRESSION) {
      result_ = arrow_expression(builder_, level_ + 1);
    }
    else if (root_ == AS_NAME) {
      result_ = as_name(builder_, level_ + 1);
    }
    else if (root_ == BINDING) {
      result_ = binding(builder_, level_ + 1);
    }
    else if (root_ == BINDINGS) {
      result_ = bindings(builder_, level_ + 1);
    }
    else if (root_ == BUILD_IN_PRAGMA) {
      result_ = build_in_pragma(builder_, level_ + 1);
    }
    else if (root_ == COMPILED_PRAGMA) {
      result_ = compiled_pragma(builder_, level_ + 1);
    }
    else if (root_ == COMPILED_TYPE_PRAGMA) {
      result_ = compiled_type_pragma(builder_, level_ + 1);
    }
    else if (root_ == CONSTRUCTORS) {
      result_ = constructors(builder_, level_ + 1);
    }
    else if (root_ == DATA_DECLARATION) {
      result_ = data_declaration(builder_, level_ + 1);
    }
    else if (root_ == EXPLICIT_TELESCOPE) {
      result_ = explicit_telescope(builder_, level_ + 1);
    }
    else if (root_ == EXPRESSION) {
      result_ = expression(builder_, level_ + 1);
    }
    else if (root_ == FORALL_EXPRESSION) {
      result_ = forall_expression(builder_, level_ + 1);
    }
    else if (root_ == FQ_NAME) {
      result_ = fqName(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_DECLARATION) {
      result_ = function_declaration(builder_, level_ + 1);
    }
    else if (root_ == FUNCTION_TYPE_DECLARATION) {
      result_ = function_type_declaration(builder_, level_ + 1);
    }
    else if (root_ == GOAL_EXPRESSION) {
      result_ = goal_expression(builder_, level_ + 1);
    }
    else if (root_ == IMPLICIT_TELESCOPE) {
      result_ = implicit_telescope(builder_, level_ + 1);
    }
    else if (root_ == IMPORT_NAME) {
      result_ = import_name(builder_, level_ + 1);
    }
    else if (root_ == INFIX) {
      result_ = infix(builder_, level_ + 1);
    }
    else if (root_ == LAMBDA_EXPRESSION) {
      result_ = lambda_expression(builder_, level_ + 1);
    }
    else if (root_ == LET_EXPRESSION) {
      result_ = let_expression(builder_, level_ + 1);
    }
    else if (root_ == LHS) {
      result_ = lhs(builder_, level_ + 1);
    }
    else if (root_ == MODULE_ARGUMENTS) {
      result_ = module_arguments(builder_, level_ + 1);
    }
    else if (root_ == MODULE_DECLARATION) {
      result_ = module_declaration(builder_, level_ + 1);
    }
    else if (root_ == MODULE_IMPORT) {
      result_ = module_import(builder_, level_ + 1);
    }
    else if (root_ == MUTUAL) {
      result_ = mutual(builder_, level_ + 1);
    }
    else if (root_ == NAME_DECLARATION) {
      result_ = name_declaration(builder_, level_ + 1);
    }
    else if (root_ == NUMBER_EXPRESSION) {
      result_ = number_expression(builder_, level_ + 1);
    }
    else if (root_ == OPEN) {
      result_ = open(builder_, level_ + 1);
    }
    else if (root_ == PARENTHESIS_EXPRESSION) {
      result_ = parenthesis_expression(builder_, level_ + 1);
    }
    else if (root_ == POSTULATE) {
      result_ = postulate(builder_, level_ + 1);
    }
    else if (root_ == POSTULATE_BINDINGS) {
      result_ = postulate_bindings(builder_, level_ + 1);
    }
    else if (root_ == PRAGMA) {
      result_ = pragma(builder_, level_ + 1);
    }
    else if (root_ == PRAGMA_STRING) {
      result_ = pragma_string(builder_, level_ + 1);
    }
    else if (root_ == RECORD_CONSTRUCTOR) {
      result_ = record_constructor(builder_, level_ + 1);
    }
    else if (root_ == RECORD_DECLARATION) {
      result_ = record_declaration(builder_, level_ + 1);
    }
    else if (root_ == RECORD_FIELD) {
      result_ = record_field(builder_, level_ + 1);
    }
    else if (root_ == RENAMING) {
      result_ = renaming(builder_, level_ + 1);
    }
    else if (root_ == SUBSTITUTE_IMPLISIT) {
      result_ = substitute_implisit(builder_, level_ + 1);
    }
    else if (root_ == TELE_ARROW) {
      result_ = tele_arrow(builder_, level_ + 1);
    }
    else if (root_ == TELESCOPE) {
      result_ = telescope(builder_, level_ + 1);
    }
    else if (root_ == TYPE_SIGNATURE) {
      result_ = type_signature(builder_, level_ + 1);
    }
    else if (root_ == TYPE_SIGNATURES) {
      result_ = type_signatures(builder_, level_ + 1);
    }
    else if (root_ == TYPED_UNTYPED_BINDING) {
      result_ = typed_untyped_binding(builder_, level_ + 1);
    }
    else if (root_ == USING_OR_HIDING) {
      result_ = using_or_hiding(builder_, level_ + 1);
    }
    else if (root_ == WHERE_EXPRESSION) {
      result_ = where_expression(builder_, level_ + 1);
    }
    else if (root_ == WHERE_PART) {
      result_ = where_part(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, null);
      result_ = parse_root_(root_, builder_, level_);
      exitErrorRecordingSection(builder_, level_, result_, true, _SECTION_RECOVER_, TOKEN_ADVANCER);
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return root(builder_, level_ + 1);
  }

  private static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    TokenSet.create(ABSURD_EXPRESSION, APPLICATION, EXPRESSION, GOAL_EXPRESSION,
      LAMBDA_EXPRESSION, NUMBER_EXPRESSION, PARENTHESIS_EXPRESSION, SUBSTITUTE_IMPLISIT),
    TokenSet.create(EXPLICIT_TELESCOPE, IMPLICIT_TELESCOPE, TELESCOPE),
  };

  public static boolean type_extends_(IElementType child_, IElementType parent_) {
    for (TokenSet set : EXTENDS_SETS_) {
      if (set.contains(child_) && set.contains(parent_)) return true;
    }
    return false;
  }

  /* ********************************************************** */
  // "(" ")"
  public static boolean absurd_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "absurd_expression")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    if (result_) {
      marker_.done(ABSURD_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // expression
  public static boolean absurd_function(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "absurd_function")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<absurd function>");
    result_ = expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(ABSURD_FUNCTION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // atom_expr maybe_application
  public static boolean application(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "application")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<application>");
    result_ = atom_expr(builder_, level_ + 1);
    result_ = result_ && maybe_application(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), APPLICATION)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(APPLICATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // maybe_application ARROW expression
  public static boolean arrow_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrow_expression")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<arrow expression>");
    result_ = maybe_application(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARROW);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(ARROW_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // "as" id
  public static boolean as_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "as_name")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<as name>");
    result_ = consumeToken(builder_, "as");
    result_ = result_ && consumeToken(builder_, ID);
    if (result_) {
      marker_.done(AS_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // absurd_expression | parenthesis_expression | goal_expression | substitute_implisit | fqName | number_expression
  static boolean atom_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "atom_expr")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = absurd_expression(builder_, level_ + 1);
    if (!result_) result_ = parenthesis_expression(builder_, level_ + 1);
    if (!result_) result_ = goal_expression(builder_, level_ + 1);
    if (!result_) result_ = substitute_implisit(builder_, level_ + 1);
    if (!result_) result_ = fqName(builder_, level_ + 1);
    if (!result_) result_ = number_expression(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "{" ids (":" expression)? "}" | "(" ids (":" expression)? ")"
  public static boolean binding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, LEFT_BRACE)
        && replaceVariants(builder_, 2, "<binding>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<binding>");
    result_ = binding_0(builder_, level_ + 1);
    if (!result_) result_ = binding_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(BINDING);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "{" ids (":" expression)? "}"
  private static boolean binding_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && ids(builder_, level_ + 1);
    result_ = result_ && binding_0_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (":" expression)?
  private static boolean binding_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding_0_2")) return false;
    binding_0_2_0(builder_, level_ + 1);
    return true;
  }

  // ":" expression
  private static boolean binding_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding_0_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && expression(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "(" ids (":" expression)? ")"
  private static boolean binding_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && ids(builder_, level_ + 1);
    result_ = result_ && binding_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (":" expression)?
  private static boolean binding_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding_1_2")) return false;
    binding_1_2_0(builder_, level_ + 1);
    return true;
  }

  // ":" expression
  private static boolean binding_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "binding_1_2_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && expression(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // binding *
  public static boolean bindings(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "bindings")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<bindings>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!binding(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "bindings");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(BINDINGS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // "BUILTIN" name_declaration name_declaration
  public static boolean build_in_pragma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "build_in_pragma")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<build in pragma>");
    result_ = consumeToken(builder_, "BUILTIN");
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, name_declaration(builder_, level_ + 1));
    result_ = pinned_ && name_declaration(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(BUILD_IN_PRAGMA);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "COMPILED" name_declaration pragma_string
  public static boolean compiled_pragma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compiled_pragma")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<compiled pragma>");
    result_ = consumeToken(builder_, "COMPILED");
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, name_declaration(builder_, level_ + 1));
    result_ = pinned_ && pragma_string(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(COMPILED_PRAGMA);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "COMPILED_TYPE" name_declaration pragma_string
  public static boolean compiled_type_pragma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compiled_type_pragma")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<compiled type pragma>");
    result_ = consumeToken(builder_, "COMPILED_TYPE");
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, name_declaration(builder_, level_ + 1));
    result_ = pinned_ && pragma_string(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(COMPILED_TYPE_PRAGMA);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // type_signature (VIRTUAL_SEMICOLON type_signature) *
  public static boolean constructors(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructors")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = type_signature(builder_, level_ + 1);
    result_ = result_ && constructors_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(CONSTRUCTORS);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (VIRTUAL_SEMICOLON type_signature) *
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

  // VIRTUAL_SEMICOLON type_signature
  private static boolean constructors_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "constructors_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_SEMICOLON);
    result_ = result_ && type_signature(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "data" name_declaration bindings ":" expression "where" (VIRTUAL_LEFT_PAREN constructors VIRTUAL_RIGHT_PAREN) ?
  public static boolean data_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration")) return false;
    if (!nextTokenIs(builder_, DATA_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, DATA_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, name_declaration(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, bindings(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COLON)) && result_;
    result_ = pinned_ && report_error_(builder_, expression(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, WHERE_KEYWORD)) && result_;
    result_ = pinned_ && data_declaration_6(builder_, level_ + 1) && result_;
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
  private static boolean data_declaration_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration_6")) return false;
    data_declaration_6_0(builder_, level_ + 1);
    return true;
  }

  // VIRTUAL_LEFT_PAREN constructors VIRTUAL_RIGHT_PAREN
  private static boolean data_declaration_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_declaration_6_0")) return false;
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
  // VIRTUAL_SEMICOLON? (record_declaration | record_field | record_constructor | recovering_declaration)
  static boolean declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "declaration")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = declaration_0(builder_, level_ + 1);
    result_ = result_ && declaration_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // VIRTUAL_SEMICOLON?
  private static boolean declaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "declaration_0")) return false;
    consumeToken(builder_, VIRTUAL_SEMICOLON);
    return true;
  }

  // record_declaration | record_field | record_constructor | recovering_declaration
  private static boolean declaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "declaration_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = record_declaration(builder_, level_ + 1);
    if (!result_) result_ = record_field(builder_, level_ + 1);
    if (!result_) result_ = record_constructor(builder_, level_ + 1);
    if (!result_) result_ = recovering_declaration(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "(" type_signature ")"
  public static boolean explicit_telescope(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "explicit_telescope")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && type_signature(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    if (result_) {
      marker_.done(EXPLICIT_TELESCOPE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // let_expression | tele_arrow | lambda_expression | forall_expression | arrow_expression | application | atom_expr
  public static boolean expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<expression>");
    result_ = let_expression(builder_, level_ + 1);
    if (!result_) result_ = tele_arrow(builder_, level_ + 1);
    if (!result_) result_ = lambda_expression(builder_, level_ + 1);
    if (!result_) result_ = forall_expression(builder_, level_ + 1);
    if (!result_) result_ = arrow_expression(builder_, level_ + 1);
    if (!result_) result_ = application(builder_, level_ + 1);
    if (!result_) result_ = atom_expr(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), EXPRESSION)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // 'forall' typed_untyped_bindings ARROW expression
  public static boolean forall_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forall_expression")) return false;
    if (!nextTokenIs(builder_, FORALL)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, FORALL);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, typed_untyped_bindings(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, ARROW)) && result_;
    result_ = pinned_ && expression(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(FORALL_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
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

  // '.' id
  private static boolean fqName_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fqName_1_0")) return false;
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
  // lhs "=" where_expression
  public static boolean function_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_declaration")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<function declaration>");
    result_ = lhs(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ASSIGNMENT);
    pinned_ = result_; // pin = 2
    result_ = result_ && where_expression(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(FUNCTION_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // name_declaration ":" expression
  public static boolean function_type_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "function_type_declaration")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = name_declaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 2
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(FUNCTION_TYPE_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "{" "!!" "}"
  public static boolean goal_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "goal_expression")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && consumeToken(builder_, "!!");
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    if (result_) {
      marker_.done(GOAL_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // name_declaration+
  static boolean ids(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ids")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = name_declaration(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!name_declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "ids");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "{" type_signature "}"
  public static boolean implicit_telescope(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "implicit_telescope")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && type_signature(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    if (result_) {
      marker_.done(IMPLICIT_TELESCOPE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // "module"? fqName
  public static boolean import_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_name")) return false;
    if (!nextTokenIs(builder_, MODULE_KEYWORD) && !nextTokenIs(builder_, ID)
        && replaceVariants(builder_, 2, "<import name>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<import name>");
    result_ = import_name_0(builder_, level_ + 1);
    result_ = result_ && fqName(builder_, level_ + 1);
    if (result_) {
      marker_.done(IMPORT_NAME);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "module"?
  private static boolean import_name_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_name_0")) return false;
    consumeToken(builder_, MODULE_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // import_name (";" import_name) *
  static boolean import_names(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_names")) return false;
    if (!nextTokenIs(builder_, MODULE_KEYWORD) && !nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = import_name(builder_, level_ + 1);
    result_ = result_ && import_names_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (";" import_name) *
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

  // ";" import_name
  private static boolean import_names_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_names_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SEMICOLON);
    result_ = result_ && import_name(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("infix" | "infixl" | "infixr") NUMBER name_declaration *
  public static boolean infix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "infix")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<infix>");
    result_ = infix_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, NUMBER));
    result_ = pinned_ && infix_2(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(INFIX);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // "infix" | "infixl" | "infixr"
  private static boolean infix_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "infix_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, INFIX_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, INFIXL_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, INFIXR_KEYWORD);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // name_declaration *
  private static boolean infix_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "infix_2")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!name_declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "infix_2");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // ("\\" | "?") (("(" ids ":" expression ")") | ids) ARROW expression
  public static boolean lambda_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expression")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<lambda expression>");
    result_ = lambda_expression_0(builder_, level_ + 1);
    result_ = result_ && lambda_expression_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARROW);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(LAMBDA_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "\\" | "?"
  private static boolean lambda_expression_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expression_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LAMBDA);
    if (!result_) result_ = consumeToken(builder_, "?");
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // ("(" ids ":" expression ")") | ids
  private static boolean lambda_expression_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expression_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = lambda_expression_1_0(builder_, level_ + 1);
    if (!result_) result_ = ids(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "(" ids ":" expression ")"
  private static boolean lambda_expression_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expression_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && ids(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
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
  // "let" name_declaration "=" expression "in" expression
  public static boolean let_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "let_expression")) return false;
    if (!nextTokenIs(builder_, LET_KEYWORD)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LET_KEYWORD);
    result_ = result_ && name_declaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ASSIGNMENT);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IN_KEYWORD);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(LET_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // ("..." "|" expression) | expression
  public static boolean lhs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lhs")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<lhs>");
    result_ = lhs_0(builder_, level_ + 1);
    if (!result_) result_ = expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(LHS);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // "..." "|" expression
  private static boolean lhs_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lhs_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, "...");
    result_ = result_ && consumeToken(builder_, "|");
    result_ = result_ && expression(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // application | atom_expr
  static boolean maybe_application(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "maybe_application")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = application(builder_, level_ + 1);
    if (!result_) result_ = atom_expr(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // expression *
  public static boolean module_arguments(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_arguments")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<module arguments>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!expression(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "module_arguments");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(MODULE_ARGUMENTS);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  /* ********************************************************** */
  // 'module' fqName bindings "where" (VIRTUAL_LEFT_PAREN declaration+ VIRTUAL_RIGHT_PAREN)?
  public static boolean module_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_declaration")) return false;
    if (!nextTokenIs(builder_, MODULE_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, MODULE_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, fqName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, bindings(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, WHERE_KEYWORD)) && result_;
    result_ = pinned_ && module_declaration_4(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(MODULE_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // (VIRTUAL_LEFT_PAREN declaration+ VIRTUAL_RIGHT_PAREN)?
  private static boolean module_declaration_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_declaration_4")) return false;
    module_declaration_4_0(builder_, level_ + 1);
    return true;
  }

  // VIRTUAL_LEFT_PAREN declaration+ VIRTUAL_RIGHT_PAREN
  private static boolean module_declaration_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_declaration_4_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_LEFT_PAREN);
    result_ = result_ && module_declaration_4_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, VIRTUAL_RIGHT_PAREN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // declaration+
  private static boolean module_declaration_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_declaration_4_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = declaration(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "module_declaration_4_0_1");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // OPEN_KEYWORD ? 'import' fqName module_arguments as_name? "public"? using_or_hiding? renaming?
  public static boolean module_import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import")) return false;
    if (!nextTokenIs(builder_, IMPORT_KEYWORD) && !nextTokenIs(builder_, OPEN_KEYWORD)
        && replaceVariants(builder_, 2, "<module import>")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<module import>");
    result_ = module_import_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IMPORT_KEYWORD);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, fqName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, module_arguments(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, module_import_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, module_import_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, module_import_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && module_import_7(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(MODULE_IMPORT);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // OPEN_KEYWORD ?
  private static boolean module_import_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_0")) return false;
    consumeToken(builder_, OPEN_KEYWORD);
    return true;
  }

  // as_name?
  private static boolean module_import_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_4")) return false;
    as_name(builder_, level_ + 1);
    return true;
  }

  // "public"?
  private static boolean module_import_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_5")) return false;
    consumeToken(builder_, PUBLIC_KEYWORD);
    return true;
  }

  // using_or_hiding?
  private static boolean module_import_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_6")) return false;
    using_or_hiding(builder_, level_ + 1);
    return true;
  }

  // renaming?
  private static boolean module_import_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_import_7")) return false;
    renaming(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // MUTUAL_KEYWORD VIRTUAL_LEFT_PAREN (declaration +) VIRTUAL_RIGHT_PAREN
  public static boolean mutual(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mutual")) return false;
    if (!nextTokenIs(builder_, MUTUAL_KEYWORD)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeTokens(builder_, 0, MUTUAL_KEYWORD, VIRTUAL_LEFT_PAREN);
    result_ = result_ && mutual_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, VIRTUAL_RIGHT_PAREN);
    if (result_) {
      marker_.done(MUTUAL);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // declaration +
  private static boolean mutual_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mutual_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = declaration(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "mutual_2");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // id
  public static boolean name_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "name_declaration")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ID);
    if (result_) {
      marker_.done(NAME_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // number
  public static boolean number_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "number_expression")) return false;
    if (!nextTokenIs(builder_, NUMBER)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, NUMBER);
    if (result_) {
      marker_.done(NUMBER_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // OPEN_KEYWORD fqName module_arguments as_name? "public"? using_or_hiding? renaming?
  public static boolean open(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "open")) return false;
    if (!nextTokenIs(builder_, OPEN_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, OPEN_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, fqName(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, module_arguments(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, open_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, open_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, open_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && open_6(builder_, level_ + 1) && result_;
    if (result_ || pinned_) {
      marker_.done(OPEN);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // as_name?
  private static boolean open_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "open_3")) return false;
    as_name(builder_, level_ + 1);
    return true;
  }

  // "public"?
  private static boolean open_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "open_4")) return false;
    consumeToken(builder_, PUBLIC_KEYWORD);
    return true;
  }

  // using_or_hiding?
  private static boolean open_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "open_5")) return false;
    using_or_hiding(builder_, level_ + 1);
    return true;
  }

  // renaming?
  private static boolean open_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "open_6")) return false;
    renaming(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "(" expression ")"
  public static boolean parenthesis_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parenthesis_expression")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, LEFT_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, expression(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RIGHT_PAREN) && result_;
    if (result_ || pinned_) {
      marker_.done(PARENTHESIS_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "postulate" (VIRTUAL_LEFT_PAREN postulate_bindings VIRTUAL_RIGHT_PAREN)
  public static boolean postulate(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postulate")) return false;
    if (!nextTokenIs(builder_, POSTULATE_KEYWORD)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, POSTULATE_KEYWORD);
    result_ = result_ && postulate_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(POSTULATE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // VIRTUAL_LEFT_PAREN postulate_bindings VIRTUAL_RIGHT_PAREN
  private static boolean postulate_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postulate_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_LEFT_PAREN);
    result_ = result_ && postulate_bindings(builder_, level_ + 1);
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
  // type_signature (VIRTUAL_SEMICOLON type_signature) *
  public static boolean postulate_bindings(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postulate_bindings")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = type_signature(builder_, level_ + 1);
    result_ = result_ && postulate_bindings_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(POSTULATE_BINDINGS);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (VIRTUAL_SEMICOLON type_signature) *
  private static boolean postulate_bindings_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postulate_bindings_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!postulate_bindings_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "postulate_bindings_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // VIRTUAL_SEMICOLON type_signature
  private static boolean postulate_bindings_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postulate_bindings_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_SEMICOLON);
    result_ = result_ && type_signature(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // PRAGMA_OPEN (build_in_pragma | compiled_type_pragma | compiled_pragma) PRAGMA_CLOSE
  public static boolean pragma(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pragma")) return false;
    if (!nextTokenIs(builder_, PRAGMA_OPEN)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, PRAGMA_OPEN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, pragma_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, PRAGMA_CLOSE) && result_;
    if (result_ || pinned_) {
      marker_.done(PRAGMA);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // build_in_pragma | compiled_type_pragma | compiled_pragma
  private static boolean pragma_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pragma_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = build_in_pragma(builder_, level_ + 1);
    if (!result_) result_ = compiled_type_pragma(builder_, level_ + 1);
    if (!result_) result_ = compiled_pragma(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // (id | "(" | ")" | "\\" | "->") *
  public static boolean pragma_string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pragma_string")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<pragma string>");
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!pragma_string_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "pragma_string");
        break;
      }
      offset_ = next_offset_;
    }
    marker_.done(PRAGMA_STRING);
    exitErrorRecordingSection(builder_, level_, true, false, _SECTION_GENERAL_, null);
    return true;
  }

  // id | "(" | ")" | "\\" | "->"
  private static boolean pragma_string_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pragma_string_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ID);
    if (!result_) result_ = consumeToken(builder_, LEFT_PAREN);
    if (!result_) result_ = consumeToken(builder_, RIGHT_PAREN);
    if (!result_) result_ = consumeToken(builder_, LAMBDA);
    if (!result_) result_ = consumeToken(builder_, ARROW);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "constructor" name_declaration
  public static boolean record_constructor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "record_constructor")) return false;
    if (!nextTokenIs(builder_, CONSTRUCTOR_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, CONSTRUCTOR_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && name_declaration(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(RECORD_CONSTRUCTOR);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // "record" name_declaration typed_untyped_bindings ":" expression "where" VIRTUAL_LEFT_PAREN (declaration +) VIRTUAL_RIGHT_PAREN
  public static boolean record_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "record_declaration")) return false;
    if (!nextTokenIs(builder_, RECORD_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, RECORD_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, name_declaration(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, typed_untyped_bindings(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COLON)) && result_;
    result_ = pinned_ && report_error_(builder_, expression(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, WHERE_KEYWORD)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, VIRTUAL_LEFT_PAREN)) && result_;
    result_ = pinned_ && report_error_(builder_, record_declaration_7(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, VIRTUAL_RIGHT_PAREN) && result_;
    if (result_ || pinned_) {
      marker_.done(RECORD_DECLARATION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // declaration +
  private static boolean record_declaration_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "record_declaration_7")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = declaration(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "record_declaration_7");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // "field" ((VIRTUAL_LEFT_PAREN type_signatures VIRTUAL_RIGHT_PAREN) | type_signature)
  public static boolean record_field(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "record_field")) return false;
    if (!nextTokenIs(builder_, FIELD_KEYWORD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, FIELD_KEYWORD);
    pinned_ = result_; // pin = 1
    result_ = result_ && record_field_1(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.done(RECORD_FIELD);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  // (VIRTUAL_LEFT_PAREN type_signatures VIRTUAL_RIGHT_PAREN) | type_signature
  private static boolean record_field_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "record_field_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = record_field_1_0(builder_, level_ + 1);
    if (!result_) result_ = type_signature(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // VIRTUAL_LEFT_PAREN type_signatures VIRTUAL_RIGHT_PAREN
  private static boolean record_field_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "record_field_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_LEFT_PAREN);
    result_ = result_ && type_signatures(builder_, level_ + 1);
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
  // ! (VIRTUAL_RIGHT_PAREN | VIRTUAL_SEMICOLON)
  static boolean recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_NOT_, null);
    result_ = !recover_0(builder_, level_ + 1);
    marker_.rollbackTo();
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_NOT_, null);
    return result_;
  }

  // VIRTUAL_RIGHT_PAREN | VIRTUAL_SEMICOLON
  private static boolean recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_RIGHT_PAREN);
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
  // module_declaration | module_import | open | infix | postulate | pragma | mutual | data_declaration | function_type_declaration | function_declaration | absurd_function
  static boolean recovering_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recovering_declaration")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, null);
    result_ = module_declaration(builder_, level_ + 1);
    if (!result_) result_ = module_import(builder_, level_ + 1);
    if (!result_) result_ = open(builder_, level_ + 1);
    if (!result_) result_ = infix(builder_, level_ + 1);
    if (!result_) result_ = postulate(builder_, level_ + 1);
    if (!result_) result_ = pragma(builder_, level_ + 1);
    if (!result_) result_ = mutual(builder_, level_ + 1);
    if (!result_) result_ = data_declaration(builder_, level_ + 1);
    if (!result_) result_ = function_type_declaration(builder_, level_ + 1);
    if (!result_) result_ = function_declaration(builder_, level_ + 1);
    if (!result_) result_ = absurd_function(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_RECOVER_, recover_parser_);
    return result_;
  }

  /* ********************************************************** */
  // "renaming" "(" renamings ")"
  public static boolean renaming(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "renaming")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<renaming>");
    result_ = consumeToken(builder_, "renaming");
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && renamings(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    if (result_) {
      marker_.done(RENAMING);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // id "to" id (";" id "to" id)*
  static boolean renamings(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "renamings")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ID);
    result_ = result_ && consumeToken(builder_, "to");
    result_ = result_ && consumeToken(builder_, ID);
    result_ = result_ && renamings_3(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // (";" id "to" id)*
  private static boolean renamings_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "renamings_3")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!renamings_3_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "renamings_3");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // ";" id "to" id
  private static boolean renamings_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "renamings_3_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, SEMICOLON);
    result_ = result_ && consumeToken(builder_, ID);
    result_ = result_ && consumeToken(builder_, "to");
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

  /* ********************************************************** */
  // "{" (name_declaration "=")?  expression "}"
  public static boolean substitute_implisit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "substitute_implisit")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && substitute_implisit_1(builder_, level_ + 1);
    result_ = result_ && expression(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    if (result_) {
      marker_.done(SUBSTITUTE_IMPLISIT);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (name_declaration "=")?
  private static boolean substitute_implisit_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "substitute_implisit_1")) return false;
    substitute_implisit_1_0(builder_, level_ + 1);
    return true;
  }

  // name_declaration "="
  private static boolean substitute_implisit_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "substitute_implisit_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = name_declaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ASSIGNMENT);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // telescope+ ARROW expression
  public static boolean tele_arrow(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tele_arrow")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, LEFT_BRACE)
        && replaceVariants(builder_, 2, "<tele arrow>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<tele arrow>");
    result_ = tele_arrow_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARROW);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(TELE_ARROW);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // telescope+
  private static boolean tele_arrow_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tele_arrow_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = telescope(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!telescope(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "tele_arrow_0");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // implicit_telescope | explicit_telescope
  public static boolean telescope(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "telescope")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN) && !nextTokenIs(builder_, LEFT_BRACE)
        && replaceVariants(builder_, 2, "<telescope>")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<telescope>");
    result_ = implicit_telescope(builder_, level_ + 1);
    if (!result_) result_ = explicit_telescope(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), TELESCOPE)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(TELESCOPE);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // ids ":" expression
  public static boolean type_signature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_signature")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = ids(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expression(builder_, level_ + 1);
    if (result_) {
      marker_.done(TYPE_SIGNATURE);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // type_signature (VIRTUAL_SEMICOLON type_signature)*
  public static boolean type_signatures(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_signatures")) return false;
    if (!nextTokenIs(builder_, ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = type_signature(builder_, level_ + 1);
    result_ = result_ && type_signatures_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(TYPE_SIGNATURES);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (VIRTUAL_SEMICOLON type_signature)*
  private static boolean type_signatures_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_signatures_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!type_signatures_1_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "type_signatures_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // VIRTUAL_SEMICOLON type_signature
  private static boolean type_signatures_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_signatures_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_SEMICOLON);
    result_ = result_ && type_signature(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // name_declaration+  | '{' ids '}' | ("{" type_signature "}") | ("(" type_signature ")")
  public static boolean typed_untyped_binding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_binding")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<typed untyped binding>");
    result_ = typed_untyped_binding_0(builder_, level_ + 1);
    if (!result_) result_ = typed_untyped_binding_1(builder_, level_ + 1);
    if (!result_) result_ = typed_untyped_binding_2(builder_, level_ + 1);
    if (!result_) result_ = typed_untyped_binding_3(builder_, level_ + 1);
    if (result_) {
      marker_.done(TYPED_UNTYPED_BINDING);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // name_declaration+
  private static boolean typed_untyped_binding_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_binding_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = name_declaration(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!name_declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "typed_untyped_binding_0");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // '{' ids '}'
  private static boolean typed_untyped_binding_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_binding_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && ids(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "{" type_signature "}"
  private static boolean typed_untyped_binding_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_binding_2")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && type_signature(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // "(" type_signature ")"
  private static boolean typed_untyped_binding_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_binding_3")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && type_signature(builder_, level_ + 1);
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
  // (typed_untyped_binding)*
  static boolean typed_untyped_bindings(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_bindings")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!typed_untyped_bindings_0(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "typed_untyped_bindings");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  // (typed_untyped_binding)
  private static boolean typed_untyped_bindings_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typed_untyped_bindings_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = typed_untyped_binding(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // 'using'
  static boolean using_keyword(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, USING_KEYWORD);
  }

  /* ********************************************************** */
  // (using_keyword '(' import_names ')') | ('hiding' '(' import_names ')')
  public static boolean using_or_hiding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "using_or_hiding")) return false;
    if (!nextTokenIs(builder_, HIDING_KEYWORD) && !nextTokenIs(builder_, USING_KEYWORD)
        && replaceVariants(builder_, 2, "<using or hiding>")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<using or hiding>");
    result_ = using_or_hiding_0(builder_, level_ + 1);
    if (!result_) result_ = using_or_hiding_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(USING_OR_HIDING);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // using_keyword '(' import_names ')'
  private static boolean using_or_hiding_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "using_or_hiding_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = using_keyword(builder_, level_ + 1);
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

  // 'hiding' '(' import_names ')'
  private static boolean using_or_hiding_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "using_or_hiding_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, HIDING_KEYWORD);
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
  // expression where_part?
  public static boolean where_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "where_expression")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<where expression>");
    result_ = expression(builder_, level_ + 1);
    result_ = result_ && where_expression_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(WHERE_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  // where_part?
  private static boolean where_expression_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "where_expression_1")) return false;
    where_part(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // "where" ((VIRTUAL_LEFT_PAREN (declaration +) VIRTUAL_RIGHT_PAREN) | declaration)
  public static boolean where_part(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "where_part")) return false;
    if (!nextTokenIs(builder_, WHERE_KEYWORD)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, WHERE_KEYWORD);
    result_ = result_ && where_part_1(builder_, level_ + 1);
    if (result_) {
      marker_.done(WHERE_PART);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  // (VIRTUAL_LEFT_PAREN (declaration +) VIRTUAL_RIGHT_PAREN) | declaration
  private static boolean where_part_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "where_part_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = where_part_1_0(builder_, level_ + 1);
    if (!result_) result_ = declaration(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // VIRTUAL_LEFT_PAREN (declaration +) VIRTUAL_RIGHT_PAREN
  private static boolean where_part_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "where_part_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, VIRTUAL_LEFT_PAREN);
    result_ = result_ && where_part_1_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, VIRTUAL_RIGHT_PAREN);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // declaration +
  private static boolean where_part_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "where_part_1_0_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = declaration(builder_, level_ + 1);
    int offset_ = builder_.getCurrentOffset();
    while (result_) {
      if (!declaration(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "where_part_1_0_1");
        break;
      }
      offset_ = next_offset_;
    }
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  final static Parser recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return recover(builder_, level_ + 1);
    }
  };
}
