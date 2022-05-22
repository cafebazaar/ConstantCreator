package com.farsitel.bazaar.constantcreator.enum

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.symbolProcessorProviders
import org.junit.Test
import java.io.File

class HasEnumConstantProcessorTest {

    @Test
    fun test() {

        val kotlinSources = File(
            "../processor/src/test/kotlin/com/farsitel/bazaar/sample/enum"
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
            symbolProcessorProviders = listOf(HasEnumConstantProcessorProvider())
        }.compile()

        println(result.messages)
    }
}
