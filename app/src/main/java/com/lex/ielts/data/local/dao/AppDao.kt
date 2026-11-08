package com.lex.ielts.data.local.dao

import androidx.room.*
import com.lex.ielts.data.local.entity.DrillEntity
import com.lex.ielts.data.local.entity.EssayEntity
import com.lex.ielts.data.local.entity.MistakeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MistakeDao {
    @Query("SELECT * FROM mistakes ORDER BY lastPracticedTimestamp ASC")
    fun getAllMistakes(): Flow<List<MistakeEntity>>

    @Query("SELECT * FROM mistakes WHERE targetBand <= :maxBand ORDER BY triggerCount DESC")
    fun getMistakesByBand(maxBand: Double): Flow<List<MistakeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMistake(mistake: MistakeEntity)

    @Update
    suspend fun updateMistake(mistake: MistakeEntity)
}

@Dao
interface DrillDao {
    @Query("SELECT * FROM drills WHERE targetBand = :band AND type = :drillType")
    fun getDrills(band: Double, drillType: String): Flow<List<DrillEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrills(drills: List<DrillEntity>)
}

@Dao
interface EssayDao {
    @Query("SELECT * FROM essays ORDER BY createdAt DESC")
    fun getAllEssays(): Flow<List<EssayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEssay(essay: EssayEntity)
}
