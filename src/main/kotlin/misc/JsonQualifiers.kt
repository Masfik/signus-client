package misc

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import models.UserStatus
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.annotation.AnnotationRetention.RUNTIME

//----------------------------------------------------------------------------------------------------------------------
// UserStatus qualifier (ONLINE, OFFLINE, BUSY)
//----------------------------------------------------------------------------------------------------------------------

@Retention(RUNTIME)
@JsonQualifier
annotation class Status

class UserStatusAdapter {
  @FromJson
  @Status
  fun fromJson(status: String): UserStatus = when (status) {
    "0"   -> UserStatus.OFFLINE
    "1"   -> UserStatus.ONLINE
    "2"   -> UserStatus.BUSY
    else  -> UserStatus.OFFLINE
  }

  @ToJson
  fun toJson(@Status status: UserStatus): String = when (status) {
    UserStatus.OFFLINE  -> "0"
    UserStatus.ONLINE   -> "1"
    UserStatus.BUSY     -> "2"
  }
}

//----------------------------------------------------------------------------------------------------------------------
// LocalDateTime qualifier
//----------------------------------------------------------------------------------------------------------------------

@Retention(RUNTIME)
@JsonQualifier
annotation class Date

class DateAdapter {
  @FromJson
  @Date
  fun dateTimeFromJson(dateTime: Long): LocalDateTime = Instant.ofEpochMilli(dateTime)
    .atZone(ZoneOffset.UTC)
    .toLocalDateTime()

  @ToJson
  fun dateTimeToJson(@Date dateTime: LocalDateTime): Long = dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
}