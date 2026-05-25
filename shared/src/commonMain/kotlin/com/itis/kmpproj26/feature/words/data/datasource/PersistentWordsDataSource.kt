package com.itis.kmpproj26.feature.words.data.datasource

import com.itis.kmpproj26.Database
import com.itis.kmpproj26.Words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.time.Clock

internal class PersistentWordsDataSource(
    private val database: Database,
) {

    suspend fun getWords(): List<Words> {
        return withContext(Dispatchers.IO) {
            database.wordsQueries
                .getAllWords()
                .executeAsList()
        }
    }

    suspend fun getWordBySpelling(
        spelling: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Words? {
        return withContext(Dispatchers.IO) {
            database.wordsQueries
                .getWordBySpellingAndLanguage(
                    spelling = spelling,
                    source_language = sourceLanguage,
                    target_language = targetLanguage,
                )
                .executeAsOneOrNull()
        }
    }

    suspend fun saveWord(
        spelling: String,
        translation: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Words {
        return withContext(Dispatchers.IO) {
            val now = Clock.System.now().toEpochMilliseconds()

            database.wordsQueries.insertWord(
                spelling = spelling,
                translation = translation,
                source_language = sourceLanguage,
                target_language = targetLanguage,
                created_at = now
            )

            database.wordsQueries
                .getWordBySpellingAndLanguage(
                    spelling = spelling,
                    source_language = sourceLanguage,
                    target_language = targetLanguage,
                )
                .executeAsOne()
        }
    }

    suspend fun deleteWordById(id: Long) {
        return withContext(Dispatchers.IO) {
            val rows = database.wordsQueries.deleteWordById(id)

            if (rows.value == 0L) throw NoSuchElementException()
        }
    }
}
