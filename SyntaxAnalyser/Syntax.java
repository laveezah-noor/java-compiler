package SyntaxAnalyser;

import java.util.*;
import LexicalAnalyser.TokenClass;
import LexicalAnalyser.Tokenizer;

public class Syntax {
    String T;
    String N;
    String Am = "pvt";
    String Cat;
    String Prnt = null;
    // ArrayList<BodyTable> refDt;
    String cTm;
    String cName;
    String cType;
    String Pl;
    String Fname;
    String Ftype;
    String opr;
    String T1 = "null";
    String T3 = "null";
    String T2 = "null";
    String T4 = "null";
    String T5 = "null";

    public static final String DATATYPE_VARS[] = new String[] { "char", "int", "string", "float", "boolean" };
    List datatype_varsList = Arrays.asList(DATATYPE_VARS);
    public static final String ACCESS_MODIFIERS[] = new String[] { "uni", "pro", "pri" };
    List access_modifierList = Arrays.asList(ACCESS_MODIFIERS);
    public static final String LOOP_KEYWORDS[] = new String[] { "break", "continue" };
    List loop_keywords = Arrays.asList(LOOP_KEYWORDS);
    public static final String COM_ASS[] = new String[] { "+", "-", "*", "/" };
    List com_ass = Arrays.asList(COM_ASS);
    static int index = 0;
    private final List<Tokenizer.Token> token;

    public void Dtempty() {
        this.cName = null;
        this.cTm = null;
        this.cType = null;
        this.Am = "pvt";
        this.Pl = null;
    }

    public void empty() {
        this.T = null;
        this.N = null;
        this.Am = "pvt";
        this.Cat = null;
        this.Prnt = null;
    }

    public Syntax(List<Tokenizer.Token> token) {
        this.token = token;
    }

    public void run() {
        System.out.println(token.get(index));
        System.out.print(token.get(index).value);
        System.out.print(token.get(index).type);
        System.out.println(token.get(index).line);

        if (S()) {
            if (token.get(index).type.equals("$")) {
                System.out.println("No Syntax Error");
                System.out.println("Valid Syntax");
            }
        } else {
            System.out.println("Syntax Error At Line No: " + token.get(index).line + " " + token.get(index).type);
        }
    }

    boolean S() {
        if (Tokenizer.isKeyword(token.get(index).value)) {
            System.out.println("Keyword");

        } else {
            System.out.println("Not Keyword");
        }
        // !!
        if (token.get(index).value.equals("interface")
                || access_modifierList.contains(token.get(index).value)
                || token.get(index).value.equals("const")
                || token.get(index).value.equals("conceptual")
                || token.get(index).value.equals("class")) {
            if (idefs()) {
                if (am()) {
                    if (cmod()) {
                        if (token.get(index).value.equals("class")) {
                            T = "class";
                            index++;
                            if (token.get(index).type.equals("ID")) {
                                N = token.get(index).value;
                                index++;
                                if (inh()) {
                                    // // refDt = semanticClass.create_DT();
                                    // // semanticClass.insert_MT(N, T, Am, Cat, Prnt, refDt);
                                    empty();
                                    if (token.get(index).value.equals("{")) {
                                        // // semanticClass.createScope();
                                        index++;
                                        if (cbody()) {
                                            if (token.get(index).value.equals("}")) {
                                                // // semanticClass.destroyScope();
                                                index++;
                                                if (defs()) {
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean idefs() {
        if (token.get(index).value.equals("interface")) {
            if (interfacedef()) {
                return true;
            }
        } else if (access_modifierList.contains(token.get(index).value)
                || token.get(index).value.equals("const")
                || token.get(index).value.equals("conceptual")
                || token.get(index).value.equals("class")) {
            return true;
        }
        return false;
    }

    boolean defs() {
        if (am()) {

            if (defs1()) {
                return true;
            }
        } else if (token.get(index).value.equals("$")) {
            return true;
        }

        return false;
    }

    boolean defs1() {
        if (token.get(index).value.equals("const")
                || token.get(index).value.equals("conceptual")
                || token.get(index).value.equals("class")) {
            if (classdef()) {
                if (defs()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("interface")) {
            if (interfacedef()) {
                if (defs()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("$")) {
            return true;
        }
        return false;
    }

    boolean classdef() {
        if (token.get(index).value.equals("const")
                || token.get(index).value.equals("conceptual")
                || token.get(index).value.equals("class")) {
            if (cmod()) {
                if (token.get(index).value.equals("class")) {
                    T = "class";
                    index++;
                    if (token.get(index).type.equals("ID")) {
                        N = token.get(index).value;
                        index++;
                        if (inh()) {
                            if (implement()) {
                                // refDt = semanticClass.create_DT();
                                // semanticClass.insert_MT(N, T, Am, Cat, Prnt, refDt);
                                empty();
                                if (token.get(index).value.equals("{")) {
                                    // semanticClass.createScope();
                                    index++;
                                    if (cbody()) {
                                        if (token.get(index).value.equals("}")) {
                                            // semanticClass.destroyScope();
                                            index++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean am() {
        if (access_modifierList.contains(token.get(index).value)) {
            Am = token.get(index).value;
            index++;
            return true;
        } else if (token.get(index).value.equals("const")
                || token.get(index).value.equals("conceptual")
                || token.get(index).value.equals("class")
                || datatype_varsList.contains(token.get(index).value)
                || token.get(index).value.equals("void")
                || token.get(index).value.equals("static")
                || token.get(index).value.equals("interface")) {
            return true;
        }
        return false;
    }

    boolean cmod() {
        if (token.get(index).value.equals("const")
                || token.get(index).value.equals("conceptual")) {
            Cat = token.get(index).value;
            index++;

            return true;
        } else if (token.get(index).value.equals("class")) {
            Cat = "General";
            return true;
        }
        return false;
    }

    boolean cbody() {
        if (access_modifierList.contains(token.get(index).value)
                || token.get(index).value.equals("void")
                || token.get(index).value.equals("static")
                || datatype_varsList.contains(token.get(index).value)) {
            if (am()) {
                if (TM()) {
                    if (cbody1()) {
                        return true;
                    }
                }
            }
        } else if (token.get(index).type.equals("ID")) {
            // if (objdec()) {
            // if (cbody()) {
            return true;
            // }
            // }
        } else if (token.get(index).value.equals("conceptual")) {
            if (conceptual()) {
                if (cbody()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("ArrayList")) {
            // if (Arraylist()) {
            // return true;
            // }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    // Checks for void
    boolean cbody1() {
        if (token.get(index).value.equals("void")) {
            cType = "void";
            index++;
            if (token.get(index).type.equals("ID")) {
                cName = token.get(index).value;
                index++;
                // if (fn()) {
                if (cbody()) {
                    return true;
                }
                // }
            }
        } else if (datatype_varsList.contains(token.get(index).value)) {
            cType = token.get(index).value;
            index++;
            if (Orarr()) {
                return true;
            }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean Orarr() {
        if (token.get(index).type.equals("ID")) {
            cName = token.get(index).value;
            if ("(".equals(token.get(index + 1).value)) {
            }
            // else {
            // semanticClass.insert_DT(cName, cType, cTm, Am, refDt);
            // }
            index++;

            if (orFn()) {
                if (cbody()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("[")) {
            index++;
            if (token.get(index).value.equals("]")) {
                index++;
                if (token.get(index).type.equals("ID")) {
                    index++;
                    if (orarrfn()) {
                        if (cbody()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean orarrfn() {
        if (token.get(index).value.equals("(")) {
            if (fn()) {
                return true;
            }
        } else if (token.get(index).value.equals("=")) {
            index++;
            // if (arrInit1()) {
            if (token.get(index).value.equals(";")) {
                index++;
                return true;
            }
            // }
        } else if (token.get(index).value.equals(",")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                index++;
                // if (oArrinit()) {
                // if (list2()) {
                return true;
                // }
                // }
            }
        }
        return false;
    }

    boolean fn() {
        if (token.get(index).value.equals("(")) {
            // semanticClass.createScope();
            index++;
            if (PL()) {
                if (token.get(index).value.equals(")")) {
                    // semanticClass.insert_DT(cName, Pl + "->" + cType, cTm, Am, refDt);
                    Dtempty();
                    index++;
                    if (token.get(index).value.equals("{")) {
                        index++;
                        if (MST()) {
                            if (token.get(index).value.equals("}")) {
                                // semanticClass.destroyScope();
                                index++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean orFn() {
        if (token.get(index).value.equals("(")) {
            if (fn()) {
                return true;
            }
        } else if (token.get(index).value.equals("=")) {
            if (init()) {
                if (list()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals(";")) {
            index++;
            return true;
        } else if (token.get(index).value.equals(",")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                cName = token.get(index).value;
                // semanticClass.insert_DT(cName, cType, cTm, Am, refDt);
                index++;
                if (init()) {
                    if (list()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean init() {
        if (token.get(index).value.equals("=")) {
            opr = "=";
            index++;
            if (oArrinit()) {
                return true;
            }
        } else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")) {
            return true;
        }
        return false;
    }

    boolean oArrinit() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                // Or Inc Dec Operator (++, --)
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            if (OE()) {
                // if (!T1.equals("null") && !T2.equals("null") && !opr.equals("null")) {
                // // T5 = semanticClass.compatibility(T1, T2, opr);
                // System.out.println("T5 = " + T5);

                // }
                return true;
            }
        } else if (token.get(index).value.equals("{")
                || token.get(index).value.equals("new")) {
            if (arrInit1()) {
                return true;
            }
        } else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")) {
            return true;
        }
        return false;
    }

    boolean arrInit1() {
        if (token.get(index).value.equals("{")) {
            index++;
            if (arrvar()) {
                if (token.get(index).value.equals("}")) {
                    index++;
                    return true;
                }
            }
        } else if (token.get(index).value.equals("new")) {
            index++;
            if (datatype_varsList.contains(token.get(index).value)) {
                index++;
                if (token.get(index).value.equals("[")) {
                    index++;
                    if (OE()) {
                        if (token.get(index).value.equals("]")) {
                            index++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean list() {
        if (token.get(index).value.equals(";")) {
            Dtempty();
            index++;
            return true;
        } else if (token.get(index).value.equals(",")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                // cName = token.get(index).value;
                // semanticClass.insert_DT(cName, cType, cTm, Am, refDt);
                index++;
                if (init()) {
                    if (list()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean fnlist() {
        if (token.get(index).value.equals(";")) {
            Dtempty();
            index++;
            return true;
        } else if (token.get(index).value.equals(",")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                Fname = token.get(index).value;
                // semanticClass.insert_FT(Fname, Ftype);
                index++;
                if (init()) {
                    if (fnlist()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean list2() {
        if (token.get(index).value.equals(";")) {
            index++;
            return true;
        } else if (token.get(index).value.equals(",")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                index++;
                if (oArrinit()) {
                    if (list2()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean arrvar() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                // Or Inc Dec Operator (++, --)
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            if (OE()) {
                if (arrvar1()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean arrvar1() {
        if (token.get(index).value.equals(",")) {
            index++;
            if (OE()) {
                if (arrvar1()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean TM() {
        if (token.get(index).value.equals("static")) {
            cTm = "static";
            index++;
            return true;
        } else if (datatype_varsList.contains(token.get(index).value)
                || token.get(index).value.equals("void")) {
            cTm = "null";
            return true;
        }
        return false;
    }

    boolean dec() {
        if (datatype_varsList.contains(token.get(index).value)) {

            if (datatype_varsList.contains(token.get(index).value)) {
                // Ftype = token.get(index).value;
                index++;
                if (arr()) {
                    if (token.get(index).type.equals("ID")) {
                        // Fname = token.get(index).value;
                        // semanticClass.insert_FT(Fname, Ftype);
                        // String N = token.get(index).value;
                        // T1 = semanticClass.lookup_FT(N);
                        index++;
                        if (init()) {
                            if (fnlist()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // // If Else Statement Checker Function
    boolean if_else() {
        if (token.get(index).value.equals("if")) {
            index++;
            // Checks for Open Parenthesis
            if (token.get(index).value.equals("(")) {
                index++;

                if (OE()) {
                    // Checks for Close Parenthesis
                    if (token.get(index).value.equals(")")) {
                        index++;
                        // Body of If Statement
                        if (body()) {
                            // IF Body Contains Else Statement
                            if (orElse()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean orElse() {
        if (token.get(index).value.equals("else")) {
            index++;
            if (body()) {
                return true;
            }
        } else if (token.get(index).value.equals("Data Format")
                || access_modifierList.contains(token.get(index).value)
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("throw")
                || token.get(index).value.equals("while")
                || token.get(index).value.equals("do")
                || token.get(index).value.equals("}")
                || token.get(index).value.equals("if")
                || token.get(index).value.equals("return")
                || loop_keywords.contains(token.get(index).value)) {

            return true;
        }
        return false;
    }

    boolean for_st() {
        if (token.get(index).value.equals("for")) {
            index++;
            // Checks for Open Parenthesis
            if (token.get(index).value.equals("(")) {
                index++;
                if (C1()) {
                    if (C2()) {
                        if (token.get(index).value.equals(";")) {
                            index++;
                            if (C3()) {
                                // Checks for Close Parenthesis
                                if (token.get(index).value.equals(")")) {
                                    index++;
                                    if (body()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean OE() {
        // Checks whether it is a
        // "This" Statement
        if (token.get(index).value.equals("this")
                // Or ID
                || token.get(index).type.equals("ID")
                // Or Open Parenthesis
                || token.get(index).value.equals("(")
                // Or String Or Float Or Character Or Integer Or Boolean
                || datatype_varsList.contains(token.get(index).value)
                // Or Inc Dec Operator (++, --)
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                // Or Not Operator (!)
                || token.get(index).value.equals("!")) {
            if (AE()) {
                if (OE1()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean OE1() {
        if (token.get(index).value.equals("||")) {
            index++;
            if (AE()) {
                if (OE1()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("]")
                || token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean AE() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                // Checks for Open Parenthesis
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            if (RE()) {
                if (AE1()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean AE1() {
        // Checks if it is AND operator
        if (token.get(index).value.equals("&&")) {
            index++;
            if (RE()) {
                if (AE1()) {
                    return true;
                }
            }
        }
        // or Semicolon, commac, or , close parenthesis
        else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")
                || token.get(index).value.equals("||")
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("]")
                || token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean RE() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                // Checks for Open Parenthesis
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            if (E()) {
                if (RE1()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean RE1() {
        if (token.get(index).value.equals("==")
                || token.get(index).value.equals(">=")
                || token.get(index).value.equals("<=")
                || token.get(index).value.equals("<")
                // Checks if it is AND operator
                || token.get(index).value.equals(">")) {
            index++;
            if (E()) {
                if (RE1()) {
                    return true;
                }
            }
        }
        // or Semicolon, commac, or , close parenthesis
        else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")
                || token.get(index).value.equals("&&")
                || token.get(index).value.equals("||")
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("]")
                || token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean E() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                // Checks for Open Parenthesis
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            if (T()) {
                if (E1()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean E1() {
        if (token.get(index).value.equals("+")
                || token.get(index).value.equals("-")) {
            opr = token.get(index).value;
            index++;
            if (T()) {
                if (E1()) {
                    return true;
                }
            }
        }
        // or Semicolon, commac, or , close parenthesis
        else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")
                || token.get(index).value.equals("==")
                || token.get(index).value.equals(">=")
                || token.get(index).value.equals("<=")
                || token.get(index).value.equals(">")
                || token.get(index).value.equals("<")
                || token.get(index).value.equals("||")
                || token.get(index).value.equals("&&")
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("]")
                || token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean T() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                // Checks for Open Parenthesis
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            if (F()) {
                if (T1()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean T1() {
        if (token.get(index).value.equals("*")
                || token.get(index).value.equals("/")
                || token.get(index).value.equals("%")) {
            opr = token.get(index).value;
            index++;
            if (F()) {
                if (T1()) {
                    return true;
                }
            }
        }

        // or Semicolon, commac, or , close parenthesis
        else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")
                || token.get(index).value.equals("+")
                || token.get(index).value.equals("-")
                || token.get(index).value.equals("<")
                || token.get(index).value.equals(">")
                || token.get(index).value.equals("||")
                || token.get(index).value.equals("==")
                || token.get(index).value.equals(">=")
                || token.get(index).value.equals("<=")
                || token.get(index).value.equals("&&")
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("]")
                || token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean F() {
        if (token.get(index).value.equals("this")
                || token.get(index).type.equals("ID")
                // Checks for Open Parenthesis
                || token.get(index).value.equals("(")
                || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Letter")
                // || token.get(index).value.equals("Whole Number")
                // || token.get(index).value.equals("Boolean")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("!")) {
            // Checks This Statement
            if (ThisStatement()) {
                // after "this:" if there is any identifier
                if (token.get(index).type.equals("ID")) {
                    index++;
                    if (F1()) {
                        return true;
                    }
                }
            } else if (datatype_varsList.contains(token.get(index).value)) {
                // Check it value is Integer
                if (Tokenizer.isInteger(token.get(index).value))
                    T2 = "int";
                // Check it value is String
                if (Tokenizer.isString(token.get(index).value))
                    T2 = "string";
                // Check it value is Float
                if (Tokenizer.isFloat(token.get(index).value))
                    T2 = "float";
                // Check it value is Character
                if (Tokenizer.isCharacter(token.get(index).value))
                    T2 = "char";
                // Check it value is Boolean
                if (Tokenizer.isBoolean(token.get(index).value))
                    T2 = "boolean";

                index++;
                return true;
            }
            // Checks for Open Parenthesis
            else if (token.get(index).value.equals("(")) {
                index++;
                if (OE()) {
                    return true;
                }
            }
            // Checks if Inc Dec Operator (++, --)
            else if (token.get(index).value.equals("++")
                    || token.get(index).value.equals("--")) {
                index++;
                // if token type is identifier
                if (token.get(index).type.equals("ID")) {
                    index++;
                    return true;
                }
            }
            // if token is not operator
            else if (token.get(index).value.equals("!")) {
                index++;
                if (F()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean F1() {
        // Checks for Open Parenthesis
        if (token.get(index).value.equals("(")) {
            index++;
            if (PL()) {
                // Checks for Close Parenthesis
                if (token.get(index).value.equals(")")) {
                    index++;
                }
            }
        }
        // Checks if Inc Dec Operator (++, --)
        else if (token.get(index).value.equals("++")
                || token.get(index).value.equals("--")) {
            index++;
            return true;
        } else if (token.get(index).value.equals("[")) {
            index++;
            if (OE()) {
                if (token.get(index).value.equals("]")) {
                    index++;
                    return true;
                }
            }
        }

        // or Semicolon, commac, or , close parenthesis
        else if (token.get(index).value.equals(";")
                || token.get(index).value.equals(",")
                || Tokenizer.isOperator(token.get(index).value)
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("]")
                || token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean PL() {
        // Checks if variable is initialized
        // Check if it is a datatype var
        if (datatype_varsList.contains(token.get(index).value)) {
            // store the value of token in ftype
            String ftype = token.get(index).value;
            Pl = token.get(index).value;
            index++;
            if (arr()) {
                // if token type is identifier
                if (token.get(index).type.equals("ID")) {
                    String fname = token.get(index).type;
                    // check if datatype var and datatype of token matches
                    if (fname.toLowerCase() == ftype) {
                        index++;
                    }
                    if (PL2()) {
                        return true;
                    }
                }
            }
        }
        // if token type is identifier
        else if (token.get(index).type.equals("ID")) {
            index++;
            if (arr()) {
                // if token type is identifier
                if (token.get(index).type.equals("ID")) {
                    index++;
                    if (PL2()) {
                        return true;
                    }
                }
            }
        } else if (token.get(index).value.equals(")")) {
            return true;
        }
        return false;
    }

    boolean PL2() {
        // Checks if it contains multiple variables initialized together
        // with comman in between
        // or has close parenthesis
        if (token.get(index).value.equals(",")) {
            index++;
            // Check if it is a datatype var
            if (datatype_varsList.contains(token.get(index).value)) {
                String ftype = token.get(index).value;
                Pl += "," + token.get(index).value;
                index++;

                // if token type is identifier
                if (token.get(index).type.equals("ID")) {
                    String fname = token.get(index).value;
                    // semanticClass.insert_FT(fname, ftype);
                    if (fname.toLowerCase() == ftype) {
                        index++;
                    }
                    if (PL2()) {
                        return true;
                    }
                }
            }
        } else if (token.get(index).value.equals(")")) {
            return true;
        }
        return false;
    }

    boolean ThisStatement() {
        // Checks this: statement
        if (token.get(index).value.equals("this")) {
            index++;
            if (token.get(index).value.equals(":")) {
                index++;
                return true;
            }
        }
        // Not Sure
        else if (token.get(index).type.equals("ID")) {
            return true;
        }
        return false;
    }

    boolean arr() {
        if (token.get(index).value.equals("[")) {
            index++;
            if (token.get(index).value.equals("]")) {
                index++;
                return true;
            }
        } else if (token.get(index).type.equals("ID")) {
            return true;
        }
        return false;
    }

    boolean body() {
        // if body has only semicolon
        if (token.get(index).value.equals(";")) {
            index++;
            return true;
        }
        // Check if it is a datatype var
        else if (datatype_varsList.contains(token.get(index).value)
                || token.get(index).type.equals("KEYWORD")
                || token.get(index).type.equals("ID")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")) {
            if (SST()) {
                return true;
            }

        } else if (token.get(index).value.equals("{")) {
            // semanticClass.createScope();
            index++;
            if (MST()) {
                if (token.get(index).value.equals("}")) {
                    // semanticClass.destroyScope();
                    index++;
                    return true;
                }
            }
        }
        return false;
    }

    boolean SST() {
        // if it equals to any keyword
        // Check if it is a datatype var
        if (datatype_varsList.contains(token.get(index).value)
                || token.get(index).type.equals("KEYWORD")
                || token.get(index).type.equals("ID")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")) {
            if (dec()) {
                return true;
            } else if (if_else()) {
                return true;
            } else if (for_st()) {
                return true;
            } else if (dowhile()) {
                return true;
            } else if (while_st()) {
                return true;
            } else if (Printst()) {
                return true;
            } else if (Arraylist()) {
                return true;
            } else if (swtcase()) {
                return true;
            } else if (returns()) {
                return true;
            } else if (_throw()) {
                return true;
            }
        } else if (loop_keywords.contains(token.get(index).value)) {
            index++;
            if (token.get(index).value.equals(";")) {
                index++;
                return true;
            }
        }
        // Checks if Inc Dec Operator (++, --)
        else if (token.get(index).value.equals("++")
                || token.get(index).value.equals("--")) {
            index++;
            // if token type is identifier
            if (token.get(index).type.equals("ID")) {
                index++;
                if (X()) {
                    if (token.get(index).value.equals(";")) {
                        return true;
                    }
                }
            }
        } else if (token.get(index).type.equals("ID")) {
            String N = token.get(index).value;
            if (token.get(index + 1).type.equals("ID")) {
                // if (semanticClass.lookup_MT(N).equals("class")) {
                // Ftype = N;
                // }
                // else if (semanticClass.lookup_MT(N).equals("interface")) {
                System.out.println("Interface instance not allow! ");
                // }
            } else {
                // T1 = semanticClass.lookup_FT(N);
                // if(T1.equals("null")||T1==null){
                // System.out.println("'" + N + "' Undeclared");
                // }
                index++;
                if (other()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean other() {
        if (token.get(index).type.equals("ID")
                || token.get(index).value.equals(".")
                || token.get(index).value.equals("[")
                || token.get(index).value.equals("(")
                // Check if it is Inc Dec Operator
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("=")) {
            if (token.get(index).type.equals("ID")) {
                Fname = token.get(index).value;
                // semanticClass.insert_FT(Fname, Ftype);
                index++;
                if (token.get(index).value.equals("=")) {
                    index++;
                    if (token.get(index).value.equals("new")) {
                        index++;
                        if (token.get(index).type.equals("ID")) {
                            index++;
                            if (token.get(index).value.equals("(")) {
                                index++;
                                if (PL()) {
                                    if (token.get(index).value.equals(")")) {
                                        index++;
                                        if (token.get(index).value.equals(";")) {
                                            index++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (Y()) {
                if (token.get(index).value.equals("(")) {
                    index++;
                    if (PL()) {
                        if (token.get(index).value.equals(")")) {
                            index++;
                            if (token.get(index).value.equals(";")) {
                                index++;
                                return true;
                            }
                        }
                    }
                }
            } else if (X()) {
                if (other2()) {
                    if (token.get(index).value.equals(";")) {
                        index++;
                        return true;
                    }
                }
            }

        }
        return false;
    }

    boolean other2() {
        if (token.get(index).value.equals("=")) {
            opr = "=";
            index++;
            if (OE()) {
                // if (!T1.equals("null") && !T2.equals("null") && !opr.equals("null")) {
                // // T5 = semanticClass.compatibility(T1, T2, opr);
                // System.out.println("T5 = " + T5);

                // }

                return true;
            }
        }
        if (token.get(index).value.equals("++") ||
                token.get(index).value.equals("--")) {
            index++;
            return true;
        }
        return false;
    }

    boolean MST() {
        if (datatype_varsList.contains(token.get(index).value)
                || token.get(index).value.equals("if")
                || token.get(index).value.equals("do")
                || token.get(index).value.equals("while")
                || token.get(index).value.equals("for")
                || token.get(index).value.equals("switch")
                || token.get(index).type.equals("ID")
                || loop_keywords.contains(token.get(index).value)
                || token.get(index).value.equals("return")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")
                || token.get(index).value.equals("print")
                || token.get(index).value.equals("ArrayList")
                || token.get(index).value.equals("throw")) {
            if (SST()) {
                if (MST()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean C1() {
        if (datatype_varsList.contains(token.get(index).value)) {
            if (dec()) {
                return true;
            }
        } else if (token.get(index).type.equals("ID")) {
            if (assin_st()) {
                return true;
            }
        } else if (token.get(index).value.equals(";")) {
            index++;
            return true;
        }
        return false;
    }

    boolean C2() {

        if (OE()) {
            return true;
        }

        else if (token.get(index).value.equals(";")) {

            return true;
        }
        return false;
    }

    boolean C3() {
        if (token.get(index).type.equals("ID")) {
            index++;
            if (X()) {
                if (C3_1()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("++") ||
                token.get(index).value.equals("--")) {
            index++;
            if (INC_DEC_ST1()) {
                return true;
            }
        } else if (token.get(index).value.equals(")")) {
            return true;
        }
        return false;
    }

    boolean C3_1() {
        if (ASSIGN_OPR()) {
            if (ASSIGN_OPR()) {
                if (OE()) {
                    return true;

                }
            }
        } else if (INC_DEC_ST()) {

            return true;
        }

        return false;
    }

    boolean assin_st() {
        // if token type is identifier
        if (token.get(index).type.equals("ID")) {
            index++;
            if (X()) {
                if (ASSIGN_OPR()) {
                    if (OE()) {
                        if (token.get(index).value.equals(";")) {
                            index++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean INC_DEC_ST() {
        // Checks if Inc Dec Operator (++, --)
        if (token.get(index).value.equals("++")
                || token.get(index).value.equals("--")) {
            index++;
            if (INC_DEC_ST1()) {
                return true;
            }
        }

        return false;
    }

    boolean INC_DEC_ST1() {
        // if token type is identifier
        if (token.get(index).type.equals("ID")) {
            index++;
            if (X()) {
                return true;
            }
            // Checks for Close Parenthesis
        } else if (token.get(index).value.equals(")")) {
            return true;
        }
        return false;
    }

    boolean ASSIGN_OPR() {
        if (token.get(index).value.equals("=")
                || com_ass.contains(token.get(index).value)) {
            if (token.get(index).value.equals("=")) {
                index++;
                return true;
            } else if (com_ass.contains(token.get(index).value)) {
                index++;
                return true;
            }
        }
        return false;
    }

    boolean while_st() {
        if (token.get(index).value.equals("while")) {
            index++;
            // Checks for Open Parenthesis
            if (token.get(index).value.equals("(")) {
                index++;
                if (OE()) {
                    // Checks for Close Parenthesis
                    if (token.get(index).value.equals(")")) {
                        index++;
                        if (body()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean dowhile() {
        if (token.get(index).value.equals("do")) {
            index++;
            if (token.get(index).value.equals("{")) {
                index++;
                if (MST()) {
                    if (token.get(index).value.equals("}")) {
                        index++;
                        if (token.get(index).value.equals("while")) {
                            index++;
                            // Checks for Open Parenthesis
                            if (token.get(index).value.equals("(")) {
                                index++;
                                if (OE()) {
                                    // Checks for Close Parenthesis
                                    if (token.get(index).value.equals(")")) {
                                        index++;
                                        // or Semicolon, commac, or , close parenthesis
                                        if (token.get(index).value.equals(";")) {
                                            index++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean swtcase() {
        if (token.get(index).value.equals("switch")) {
            index++;
            // Checks for Open Parenthesis
            if (token.get(index).value.equals("(")) {
                index++;
                if (OE()) {
                    // Checks for Close Parenthesis
                    if (token.get(index).value.equals(")")) {
                        index++;
                        if (token.get(index).value.equals("{")) {
                            index++;
                            if (swbody()) {
                                if (defaultt()) {
                                    if (token.get(index).value.equals("}")) {
                                        index++;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean swbody() {
        if (token.get(index).value.equals("case")) {
            index++;
            // Checks if it is AND operator
            // if token type is iden&ier
            if (token.get(index).type.equals("ID")) {
                index++;
                if (token.get(index).value.equals(":")) {
                    index++;
                    if (body()) {
                        if (loop_keywords.contains(token.get(index).value)) {
                            index++;
                            // or Semicolon, commac, or , close parenthesis
                            if (token.get(index).value.equals(";")) {
                                index++;
                                if (swbody()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } else if (token.get(index).value.equals("default")
                || token.get(index).value.equals("}")) {
            return true;

        }
        return false;
    }

    boolean defaultt() {
        if (token.get(index).value.equals("default")) {
            index++;
            if (token.get(index).value.equals(":")) {
                index++;
                if (body()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean _throw() {
        if (token.get(index).value.equals("throw")) {
            index++;
            if (token.get(index).value.equals("new")) {
                index++;
                if (token.get(index).value.equals("Error")) {
                    index++;
                    if (token.get(index).value.equals("String")) {
                        index++;
                        // or Semicolon
                        if (token.get(index).value.equals(";")) {
                            index++;
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    boolean returns() {
        if (token.get(index).value.equals("return")) {
            index++;
            if (returns1()) {
                return true;
            }
        }
        return false;
    }

    boolean returns1() {
        // or Semicolon, commac, or , close parenthesis
        if (token.get(index).value.equals(";")) {
            index++;
            return true;
        }
        // Check if it is a datatype variable or identifier
        else if (token.get(index).type.equals("ID")
                || datatype_varsList.contains(token.get(index).value)
        // || token.get(index).value.equals("Text")
        // || token.get(index).value.equals("Float")
        // || token.get(index).value.equals("Letter")
        // || token.get(index).value.equals("Whole Number")
        // || token.get(index).value.equals("Boolean")
        ) {
            if (OE()) {
                if (returns1()) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean Arraylist() {
        if (token.get(index).value.equals("ArrayList")) {
            index++;
            if (token.get(index).value.equals("<")) {
                index++;
                // Check if it is a datatype var
                if (datatype_varsList.contains(token.get(index).value)) {
                    index++;
                    if (token.get(index).value.equals(">")) {
                        index++;
                        // if token type is identifier
                        if (token.get(index).type.equals("ID")) {
                            index++;
                            if (token.get(index).value.equals("=")) {
                                index++;
                                if (token.get(index).value.equals("new")) {
                                    index++;
                                    if (token.get(index).value.equals("ArrayList")) {
                                        index++;
                                        if (token.get(index).value.equals("<")) {
                                            index++;
                                            // Check if it is a datatype var
                                            if (datatype_varsList.contains(token.get(index).value)) {
                                                index++;
                                                if (token.get(index).value.equals(">")) {
                                                    index++;
                                                    if (token.get(index).value.equals("(")) {
                                                        index++;
                                                        if (token.get(index).value.equals(")")) {
                                                            index++;
                                                            if (token.get(index).value.equals(";")) {
                                                                index++;
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean Printst() {
        if (token.get(index).value.equals("print")) {
            index++;
            // Checks for Open Parenthesis
            if (token.get(index).value.equals("(")) {
                index++;
                if (token.get(index).type.equals("ID")
                        || datatype_varsList.contains(token.get(index).value)
                // || token.get(index).value.equals("Text")
                // || token.get(index).value.equals("Letter")
                // Checks if it is AND operator
                // || token.get(index).value.equals("Whole Nu&r")
                // || token.get(index).value.equals("Float")
                // || token.get(index).value.equals("Boolean")
                ) {
                    index++;
                    // Checks for Close Parenthesis
                    if (token.get(index).value.equals(")")) {
                        index++;
                        // or Semicolon, commac, or , close parenthesis
                        if (token.get(index).value.equals(";")) {
                            index++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean conceptual() {
        if (token.get(index).value.equals("conceptual")) {
            index++;
            if (token.get(index).value.equals("void")) {
                index++;
                if (token.get(index).type.equals("ID")) {
                    index++;
                    if (token.get(index).value.equals("(")) {
                        index++;
                        if (PL()) {
                            if (token.get(index).value.equals(")")) {
                                index++;
                                if ((token.get(index).value.equals(";"))) {
                                    index++;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } else if (token.get(index).value.equals("}")) {
            return true;
        }
        return false;
    }

    boolean implement() {
        if (token.get(index).value.equals("implement")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                String N = token.get(index).value;
                // T=semanticClass.lookup_MT(N);
                if (T.equals("null")) {
                    System.out.println(N + " Undeclared");
                } else if (T.equals("class")) {
                    System.out.println(N + " cannot be implement");
                } else
                    Prnt = N;
                index++;
                if (implement1()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("{")) {
            return true;
        }
        return false;
    }

    boolean implement1() {
        if (token.get(index).value.equals("Comma")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                String N = token.get(index).value;
                // T=semanticClass.lookup_MT(N);
                if (T.equals("null")) {
                    System.out.println(N + " Undeclared");
                } else if (T.equals("class")) {
                    System.out.println(N + " cannot be implement");
                } else if (Prnt.equals(N)) {
                    System.out.println("Same interface implement twice");
                } else
                    Prnt += "," + N;
                index++;
                if (implement()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("{")) {
            return true;
        }
        return false;
    }

    boolean objdec() {
        if (token.get(index).type.equals("ID")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                index++;
                if (token.get(index).value.equals("=")) {
                    index++;
                    if (token.get(index).value.equals("new")) {
                        index++;
                        if (token.get(index).type.equals("ID")) {
                            index++;
                            if (token.get(index).value.equals("(")) {
                                index++;
                                if (PL()) {
                                    if (token.get(index).value.equals(")")) {
                                        index++;
                                        if (token.get(index).value.equals(";")) {
                                            index++;
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Checks for Inherit Statement
    boolean inh() {
        if (token.get(index).value.equals("inherit")) {
            index++;
            if (token.get(index).type.equals("ID")) {
                String N = token.get(index).value;
                // T=semanticClass.lookup_MT(N);
                if (T.equals("null")) {
                    System.out.println(N + " Undeclared");
                }
                // else if(T.equals("class")&&semanticClass.Cat.equals("const")){
                // System.out.println("Const class cannot be inherited ");
                // }
                else
                    Prnt = N;
                index++;
                return true;
            }
        } else if (token.get(index).value.equals("implement")
                || token.get(index).value.equals("{")) {
            return true;
        }
        return false;
    }

    boolean interfacedef() {
        if (token.get(index).value.equals("interface")) {
            T = token.get(index).value;
            index++;
            if (token.get(index).type.equals("ID")) {
                N = token.get(index).value;
                // refDt=semanticClass.create_DT();
                // semanticClass.insert_MT(N,T,Am,Cat,Prnt,refDt);
                empty();
                index++;

                if (token.get(index).value.equals("{")) {
                    // semanticClass.createScope();
                    index++;
                    if (interfacebody()) {
                        if (token.get(index).value.equals("}")) {
                            // semanticClass.destroyScope();
                            index++;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean interfacebody() {
        if (token.get(index).value.equals("conceptual")) {
            if (conceptual()) {
                if (interfacebody()) {
                    return true;
                }
            }
        } else if (dec()) {
            if (interfacebody()) {
                return true;
            }
        } else if (token.get(index).value.equals("}")) {

            return true;
        }
        return false;
    }

    boolean Y() {
        if (token.get(index).value.equals(".")) {
            index++;
            if ((token.get(index).type.equals("ID"))) {
                index++;
                if (Y()) {
                    return true;
                }
            }
        } else if (token.get(index).value.equals("[")) {
            index++;
            if (OE()) {
                if (token.get(index).value.equals("]")) {
                    index++;
                    if (token.get(index).value.equals(".")) {
                        index++;
                        if ((token.get(index).type.equals("ID"))) {
                            index++;
                            if (Y()) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else if (token.get(index).value.equals("(")) {
            return true;
        }
        return false;
    }

    boolean X() {
        if (token.get(index).value.equals(".")
                || token.get(index).value.equals("(")
                || token.get(index).value.equals("{")
                || token.get(index).value.equals("[")) {
            if (token.get(index).value.equals(".")) {
                index++;
                if (token.get(index).type.equals("ID")) {
                    index++;
                    if (X()) {
                        return true;
                    }
                }
            } else if (token.get(index).value.equals("[")) {
                index++;
                if (OE()) {
                    if (token.get(index).value.equals("]")) {
                        index++;
                        if (token.get(index).value.equals(".")) {
                            index++;
                            if (token.get(index).type.equals("ID")) {
                                index++;
                                if (X()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            } else if (token.get(index).value.equals("(")) {
                index++;
                if (PL()) {
                    if (token.get(index).value.equals(")")) {
                        index++;
                        if (token.get(index).value.equals(".")) {
                            index++;
                            if (token.get(index).type.equals("ID")) {
                                index++;
                                if (X()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } else if (token.get(index).value.equals("==")
                || token.get(index).value.equals(">=")
                || token.get(index).value.equals("<=")
                || token.get(index).type.equals("ID")
                || token.get(index).value.equals("=")
                || token.get(index).value.equals(";")
                || token.get(index).value.equals(")")
                || token.get(index).value.equals("++")
                || token.get(index).value.equals("--")) {
            return true;
        }
        return false;
    }

    boolean Th() {
        if (token.get(index).value.equals("this")) {
            index++;
            if (token.get(index).value.equals(".")) {
                index++;
                return true;
            }
        } else if (token.get(index).type.equals("ID")) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {

    }
}
