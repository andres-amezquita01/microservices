package utils

import com.google.api.core.*
import zio.ZIO
import zio.Task

extension [A] (apifuture: ApiFuture[A]){

  def toTask: Task[A] = ZIO.async( handler =>
    ApiFutures.addCallback(apifuture, new ApiFutureCallback[A] {
        override def onFailure(t: Throwable): Unit = handler(ZIO.fail(t))
        override def onSuccess(result: A): Unit = handler(ZIO.succeed(result))
      })
  )
}
  

