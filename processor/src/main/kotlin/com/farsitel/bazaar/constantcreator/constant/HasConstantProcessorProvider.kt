package com.farsitel.bazaar.constantcreator.constant

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class HasConstantProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        val projectPath = requireNotNull(environment.options["projectPath"]) {
            environment.logger.error("projectPath is not specifies for this project/subProject.")
        }
        environment.logger.logging("projectPath: $projectPath")
        return HasConstantProcessor(environment.codeGenerator, environment.logger, projectPath)
    }
}
