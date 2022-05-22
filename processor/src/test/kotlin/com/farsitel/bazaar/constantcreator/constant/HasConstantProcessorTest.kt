package com.farsitel.bazaar.constantcreator.constant

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.symbolProcessorProviders
import org.junit.Test
import java.io.File

class HasConstantProcessorTest {

    @Test
    fun test() {

        val kotlinSources = File(
            "../processor/src/test/kotlin/com/farsitel/bazaar/sample/constant"
        )
            .walkTopDown()
            .mapNotNull {
                if (it.isFile) {
                    return@mapNotNull SourceFile.fromPath(it)
                }
                null
            }

        val result = KotlinCompilation().apply {
            sources = kotlinSources.toList()
            inheritClassPath = true
            messageOutputStream = System.out // see diagnostics in real time
            symbolProcessorProviders = listOf(HasConstantProcessorProvider())
        }.compile()

        println(result.messages)
    }
}
