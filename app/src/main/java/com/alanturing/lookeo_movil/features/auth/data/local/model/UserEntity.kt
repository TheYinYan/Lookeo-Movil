package com.alanturing.lookeo_movil.features.auth.data.local.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.alanturing.lookeo_movil.features.auth.data.model.User
import java.util.Date

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: Date,
    val imageUrl: String?
)

fun UserEntity.toModel(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
        imageUrl = this.imageUrl,
    )
}

fun List<UserEntity>.toModel() = this.map(UserEntity::toModel)


fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name= this.name,
        email = this.email,
        createdAt = this.createdAt,
        imageUrl = this.imageUrl
    )
}

fun List<User>.toEntity() = this.map(User::toEntity)
