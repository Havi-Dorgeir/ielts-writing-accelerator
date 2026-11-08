package com.lex.ielts.data.repository

import com.lex.ielts.data.local.dao.DrillDao
import com.lex.ielts.data.local.dao.EssayDao
import com.lex.ielts.data.local.dao.MistakeDao
import com.lex.ielts.data.local.entity.DrillEntity
import com.lex.ielts.data.local.entity.EssayEntity
import com.lex.ielts.data.local.entity.MistakeEntity
import kotlinx.coroutines.flow.Flow

class IeltsRepository(
    private val mistakeDao: MistakeDao,
    private val drillDao: DrillDao,
    private val essayDao: EssayDao
) {
    fun getMistakesByBand(band: Double): Flow<List<MistakeEntity>> =
        mistakeDao.getMistakesByBand(band)

    fun getDrills(band: Double, type: String): Flow<List<DrillEntity>> =
        drillDao.getDrills(band, type)

    fun getAllEssays(): Flow<List<EssayEntity>> =
        essayDao.getAllEssays()

    suspend fun recordMistake(mistake: MistakeEntity) =
        mistakeDao.insertMistake(mistake)

    suspend fun saveEssay(essay: EssayEntity) =
        essayDao.insertEssay(essay)
}
