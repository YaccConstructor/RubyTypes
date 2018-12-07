package org.jetbrains.plugins.ruby.types.controlflow

import org.jetbrains.plugins.ruby.erb.psi.ERbFile
import org.jetbrains.plugins.ruby.ruby.lang.psi.RFile
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement
import org.jetbrains.plugins.ruby.ruby.lang.psi.assoc.RAssoc
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.basicTypes.stringLiterals.heredocs.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.blocks.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.classes.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.modifierStatements.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.modules.RModule
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.names.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.expressions.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.iterators.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.references.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.ruby19.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.ruby19.controlStructures.RBlockLocalVariables
import org.jetbrains.plugins.ruby.ruby.lang.psi.ruby19.controlStructures.RLambda
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.*
import org.jetbrains.plugins.ruby.ruby.lang.psi.variables.fields.*
import org.jetbrains.plugins.ruby.types.controlflow.data.NodeDescription

class RubyElementInfoCollector {

    private val nodesDescription = mutableListOf<NodeDescription>()

    fun getNodesDescriptions(): List<NodeDescription> = nodesDescription

    fun clearDescriptionsList() {
        nodesDescription.clear()
    }

    fun visit(element: RPsiElement, id: Int) {
        currentId = id
        when (element) {
            is RUnlessModStatement -> visitUnlessModStatement(element)
            is RModule -> visitModule(element)
            is RRedoStatement -> visitRedoStatement(element)
            is RWhenCase -> visitWhenCase(element)
            is RColonReference -> visitColonReference(element)
            is RBeginEndBlockStatement -> visitBeginEndBlockStatement(element)
            is RArgumentList -> visitArgumentList(element)
            is RFloatConstant -> visitFloatConstant(element)
            is RWhileModStatement -> visitWhileModStatement(element)
            is RFName -> visitFName(element)
            is RArgument -> visitArgument(element)
            is RObjectClass -> visitObjectClass(element)
            is RIfModStatement -> visitIfModStatement(element)
            is RUnaryExpression -> visitUnaryExpression(element)
            is RAssoc -> visitAssoc(element)
            is RPseudoConstant -> visitPseudoConstant(element)
            is RNamedArgument -> visitNamedArgument(element)
            is ERbFile -> visitRbFile(element)
            is RCompoundStatement -> visitCompoundStatement(element)
            is RNextStatement -> visitNextStatement(element)
            is RFile -> visitFile(element)
            is RClass -> visitClass(element)
            is RDefinedStatement -> visitDefinedStatement(element)
            is RFid -> visitFid(element)
            is RWords -> visitWords(element)
            is RTopConstReference -> visitTopConstReference(element)
            is RClassVariable -> visitClassVariable(element)
            is RForStatement -> visitForStatement(element)
            is RYieldStatement -> visitYieldStatement(element)
            is RHashToArguments -> visitHashToArguments(element)
            is RAssocKey -> visitAssocKey(element)
            is RGlobalVariable -> visitGlobalVariable(element)
            is RReference -> visitReference(element)
            is RBodyStatement -> visitBodyStatement(element)
            is RBoolNegExpression -> visitBoolNegExpression(element)
            is RConstant -> visitConstant(element)
            is RTernaryExpression -> visitTernaryExpression(element)
            is RIdentifier -> visitIdentifier(element)
            is RAliasStatement -> visitAliasStatement(element)
            is RElsifBlock -> visitElsifBlock(element)
            is RBlockLocalVariables -> visitBlockLocalVariables(element)
            is RUndefStatement -> visitUndefStatement(element)
            is RListOfExpressions -> visitListOfExpressions(element)
            is RBreakStatement -> visitBreakStatement(element)
            is RSymbol -> visitSymbol(element)
            is RArrayIndexing -> visitArrayIndexing(element)
            is RWhileStatement -> visitWhileStatement(element)
            is RAssignmentExpression -> visitAssignmentExpression(element)
            is RSuperClass -> visitSuperClass(element)
            is RInstanceVariable -> visitInstanceVariable(element)
            is RLambdaCall -> visitLambdaCall(element)
            is RMethod -> visitMethod(element)
            is RElseBlock -> visitElseBlock(element)
            is RUnlessStatement -> visitUnlessStatement(element)
            is RCondition -> visitCondition(element)
            is RUntilModStatement -> visitUntilModStatement(element)
            is RCaseStatement -> visitCaseStatement(element)
            is RReturnStatement -> visitReturnStatement(element)
            is RBoolBinExpression -> visitBoolBinExpression(element)
            is RBlockCall -> visitBlockCall(element)
            is RUntilStatement -> visitUntilStatement(element)
            is RDotReference -> visitDotReference(element)
            is RIfStatement -> visitIfStatement(element)
            is RBinaryExpression -> visitBinaryExpression(element)
            is RAssocList -> visitAssocList(element)
            is RConditionalStatement -> visitConditionalStatement(element)
            is RArray -> visitArray(element)
            is RSingletonMethod -> visitSingletonMethod(element)
            is RName -> visitName(element)
            is RArgumentDestructing -> visitArgumentDestructing(element)
            is RCall -> visitCall(element)
            is RIntegerConstant -> visitIntegerConstant(element)
            is RStringLiteral -> visitStringLiteral(element)
            is RLambda -> visitLambda(element)
            is RDoBlockCall -> visitDoBlockCall(element)
            is RCodeBlock -> visitCodeBlock(element)
            is RDoCodeBlock -> visitDoCodeBlock(element)
            is RBraceCodeBlock -> visitBraceCodeBlock(element)
            is RSelfAssignmentExpression -> visitSelfAssignmentExpression(element)
            is RRescueModStatement -> visitRescueModStatement(element)
            is REnsureBlock -> visitEnsureBlock(element)
            is RBraceBlockCall -> visitBraceBlockCall(element)
            is RLBeginStatement -> visitLBeginStatement(element)
            is RHeredocId -> visitHeredocId(element)
            is RHeredocValue -> visitHeredocValue(element)
            is RRetryStatement -> visitRetryStatement(element)
            is RBlockArgumentList -> visitBlockArgumentList(element)
            is RArgumentToBlock -> visitArgumentToBlock(element)
            is RRescueBlock -> visitRescueBlock(element)
            is RExpressionSubstitution -> visitExpressionSubstitution(element)
            is RGroupedExpression -> visitGroupedExpression(element)
        }
    }

    fun visitNamedArgument(namedArgument: RNamedArgument) {
        addNodeInfo(
                "Named argument",
                namedArgument.text,
                Pair("is requires", namedArgument.isRequired.toString())
        )
    }

    fun visitUnlessModStatement(rUnlessModStatement: RUnlessModStatement) {
        addNodeInfo("Unless mod statement")
    }

    fun visitModule(rModule: RModule) {
        addNodeInfo(
                "Module",
                null,
                Pair("qname", rModule.qualifiedName)
        )
    }

    fun visitRedoStatement(rRedoStatement: RRedoStatement) {
        addNodeInfo("Redo statement")
    }

    fun visitWhenCase(rWhenCase: RWhenCase) {
        addNodeInfo("When case")
    }

    fun visitColonReference(rColonReference: RColonReference) {
        addNodeInfo("Colon reference", rColonReference.text)
    }

    fun visitBeginEndBlockStatement(rBeginEndBlockStatement: RBeginEndBlockStatement) {
        addNodeInfo("Begin-end block statement")
    }

    fun visitArgumentList(list: RArgumentList) {
        addNodeInfo("Argument list", list.text)
    }

    fun visitFloatConstant(rFloatConstant: RFloatConstant) {
        addNodeInfo(
                "Float constant",
                rFloatConstant.text,
                Pair("value", rFloatConstant.content)
        )
    }

    fun visitWhileModStatement(rWhileModStatement: RWhileModStatement) {
        addNodeInfo("While mod statement")
    }

    fun visitFName(rFName: RFName) {
        addNodeInfo("Fname", rFName.text)
    }

    fun visitArgument(argument: RArgument) {
        addNodeInfo(
                "Argument",
                argument.text,
                Pair("argument type", argument.type.toString())
        )
    }

    fun visitObjectClass(rsClass: RObjectClass) {
        addNodeInfo(
                "Object class",
                rsClass.text,
                Pair("presentable name", rsClass.presentableName)
        )
    }

    fun visitIfModStatement(rIfModStatement: RIfModStatement) {
        addNodeInfo("If mod statement")
    }

    fun visitUnaryExpression(rUnaryExpression: RUnaryExpression) {
        addNodeInfo(
                "Unary expression",
                rUnaryExpression.text,
                Pair("operation", rUnaryExpression.operationName),
                Pair("expression type", rUnaryExpression.type.presentableName)
        )
    }

    fun visitAssoc(rAssoc: RAssoc) {
        addNodeInfo(
                "Assoc",
                rAssoc.text,
                Pair("key text", rAssoc.keyText)
        )
    }

    fun visitPseudoConstant(rPseudoConstant: RPseudoConstant) {
        addNodeInfo("Pseudo constant", rPseudoConstant.text)
    }

    fun visitRbFile(rFile: ERbFile) {
        addNodeInfo("File", rFile.text)
    }

    fun visitCompoundStatement(rCompoundStatement: RCompoundStatement) {
        addNodeInfo("Compound statement", rCompoundStatement.text)
    }

    fun visitNextStatement(rNextStatement: RNextStatement) {
        addNodeInfo("Next statement")
    }

    fun visitFile(rFile: RFile) {
        addNodeInfo("File", rFile.text)
    }

    fun visitClass(rClass: RClass) {
        addNodeInfo(
                "Class",
                rClass.text,
                Pair("qname", rClass.qualifiedName),
                Pair("superclass", rClass.superClassValue.toString())
        )
    }

    fun visitDefinedStatement(rDefinedStatement: RDefinedStatement) {
        addNodeInfo("Defined statement")
    }

    fun visitFid(rFid: RFid) {
        addNodeInfo("Fid", rFid.text)
    }

    fun visitWords(rSymbols: RWords) {
        addNodeInfo("Words", rSymbols.text)
    }

    fun visitTopConstReference(rTopConstReference: RTopConstReference) {
        addNodeInfo("Top const reference", rTopConstReference.text)
    }

    fun visitClassVariable(rClassVariable: RClassVariable) {
        addNodeInfo("Class variable", rClassVariable.text)
    }

    fun visitForStatement(rForStatement: RForStatement) {
        addNodeInfo("For statement")
    }

    fun visitYieldStatement(rYieldStatement: RYieldStatement) {
        addNodeInfo("Yield statement")
    }

    fun visitHashToArguments(rHashToArguments: RHashToArguments) {
        addNodeInfo("Hash to arguments", rHashToArguments.text)
    }

    fun visitAssocKey(rAssocKey: RAssocKey) {
        addNodeInfo("Assoc key", rAssocKey.text)
    }

    fun visitGlobalVariable(rGlobalVariable: RGlobalVariable) {
        addNodeInfo(
                "Global variable",
                rGlobalVariable.text,
                Pair("variable type", rGlobalVariable.globalVariableType.toString())
        )
    }

    fun visitReference(rReference: RReference) {
        addNodeInfo("Reference", rReference.text)
    }

    fun visitBodyStatement(rBodyStatement: RBodyStatement) {
        addNodeInfo("Body statement")
    }

    fun visitBoolNegExpression(rBoolNegExpression: RBoolNegExpression) {
        addNodeInfo("Bool neg expression", rBoolNegExpression.text)
    }

    fun visitConstant(rConstant: RConstant) {
        addNodeInfo("Constant", rConstant.text)
    }

    fun visitTernaryExpression(ternaryExpression: RTernaryExpression) {
        addNodeInfo(
                "Ternary expression",
                null,
                Pair("expression type", ternaryExpression.type.presentableName)
        )
    }

    fun visitGroupedExpression(groupedExpression: RGroupedExpression) {
        addNodeInfo("Grouped expression")
    }

    fun visitIdentifier(rIdentifier: RIdentifier) {
        addNodeInfo(
                "Identifier",
                rIdentifier.text,
                Pair("kind",
                        when {
                            rIdentifier.isBlockCallLocalDeclaration -> "block call local declaration"
                            rIdentifier.isBlockParameterDeclaration -> "block parameter declaration"
                            rIdentifier.isConstructorLike -> "constuctor-like variable"
                            rIdentifier.isForLoopVariable -> "for-loop variable"
                            rIdentifier.isLambdaParameterDeclaration -> "lambda parameter"
                            rIdentifier.isLocalVariable -> "local variable"
                            rIdentifier.isMethodParameterDeclaration -> "method parameter declaration"
                            rIdentifier.isParameter -> "parameter"
                            rIdentifier.isParameterDeclaration -> "parameter declaration"
                            rIdentifier.isRescueParameterDeclaration -> "rescue parameter declaration"
                            else -> "unknown"
                        }
                )
        )
    }

    fun visitAliasStatement(rAliasStatement: RAliasStatement) {
        addNodeInfo(
                "Alias statement",
                null,
                Pair("old name", rAliasStatement.oldName),
                Pair("new name", rAliasStatement.newName)
        )
    }

    fun visitExpressionSubstitution(rExpressionSubstitution: RExpressionSubstitution) {
        addNodeInfo("Expression substitution")
    }

    fun visitElsifBlock(rElsifBlock: RElsifBlock) {
        addNodeInfo("Elsif block")
    }

    fun visitBlockLocalVariables(rBlockLocalVariables: RBlockLocalVariables) {
        addNodeInfo("Block local variables")
    }

    fun visitUndefStatement(rUndefStatement: RUndefStatement) {
        addNodeInfo(
                "Undef statement",
                null,
                Pair("names", rUndefStatement.names.joinToString())
        )
    }

    fun visitListOfExpressions(rListOfExpressions: RListOfExpressions) {
        addNodeInfo("List of expressions")
    }

    fun visitBreakStatement(breakStatement: RBreakStatement) {
        addNodeInfo("Break statement")
    }

    fun visitRescueBlock(rRescueBlock: RRescueBlock) {
        addNodeInfo("Rescue block")
    }

    fun visitSymbol(rSymbol: RSymbol) {
        addNodeInfo(
                "Symbol",
                rSymbol.text,
                Pair("value", rSymbol.value)
        )
    }

    fun visitArrayIndexing(arrayIndexing: RArrayIndexing) {
        addNodeInfo("Array indexing")
    }

    fun visitWhileStatement(rWhileStatement: RWhileStatement) {
        addNodeInfo("While statement")
    }

    fun visitArgumentToBlock(rArgumentToBlock: RArgumentToBlock) {
        addNodeInfo("Argument-to-block")
    }

    fun visitBlockArgumentList(blockParameters: RBlockArgumentList) {
        addNodeInfo("Block arguments")
    }

    fun visitAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        addNodeInfo(
                "Assignment expression",
                null,
                Pair("operation type", assignmentExpression.operationType.toString())
        )
    }

    fun visitHeredocValue(heredocValue: RHeredocValue) {
        addNodeInfo("Heredoc value")
    }

    fun visitSuperClass(rSuperClass: RSuperClass) {
        addNodeInfo("Superclass", rSuperClass.text)
    }

    fun visitRetryStatement(rRetryStatement: RRetryStatement) {
        addNodeInfo("Retry statement")
    }

    fun visitInstanceVariable(rInstanceVariable: RInstanceVariable) {
        addNodeInfo("Instance variable", rInstanceVariable.text)
    }

    fun visitLambdaCall(rLambdaCall: RLambdaCall) {
        addNodeInfo("Lambda call", rLambdaCall.text)
    }

    fun visitHeredocId(heredocId: RHeredocId) {
        addNodeInfo("Heredoc id", heredocId.id)
    }

    fun visitMethod(rMethod: RMethod) {
        addNodeInfo(
                "Method",
                rMethod.text,
                Pair("presentable name", rMethod.getPresentableName(true)),
                Pair("is constructor", rMethod.isConstructor.toString()),
                Pair("deprecated", rMethod.isDeprecated.toString())
        )
    }

    fun visitElseBlock(rElseBlock: RElseBlock) {
        addNodeInfo("Else block")
    }

    fun visitUnlessStatement(rUnlessStatement: RUnlessStatement) {
        addNodeInfo("Unless statement")
    }

    fun visitCondition(rCondition: RCondition) {
        addNodeInfo("Condition")
    }

    fun visitUntilModStatement(rUntilModStatement: RUntilModStatement) {
        addNodeInfo("Until mod statement")
    }

    fun visitCaseStatement(rCaseStatement: RCaseStatement) {
        addNodeInfo("Case statement")
    }

    fun visitLBeginStatement(rlBeginStatement: RLBeginStatement) {
        addNodeInfo("RLBegin statement")
    }

    fun visitBraceBlockCall(rBraceBlockCall: RBraceBlockCall) {
        addNodeInfo("Brace block call", rBraceBlockCall.text)
    }

    fun visitEnsureBlock(rEnsureBlock: REnsureBlock) {
        addNodeInfo("Ensure block", rEnsureBlock.text)
    }

    fun visitReturnStatement(rReturnStatement: RReturnStatement) {
        addNodeInfo("Return statement")
    }

    fun visitRescueModStatement(rRescueModStatement: RRescueModStatement) {
        addNodeInfo("Rescue mod statement")
    }

    fun visitBoolBinExpression(rBoolBinExpression: RBoolBinExpression) {
        addNodeInfo("Boolean binary expression", rBoolBinExpression.text)
    }

    fun visitSelfAssignmentExpression(selfAssignmentExpression: RSelfAssignmentExpression) {
        addNodeInfo("Self-assignment expression")
    }

    fun visitBlockCall(blockCall: RBlockCall) {
        addNodeInfo("Block call")
    }

    fun visitUntilStatement(rUntilStatement: RUntilStatement) {
        addNodeInfo("Until statement")
    }

    fun visitDotReference(rDotReference: RDotReference) {
        addNodeInfo("Dot reference", rDotReference.text)
    }

    fun visitIfStatement(ifStatement: RIfStatement) {
        addNodeInfo("If statement")
    }

    fun visitBinaryExpression(rBinaryExpression: RBinaryExpression) {
        addNodeInfo(
                "Binary expression",
                rBinaryExpression.text,
                Pair("expression type", rBinaryExpression.type.presentableName),
                Pair("operation type", rBinaryExpression.operationType.toString())
        )
    }

    fun visitAssocList(rAssocList: RAssocList) {
        addNodeInfo("Assoc list")
    }

    fun visitBraceCodeBlock(rBraceCodeBlock: RBraceCodeBlock) {
        addNodeInfo("Brace code block")
    }

    fun visitConditionalStatement(rConditionalStatement: RConditionalStatement) {
        addNodeInfo("Conditional statement")
    }

    fun visitArray(array: RArray) {
        addNodeInfo("Array", array.text)
    }

    fun visitDoCodeBlock(rDoCodeBlock: RDoCodeBlock) {
        addNodeInfo("Do code block")
    }

    fun visitCodeBlock(codeBlock: RCodeBlock) {
        addNodeInfo("Code block")
    }

    fun visitDoBlockCall(rDoBlockCall: RDoBlockCall) {
        addNodeInfo("Do block call")
    }

    fun visitSingletonMethod(rsMethod: RSingletonMethod) {
        addNodeInfo("Singleton method", rsMethod.text)
    }

    fun visitName(name: RName) {
        addNodeInfo("Name", name.text)
    }

    fun visitArgumentDestructing(rArgumentDestructing: RArgumentDestructing) {
        addNodeInfo("Argument destructing", rArgumentDestructing.text)
    }

    fun visitCall(rCall: RCall) {
        addNodeInfo(
                "Call",
                rCall.text,
                Pair("call type", rCall.type.presentableName)
        )
    }

    fun visitIntegerConstant(rIntegerConstant: RIntegerConstant) {
        addNodeInfo("Integer constant", rIntegerConstant.text)
    }

    fun visitStringLiteral(rStringLiteral: RStringLiteral) {
        addNodeInfo(
                "String literal",
                rStringLiteral.text,
                Pair("value", rStringLiteral.content)
        )
    }

    fun visitLambda(rLambda: RLambda) {
        addNodeInfo("Lambda", rLambda.text)
    }

    private fun addNodeInfo(
            nodeType: String,
            text: String? = null,
            vararg additionalProperties: Pair<String, String?>
    ) {
        val nodeDescription = NodeDescription(nodeType, text, currentId)
        additionalProperties.forEach { nodeDescription.addProperty(it.first, it.second) }
        nodesDescription.add(nodeDescription)
    }

    companion object {
        var currentId = 0
    }
}