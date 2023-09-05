package traces.infrastructure.repository

import traces.domain.repository.LoggingTraceRepository
import traces.domain.entity.LoggingTrace
import zio.Task
import com.google.firebase.FirebaseApp
import java.io.FileInputStream
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.*
import com.google.firebase.cloud.*
import zio.ZIO
import utils.toTask

class LoggingTraceRepositoryImpl extends LoggingTraceRepository:

  private val firebaseApp: Option[FirebaseApp] = getFirebaseApp();
  private val database: Firestore = FirestoreClient.getFirestore();

  private def getFirebaseApp(): Option[FirebaseApp] = {
    try {
      val serviceAccount = new FileInputStream("keys.json")
      val options = FirebaseOptions
        .builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://microservicesloggingtrace.firebaseio.com")
        .build()
      return Some(FirebaseApp.initializeApp(options))
    } catch {
      case e: Exception =>
        e.printStackTrace()
        return None
    }
  }

  override def saveLoggingTrace(trace: LoggingTrace): Task[String] =  
    for {
      document <- ZIO.succeed(
        Map(
          "time" -> trace.time,
          "owner" -> trace.owner,
          "ownerId" -> trace.ownerId,
          "action" -> trace.action,
          "entity" -> trace.entity.getOrElse(""),
          "information" -> trace.information.getOrElse("")
        )
      )
      uploadDocument <- database
        .collection("loggin_traces")
        .add(document)
        .toTask
    }
    yield(uploadDocument.getId)
        
