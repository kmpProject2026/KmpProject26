package com.itis.kmpproj26.feature.words.data.repository

import com.itis.kmpproj26.core.util.result.FailureReason
import com.itis.kmpproj26.core.util.result.Result
import com.itis.kmpproj26.feature.words.data.datasource.PersistentWordsDataSource
import com.itis.kmpproj26.feature.words.data.datasource.RemoteWordsDataSource
import com.itis.kmpproj26.feature.words.data.datasource.local.mapToDomain
import com.itis.kmpproj26.feature.words.data.datasource.remote.mapToTranslation
import com.itis.kmpproj26.feature.words.domain.model.Word
import com.itis.kmpproj26.feature.words.domain.repository.WordsRepository
import kotlinx.coroutines.CancellationException

internal class WordsRepositoryImpl(
    private val remoteDataSource: RemoteWordsDataSource,
    private val persistentDataSource: PersistentWordsDataSource,
) : WordsRepository {

    override suspend fun getWords(): Result<List<Word>> {
        return try {
            Result.Success(
                persistentDataSource.getWords()
                    .map { it.mapToDomain() }
            )
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun translateWord(
        spelling: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Result<String> {
        return try {
            val response = remoteDataSource.translate(
                text = spelling,
                sourceLanguage = sourceLanguage,
                targetLanguage = targetLanguage,
            )
            val translation = response.mapToTranslation()
                ?: return Result.Failure(FailureReason.BadRequest())

            Result.Success(translation)
        } catch (ex: CancellationException) {
            throw ex
        } catch (ex: Exception) {
            Result.Failure(FailureReason.Network(ex.message))
        }
    }

    override suspend fun addWord(
        spelling: String,
        translation: String,
        sourceLanguage: String,
        targetLanguage: String,
    ): Result<Word> {
        return try {
            val word = persistentDataSource.saveWord(
                spelling = spelling,
                translation = translation,
                sourceLanguage = sourceLanguage,
                targetLanguage = targetLanguage,
            )

            Result.Success(word.mapToDomain())
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }

    override suspend fun deleteWord(id: Long): Result<Boolean> {
        return try {
            persistentDataSource.deleteWordById(id)
            Result.Success(true)
        } catch (_: NoSuchElementException) {
            Result.Failure(FailureReason.BadRequest())
        } catch (_: Exception) {
            Result.Failure(FailureReason.Unknown())
        }
    }
}
