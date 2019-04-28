package org.jetbrains.plugins.ruby.types.controlflow

import com.intellij.codeInsight.controlflow.Instruction
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
import org.jetbrains.plugins.ruby.types.controlflow.annotations.Annotations
import org.jetbrains.plugins.ruby.types.controlflow.data.NodeDescription
import org.jetbrains.plugins.ruby.types.controlflow.typeinfo.rightOffset

class RubyElementsInfoCollector {

    private val nodesDescription = mutableListOf<NodeDescription>()

    fun getNodesDescriptions(): List<NodeDescription> = nodesDescription

    fun clearDescriptionsList() {
        nodesDescription.clear()
    }

    fun visitGraph(instructions: List<Instruction>) {
        instructions.forEach {
            currentElementId = it.num()
            visit(it)
        }
    }
    
    private fun visit(instruction: Instruction) {
        val element = instruction.element ?: return
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

    private fun visitNamedArgument(namedArgument: RNamedArgument) {
        addNodeInfo(
                "Named argument",
                namedArgument.text,
                Pair("is requires", namedArgument.isRequired.toString())
        )
    }

    private fun visitUnlessModStatement(rUnlessModStatement: RUnlessModStatement) {
        addNodeInfo("Unless mod statement")
    }

    private fun visitModule(rModule: RModule) {
        addNodeInfo(
                "Module",
                null,
                Pair("qname", rModule.qualifiedName)
        )
    }

    private fun visitRedoStatement(rRedoStatement: RRedoStatement) {
        addNodeInfo("Redo statement")
    }

    private fun visitWhenCase(rWhenCase: RWhenCase) {
        addNodeInfo("When case")
    }

    private fun visitColonReference(rColonReference: RColonReference) {
        addNodeInfo("Colon reference", rColonReference.text)
    }

    private fun visitBeginEndBlockStatement(rBeginEndBlockStatement: RBeginEndBlockStatement) {
        addNodeInfo("Begin-end block statement")
    }

    private fun visitArgumentList(list: RArgumentList) {
        addNodeInfo("Argument list", list.text)
    }

    private fun visitFloatConstant(rFloatConstant: RFloatConstant) {
        addNodeInfo(
                "Float constant",
                rFloatConstant.text,
                Pair("value", rFloatConstant.content)
        )
    }

    private fun visitWhileModStatement(rWhileModStatement: RWhileModStatement) {
        addNodeInfo("While mod statement")
    }

    private fun visitFName(rFName: RFName) {
        addNodeInfo("Fname", rFName.text)
    }

    private fun visitArgument(argument: RArgument) {
        addNodeInfo(
                "Argument",
                argument.text,
                Pair("argument typeDefinition", argument.type.toString())
        )
    }

    private fun visitObjectClass(rsClass: RObjectClass) {
        addNodeInfo(
                "Object class",
                rsClass.text,
                Pair("presentable name", rsClass.presentableName)
        )
    }

    private fun visitIfModStatement(rIfModStatement: RIfModStatement) {
        addNodeInfo("If mod statement")
    }

    private fun visitUnaryExpression(rUnaryExpression: RUnaryExpression) {
        addNodeInfo(
                "Unary expression",
                rUnaryExpression.text,
                Pair("operation", rUnaryExpression.operationName),
                Pair("expression typeDefinition", rUnaryExpression.type.presentableName)
        )
    }

    private fun visitAssoc(rAssoc: RAssoc) {
        addNodeInfo(
                "Assoc",
                rAssoc.text,
                Pair("key text", rAssoc.keyText)
        )
    }

    private fun visitPseudoConstant(rPseudoConstant: RPseudoConstant) {
        addNodeInfo("Pseudo constant", rPseudoConstant.text)
    }

    private fun visitRbFile(rFile: ERbFile) {
        addNodeInfo("File", rFile.text)
    }

    private fun visitCompoundStatement(rCompoundStatement: RCompoundStatement) {
        addNodeInfo("Compound statement", rCompoundStatement.text)
    }

    private fun visitNextStatement(rNextStatement: RNextStatement) {
        addNodeInfo("Next statement")
    }

    private fun visitFile(rFile: RFile) {
        addNodeInfo("File", rFile.text)
    }

    private fun visitClass(rClass: RClass) {
        addNodeInfo(
                "Class",
                rClass.text,
                Pair("qname", rClass.qualifiedName),
                Pair("superclass", rClass.superClassValue.toString())
        )
    }

    private fun visitDefinedStatement(rDefinedStatement: RDefinedStatement) {
        addNodeInfo("Defined statement")
    }

    private fun visitFid(rFid: RFid) {
        addNodeInfo("Fid", rFid.text)
    }

    private fun visitWords(rSymbols: RWords) {
        addNodeInfo("Words", rSymbols.text)
    }

    private fun visitTopConstReference(rTopConstReference: RTopConstReference) {
        addNodeInfo("Top const reference", rTopConstReference.text)
    }

    private fun visitClassVariable(rClassVariable: RClassVariable) {
        addNodeInfo("Class variable", rClassVariable.text)
    }

    private fun visitForStatement(rForStatement: RForStatement) {
        addNodeInfo("For statement")
    }

    private fun visitYieldStatement(rYieldStatement: RYieldStatement) {
        addNodeInfo("Yield statement")
    }

    private fun visitHashToArguments(rHashToArguments: RHashToArguments) {
        addNodeInfo("Hash to arguments", rHashToArguments.text)
    }

    private fun visitAssocKey(rAssocKey: RAssocKey) {
        addNodeInfo("Assoc key", rAssocKey.text)
    }

    private fun visitGlobalVariable(rGlobalVariable: RGlobalVariable) {
        addNodeInfo(
                "Global variable",
                rGlobalVariable.text,
                Pair("variable typeDefinition", rGlobalVariable.globalVariableType.toString())
        )
    }

    private fun visitReference(rReference: RReference) {
        addNodeInfo("Reference", rReference.text)
    }

    private fun visitBodyStatement(rBodyStatement: RBodyStatement) {
        addNodeInfo("Body statement")
    }

    private fun visitBoolNegExpression(rBoolNegExpression: RBoolNegExpression) {
        addNodeInfo("Bool neg expression", rBoolNegExpression.text)
    }

    private fun visitConstant(rConstant: RConstant) {
        addNodeInfo("Constant", rConstant.text)
    }

    private fun visitTernaryExpression(ternaryExpression: RTernaryExpression) {
        addNodeInfo(
                "Ternary expression",
                null,
                Pair("expression typeDefinition", ternaryExpression.type.presentableName)
        )
    }

    private fun visitGroupedExpression(groupedExpression: RGroupedExpression) {
        addNodeInfo("Grouped expression")
    }

    private fun visitIdentifier(rIdentifier: RIdentifier) {
        addNodeInfo(
                "Identifier",
                rIdentifier.text,
                Pair("kind",
                        when {
                            rIdentifier.isBlockCallLocalDeclaration -> "block call local declaration"
                            rIdentifier.isBlockParameterDeclaration -> "block parameter declaration"
                            rIdentifier.isConstructorLike -> "constructor-like variable"
                            rIdentifier.isForLoopVariable -> "for-loop variable"
                            rIdentifier.isLambdaParameterDeclaration -> "lambda parameter"
                            rIdentifier.isLocalVariable -> "local variable"
                            rIdentifier.isMethodParameterDeclaration -> "method parameter declaration"
                            rIdentifier.isParameter -> "parameter"
                            rIdentifier.isParameterDeclaration -> "parameter declaration"
                            rIdentifier.isRescueParameterDeclaration -> "rescue parameter declaration"
                            else -> "unknown"
                        }
                ),
                Pair("reference", rIdentifier.reference?.canonicalText),
                "typeDefinition" to Annotations.definitionsForIdentifier(rIdentifier)?.joinToString(" || ") { it.toString() },
                offset = rIdentifier.rightOffset
        )
    }

    private fun visitAliasStatement(rAliasStatement: RAliasStatement) {
        addNodeInfo(
                "Alias statement",
                null,
                Pair("old name", rAliasStatement.oldName),
                Pair("new name", rAliasStatement.newName)
        )
    }

    private fun visitExpressionSubstitution(rExpressionSubstitution: RExpressionSubstitution) {
        addNodeInfo("Expression substitution")
    }

    private fun visitElsifBlock(rElsifBlock: RElsifBlock) {
        addNodeInfo("Elsif block")
    }

    private fun visitBlockLocalVariables(rBlockLocalVariables: RBlockLocalVariables) {
        addNodeInfo("Block local variables")
    }

    private fun visitUndefStatement(rUndefStatement: RUndefStatement) {
        addNodeInfo(
                "Undef statement",
                null,
                Pair("names", rUndefStatement.names.joinToString())
        )
    }

    private fun visitListOfExpressions(rListOfExpressions: RListOfExpressions) {
        addNodeInfo(
                "List of expressions",
                rListOfExpressions.text
        )
    }

    private fun visitBreakStatement(breakStatement: RBreakStatement) {
        addNodeInfo("Break statement")
    }

    private fun visitRescueBlock(rRescueBlock: RRescueBlock) {
        addNodeInfo("Rescue block")
    }

    private fun visitSymbol(rSymbol: RSymbol) {
        addNodeInfo(
                "Symbol",
                rSymbol.text,
                Pair("value", rSymbol.value)
        )
    }

    private fun visitArrayIndexing(arrayIndexing: RArrayIndexing) {
        addNodeInfo("Array indexing")
    }

    private fun visitWhileStatement(rWhileStatement: RWhileStatement) {
        addNodeInfo("While statement")
    }

    private fun visitArgumentToBlock(rArgumentToBlock: RArgumentToBlock) {
        addNodeInfo("Argument-to-block")
    }

    private fun visitBlockArgumentList(blockParameters: RBlockArgumentList) {
        addNodeInfo("Block arguments")
    }

    private fun visitAssignmentExpression(assignmentExpression: RAssignmentExpression) {
        addNodeInfo(
                "Assignment expression",
                null,
                Pair("operation typeDefinition", assignmentExpression.operationType.toString())
        )
    }

    private fun visitHeredocValue(heredocValue: RHeredocValue) {
        addNodeInfo("Heredoc value")
    }

    private fun visitSuperClass(rSuperClass: RSuperClass) {
        addNodeInfo("Superclass", rSuperClass.text)
    }

    private fun visitRetryStatement(rRetryStatement: RRetryStatement) {
        addNodeInfo("Retry statement")
    }

    private fun visitInstanceVariable(rInstanceVariable: RInstanceVariable) {
        addNodeInfo("Instance variable", rInstanceVariable.text)
    }

    private fun visitLambdaCall(rLambdaCall: RLambdaCall) {
        addNodeInfo("Lambda call", rLambdaCall.text)
    }

    private fun visitHeredocId(heredocId: RHeredocId) {
        addNodeInfo("Heredoc id", heredocId.id)
    }

    private fun visitMethod(rMethod: RMethod) {
        addNodeInfo(
                "Method",
                rMethod.text,
                "presentable name" to rMethod.getPresentableName(true),
                "is constructor" to rMethod.isConstructor.toString(),
                "deprecated" to rMethod.isDeprecated.toString(),
                offset = rMethod.rightOffset
        )
    }

    private fun visitElseBlock(rElseBlock: RElseBlock) {
        addNodeInfo("Else block")
    }

    private fun visitUnlessStatement(rUnlessStatement: RUnlessStatement) {
        addNodeInfo("Unless statement")
    }

    private fun visitCondition(rCondition: RCondition) {
        addNodeInfo("Condition")
    }

    private fun visitUntilModStatement(rUntilModStatement: RUntilModStatement) {
        addNodeInfo("Until mod statement")
    }

    private fun visitCaseStatement(rCaseStatement: RCaseStatement) {
        addNodeInfo("Case statement")
    }

    private fun visitLBeginStatement(rlBeginStatement: RLBeginStatement) {
        addNodeInfo("RLBegin statement")
    }

    private fun visitBraceBlockCall(rBraceBlockCall: RBraceBlockCall) {
        addNodeInfo("Brace block call", rBraceBlockCall.text)
    }

    private fun visitEnsureBlock(rEnsureBlock: REnsureBlock) {
        addNodeInfo("Ensure block", rEnsureBlock.text)
    }

    private fun visitReturnStatement(rReturnStatement: RReturnStatement) {
        addNodeInfo("Return statement")
    }

    private fun visitRescueModStatement(rRescueModStatement: RRescueModStatement) {
        addNodeInfo("Rescue mod statement")
    }

    private fun visitBoolBinExpression(rBoolBinExpression: RBoolBinExpression) {
        addNodeInfo("Boolean binary expression", rBoolBinExpression.text)
    }

    private fun visitSelfAssignmentExpression(selfAssignmentExpression: RSelfAssignmentExpression) {
        addNodeInfo("Self-assignment expression")
    }

    private fun visitBlockCall(blockCall: RBlockCall) {
        addNodeInfo("Block call")
    }

    private fun visitUntilStatement(rUntilStatement: RUntilStatement) {
        addNodeInfo("Until statement")
    }

    private fun visitDotReference(rDotReference: RDotReference) {
        addNodeInfo("Dot reference", rDotReference.text)
    }

    private fun visitIfStatement(ifStatement: RIfStatement) {
        addNodeInfo("If statement")
    }

    private fun visitBinaryExpression(rBinaryExpression: RBinaryExpression) {
        addNodeInfo(
                "Binary expression",
                rBinaryExpression.text,
                Pair("expression typeDefinition", rBinaryExpression.type.presentableName),
                Pair("operation typeDefinition", rBinaryExpression.operationType.toString())
        )
    }

    private fun visitAssocList(rAssocList: RAssocList) {
        addNodeInfo("Assoc list")
    }

    private fun visitBraceCodeBlock(rBraceCodeBlock: RBraceCodeBlock) {
        addNodeInfo("Brace code block")
    }

    private fun visitConditionalStatement(rConditionalStatement: RConditionalStatement) {
        addNodeInfo("Conditional statement")
    }

    private fun visitArray(array: RArray) {
        addNodeInfo("Array", array.text)
    }

    private fun visitDoCodeBlock(rDoCodeBlock: RDoCodeBlock) {
        addNodeInfo("Do code block")
    }

    private fun visitCodeBlock(codeBlock: RCodeBlock) {
        addNodeInfo("Code block")
    }

    private fun visitDoBlockCall(rDoBlockCall: RDoBlockCall) {
        addNodeInfo("Do block call")
    }

    private fun visitSingletonMethod(rsMethod: RSingletonMethod) {
        addNodeInfo("Singleton method", rsMethod.text)
    }

    private fun visitName(name: RName) {
        addNodeInfo("Name", name.text)
    }

    private fun visitArgumentDestructing(rArgumentDestructing: RArgumentDestructing) {
        addNodeInfo("Argument destructing", rArgumentDestructing.text)
    }

    private fun visitCall(rCall: RCall) {
        val callee: RMethod? = rCall.getCalledMethod()
        addNodeInfo(
                nodeType = "Call",
                text = rCall.text,
                additionalProperties = "typeDefinition" to callee?.let { Annotations.declarationForMethod(it)?.typeDefinitions?.joinToString(" || ") { it.toStringIgnoreNames() } },
                callee = rCall.getCalledMethod(),
                offset = rCall.textOffset
        )
    }

    private fun visitIntegerConstant(rIntegerConstant: RIntegerConstant) {
        addNodeInfo("Integer constant", rIntegerConstant.text)
    }

    private fun visitStringLiteral(rStringLiteral: RStringLiteral) {
        addNodeInfo(
                "String literal",
                rStringLiteral.text,
                Pair("value", rStringLiteral.content)
        )
    }

    private fun visitLambda(rLambda: RLambda) {
        addNodeInfo("Lambda", rLambda.text)
    }

    private fun addNodeInfo(
            nodeType: String,
            text: String? = null,
            vararg additionalProperties: Pair<String, String?>,
            callee: RPsiElement? = null,
            offset: Int = 0
    ) {
        val nodeDescription = NodeDescription(nodeType, text, currentElementId, callee, offset)
        additionalProperties.forEach { nodeDescription.addProperty(it.first, it.second) }
        nodesDescription.add(nodeDescription)
    }

    companion object {
        private var currentElementId = 1
    }
}